package sender;


import exception.IllegalReturnCode;
import model.scparametr.ScAddress;
import model.scparametr.ScIdSubscription;
import model.scparametr.ScParameter;
import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class FluentSctpResponceImpl<T> implements FluentSctpResponce<T> {

    private SctpResponse response;
    private Exception exception;

    protected FluentSctpResponceImpl(SctpResponse response) {
        this.response = response;
        if (response==null){
            setException(new NullPointerException());
        }
    }

    protected FluentSctpResponceImpl<T> setException(Exception exception) {
        this.exception = exception;
        return this;
    }


    @Override
    public FluentSctpResponce<T> success(CommandaWithParametr<T> commanda) {
        if ((exception != null) && (response.getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
            commanda.execute((T) response.getParameters().get(0));
        }
        return this;

    }

    @Override
    public FluentSctpResponce<T> unsuccess(Commanda commanda) {
        if ((exception != null) && (response.getSctpCodeReturn() == SctpCodeReturn.UNSUCCESSFULLY)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unfound(Commanda commanda) {
        if ((exception != null) && (response.getSctpCodeReturn() == SctpCodeReturn.NOT_FIND)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unavailable(Commanda commanda) {
        if ((exception != null) && (response.getSctpCodeReturn() == SctpCodeReturn.PERMISSION_DENIED)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> error(CommandaWithParametr<SctpCodeReturn> commanda) {
        if ((exception != null) && (response.getSctpCodeReturn() == SctpCodeReturn.UNSUCCESSFULLY)) {
            commanda.execute(response.getSctpCodeReturn());
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> exception(CommandaWithParametr<Exception> commanda) {
        if (exception != null) {
            commanda.execute(exception);
        }
        return this;
    }

    @Override
    public SctpResponse getResponce() {
        return response;
    }

    @Override
    public T getResult() {
        return buildResponce(response);
    }

    private T buildResponce(SctpResponse response) {
        List<ScParameter> parameters = response.getParameters();
        switch (response.getSctpCommandType()) {
            case CHECK_ELEMENT_COMMAND:
                return (T) response.getSctpCodeReturn();
            case CREATE_CONSTRUCTION:
                return null;
            case CREATE_ARC_COMMAND:
                return (T) parameters.get(0);
            case CREATE_LINK_COMMAND:
                return (T) parameters.get(0);
            case CREATE_NODE_COMMAND:
                return (T) parameters.get(0);
            case DELETE_ELEMENT_COMMAND:
                return (T) response.getSctpCodeReturn();
            case DELETE_SUBSCRIPTION_COMMAND:
                return (T) parameters.get(0);
            case FIND_CONSTRUCTIONS_COMMAND:
                return null;
            case FIND_ELEMENT_BY_SYSIDTF_COMMAND:
                return (T) parameters.get(0);
            case FIND_LINKS_COMMAND:
                return (T) parameters.get(0);
            case GET_ARC_VERTEXES_COMMAND:
                List<ScAddress> scAddress = new ArrayList<>();
                scAddress.add((ScAddress) parameters.get(0));
                scAddress.add((ScAddress) parameters.get(1));
                return (T) scAddress.toArray();
            case GET_ELEMENT_TYPE_COMMAND:
                return (T) parameters.get(0);
            case GET_EVENT_COMMAND:
                int numEvents = parameters.size()/3;
                List<SctpSender.Event> events = new ArrayList<>();
                List<List<ScParameter>> partitionParametrs = Lists.partition(parameters, 3);
                partitionParametrs.forEach(element->{
                    SctpSender.Event event = new SctpSender.Event();
                    event.setScIdSubscription((ScIdSubscription) element.get(0));
                    event.setScAddress((ScAddress) element.get(1));
                    event.setArgScAddress((ScAddress) element.get(2));
                    events.add(event);
                });
                return (T) events.toArray();
            case GET_LINK_CONTENT_COMMAND:
                return (T) parameters.get(0);
            case ITERATE_CONSTRUCTION_COMMAND:
                return null;
            case MAKE_SUBSCRIPTION_COMMAND:
                return (T) parameters.get(0);
            case SET_LINK_CONTENT_COMMAND:
                return (T) response.getSctpCodeReturn();
            case SET_SYSIDTF_COMMAND:
                return (T) response.getSctpCodeReturn();
            case SHUTDOWN_COMMAND:
                return null;
            case STATISTICS_COMMAND:
                return null;
            case VERSION_COMMAND:
                return null;
            default:
                new IllegalReturnCode("Not support command= " + response.getSctpCommandType());
                break;
        }
        return null;
    }

}
