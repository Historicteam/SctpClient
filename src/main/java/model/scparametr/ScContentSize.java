
package model.scparametr;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class ScContentSize implements ScParameter<Integer> {

    private int size;
    final static public int SIZE = 4;


    public ScContentSize(int size) {
        this.size = size;
    }

    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(size);
        return buffer.array();
    }

    public int getByteSize() {
        return SIZE;
    }

    @Override
    public Integer get() {
        return size;
    }


    @Override
    public String toString() {
        return "ScContentSize{" +
                "size=" + size +
                '}';
    }
}
