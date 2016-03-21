package model.stcprequest;


import model.scparametr.ScContentSize;
import model.scparametr.ScString;
import model.scparametr.SctpCommandType;

public class GetLinksByContentSctpRequest extends SctpRequest {
    public GetLinksByContentSctpRequest(ScString scString) {
        super();
        setSctpCommandType(SctpCommandType.FIND_LINKS_COMMAND);
        addParameters(new ScContentSize(scString.getByteSize()));
        addParameters(scString);
    }
}
