package model.stcprequest;


import model.scparametr.SctpCommandType;
import model.scparametr.scelementtype.ScNodeType;

public class CreateNodeSctpRequest extends SctpRequest {
    public CreateNodeSctpRequest(ScNodeType scNodeType) {
        super();
        setSctpCommandType(SctpCommandType.CREATE_NODE_COMMAND);
        addParameters(scNodeType);
    }
}
