package new_client;


import sender.AlphaSender;
import sender.BetaSender;
import sender.SctpSender;
import com.google.common.io.Closer;

import java.io.IOException;
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
        return new AlphaSender(host, port);
    }

    public static SctpSender createSender(String host, Integer port) {
        return new BetaSender(host, port);
    }

    public void close() throws IOException {
        for (SctpSender sender:senders){
            sender.close();
        }
    }


}
