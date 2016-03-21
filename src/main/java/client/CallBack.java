package client;


import model.scresponce.SctpResponse;
import model.stcprequest.SctpRequest;

public interface CallBack {
    void success(SctpRequest sctpRequest, SctpResponse responce);
    void error(SctpRequest sctpRequest, SctpResponse responce);
}
