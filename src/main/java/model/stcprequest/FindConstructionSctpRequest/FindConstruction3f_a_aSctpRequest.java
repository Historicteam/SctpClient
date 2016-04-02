package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScAddress;
import model.scparametr.scelementtype.ScConnectorType;
import model.scparametr.scelementtype.ScElementType;

public class FindConstruction3f_a_aSctpRequest extends FindConstructionSctpRequest {
    public FindConstruction3f_a_aSctpRequest(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScElementType scThirdElementType) {
            super();
            addParameters(PatternType.SCTP_ITERATOR_3F_A_A);
            addParameters(scAddressFirstElement);
            addParameters(scFirstConnectorType);
            addParameters(scThirdElementType);
        }
}
