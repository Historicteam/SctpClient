package model.scresponce.builder;

import model.scparametr.SctpCodeReturn;
import model.scparametr.SctpCommandType;
import model.scresponce.SctpResponse;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class SctpResponceBytesBuilder {
    public static SctpResponse build(byte[] byteResponce) {
        return parse(byteResponce);
    }

    private static SctpResponse parse(byte[] byteResponce) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteResponce);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        SctpResponse sctpResponse = new SctpResponse();
        sctpResponse.setSctpCommandType(SctpCommandType.getByCode(byteBuffer.get()));
        sctpResponse.setId(byteBuffer.getInt());
        sctpResponse.setSctpCodeReturn(SctpCodeReturn.getByCode(byteBuffer.get()));
        sctpResponse.setSize(byteBuffer.getInt());
        byte[] newArray = Arrays.copyOfRange(byteResponce, 10, byteResponce.length);
        sctpResponse.setParameters(ResponseBodyBuilder.getInstance().build(newArray, sctpResponse));
        return sctpResponse;
    }
}
