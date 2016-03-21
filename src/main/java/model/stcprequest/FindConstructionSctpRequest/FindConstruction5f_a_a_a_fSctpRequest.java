package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScAddress;
import model.scparametr.scelement.ScConnectorType;
import model.scparametr.scelement.ScElementType;

public class FindConstruction5f_a_a_a_fSctpRequest extends FindConstructionSctpRequest {
    public FindConstruction5f_a_a_a_fSctpRequest(ScAddress scAddressFirstElement,  ScConnectorType scFirstConnectorType, ScElementType scThirdElementType, ScConnectorType scSecondConnectorType, ScAddress scAddressFifthElement) {
        super();
        addParameters(PatternType.SCTP_ITERATOR_5F_A_A_A_F);
        addParameters(scAddressFirstElement);
        addParameters(scFirstConnectorType);
        addParameters(scThirdElementType);
        addParameters(scSecondConnectorType);
        addParameters(scAddressFifthElement);
    }
}
