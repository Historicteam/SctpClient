package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.ScContentSize;
import model.scparametr.ScString;
import model.scparametr.SctpCommandType;

public class SetLinkContentSctpRequest extends SctpRequest {
    public SetLinkContentSctpRequest(ScAddress scAddress, ScString scString) {
        super();
        setSctpCommandType(SctpCommandType.SET_LINK_CONTENT_COMMAND);
        addParameters(scAddress);
        addParameters(new ScContentSize(scString.getByteSize()));
        addParameters(scString);
    }


}
