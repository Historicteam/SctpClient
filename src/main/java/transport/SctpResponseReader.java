package transport;

import model.scresponce.SctpResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class SctpResponseReader {

    public static final Logger LOG = Logger.getLogger(SctpResponseReader.class);
    private InputStream source;

    public SctpResponseReader(InputStream source) {
        this.source = source;
    }

    public byte[] read() throws IOException {
        byte[] byteCode = getBytesFromResp(source, SctpResponse.CODE_SIZE);
        byte[] byteId = getBytesFromResp(source, SctpResponse.ID_SIZE);
        byte[] byteReturnCode = getBytesFromResp(source, SctpResponse.RETURN_CODE_SIZE);
        byte[] byteParametrSize = getBytesFromResp(source, SctpResponse.PARAMETRS_SIZE_SIZE);
        int parametrs_size = getIntFromBytes(byteParametrSize);
        byte[] parametersBytes = getBytesFromResp(source, parametrs_size);
        ByteBuffer byteBuffer = ByteBuffer.allocate(SctpResponse.CODE_SIZE+SctpResponse.ID_SIZE+SctpResponse.RETURN_CODE_SIZE+SctpResponse.PARAMETRS_SIZE_SIZE+parametrs_size);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put(byteCode);
        byteBuffer.put(byteId);
        byteBuffer.put(byteReturnCode);
        byteBuffer.put(byteParametrSize);
        byteBuffer.put(parametersBytes);
        byte[] data = byteBuffer.array();
        StringBuilder dataStr = new StringBuilder();
        for (byte b : data) {
            dataStr.append(b).append(".");
        }
        LOG.info(dataStr);
        return data;
    }

    private byte[] getBytesFromResp(InputStream source, int count) throws IOException {
        byte[] result = new byte[count];
        source.read(result);
        return result;
    }

    private int getIntFromBytes(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getInt();
    }






}
