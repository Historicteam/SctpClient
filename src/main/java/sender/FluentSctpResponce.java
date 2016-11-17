package sender;


import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;

public interface FluentSctpResponce<F> {
    FluentSctpResponce<F> success(Commanda<F> commanda);
    FluentSctpResponce<F> error(Commanda<SctpCodeReturn> commanda);
    FluentSctpResponce<F> unsuccess();
    FluentSctpResponce<F> unfound();
    FluentSctpResponce<F> unavailable();
    SctpResponse getResponce();
    F getResult();
}
