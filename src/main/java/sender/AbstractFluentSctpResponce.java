package sender;


import com.google.common.collect.Lists;
import model.scparametr.ScAddress;
import model.scparametr.ScIdSubscription;
import model.scparametr.ScParameter;
import model.scresponce.SctpResponse;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFluentSctpResponce<T> implements FluentSctpResponce<T> {

    private Exception exception;


    protected FluentSctpResponce<T> setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    protected Exception getException() {
        return exception;
    }



    protected T buildResponce(SctpResponse response) {
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
                List<ScParameter> parametrsCopy = parameters.subList(1, parameters.size());
                if (parametrsCopy.size() % 3==0){
                    List<SctpSender.Triple> scAddress = new ArrayList<>();
                    List<List<ScParameter>> partitionParametrs = Lists.partition(parametrsCopy, 3);
                    partitionParametrs.stream().forEach(element->{
                        scAddress.add(new SctpSender.Triple((ScAddress) element.get(0), (ScAddress)element.get(1),(ScAddress) element.get(2)));
                    });
                    return (T) scAddress.toArray(new SctpSender.Triple[scAddress.size()]);
                } else {
                    List<SctpSender.Quintuple> scAddress = new ArrayList<>();
                    List<List<ScParameter>> partitionParametrs = Lists.partition(parametrsCopy, 5);
                    partitionParametrs.stream().forEach(element->{
                        scAddress.add(new SctpSender.Quintuple((ScAddress) element.get(0), (ScAddress)element.get(1),(ScAddress) element.get(2),(ScAddress) element.get(3),(ScAddress) element.get(4)));
                    });
                    return (T) scAddress.toArray(new SctpSender.Quintuple[scAddress.size()]);
                }
            case FIND_ELEMENT_BY_SYSIDTF_COMMAND:
                return (T) parameters.get(0);
            case FIND_LINKS_COMMAND:
                List<ScAddress> scAddresses = new ArrayList<>();
                List<ScParameter> parametersCopy = parameters.subList(1, parameters.size());
                parametersCopy.stream().forEach((element)->{scAddresses.add((ScAddress) element);});
                return (T) scAddresses.toArray(new ScAddress[scAddresses.size()]);
            case GET_ARC_VERTEXES_COMMAND:
                List<ScAddress> scAddress = new ArrayList<>();
                scAddress.add((ScAddress) parameters.get(0));
                scAddress.add((ScAddress) parameters.get(1));
                return (T) scAddress.toArray(new ScAddress[scAddress.size()]);
            case GET_ELEMENT_TYPE_COMMAND:
                return (T) parameters.get(0);
            case GET_EVENT_COMMAND:
                int numEvents = parameters.size() / 3;
                List<SctpSender.Event> events = new ArrayList<>();
                List<List<ScParameter>> partitionParametrs = Lists.partition(parameters, 3);
                partitionParametrs.forEach(element -> {
                    SctpSender.Event event = new SctpSender.Event();
                    event.setScIdSubscription((ScIdSubscription) element.get(0));
                    event.setScAddress((ScAddress) element.get(1));
                    event.setArgScAddress((ScAddress) element.get(2));
                    events.add(event);
                });
                return (T) events.toArray(new SctpSender.Event[events.size()]);
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
                return null;

        }
    }
}
