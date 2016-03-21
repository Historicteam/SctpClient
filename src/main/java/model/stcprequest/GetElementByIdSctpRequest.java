package model.stcprequest;


import model.scparametr.ScContentSize;
import model.scparametr.ScString;
import model.scparametr.SctpCommandType;

public class GetElementByIdSctpRequest extends SctpRequest {
    public GetElementByIdSctpRequest(ScString scString) {
        super();
        setSctpCommandType(SctpCommandType.FIND_ELEMENT_BY_SYSIDTF_COMMAND);
        addParameters(new ScContentSize(scString.getByteSize()));
        addParameters(scString);
    }
}
