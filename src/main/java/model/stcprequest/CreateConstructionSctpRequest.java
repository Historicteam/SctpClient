package model.stcprequest;

import model.scparametr.SctpCommandType;

@Deprecated
public class CreateConstructionSctpRequest extends SctpRequest {
    public CreateConstructionSctpRequest() {
        super();
        setSctpCommandType(SctpCommandType.CREATE_CONSTRUCTION);
    }
}
