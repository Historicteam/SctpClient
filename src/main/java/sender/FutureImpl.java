package sender;


import model.scresponce.SctpResponse;

import java.util.concurrent.*;

public class FutureImpl implements Future<SctpResponse> {
    private boolean done = false;
    private boolean cancel = false;
    private SctpResponse sctpResponse;
    private Exchanger<SctpResponse> exchanger;

    public FutureImpl(Exchanger<SctpResponse> exchanger) {
        this.exchanger = exchanger;
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public SctpResponse get() throws InterruptedException, ExecutionException {
        sctpResponse = exchanger.exchange(new SctpResponse());
        done=true;
        return sctpResponse;
    }

    @Override
    public SctpResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        sctpResponse = exchanger.exchange(new SctpResponse(), timeout, unit);
        if (sctpResponse!=null){
            done=true;
        } else {
            cancel=true;
        }
        return sctpResponse;
    }
}
