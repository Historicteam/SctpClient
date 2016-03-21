package model.stcprequest;


import model.scparametr.ScIdSubscription;
import model.scparametr.SctpCommandType;

public class DeleteSubscriptionSctpRequest extends SctpRequest {
    public DeleteSubscriptionSctpRequest(ScIdSubscription scIdSubscription) {
        super();
        setSctpCommandType(SctpCommandType.DELETE_SUBSCRIPTION_COMMAND);
        addParameters(scIdSubscription);
    }
}
