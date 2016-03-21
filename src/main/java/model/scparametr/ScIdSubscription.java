package model.scparametr;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ScIdSubscription implements ScParameter<Integer> {
    private int id;
    final static public int SIZE = 4;

    public ScIdSubscription(int size) {
        this.id = size;
    }

    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(id);
        return buffer.array();
    }

    public int getByteSize() {
        return SIZE;
    }

    @Override
    public Integer get() {
        return id;
    }

    @Override
    public String toString() {
        return "ScIdSubscription{" +
                "id=" + id +
                '}';
    }
}
