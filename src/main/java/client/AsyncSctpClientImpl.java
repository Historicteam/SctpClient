package client;

import model.stcprequest.SctpRequest;
import transport.SctpRequestSender;

import java.io.IOException;

public class AsyncSctpClientImpl extends SctpClient implements AsyncSctpClient {
    public AsyncSctpClientImpl(String host, int port) throws IOException {
        super(host, port);
    }

    public AsyncSctpClientImpl(String host) throws IOException {
        super(host);
    }

    @Override
    public void execute(SctpRequest stcpRequest, CallBack callBack) throws IOException {
        initSocket();
        initReaderThreat();
        addToCallBackMap(stcpRequest.getId(), callBack);
        addToRequestBackMap(stcpRequest.getId(), stcpRequest);
        SctpRequestSender sender = new SctpRequestSender(getOutputStream());
        sender.sendRequest(stcpRequest);
    }



    @Override
    public void close() throws IOException {

    }
}
