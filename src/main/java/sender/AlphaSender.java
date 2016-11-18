package sender;


import model.scparametr.*;
import model.scparametr.scelementtype.ScConnectorType;
import model.scparametr.scelementtype.ScNodeType;
import model.scresponce.SctpResponse;
import model.stcprequest.SctpRequest;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.net.Socket;
import java.time.Clock;

public class AlphaSender extends AbstractSender{

    public AlphaSender(String host, Integer port) {
        super(host,port);
    }

    @Override
    public SctpResponse send(SctpRequest request) {
        return null;
    }


}
