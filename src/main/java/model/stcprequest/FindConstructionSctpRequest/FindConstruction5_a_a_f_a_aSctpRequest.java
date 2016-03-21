package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScAddress;
import model.scparametr.scelement.ScConnectorType;
import model.scparametr.scelement.ScElementType;

public class FindConstruction5_a_a_f_a_aSctpRequest extends FindConstructionSctpRequest {
    public FindConstruction5_a_a_f_a_aSctpRequest(ScElementType scFirstElementType, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement,  ScConnectorType scSecondConnectorType, ScElementType scFifthElementType) {
        super();
        addParameters(PatternType.SCTP_ITERATOR_5_A_A_F_A_A);
        addParameters(scFirstElementType);
        addParameters(scFirstConnectorType);
        addParameters(scAddressThirdElement);
        addParameters(scSecondConnectorType);
        addParameters(scFifthElementType);
    }
}
