package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScAddress;
import model.scparametr.scelementtype.ScConnectorType;

public class FindConstruction5_f_a_f_a_fSctpRequest extends FindConstructionSctpRequest {
    public FindConstruction5_f_a_f_a_fSctpRequest(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement, ScConnectorType scSecondConnectorType, ScAddress scAddressFifthElement) {
        super();
        addParameters(PatternType.SCTP_ITERATOR_5_F_A_F_A_F);
        addParameters(scAddressFirstElement);
        addParameters(scFirstConnectorType);
        addParameters(scAddressThirdElement);
        addParameters(scSecondConnectorType);
        addParameters(scAddressFifthElement);

    }
}
