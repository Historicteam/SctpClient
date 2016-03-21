package model.stcprequest;


import model.scparametr.SctpCommandType;
import model.scparametr.scelement.ScNodeType;

public class CreateNodeSctpRequest extends SctpRequest {
    public CreateNodeSctpRequest(ScNodeType scNodeType) {
        super();
        setSctpCommandType(SctpCommandType.CREATE_NODE_COMMAND);
        addParameters(scNodeType);
    }
}
