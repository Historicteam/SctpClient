package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.ScEventType;
import model.scparametr.SctpCommandType;

public class MakeSubscriptionSctpRequest extends SctpRequest {
    public MakeSubscriptionSctpRequest(ScEventType scEventType, ScAddress scAddress) {
        super();
        setSctpCommandType(SctpCommandType.MAKE_SUBSCRIPTION_COMMAND);
        addParameters(scEventType);
        addParameters(scAddress);
    }
}
