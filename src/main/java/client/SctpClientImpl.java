package client;


import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.SctpRequest;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class SctpClientImpl implements SctpClient {

    private String host;
    private int port;
    private Map<Integer, CallBack> callBackMap;
    private Map<Integer, SctpRequest> requestMap;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private Thread threadReader;


    public SctpClientImpl(final String host, final int port) throws IOException {
        this.host = host;
        this.port = port;
        init(host, port);
        callBackMap = new HashMap<Integer, CallBack>();
        requestMap = new HashMap<Integer, SctpRequest>();
    }

    public SctpClientImpl(final String host) throws IOException {
        this(host, 56787);
    }

    public void execute(SctpRequest stcpRequest, CallBack callBack) throws IOException {
        if (socket.isClosed()) {
            init(host, port);
        }
        if (threadReader == null) {
            threadReader = new Thread(new Reader());
            threadReader.start();
        }

        callBackMap.put(stcpRequest.getId(), callBack);
        SctpRequestSender sender = new SctpRequestSender(outputStream);
        sender.sendRequest(stcpRequest);
    }

    public SctpResponse execute(SctpRequest stcpRequest) throws IOException {
        if (socket.isClosed()) {
            init(host, port);
        }
        SctpRequestSender sender = new SctpRequestSender(outputStream);
        SctpResponseReader reader = new SctpResponseReader(inputStream);
        sender.sendRequest(stcpRequest);
        return SctpResponceBytesBuilder.build(reader.read());
    }


    private void init(String host, int port) throws IOException {
        socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void close() throws IOException {
        closeResources();
    }

    private void closeResources() throws IOException {
        if (threadReader != null) {
            threadReader.stop();
        }
        socket.close();
        inputStream.close();
        outputStream.close();

    }

    private class Reader implements Runnable {
        public void run() {
            SctpResponseReader reader = new SctpResponseReader(inputStream);
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
            if (sctpResponse.getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL) {
                callBack.success(requestMap.get(sctpResponse.getId()), sctpResponse);
            } else {
                callBack.error(requestMap.get(sctpResponse.getId()),sctpResponse);
            }
        }
    }


}
