package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.scelement.ScConnectorType;
import model.scparametr.SctpCommandType;

public class CreateArcSctpRequest extends SctpRequest {
    public CreateArcSctpRequest(ScConnectorType scConnector, ScAddress scAddressFirstElement, ScAddress scAddressSecondElement) {
        super();
        setSctpCommandType(SctpCommandType.CREATE_ARC_COMMAND);
        addParameters(scConnector);
        addParameters(scAddressFirstElement);
        addParameters(scAddressSecondElement);

    }
}
