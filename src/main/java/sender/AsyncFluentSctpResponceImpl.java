package sender;


import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class AsyncFluentSctpResponceImpl<T> extends AbstractFluentSctpResponce<T> {
    private ScheduledExecutorService scheduledExecutorService;
    private Future<SctpResponse> response;

    public void setFutureResponce(Future<SctpResponse> response) {
        this.response = response;
    }

    public Future<SctpResponse> getFutureResponce() {
        return response;
    }

    protected AsyncFluentSctpResponceImpl(Future<SctpResponse> response) {
        this.response = response;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(3);
    }


    @Override
    public FluentSctpResponce<T> success(CommandaWithParametr<T> commanda) {
        try {
            Exception exception = scheduledExecutorService.submit(() -> {
                SctpResponse response = null;
                try {
                    response = getFutureResponce().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return e;
                }
                if ((getException() != null) && (response.getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
                    commanda.execute((T) buildResponce(response));
                }
                return null;
            }).get();
            if (exception != null) {
                setException(exception);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unsuccess(Commanda commanda) {
        try {
            Exception exception = scheduledExecutorService.submit(() -> {
                SctpResponse response = null;
                try {
                    response = getFutureResponce().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return e;
                }
                if ((getException() != null) && (response.getSctpCodeReturn() == SctpCodeReturn.UNSUCCESSFULLY)) {
                    commanda.execute();
                }
                return null;
            }).get();
            if (exception != null) {
                setException(exception);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unfound(Commanda commanda) {
        try {
            Exception exception = scheduledExecutorService.submit(() -> {
                SctpResponse response = null;
                try {
                    response = getFutureResponce().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return e;
                }
                if ((getException() != null) && (response.getSctpCodeReturn() == SctpCodeReturn.NOT_FIND)) {
                    commanda.execute();
                }
                return null;
            }).get();
            if (exception != null) {
                setException(exception);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unavailable(Commanda commanda) {
        try {
            Exception exception = scheduledExecutorService.submit(() -> {
                SctpResponse response = null;
                try {
                    response = getFutureResponce().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return e;
                }
                if ((getException() != null) && (response.getSctpCodeReturn() == SctpCodeReturn.PERMISSION_DENIED)) {
                    commanda.execute();
                }
                return null;
            }).get();
            if (exception != null) {
                setException(exception);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> error(CommandaWithParametr<SctpCodeReturn> commanda) {
        try {
            Exception exception = scheduledExecutorService.submit(() -> {
                SctpResponse response = null;
                try {
                    response = getFutureResponce().get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return e;
                }
                if ((getException() != null) && (response.getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
                    commanda.execute(getFutureResponce().get().getSctpCodeReturn());
                }
                return null;
            }).get();
            if (exception != null) {
                setException(exception);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> exception(CommandaWithParametr<Exception> commanda) {
        if (getException() != null) {
            commanda.execute(getException());
        }
        return this;
    }


    @Override
    public T get() {
        try {
            return buildResponce(getFutureResponce().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        return null;
    }

    @Override
    public SctpResponse getResponce() {
        try {
            return getFutureResponce().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        return null;
    }
}
