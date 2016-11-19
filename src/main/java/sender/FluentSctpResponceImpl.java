package sender;


import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;

public class FluentSctpResponceImpl<T> extends AbstractFluentSctpResponce<T> {

    private SctpResponse response;


    public SctpResponse getResponce() {
        return response;
    }



    protected FluentSctpResponceImpl(SctpResponse response) {
        if (response==null){
            setException(new NullPointerException());
        }
        this.response=response;
    }



    @Override
    public FluentSctpResponce<T> success(CommandaWithParametr<T> commanda) {
        if ((getException() == null) && (getResponce().getSctpCodeReturn() == SctpCodeReturn.SUCCESSFUL)) {
            commanda.execute((T) buildResponce(response));
        }
        return this;

    }

    @Override
    public FluentSctpResponce<T> unsuccess(Commanda commanda) {
        if ((getException() == null) && (getResponce().getSctpCodeReturn() == SctpCodeReturn.UNSUCCESSFULLY)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unfound(Commanda commanda) {
        if ((getException() == null) && (getResponce().getSctpCodeReturn() == SctpCodeReturn.NOT_FIND)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> unavailable(Commanda commanda) {
        if ((getException() == null) && (getResponce().getSctpCodeReturn() == SctpCodeReturn.PERMISSION_DENIED)) {
            commanda.execute();
        }
        return this;
    }

    @Override
    public FluentSctpResponce<T> error(CommandaWithParametr<SctpCodeReturn> commanda) {
        if ((getException() == null) && (getResponce().getSctpCodeReturn() == SctpCodeReturn.UNSUCCESSFULLY)) {
            commanda.execute(getResponce().getSctpCodeReturn());
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
    public T get()  {
        return buildResponce(getResponce());
    }




}
