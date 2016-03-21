package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScAddress;
import model.scparametr.scelement.ScConnectorType;
import model.scparametr.scelement.ScElementType;

public class FindConstruction5_f_a_a_a_aSctpRequest extends FindConstructionSctpRequest {
    public FindConstruction5_f_a_a_a_aSctpRequest(ScAddress scAddressFirstElement,  ScConnectorType scFirstConnectorType, ScElementType scThirdElementType, ScConnectorType scSecondConnectorType, ScElementType scFifthElementType) {
            super();
            addParameters(PatternType.SCTP_ITERATOR_5_F_A_A_A_A);
            addParameters(scAddressFirstElement);
            addParameters(scFirstConnectorType);
            addParameters(scThirdElementType);
            addParameters(scSecondConnectorType);
            addParameters(scFifthElementType);
        }
}
