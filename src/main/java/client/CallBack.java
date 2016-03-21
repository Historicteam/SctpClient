package client;


import model.scresponce.SctpResponse;

public interface CallBack {
    void success(SctpResponse responce);
    void error (SctpResponse responce);
}
