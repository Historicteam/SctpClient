package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.SctpCommandType;

public class GetArcVertexesSctpRequest extends SctpRequest {
    public GetArcVertexesSctpRequest(ScAddress scAddress) {
        super();
        setSctpCommandType(SctpCommandType.GET_ARC_VERTEXES_COMMAND);
        addParameters(scAddress);
    }
}
