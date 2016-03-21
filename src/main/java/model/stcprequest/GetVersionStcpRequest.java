package model.stcprequest;

import model.scparametr.SctpCommandType;

@Deprecated
public class GetVersionStcpRequest extends SctpRequest {
    public GetVersionStcpRequest() {
        super();
        setSctpCommandType(SctpCommandType.VERSION_COMMAND);
        setSize(0);
    }
}
