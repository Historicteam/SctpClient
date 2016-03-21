package model.scparametr;

public interface ScParameter<T> {


    byte[] getBytes();

    int getByteSize();

    T get();


}
