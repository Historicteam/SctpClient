package model.stcprequest;


import model.scparametr.SctpCommandType;

public class CreateLinkSctpRequest extends SctpRequest {
    public CreateLinkSctpRequest() {
        super();
        setSctpCommandType(SctpCommandType.CREATE_LINK_COMMAND);
    }
}
