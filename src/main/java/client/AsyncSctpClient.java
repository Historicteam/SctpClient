package client;

import model.stcprequest.SctpRequest;

import java.io.IOException;


interface AsyncSctpClient {
    void execute(SctpRequest stcpRequest, CallBack callBack) throws IOException;


    void close() throws IOException;
}
