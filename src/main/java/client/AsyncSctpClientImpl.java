package client;

import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.SctpRequest;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncSctpClientImpl extends SctpClientHelper implements AsyncSctpClient {

    private Map<Integer, CallBack> callBackMap;
    private Map<Integer, SctpRequest> requestMap;
    private Map<Integer, FutureImpl> futureMap;
    private ExecutorService service;
    private Thread ReaderThread;
    private int requests;
    private final Object monitor = new Object();

    public int getRequests() {
        return requests;
    }

    private void increaseRequests(){
        ++requests;
    }
    private void reduceRequests(){
        synchronized (monitor) {
            --requests;
            monitor.notifyAll();
        }
    }

    public AsyncSctpClientImpl(String host, int port) throws IOException {
        super(host, port);
        callBackMap = new HashMap<Integer, CallBack>();
        requestMap = new HashMap<Integer, SctpRequest>();
        futureMap = new HashMap<Integer, FutureImpl>();
        service = Executors.newCachedThreadPool();
        requests=0;
    }

    public AsyncSctpClientImpl(String host) throws IOException {
        super(host);
        callBackMap = new HashMap<Integer, CallBack>();
        requestMap = new HashMap<Integer, SctpRequest>();
        futureMap = new HashMap<Integer, FutureImpl>();
        service = Executors.newCachedThreadPool();
        requests=0;
    }

    @Override
    public void execute(SctpRequest stcpRequest, CallBack callBack) throws IOException {
        increaseRequests();
        init();
        initReaderThreat();
        generateIdRequest(stcpRequest);
        if (callBack != null) {
            callBackMap.put(stcpRequest.getId(), callBack);
            requestMap.put(stcpRequest.getId(), stcpRequest);
        }
        SctpRequestSender sender = new SctpRequestSender(getOutputStream());
        sender.sendRequest(stcpRequest);
    }

    @Override
    public FutureImpl execute(SctpRequest stcpRequest) throws IOException {
        init();
        initReaderThreat();
        generateIdRequest(stcpRequest);
        FutureImpl future = new FutureImpl();
        futureMap.put(stcpRequest.getId(), future);
        requestMap.put(stcpRequest.getId(), stcpRequest);
        SctpRequestSender sender = new SctpRequestSender(getOutputStream());
        sender.sendRequest(stcpRequest);
        increaseRequests();
        return future;
    }

    private void initReaderThreat() {
        if (ReaderThread == null) {
            ReaderThread = new Thread(new Reader());
            ReaderThread.start();
        }
    }


    @Override
    public void close() throws IOException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (getRequests()!=0){
                            synchronized (monitor) {
                                try {
                                    monitor.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                try {
                    closeResources();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void closeResources() throws IOException {
        if (ReaderThread != null&&ReaderThread.isAlive()) {
            ReaderThread.stop();
        }
        super.closeResources();
    }


    private class Reader implements Runnable {
        public void run() {
            SctpResponseReader reader = new SctpResponseReader(getInputStream());
            while (true) {
                try {
                    byte[] byteSctpResponse = reader.read();
                    new Thread(new RollBackNotificator(byteSctpResponse)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class RollBackNotificator implements Runnable {
        private byte[] byteSctpResponse;

        public RollBackNotificator(byte[] byteSctpResponse) {
            this.byteSctpResponse = byteSctpResponse;
        }

        public void run() {
            SctpResponse sctpResponse = SctpResponceBytesBuilder.build(byteSctpResponse);
            reduceRequests();
            CallBack callBack = callBackMap.get(sctpResponse.getId());
            if (callBack != null) {
                callBackMap.remove(sctpResponse.getId());
                if (sctpResponse.getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL) {
                    callBack.success(requestMap.get(sctpResponse.getId()), sctpResponse);
                }
                else {
                    callBack.unsuccess(requestMap.get(sctpResponse.getId()), sctpResponse);
                }
                requestMap.remove(sctpResponse.getId());
                return;
            }
            FutureImpl future = futureMap.get(sctpResponse.getId());
            if (future != null) {
                futureMap.remove(future);
                future.setSctpResponse(sctpResponse);
                future.setDone(true);
                futureMap.remove(future);
                requestMap.remove(sctpResponse.getId());
            }


        }
    }

    @Override
    public String toString() {
        return "AsyncSctpClientImpl{" +
                "callBackMap=" + callBackMap +
                ", requestMap=" + requestMap +
                ", futureMap=" + futureMap +
                ", service=" + service +
                ", ReaderThread=" + ReaderThread +
                ", requests=" + requests +
                ", monitor=" + monitor +
                '}';
    }
}
