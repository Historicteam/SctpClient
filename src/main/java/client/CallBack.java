package client;


import model.scresponce.SctpResponse;
import model.stcprequest.SctpRequest;

public interface CallBack {
    void success(SctpRequest sctpRequest, SctpResponse sctpResponse);
    void unsuccess(SctpRequest sctpRequest, SctpResponse sctpResponse);
}
