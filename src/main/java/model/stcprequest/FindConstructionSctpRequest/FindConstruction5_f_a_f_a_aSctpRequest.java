package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScAddress;
import model.scparametr.scelement.ScConnectorType;
import model.scparametr.scelement.ScElementType;

public class FindConstruction5_f_a_f_a_aSctpRequest extends FindConstructionSctpRequest {
    public FindConstruction5_f_a_f_a_aSctpRequest(ScAddress scAddressFirstElement,  ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement, ScConnectorType scSecondConnectorType,  ScElementType scFifthElementType) {
            super();
            addParameters(PatternType.SCTP_ITERATOR_5_F_A_F_A_A);
            addParameters(scAddressFirstElement);
            addParameters(scFirstConnectorType);
            addParameters(scAddressThirdElement);
            addParameters(scSecondConnectorType);
            addParameters(scFifthElementType);
        }
}
