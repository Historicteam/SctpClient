package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.SctpCommandType;

public class DeleteElementSctpRequest extends SctpRequest {
    public DeleteElementSctpRequest(ScAddress scAddress) {
        super();
        setSctpCommandType(SctpCommandType.DELETE_ELEMENT_COMMAND);
        addParameters(scAddress);
    }
}
