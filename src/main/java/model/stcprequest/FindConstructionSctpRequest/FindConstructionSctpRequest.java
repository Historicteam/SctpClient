package model.stcprequest.FindConstructionSctpRequest;


import model.scparametr.ScParameter;
import model.scparametr.SctpCommandType;
import model.stcprequest.SctpRequest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

abstract public class FindConstructionSctpRequest extends SctpRequest {
    public FindConstructionSctpRequest() {
        super();
        setSctpCommandType(SctpCommandType.FIND_CONSTRUCTIONS_COMMAND);
    }

    protected enum PatternType implements ScParameter<Byte> {
        SCTP_ITERATOR_3F_A_A(0),
        SCTP_ITERATOR_3_A_A_F(1),
        SCTP_ITERATOR_3F_A_F(2),
        SCTP_ITERATOR_5F_A_A_A_F(3),
        SCTP_ITERATOR_5_A_A_F_A_F(4),
        SCTP_ITERATOR_5_F_A_F_A_F(5),
        SCTP_ITERATOR_5_F_A_F_A_A(6),
        SCTP_ITERATOR_5_F_A_A_A_A(7),
        SCTP_ITERATOR_5_A_A_F_A_A(8);

        private byte value;
        private int size = 1;


        private PatternType(int value) {

            this.value = (byte) value;
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
            return new Byte(value);
        }
    }
}
