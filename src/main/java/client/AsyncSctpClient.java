package client;

import model.scresponce.SctpResponse;
import model.stcprequest.SctpRequest;

import java.io.IOException;
import java.util.concurrent.Future;


interface AsyncSctpClient {
    void execute(SctpRequest stcpRequest, CallBack callBack) throws IOException;

    Future<SctpResponse> execute(SctpRequest stcpRequest) throws IOException;

    void close() throws IOException;
}
