package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.ScContentSize;
import model.scparametr.ScString;
import model.scparametr.SctpCommandType;

public class SetIdentifierSctpRequest extends SctpRequest {
    public SetIdentifierSctpRequest(ScAddress scAddress, ScString scString) {
        super();
        setSctpCommandType(SctpCommandType.SET_SYSIDTF_COMMAND);
        addParameters(scAddress);
        addParameters(new ScContentSize(scString.getByteSize()));
        addParameters(scString);
    }
}
