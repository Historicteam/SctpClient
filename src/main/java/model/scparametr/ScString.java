package model.scparametr;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ScString implements ScParameter<String> {

    private String content;

    public ScString(String content) {

        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }


    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(content.getBytes().length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(content.getBytes());
        return buffer.array();
    }


    public int getByteSize() {
        return content.getBytes().length;
    }

    @Override
    public String get() {
        return content;
    }

    @Override
    public String toString() {
        return "ScString{" +
                "content='" + content + '\'' +
                '}';
    }
}
