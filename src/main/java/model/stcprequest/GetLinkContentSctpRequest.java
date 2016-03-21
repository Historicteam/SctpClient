package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.SctpCommandType;

public class GetLinkContentSctpRequest extends SctpRequest {
    public GetLinkContentSctpRequest(ScAddress scAddress) {
        super();
        setSctpCommandType(SctpCommandType.GET_LINK_CONTENT_COMMAND);
        addParameters(scAddress);
    }
}
