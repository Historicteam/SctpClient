package model.stcprequest;


import model.scparametr.ScParameter;
import model.scparametr.SctpCommandType;

import java.util.ArrayList;
import java.util.List;

abstract public class SctpRequest {
    private SctpCommandType sctpCommandType;
    private byte flag;
    private int id;
    private int size;
    public static final int CODE_SIZE = 1;
    public static final int FLAG_SIZE = 1;
    public static final int ID_SIZE = 4;
    public static final int PARAMETRS_SIZE_SIZE = 4;
    private static int ID_GENERATE = Integer.MIN_VALUE;
    private List<ScParameter> parameters;

    public SctpRequest() {
        setFlag((byte)0);
        setId(ID_GENERATE++);
        parameters = new ArrayList<>();
    }

    public SctpCommandType getSctpCommandType() {
        return sctpCommandType;
    }

    public void setSctpCommandType(SctpCommandType sctpCommandType) {
        this.sctpCommandType = sctpCommandType;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    public List<ScParameter> getParameters() {
        return parameters;
    }

    public ScParameter getParametr(int index){
        return parameters.get(index);
    }

    public void addParameters(ScParameter parameter) {
        parameters.add(parameter);
        int size = 0;
        for (ScParameter parametr : getParameters()) {
            size = size + parametr.getByteSize();
        }
        setSize(size);
    }

    public int getByteLenght() {
        int size = CODE_SIZE + ID_SIZE + FLAG_SIZE + PARAMETRS_SIZE_SIZE;
        for (ScParameter parametr : getParameters()) {
            size = size + parametr.getByteSize();
        }
        return size;
    }

    @Override
    public String toString() {
        return "SctpRequest{" +
                "sctpCommandType=" + sctpCommandType +
                ", flag=" + flag +
                ", id=" + id +
                ", size=" + size +
                ", parameters=" + parameters +
                '}';
    }
}
