package client;

import model.scresponce.SctpResponse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureImpl implements java.util.concurrent.Future<SctpResponse>{

    private SctpResponse sctpResponse;
    private boolean done;
    private final Object monitor = new Object();

    public void setSctpResponse(SctpResponse sctpResponse) {
        synchronized (monitor) {
            this.sctpResponse = sctpResponse;
            monitor.notifyAll();
        }
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return done;
    }

    public SctpResponse get() {
        if (isDone()) {
            return sctpResponse;
        }
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sctpResponse;
        }
    }

    @Override
    public SctpResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException,
            TimeoutException {
        return null;
    }


}
