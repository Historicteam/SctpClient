package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


abstract public class SctpClient {

    private String host;
    private int port;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;


    public SctpClient(final String host, final int port) throws IOException {
        this.host = host;
        this.port = port;
        init(host, port);
        close();
    }

    public SctpClient(final String host) throws IOException {
        this(host, 56787);
    }


    protected void initSocket() throws IOException {
        if (socket.isClosed()) {
            close();
            init(host, port);
        }
    }

    public void close() throws IOException {
        closeResources();
    }

    protected void closeResources() throws IOException {
        socket.close();
        inputStream.close();
        outputStream.close();

    }

    private void init(String host, int port) throws IOException {
        socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public String toString() {
        return "SctpClient{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", inputStream=" + inputStream +
                ", outputStream=" + outputStream +
                ", socket=" + socket +
                '}';
    }
}
