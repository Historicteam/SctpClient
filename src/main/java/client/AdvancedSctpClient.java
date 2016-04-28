package client;

import model.scresponce.SctpResponse;
import model.stcprequest.SctpRequest;

import java.io.IOException;

public class AdvancedSctpClient extends AsyncSctpClientImpl implements ConsSctpClient{
    public AdvancedSctpClient(String host, int port) throws IOException {
        super(host, port);
    }


    @Override
    public SctpResponse perform(SctpRequest stcpRequest) throws IOException {
        return execute(stcpRequest).get();
    }
}
