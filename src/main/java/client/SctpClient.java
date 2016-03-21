package client;


import model.scresponce.SctpResponse;
import model.stcprequest.SctpRequest;

import java.io.IOException;

public interface SctpClient extends AutoCloseable{
    void execute(SctpRequest stcpRequest, CallBack callBack) throws IOException;
    SctpResponse execute(SctpRequest stcpRequest) throws IOException;
}
