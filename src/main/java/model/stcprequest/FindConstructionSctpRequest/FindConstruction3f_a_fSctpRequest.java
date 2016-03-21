package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScAddress;
import model.scparametr.scelement.ScConnectorType;

public class FindConstruction3f_a_fSctpRequest extends FindConstructionSctpRequest{
    public FindConstruction3f_a_fSctpRequest(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement) {
            super();
            addParameters(PatternType.SCTP_ITERATOR_3F_A_F);
            addParameters(scAddressFirstElement);
            addParameters(scFirstConnectorType);
            addParameters(scAddressThirdElement);

        }
}
