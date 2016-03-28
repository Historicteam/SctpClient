package client;

import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.SctpRequest;
import transport.SctpResponseReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

//TODO кое какую функциональность надо перенести в AsyncSctpClient
public class SctpClient {

    private String host;
    private int port;
    private Map<Integer, CallBack> callBackMap;
    private Map<Integer, SctpRequest> requestMap;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private Thread ReaderThread;



    public SctpClient(final String host, final int port) throws IOException {
        this.host = host;
        this.port = port;
        init(host, port);
        close();
        callBackMap = new HashMap<Integer, CallBack>();
        requestMap = new HashMap<Integer, SctpRequest>();
    }

    public SctpClient(final String host) throws IOException {
        this(host, 56787);
    }


    protected void initSocket() throws IOException {
        if (socket.isClosed()) {
              init(host, port);
          }
    }

    protected void initReaderThreat(){
        if (ReaderThread == null) {
            ReaderThread = new Thread(new Reader());
            ReaderThread.start();
        }
    }

    protected void addToCallBackMap(int id, CallBack callBack){
        callBackMap.put(id, callBack);
    }

    protected void addToRequestBackMap(int id, SctpRequest request){
        requestMap.put(id, request);
    }


    public void close() throws IOException {
        closeResources();
    }

    protected void closeResources() throws IOException {
        if (ReaderThread != null) {
            ReaderThread.stop();
        }
        socket.close();
        inputStream.close();
        outputStream.close();

    }

    private void init(String host, int port) throws IOException {
            socket = new Socket(host, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        }

    private class Reader implements Runnable {
        public void run() {
            SctpResponseReader reader = new SctpResponseReader(inputStream);
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
