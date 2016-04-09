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

public class AsyncSctpClientImpl extends SctpClient implements AsyncSctpClient {

    private Map<Integer, CallBack> callBackMap;
    private Map<Integer, SctpRequest> requestMap;
    private Thread ReaderThread;

    public AsyncSctpClientImpl(String host, int port) throws IOException {
        super(host, port);
        callBackMap = new HashMap<Integer, CallBack>();
        requestMap = new HashMap<Integer, SctpRequest>();
    }

    public AsyncSctpClientImpl(String host) throws IOException {
        super(host);
    }

    @Override
    public void execute(SctpRequest stcpRequest, CallBack callBack) throws IOException {
        initSocket();
        initReaderThreat();
        callBackMap.put(stcpRequest.getId(), callBack);
        requestMap.put(stcpRequest.getId(), stcpRequest);
        SctpRequestSender sender = new SctpRequestSender(getOutputStream());
        sender.sendRequest(stcpRequest);
    }

    private void initReaderThreat() {
        if (ReaderThread == null) {
            ReaderThread = new Thread(new Reader());
            ReaderThread.start();
        }
    }



    @Override
    public void close() throws IOException {

    }

    @Override
    protected void closeResources() throws IOException {
        if (ReaderThread != null) {
            ReaderThread.stop();
        }
        super.closeResources();
    }


    private class Reader implements Runnable {
            public void run() {
                SctpResponseReader reader = new SctpResponseReader(getInputStream());
                //TODO может быть надо добавить Handler
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
                CallBack callBack = callBackMap.get(sctpResponse.getId());
                callBackMap.remove(sctpResponse.getId());
                if (sctpResponse.getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL) {
                    callBack.success(requestMap.get(sctpResponse.getId()), sctpResponse);
                } else {
                    callBack.unsuccess(requestMap.get(sctpResponse.getId()),sctpResponse);
                }
                requestMap.remove(sctpResponse.getId());
            }
        }
}
