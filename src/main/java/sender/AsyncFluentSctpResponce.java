package sender;


import exception.SctpException;
import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class AsyncFluentSctpResponce<T> extends AbstractFluentSctpResponce<T> {
    private ScheduledExecutorService scheduledExecutorService;
    private Future<SctpResponse> futureResponse;

    public void setFutureResponce(Future<SctpResponse> response) {
        this.futureResponse = response;
    }


    public Future<SctpResponse> getFutureResponce() {
        return futureResponse;
    }



    protected AsyncFluentSctpResponce(Future<SctpResponse> futureResponse) {
        this.futureResponse = futureResponse;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
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
    public Optional<T> getOptinal() {
        try {
            if ((getException() == null) && (getResponce().getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
                return Optional.ofNullable(buildResponce(getFutureResponce().get()));
            }
        } catch (InterruptedException | ExecutionException | SctpException e) {
            e.printStackTrace();
            setException(e);
        }
        return Optional.empty();
    }

    @Override
    public T get() throws SctpException {
        try {
            if ((getException() == null) && (getResponce().getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
                return buildResponce(getFutureResponce().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        throw new SctpException(getException());
    }


    @Override
    public SctpResponse getResponce() throws SctpException {
        SctpResponse response =null;
        try {
            response = getFutureResponce().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            setException(e);
        }
        if (response!=null){
            return response;
        }
        throw new SctpException(getException());
    }
}
