package sender;


import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;

public interface FluentSctpResponce<F> {
    FluentSctpResponce<F> success(CommandaWithParametr<F> commanda);

    FluentSctpResponce<F> unsuccess(Commanda commanda);

    FluentSctpResponce<F> unfound(Commanda commanda);

    FluentSctpResponce<F> unavailable(Commanda commanda);

    FluentSctpResponce<F> error(CommandaWithParametr<SctpCodeReturn> commanda);

    FluentSctpResponce<F> exception(CommandaWithParametr<Exception> commanda);

    SctpResponse getResponce();

    F getResult();
}
