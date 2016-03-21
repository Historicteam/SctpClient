package model.stcprequest;

import model.scparametr.SctpCommandType;


public class GetEventSctpRequest extends SctpRequest {
    public GetEventSctpRequest() {
        super();
        setSctpCommandType(SctpCommandType.GET_EVENT_COMMAND);
    }
}
