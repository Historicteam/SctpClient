package model.stcprequest;

import model.scparametr.SctpCommandType;

@Deprecated
public class GetStatisticStcpRequest extends SctpRequest {
    public GetStatisticStcpRequest() {
        super();
        setSctpCommandType(SctpCommandType.STATISTICS_COMMAND);
    }
}
