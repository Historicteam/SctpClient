package model.scparametr;

public enum SctpCodeReturn implements ScParameter<Byte>{
    SUCCESSFUL(0x00),
    UNSUCCESSFULLY(0x01),
    NOT_FIND(0x02),
    PERMISSION_DENIED(0x03);

    byte value;

    SctpCodeReturn(int value) {
        this.value = (byte) value;
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public int getByteSize() {
        return 0;
    }

    public byte getValue() {
        return value;
    }

    public static SctpCodeReturn getByCode(byte code) {

        for (SctpCodeReturn sctpCodeReturn: values()) {
            if (sctpCodeReturn.getValue() == code) {
                return sctpCodeReturn;
            }
        }
        throw new IllegalArgumentException("Unsupported SctpCommandType");
    }

    @Override
    public Byte get() {
        return new Byte(value);
    }
}
