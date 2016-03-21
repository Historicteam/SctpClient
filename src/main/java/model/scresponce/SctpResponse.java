package model.scresponce;


import model.scparametr.ScParameter;
import model.scparametr.SctpCodeReturn;
import model.scparametr.SctpCommandType;

import java.util.ArrayList;
import java.util.List;

public class SctpResponse {

    public static final int CODE_SIZE = 1;
    public static final int ID_SIZE = 4;
    public static final int RETURN_CODE_SIZE = 1;
    public static final int PARAMETRS_SIZE_SIZE = 4;


    private SctpCommandType sctpCommandType;
    private int id;
    private SctpCodeReturn sctpCodeReturn;
    private int size;
    private List<ScParameter> parameters;

    public SctpResponse() {
        parameters = new ArrayList<>();
    }

    public SctpCommandType getSctpCommandType() {
        return sctpCommandType;
    }

    public void setSctpCommandType(SctpCommandType sctpCommandType) {
        this.sctpCommandType = sctpCommandType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SctpCodeReturn getSctpCodeReturn() {
        return sctpCodeReturn;
    }

    public void setSctpCodeReturn(SctpCodeReturn sctpCodeReturn) {
        this.sctpCodeReturn = sctpCodeReturn;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ScParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScParameter> parameters) {
        this.parameters = parameters;
    }

    public int getByteLenght() {
        int size = CODE_SIZE + ID_SIZE + RETURN_CODE_SIZE + PARAMETRS_SIZE_SIZE;
        for (ScParameter parametr : getParameters()) {
            size = size + parametr.getByteSize();
        }
        return size;
    }

    public ScParameter getParametr(int index){
        return parameters.get(index);
    }

    @Override
    public String toString() {
        return "SctpResponse{" +
                "sctpCommandType=" + sctpCommandType +
                ", id=" + id +
                ", sctpCodeReturn=" + sctpCodeReturn +
                ", size=" + size +
                ", parameters=" + parameters +
                '}';
    }




}
