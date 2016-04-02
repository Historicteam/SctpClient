package transport;


import model.stcprequest.SctpRequest;
import model.stcprequest.builder.SctpRequestBytesBuilder;

import java.io.IOException;
import java.io.OutputStream;

public class SctpRequestSender {
    private OutputStream outputStream;

    public SctpRequestSender(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendRequest(SctpRequest request) throws IOException {

        byte[] data = SctpRequestBytesBuilder.build(request);
        for (byte b : data) {
            System.out.print(b+".");
        }
        System.out.println();
        outputStream.write(data);
        outputStream.flush();

    }
}
