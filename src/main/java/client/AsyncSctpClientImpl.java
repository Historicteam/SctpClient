package client;

import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.SctpRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.io.IOException;
import java.util.concurrent.Future;

public class AsyncSctpClientImpl extends SctpClientUtil implements AsyncSctpClient {
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
    @Async
    public Future<SctpResponse> execute(SctpRequest stcpRequest) throws IOException {
        initSocket();
        SctpRequestSender sender = new SctpRequestSender(getOutputStream());
        SctpResponseReader reader = new SctpResponseReader(getInputStream());
        sender.sendRequest(stcpRequest);
        return new AsyncResult<SctpResponse>(SctpResponceBytesBuilder.build(reader.read()));
    }

    @Override
    public void close() throws IOException {

    }
}
