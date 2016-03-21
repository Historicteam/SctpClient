package model.scparametr;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public enum ScEventType implements ScParameter<Byte> {


    //SC_EVENT_UNKNOWN(-1),
    SC_EVENT_ADD_OUTPUT_ARC(0),
    SC_EVENT_ADD_INPUT_ARC(1),
    SC_EVENT_REMOVE_OUTPUT_ARC(2),
    SC_EVENT_REMOVE_INPUT_ARC(3),
    SC_EVENT_REMOVE_ELEMENT(4);

    private byte value;
    private int size =1;


    ScEventType(int value) {
        this.value = (byte)value;
    }


    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(value);
        return buffer.array();
    }

    public int getByteSize() {
        return size;
    }

    @Override
    public Byte get() {
        return value;
    }


}
