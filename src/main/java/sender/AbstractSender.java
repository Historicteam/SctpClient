package sender;


import exception.IllegalCommand;
import exception.IllegalReturnCode;
import model.scparametr.*;
import model.scparametr.scelementtype.ScConnectorType;
import model.scparametr.scelementtype.ScNodeType;
import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.*;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.io.IOException;
import java.net.Socket;
import java.time.Clock;

public abstract class AbstractSender implements SctpSender {
    private Socket socket;
    private SctpRequestSender sender;
    private SctpResponseReader reader;
    private Exception exception;

    public AbstractSender(String host, Integer port) {
        try {
            init(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected SctpRequestSender getSender() {
        return sender;
    }

    protected SctpResponseReader getReader() {
        return reader;
    }


    protected void setException(Exception exception) {
        this.exception = exception;
    }

    private Exception getException() {
        return exception;
    }


    @Override
    public FluentSctpResponce<SctpCodeReturn> check(ScAddress scAddress) {
        return new ExecuteHandler<SctpCodeReturn>().execute(new CheckElementExistenceSctpRequest(scAddress));
    }

    @Override
    public FluentSctpResponce<ScNodeType> getType(ScAddress scAddress) {
        return new ExecuteHandler<ScNodeType>().execute(new GetElementTypeStcpRequest(scAddress));
    }

    @Override
    public FluentSctpResponce<SctpCodeReturn> delete(ScAddress scAddress) {
        return new ExecuteHandler<SctpCodeReturn>().execute(new DeleteElementSctpRequest(scAddress));
    }

    @Override
    public FluentSctpResponce<ScAddress> create(ScNodeType scNodeType) {
        return new ExecuteHandler<ScAddress>().execute(new CreateNodeSctpRequest(scNodeType));
    }

    @Override
    public FluentSctpResponce<ScAddress> create() {
        return new ExecuteHandler<ScAddress>().execute(new CreateLinkSctpRequest());
    }

    @Override
    public FluentSctpResponce<ScAddress> create(ScConnectorType scConnector, ScAddress scAddressFirstElement, ScAddress scAddressSecondElement) {
        return new ExecuteHandler<ScAddress>().execute(new CreateArcSctpRequest(scConnector, scAddressFirstElement, scAddressSecondElement));
    }

    @Override
    public FluentSctpResponce<ScAddress[]> get(ScAddress scAddress) {
        return new ExecuteHandler<ScAddress[]>().execute(new GetArcVertexesSctpRequest(scAddress));
    }

    @Override
    public FluentSctpResponce<ScString> getContent(ScAddress scAddress) {
        return new ExecuteHandler<ScString>().execute(new GetLinkContentSctpRequest(scAddress));
    }

    @Override
    public FluentSctpResponce<ScAddress[]> findLink(ScString scString) {
        return new ExecuteHandler<ScAddress[]>().execute(new GetLinksByContentSctpRequest(scString));
    }

    @Override
    public FluentSctpResponce<SctpCodeReturn> setLinkContent(ScAddress scAddress, ScString scString) {
        return new ExecuteHandler<SctpCodeReturn>().execute(new SetLinkContentSctpRequest(scAddress, scString));
    }

    @Override
    public FluentSctpResponce<ScIdSubscription> subscribe(ScEventType scEventType, ScAddress scAddress) {
        return new ExecuteHandler<ScIdSubscription>().execute(new MakeSubscriptionSctpRequest(scEventType, scAddress));
    }

    @Override
    public FluentSctpResponce<ScIdSubscription> delete(ScIdSubscription scIdSubscription) {
        return new ExecuteHandler<ScIdSubscription>().execute(new DeleteSubscriptionSctpRequest(scIdSubscription));
    }

    @Override
    public FluentSctpResponce<Event[]> getEvent() {
        return new ExecuteHandler<Event[]>().execute(new GetEventSctpRequest());
    }

    @Override
    public FluentSctpResponce<ScAddress> find(ScString scString) {
        return new ExecuteHandler<ScAddress>().execute(new GetElementByIdSctpRequest(scString));
    }

    @Override
    public FluentSctpResponce<SctpCodeReturn> set(ScAddress scAddress, ScString scString) {
        return new ExecuteHandler<SctpCodeReturn>().execute(new SetIdentifierSctpRequest(scAddress, scString));
    }

    @Override
    @Deprecated
    public FluentSctpResponce<EventTimeSignature> get(Clock start, Clock finish) {
        return new ExecuteHandler<EventTimeSignature>().execute(new GetStatisticStcpRequest());
    }

    @Override
    public FluentSctpResponce<Integer> get() {
        return new ExecuteHandler<Integer>().execute(new GetVersionStcpRequest());
    }


    class ExecuteHandler<T> {
        public FluentSctpResponceImpl<T> execute(SctpRequest request) {
            return (new FluentSctpResponceImpl<T>(send(request))).setException(getException());
        }
    }



    public abstract SctpResponse send(SctpRequest request);


    synchronized private Socket init(String host, Integer port) throws IOException {
        if ((socket == null) || socket.isClosed()) {
            socket = new Socket(host, port);
            sender = new SctpRequestSender(socket.getOutputStream());
            reader = new SctpResponseReader(socket.getInputStream());
        }
        return socket;
    }


    @Override
    public void close() throws IOException {
        socket.close();
    }
}
