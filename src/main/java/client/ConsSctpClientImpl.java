package client;

import exception.IllegalCommand;
import exception.IllegalReturnCode;
import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.SctpRequest;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.io.IOException;

public class ConsSctpClientImpl extends SctpClientHelper implements ConsSctpClient {
    public ConsSctpClientImpl(String host, int port) throws IOException {
        super(host, port);
    }

    public ConsSctpClientImpl(String host) throws IOException {
        super(host);
    }

    @Override
    public SctpResponse perform(SctpRequest stcpRequest) throws IOException, IllegalReturnCode, IllegalCommand {
        init();
        generateIdRequest(stcpRequest);
        SctpRequestSender sender = new SctpRequestSender(getOutputStream());
        SctpResponseReader reader = new SctpResponseReader(getInputStream());
        sender.sendRequest(stcpRequest);
        byte[] data = reader.read();
        return SctpResponceBytesBuilder.build(data);
    }
}
