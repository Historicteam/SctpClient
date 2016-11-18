package client;

import exception.IllegalCommand;
import exception.IllegalReturnCode;
import model.scresponce.SctpResponse;
import model.stcprequest.SctpRequest;

import java.io.IOException;

public interface ConsSctpClient {
    SctpResponse perform(SctpRequest stcpRequest) throws IOException, IllegalReturnCode, IllegalCommand;
    void close() throws IOException;
}
