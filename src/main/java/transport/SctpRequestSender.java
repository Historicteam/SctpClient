package transport;


import model.stcprequest.SctpRequest;
import model.stcprequest.builder.SctpRequestBytesBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;

public class SctpRequestSender {

    public static final Logger LOG = Logger.getLogger(SctpRequestSender.class);
    private OutputStream outputStream;

    public SctpRequestSender(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendRequest(SctpRequest request) throws IOException {

        byte[] data = SctpRequestBytesBuilder.build(request);
        StringBuilder dataStr = new StringBuilder();
        for (byte b : data) {
            dataStr.append(b).append(".");
        }
        LOG.info(dataStr);
        outputStream.write(data);
        outputStream.flush();

    }
}
