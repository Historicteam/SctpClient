package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.SctpCommandType;

public class GetElementTypeStcpRequest extends SctpRequest {
    public GetElementTypeStcpRequest(ScAddress scAddress) {
        super();
        setSctpCommandType(SctpCommandType.GET_ELEMENT_TYPE_COMMAND);
        addParameters(scAddress);
    }
}
