package client;

import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.SctpRequest;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.io.IOException;

public class ConsSctpClientImpl extends SctpClient implements ConsSctpClient {
    public ConsSctpClientImpl(String host, int port) throws IOException {
        super(host, port);
    }

    public ConsSctpClientImpl(String host) throws IOException {
        super(host);
    }

    @Override
    public SctpResponse execute(SctpRequest stcpRequest) throws IOException {
        initSocket();
        SctpRequestSender sender = new SctpRequestSender(getOutputStream());
        SctpResponseReader reader = new SctpResponseReader(getInputStream());
        sender.sendRequest(stcpRequest);
        return SctpResponceBytesBuilder.build(reader.read());
    }

    @Override
    public void close() throws IOException {
        closeResources();
    }
}
