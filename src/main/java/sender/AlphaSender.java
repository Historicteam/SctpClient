package sender;


import exception.IllegalCommand;
import exception.IllegalReturnCode;
import model.scparametr.*;
import model.scparametr.scelementtype.*;
import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.*;
import model.stcprequest.FindConstructionSctpRequest.*;

import java.io.IOException;
import java.time.Clock;
import java.util.Map;
import java.util.concurrent.*;

public class AlphaSender extends AbstractSender {

    private static int ID_GENERATE = Integer.MIN_VALUE;
    private Map<Integer, Triple<SctpRequest,Future<SctpResponse>,Exchanger<SctpResponse>>> tuples;
    private static final Semaphore SEMAPHORE = new Semaphore(1, true);
    private ScheduledExecutorService scheduledExecutorService;

    public AlphaSender(String host, Integer port) {
        super(host, port);
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }


    @Override
    public FluentSctpResponce<SctpCodeReturn> check(ScAddress scAddress) {
        return new ExecuteHandler<SctpCodeReturn>().execute(new CheckElementExistenceSctpRequest(scAddress));
    }

    @Override
    public FluentSctpResponce<ScElementType> type(ScAddress scAddress) {
        return new ExecuteHandler<ScElementType>().execute(new GetElementTypeStcpRequest(scAddress));
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
    public FluentSctpResponce<ScAddress> create(ScNode scNode) {
        return new ExecuteHandler<ScAddress>().execute(new CreateNodeSctpRequest(scNode.get()));
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
    public FluentSctpResponce<ScAddress> create(ScConnector scConnector, ScAddress scAddressFirstElement, ScAddress scAddressSecondElement) {
        return new ExecuteHandler<ScAddress>().execute(new CreateArcSctpRequest(scConnector.get(), scAddressFirstElement, scAddressSecondElement));
    }

    @Override
    public FluentSctpResponce<ScAddress[]> get(ScAddress scAddress) {
        return new ExecuteHandler<ScAddress[]>().execute(new GetArcVertexesSctpRequest(scAddress));
    }

    @Override
    public FluentSctpResponce<ScString> content(ScAddress scAddress) {
        return new ExecuteHandler<ScString>().execute(new GetLinkContentSctpRequest(scAddress));
    }

    @Override
    public FluentSctpResponce<ScAddress[]> serch(ScString scString) {
        return new ExecuteHandler<ScAddress[]>().execute(new GetLinksByContentSctpRequest(scString));
    }

    @Override
    public FluentSctpResponce<SctpCodeReturn> link(ScAddress scAddress, ScString scString) {
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
    public FluentSctpResponce<Event[]> events() {
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

    @Override
    public FluentSctpResponce<SctpSender.Triple[]> find(ScElementType scFirstElementType, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement) {
        return new ExecuteHandler<SctpSender.Triple[]>().execute(new FindConstruction3a_a_fSctpRequest(scFirstElementType, scFirstConnectorType, scAddressThirdElement));
    }

    @Override
    public FluentSctpResponce<SctpSender.Triple[]> find(ScElement scFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement) {
        return find(scFirstElement.get(), scFirstConnector.get(), scAddressThirdElement);
    }



    @Override
    public FluentSctpResponce<SctpSender.Triple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScElementType scThirdElementType) {
        return new ExecuteHandler<SctpSender.Triple[]>().execute(new FindConstruction3f_a_aSctpRequest(scAddressFirstElement, scFirstConnectorType, scThirdElementType));
    }

    @Override
    public FluentSctpResponce<SctpSender.Triple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScElement scThirdElement) {
        return find(scAddressFirstElement,scFirstConnector.get(), scThirdElement.get());
    }


    @Override
    public FluentSctpResponce<SctpSender.Triple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement) {
        return new ExecuteHandler<SctpSender.Triple[]>().execute(new FindConstruction3f_a_fSctpRequest(scAddressFirstElement, scFirstConnectorType, scAddressThirdElement));
    }

    @Override
    public FluentSctpResponce<SctpSender.Triple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement) {
        return find(scAddressFirstElement, scFirstConnector.get(), scAddressThirdElement);
    }


    @Override
    public FluentSctpResponce<Quintuple[]> find(ScElementType scFirstElementType, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement, ScConnectorType scSecondConnectorType, ScElementType scFifthElementType) {
        return new ExecuteHandler<Quintuple[]>().execute(new FindConstruction5_a_a_f_a_aSctpRequest(scFirstElementType, scFirstConnectorType, scAddressThirdElement, scSecondConnectorType, scFifthElementType));
    }


    @Override
    public FluentSctpResponce<Quintuple[]> find(ScElement scFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement, ScConnector scSecondConnector, ScElement scFifthElement) {
        return find(scFirstElement.get(), scSecondConnector.get(), scAddressThirdElement, scSecondConnector.get(), scFifthElement.get());
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScElementType scFirstElementType, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement, ScConnectorType scSecondConnectorType, ScAddress scAddressFifthElement) {
        return new ExecuteHandler<Quintuple[]>().execute(new FindConstruction5_a_a_f_a_fSctpRequest(scFirstElementType, scFirstConnectorType, scAddressThirdElement, scSecondConnectorType, scAddressFifthElement));
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScElement scFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement, ScConnector scSecondConnector, ScAddress scAddressFifthElement) {
        return find(scFirstElement.get(),scFirstConnector.get(), scAddressThirdElement, scSecondConnector.get(), scAddressFifthElement);
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScElementType scThirdElementType, ScConnectorType scSecondConnectorType, ScElementType scFifthElementType) {
        return new ExecuteHandler<Quintuple[]>().execute(new FindConstruction5_f_a_a_a_aSctpRequest(scAddressFirstElement, scFirstConnectorType, scThirdElementType, scSecondConnectorType, scFifthElementType));
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScElement scThirdElement, ScConnector scSecondConnector, ScElement scFifthElement) {
        return find(scAddressFirstElement, scFirstConnector.get(), scThirdElement.get(), scSecondConnector.get(), scFifthElement.get());
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement, ScConnectorType scSecondConnectorType, ScElementType scFifthElementType) {
        return new ExecuteHandler<Quintuple[]>().execute(new FindConstruction5_f_a_f_a_aSctpRequest(scAddressFirstElement, scFirstConnectorType, scAddressThirdElement, scSecondConnectorType, scFifthElementType));
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement, ScConnector scSecondConnector, ScElement scFifthElement) {
        return find(scAddressFirstElement, scFirstConnector.get(), scAddressThirdElement, scSecondConnector.get(), scFifthElement.get());
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnector, ScAddress scAddressThirdElement, ScConnectorType scSecondConnector, ScAddress scAddressFifthElement) {
        return new ExecuteHandler<Quintuple[]>().execute(new FindConstruction5_f_a_f_a_fSctpRequest(scAddressFirstElement, scFirstConnector, scAddressThirdElement, scSecondConnector, scAddressFifthElement));
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement, ScConnector scSecondConnector, ScAddress scAddressFifthElement) {
        return find(scAddressFirstElement, scSecondConnector.get(), scAddressThirdElement, scSecondConnector.get(), scAddressFifthElement);
    }


    @Override
    public FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScElementType scThirdElementType, ScConnectorType scSecondConnectorType, ScAddress scAddressFifthElement) {
        return find(scAddressFirstElement, scFirstConnectorType, scThirdElementType, scSecondConnectorType, scFirstConnectorType);
    }

    @Override
    public FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScElement scThirdElement, ScConnector scSecondConnector, ScAddress scAddressFifthElement) {
        return find(scAddressFirstElement, scSecondConnector.get(), scThirdElement.get(), scSecondConnector.get(), scAddressFifthElement);
    }


    class ExecuteHandler<T> {
        public FluentSctpResponce<T> execute(SctpRequest request) {
            return (new AsyncFluentSctpResponceImpl<T>(send(request))).setException(getException());
        }
    }

    private Future<SctpResponse> send(SctpRequest request) {
        request.setId(generateRequestId());
        Exchanger<SctpResponse> exchanger = new Exchanger<>();
        Future<SctpResponse> sctpResponseFuture = new FutureImpl(exchanger);
        tuples.put(request.getId(), new Triple<>(request, sctpResponseFuture, exchanger));
        try {
            getSender().sendRequest(request);
        } catch (IOException e) {
            setException(e);
            e.printStackTrace();
        }
        scheduledExecutorService.submit(new Reader());
        return sctpResponseFuture;
    }




    class Reader implements Callable<SctpResponse> {
        @Override
        public SctpResponse call() {
            try {
                SEMAPHORE.acquire(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
                setException(e);
            }
            SctpResponse responce = null;
            try {
                responce = SctpResponceBytesBuilder.build(getReader().read());
            } catch (IOException | IllegalReturnCode | IllegalCommand e) {
                setException(e);
                e.printStackTrace();
            }
            try {
                tuples.get(responce.getId()).getThrid().exchange(responce);
            } catch (InterruptedException e) {
                setException(e);
                e.printStackTrace();
            }
            SEMAPHORE.release();
            return responce;
        }
    }

    @Override
    public void close() throws IOException {
        try {
            SEMAPHORE.acquire(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.close();
        SEMAPHORE.release();
    }

    private int generateRequestId() {
        int id = ID_GENERATE++;
        return id;
    }

    public class Triple<X, Y, Z> {
        public X x;
        public Y y;
        public Z z;

        public Triple(X x, Y y, Z z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public X getFirst() {
            return x;
        }

        public Y getSecond() {
            return y;
        }

        public void setFirst(X x) {
            this.x = x;
        }

        public void setSecond(Y y) {
            this.y = y;
        }

        public Z getThrid() {
            return z;
        }

        public void setThird(Z z) {
            this.z = z;
        }
    }



}
