package model.stcprequest;

import model.scparametr.SctpCommandType;

@Deprecated
public class ConstructionIterationSctpRequest extends SctpRequest {
    public ConstructionIterationSctpRequest() {
        super();
        setSctpCommandType(SctpCommandType.ITERATE_CONSTRUCTION_COMMAND);
    }
}
