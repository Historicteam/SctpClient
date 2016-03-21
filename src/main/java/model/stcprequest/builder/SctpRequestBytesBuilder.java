package model.stcprequest.builder;


import model.scparametr.ScParameter;
import model.stcprequest.SctpRequest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public class SctpRequestBytesBuilder {
    public static byte[] build(SctpRequest request) {
        return parse(request);
    }

    private static byte[] parse(SctpRequest request) {
        ByteBuffer tempBuffer = ByteBuffer.allocate(request.getByteLenght());
        tempBuffer.order(ByteOrder.LITTLE_ENDIAN);
        tempBuffer.put(request.getSctpCommandType().getValue());
        tempBuffer.put(request.getFlag());
        tempBuffer.putInt(request.getId());
        tempBuffer.putInt(request.getSize());
        List<ScParameter> parameterList = request.getParameters();
        for (ScParameter parameter : parameterList) {
            tempBuffer.put(parameter.getBytes());
        }
        return tempBuffer.array();
    }


}
