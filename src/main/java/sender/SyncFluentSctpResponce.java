package sender;


import exception.SctpException;
import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;

import java.util.Optional;

public class SyncFluentSctpResponce<T> extends AbstractFluentSctpResponce<T> {

    private SctpResponse response;




    protected SyncFluentSctpResponce(SctpResponse response) {
        if (response==null){
            setException(new NullPointerException());
        }
        this.response=response;
    }




    @Override
    public FluentSctpResponce<T> success(CommandaWithParametr<T> commanda) {
        if ((getException() == null) && (response.getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
            commanda.execute(buildResponce(response));
        }
        return this;

    }

    @Override
    public FluentSctpResponce<T> unsuccess(Commanda commanda) {
        if ((getException() == null) && (response.getSctpCodeReturn() == SctpCodeReturn.UNSUCCESSFULLY)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unfound(Commanda commanda) {
        if ((getException() == null) && (response.getSctpCodeReturn() == SctpCodeReturn.NOT_FIND)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unavailable(Commanda commanda) {
        if ((getException() == null) && (response.getSctpCodeReturn() == SctpCodeReturn.PERMISSION_DENIED)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> error(CommandaWithParametr<SctpCodeReturn> commanda) {
        if ((getException() == null) && (response.getSctpCodeReturn() == SctpCodeReturn.UNSUCCESSFULLY)) {
            commanda.execute(response.getSctpCodeReturn());
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
    public Optional<T> getOptinal()  {
        if ((getException() == null) && (response.getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
            return Optional.ofNullable(buildResponce(response));
        }
        return Optional.empty();
    }

    @Override
    public T get() throws SctpException {
        if ((getException() == null) && (getResponce().getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
            return buildResponce(getResponce());
        }
        throw new SctpException(getException());
    }

    @Override
    public SctpResponse getResponce() throws SctpException {
        if (response!=null){
            return response;
        }
        else throw new SctpException(getException());
    }


}
