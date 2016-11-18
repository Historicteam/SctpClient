package sender;


import exception.IllegalCommand;
import exception.IllegalReturnCode;
import model.scparametr.*;
import model.scparametr.scelementtype.ScConnectorType;
import model.scparametr.scelementtype.ScNodeType;
import model.scresponce.SctpResponse;
import model.scresponce.builder.SctpResponceBytesBuilder;
import model.stcprequest.*;
import transport.SctpRequestSender;
import transport.SctpResponseReader;

import java.io.IOException;
import java.net.Socket;
import java.time.Clock;

public class BetaSender extends AbstractSender {


    public BetaSender(String host, Integer port) {
        super(host,port);
    }

    @Override
    public SctpResponse send(SctpRequest request) {
        SctpResponse response = null;
        try {
            getSender().sendRequest(request);
            response = SctpResponceBytesBuilder.build(getReader().read());
        } catch (IOException | IllegalCommand | IllegalReturnCode e) {
            e.printStackTrace();
            setException(e);
        }
        return response;
    }


}
