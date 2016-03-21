package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScAddress;
import model.scparametr.scelement.ScConnectorType;
import model.scparametr.scelement.ScElementType;

public class FindConstruction3a_a_fSctpRequest extends FindConstructionSctpRequest {
    public FindConstruction3a_a_fSctpRequest(ScElementType scFirstElementType, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement) {
        super();
        addParameters(PatternType.SCTP_ITERATOR_3_A_A_F);
        addParameters(scFirstElementType);
        addParameters(scFirstConnectorType);
        addParameters(scAddressThirdElement);

    }
}
