package sender;


import exception.SctpException;
import model.scparametr.*;
import model.scparametr.scelementtype.*;

import java.io.IOException;
import java.time.Clock;
import java.time.Duration;

public interface SctpSender extends AutoCloseable {


    /**
     * Проверка существования элемента с указанным sc-адресом
     *
     * @param scAddress
     */
    FluentSctpResponce<SctpCodeReturn> check(ScAddress scAddress);


    /**
     * Получение типа sc-элемента по sc-адресу
     *
     * @param scAddress
     */
    FluentSctpResponce<ScElementType> type(ScAddress scAddress);


    /***
     * Удаление sc-элемента с указанным sc-адресом
     * @param scAddress
     */
    FluentSctpResponce<SctpCodeReturn> delete(ScAddress scAddress);


    /**
     * Создание нового sc-узла указанного типа
     *
     * @param scNodeType
     */
    FluentSctpResponce<ScAddress> create(ScNodeType scNodeType);

    /**
     * Создание нового sc-узла указанного типа
     *
     * @param scNode
     * @return
     */
    FluentSctpResponce<ScAddress> create(ScNode scNode);

    /**
     * Создание новой sc-ссылки
     */
    FluentSctpResponce<ScAddress> create();


    /**
     * Создание новой sc-дуги указанного типа, с указнным начальным и конечным элементами
     *
     * @param scConnectorType
     * @param scAddressFirstElement
     * @param scAddressSecondElement
     */
    FluentSctpResponce<ScAddress> create(ScConnectorType scConnectorType, ScAddress scAddressFirstElement, ScAddress scAddressSecondElement);

    /**
     * Создание новой sc-дуги указанного типа, с указнным начальным и конечным элементами
     *
     * @param scConnector
     * @param scAddressFirstElement
     * @param scAddressSecondElement
     */
    FluentSctpResponce<ScAddress> create(ScConnector scConnector, ScAddress scAddressFirstElement, ScAddress scAddressSecondElement);

    /**
     * Получение начального и конечного элемента sc-дуги
     *
     * @param scAddress
     */
    FluentSctpResponce<ScAddress[]> get(ScAddress scAddress);


    /**
     * Получение содержимого sc-ссылки
     *
     * @param scAddress
     */
    FluentSctpResponce<ScString> content(ScAddress scAddress);


    /**
     * Поиск всех sc-ссылок с указанным содержимым
     *
     * @param scString
     */
    FluentSctpResponce<ScAddress[]> serch(ScString scString);


    /**
     * Установка содержимого sc-ссылки
     *
     * @param scAddress
     * @param scString
     */
    FluentSctpResponce<SctpCodeReturn> link(ScAddress scAddress, ScString scString);


    /**
     * Создание подписки на событие
     *
     * @param scEventType
     * @param scAddress
     */
    FluentSctpResponce<ScIdSubscription> subscribe(ScEventType scEventType, ScAddress scAddress);


    /**
     * Удаление подписки на событие
     *
     * @param scIdSubscription
     */
    FluentSctpResponce<ScIdSubscription> delete(ScIdSubscription scIdSubscription);

    /**
     * Запрос произошедших событий
     */
    FluentSctpResponce<Event[]> events();


    /**
     * Поиск sc-элемента по его системному идентификатору
     *
     * @param scString
     */
    FluentSctpResponce<ScAddress> find(ScString scString);


    /**
     * Установка системного идентификатора sc-элемента
     *
     * @param scAddress
     * @param scString
     */
    FluentSctpResponce<SctpCodeReturn> set(ScAddress scAddress, ScString scString);


    /**
     * Получение статистики с сервера, в ременных границах. Время используется в формате http://en.wikipedia.org/wiki/Unix_time
     *
     * @param start
     * @param finish
     */
    FluentSctpResponce<EventTimeSignature> get(Clock start, Clock finish);

    /**
     * Получение версии протокола
     */
    FluentSctpResponce<Integer> get();

    FluentSctpResponce<Triple[]> find(ScElementType scFirstElementType, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement);

    FluentSctpResponce<Triple[]> find(ScElement scFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement);

    FluentSctpResponce<Triple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScElementType scThirdElementType);

    FluentSctpResponce<Triple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScElement scThirdElement);

    FluentSctpResponce<Triple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement);

    FluentSctpResponce<Triple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement);

    FluentSctpResponce<Quintuple[]> find(ScElementType scFirstElementType, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement, ScConnectorType scSecondConnectorType, ScElementType scFifthElementType);

    FluentSctpResponce<Quintuple[]> find(ScElement scFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement, ScConnector scSecondConnector, ScElement scFifthElement);

    FluentSctpResponce<Quintuple[]> find(ScElementType scFirstElementType, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement, ScConnectorType scSecondConnectorType, ScAddress scAddressFifthElement);

    FluentSctpResponce<Quintuple[]> find(ScElement scFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement, ScConnector scSecondConnector, ScAddress scAddressFifthElement);

    FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScElementType scThirdElementType, ScConnectorType scSecondConnectorType, ScElementType scFifthElementType);

    FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScElement scThirdElement, ScConnector scSecondConnector, ScElement scFifthElement);

    FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScAddress scAddressThirdElement, ScConnectorType scSecondConnectorType, ScElementType scFifthElementType);

    FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement, ScConnector scSecondConnector, ScElement scFifthElement);

    FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnector, ScAddress scAddressThirdElement, ScConnectorType scSecondConnector, ScAddress scAddressFifthElement);

    FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScAddress scAddressThirdElement, ScConnector scSecondConnector, ScAddress scAddressFifthElement);

    FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnectorType scFirstConnectorType, ScElementType scThirdElementType, ScConnectorType scSecondConnectorType, ScAddress scAddressFifthElement);

    FluentSctpResponce<Quintuple[]> find(ScAddress scAddressFirstElement, ScConnector scFirstConnector, ScElement scThirdElement, ScConnector scSecondConnector, ScAddress scAddressFifthElement);

    void close() throws SctpException;


    public class Triple {
        private ScAddress firstAddress;
        private ScAddress secondAddress;
        private ScAddress thirdAddress;

        public Triple(ScAddress firstAddress, ScAddress secondAddress, ScAddress thirdAddress) {
            this.firstAddress = firstAddress;
            this.secondAddress = secondAddress;
            this.thirdAddress = thirdAddress;
        }

        public ScAddress getFirstAddress() {
            return firstAddress;
        }

        public ScAddress getSecondAddress() {
            return secondAddress;
        }

        public ScAddress getThirdAddress() {
            return thirdAddress;
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "firstAddress=" + firstAddress +
                    ", secondAddress=" + secondAddress +
                    ", thirdAddress=" + thirdAddress +
                    '}';
        }
    }


    public class Quintuple {
        private ScAddress firstAddress;
        private ScAddress secondAddress;
        private ScAddress thirdAddress;
        private ScAddress fourthAddress;
        private ScAddress fifthAddress;

        public Quintuple(ScAddress firstAddress, ScAddress secondAddress, ScAddress thirdAddress, ScAddress fourthAddress, ScAddress fifthAddress) {
            this.firstAddress = firstAddress;
            this.secondAddress = secondAddress;
            this.thirdAddress = thirdAddress;
            this.fourthAddress = fourthAddress;
            this.fifthAddress = fifthAddress;
        }

        public ScAddress getFirstAddress() {
            return firstAddress;
        }

        public ScAddress getSecondAddress() {
            return secondAddress;
        }

        public ScAddress getThirdAddress() {
            return thirdAddress;
        }

        public ScAddress getFourthAddress() {
            return fourthAddress;
        }

        public ScAddress getFifthAddress() {
            return fifthAddress;
        }
    }

    public class Event {
        private ScIdSubscription scIdSubscription;
        private ScAddress scAddress;
        private ScAddress argScAddress;

        public ScIdSubscription getScIdSubscription() {
            return scIdSubscription;
        }

        public void setScIdSubscription(ScIdSubscription scIdSubscription) {
            this.scIdSubscription = scIdSubscription;
        }

        public ScAddress getScAddress() {
            return scAddress;
        }

        public void setScAddress(ScAddress scAddress) {
            this.scAddress = scAddress;
        }

        public ScAddress getArgScAddress() {
            return argScAddress;
        }

        public void setArgScAddress(ScAddress argScAddress) {
            this.argScAddress = argScAddress;
        }


    }

    public class EventTimeSignature {
        private Duration durationWork;
        private Integer scNodeNumber;
        private Integer scConnectorNumber;
        private Integer scLinkNumber;
        private Integer scEmptyCellNumber;
        private Integer scClientNumber;
        private Integer sctpCommandNumber;
        private Byte flag;
    }

}
