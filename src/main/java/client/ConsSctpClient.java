package client;

import model.scresponce.SctpResponse;
import model.stcprequest.SctpRequest;

import java.io.IOException;

public interface ConsSctpClient {
    SctpResponse execute(SctpRequest stcpRequest) throws IOException;
    void close() throws IOException;
}
