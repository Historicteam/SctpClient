package model.scparametr;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;



public class ScAddress implements ScParameter<Integer> {

    private int address;

    private final static int SIZE=4;

    @Deprecated
    public ScAddress(short segment, short offset) {
        int temp = (int)segment;
        temp =temp<<16;
        temp = temp |(int)offset;
        this.address = temp;
    }


    public ScAddress(int address) {
        this.address=address;
    }




    @Deprecated
    public short getOffset() {
        int mask = 0b11111111;
        return (short)(mask & address);
    }
    @Deprecated
    public short getSegment() {
        int mask = 0b11111111;
        return (short)(mask & address>>8);
    }




    public byte[] getBytes() {
        ByteBuffer tempBuffer = ByteBuffer.allocate(SIZE);
        tempBuffer.order(ByteOrder.LITTLE_ENDIAN);
        tempBuffer.putInt(address);
        return tempBuffer.array();
    }


    public int getByteSize() {

        return SIZE;
    }

    @Override
    public Integer get() {
        return address;
    }


    @Override
    public String toString() {
        return "ScAddress{" +
                "address=" + address +
                '}';
    }
}
