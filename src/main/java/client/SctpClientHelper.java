package client;

import model.stcprequest.SctpRequest;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


abstract public class SctpClientHelper {

    public static final Logger LOG = Logger.getLogger(SctpClientHelper.class);

    private String host;
    private int port;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private static int ID_GENERATE = Integer.MIN_VALUE;


    public SctpClientHelper(final String host, final int port) throws IOException {
        this.host = host;
        this.port = port;
        init();
        close();
    }

    public SctpClientHelper(final String host) throws IOException {
        this(host, 56787);
    }

    protected int generateIdRequest(SctpRequest sctpRequest){
        int id = ID_GENERATE++;
        sctpRequest.setId(id);
        return id;
    }

    synchronized protected Socket init() throws IOException {
        if ((socket == null) || socket.isClosed()) {
            socket = new Socket(host, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        }
        return socket;
    }

    public void close() throws IOException {
        closeResources();
    }

    protected void closeResources() throws IOException {
        socket.close();
        inputStream.close();
        outputStream.close();
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public String toString() {
        return "SctpClientHelper{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", inputStream=" + inputStream +
                ", outputStream=" + outputStream +
                ", socket=" + socket +
                '}';
    }
}
