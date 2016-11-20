package sender;


import exception.SctpException;
import model.scparametr.SctpCodeReturn;
import model.scresponce.SctpResponse;

import java.util.Optional;

public interface FluentSctpResponce<F> {

    FluentSctpResponce<F> success(CommandaWithParametr<F> commanda);

    FluentSctpResponce<F> unsuccess(Commanda commanda);

    FluentSctpResponce<F> unfound(Commanda commanda);

    FluentSctpResponce<F> unavailable(Commanda commanda);

    FluentSctpResponce<F> error(CommandaWithParametr<SctpCodeReturn> commanda);

    FluentSctpResponce<F> exception(CommandaWithParametr<Exception> commanda);

    Optional<F> getOptinal();

    F get() throws SctpException;

    SctpResponse getResponce() throws SctpException;
}
