package model.stcprequest;


import model.scparametr.ScAddress;
import model.scparametr.SctpCommandType;

/**
 * Проверка существования элемента с указанным sc-адресом
 */
public class CheckElementExistenceSctpRequest extends SctpRequest {
    /**
     * @param scAddress sc-адрес проверяемого sc-элемента
     */
    public CheckElementExistenceSctpRequest(ScAddress scAddress) {
        super();
        setSctpCommandType(SctpCommandType.CHECK_ELEMENT_COMMAND);
        addParameters(scAddress);
    }

}
