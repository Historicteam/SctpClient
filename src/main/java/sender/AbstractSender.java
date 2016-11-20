package sender;


import exception.SctpException;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.io.IOException;
import java.net.Socket;

public abstract class AbstractSender implements SctpSender {
    private Socket socket;
    private SctpRequestSender sender;
    private SctpResponseReader reader;
    private Exception exception;

    public AbstractSender(String host, Integer port) {
        try {
            init(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected SctpRequestSender getSender() {
        return sender;
    }

    protected SctpResponseReader getReader() {
        return reader;
    }


    protected void setException(Exception exception) {
        if (exception != null) {
            this.exception = exception;
        }
    }

    protected Exception getException() {
        return exception;
    }


    synchronized private Socket init(String host, Integer port) throws IOException {
        if ((socket == null) || socket.isClosed()) {
            socket = new Socket(host, port);
            sender = new SctpRequestSender(socket.getOutputStream());
            reader = new SctpResponseReader(socket.getInputStream());
        }
        return socket;
    }


    @Override
    public void close() throws SctpException {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SctpException(e);
        }
    }

    @Override
    public String toString() {
        return "AbstractSender{" +
                "socket=" + socket +
                ", sender=" + sender +
                ", reader=" + reader +
                ", exception=" + exception +
                '}';
    }
}
