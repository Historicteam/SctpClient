package new_client;


import exception.SctpException;
import sender.AsyncSender;
import sender.SyncSender;
import sender.SctpSender;

import java.util.List;

public class SctpSenderCreater implements AutoCloseable{
    List<SctpSender> senders;
    private String host;
    private Integer port;

    protected SctpSenderCreater(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public static SctpSender createAsyncSender(String host, Integer port) {
        return new AsyncSender(host, port);
    }

    public static SctpSender createSender(String host, Integer port) {
        return new SyncSender(host, port);
    }

    public void close() throws SctpException {
        for (SctpSender sender:senders){
            sender.close();
        }
    }


}
