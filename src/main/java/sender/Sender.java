package sender;


import model.scparametr.*;
import model.scparametr.scelementtype.ScConnectorType;
import model.scparametr.scelementtype.ScNodeType;

import java.time.Clock;
import java.time.Duration;

public interface Sender {

    /**
     * Конфигурирует
     * @param host
     * @param port
     */
    Sender configure(String host, Integer port);

    /**
     * Проверка существования элемента с указанным sc-адресом
     * @param scAddress
     */
    FluentSctpResponce<SctpCodeReturn> check(ScAddress scAddress);


    /**
     * Получение типа sc-элемента по sc-адресу
     * @param scAddress
     */
    FluentSctpResponce<ScNodeType> getType(ScAddress scAddress);


    /***
     * Удаление sc-элемента с указанным sc-адресом
     * @param scAddress
     */
    FluentSctpResponce<SctpCodeReturn> delete(ScAddress scAddress);


    /**
     * Создание нового sc-узла указанного типа
     * @param scNodeType
     */
    FluentSctpResponce<ScAddress> create(ScNodeType scNodeType);

    /**
     * Создание новой sc-ссылки
     */
    FluentSctpResponce<ScAddress> create();



    /**
     * Создание новой sc-дуги указанного типа, с указнным начальным и конечным элементами
     * @param scConnector
     * @param scAddressFirstElement
     * @param scAddressSecondElement
     */
    FluentSctpResponce<ScAddress> create(ScConnectorType scConnector, ScAddress scAddressFirstElement, ScAddress scAddressSecondElement);


    /**
     * Получение начального и конечного элемента sc-дуги
     * @param scAddress
     */
    FluentSctpResponce<ScAddress[]> get(ScAddress scAddress);


    /**
     * Получение содержимого sc-ссылки
     * @param scAddress
     */
    FluentSctpResponce<ScString>  getContent(ScAddress scAddress);


    /**
     * Поиск всех sc-ссылок с указанным содержимым
     * @param scString
     */
    FluentSctpResponce<ScAddress[]> findLink(ScString scString);


    /**
     * Установка содержимого sc-ссылки
     * @param scAddress
     * @param scString
     */
    FluentSctpResponce<SctpCodeReturn> setLinkContent(ScAddress scAddress, ScString scString);



    /**
     * Создание подписки на событие
     * @param scEventType
     * @param scAddress
     */
    FluentSctpResponce<ScIdSubscription> subscribe(ScEventType scEventType, ScAddress scAddress);


    /**
     * Удаление подписки на событие
     * @param scIdSubscription
     */
    FluentSctpResponce<ScIdSubscription> delete(ScIdSubscription scIdSubscription);

    /**
     * Запрос произошедших событий
     */
    FluentSctpResponce<Event[]> getEvent();


    /**
     * Поиск sc-элемента по его системному идентификатору
     * @param scString
     */
    FluentSctpResponce<ScAddress> find(ScString scString);


    /**
     * Установка системного идентификатора sc-элемента
     * @param scAddress
     * @param scString
     */
    FluentSctpResponce<SctpCodeReturn> set(ScAddress scAddress, ScString scString);


    /**
     * Получение статистики с сервера, в ременных границах. Время используется в формате http://en.wikipedia.org/wiki/Unix_time
     * @param start
     * @param finish
     */
    FluentSctpResponce<EventTimeSignature> get(Clock start, Clock finish);

    /**
     * Получение версии протокола
     */
    FluentSctpResponce<Integer> get();

    class Event {
        private ScIdSubscription scIdSubscription;
        private ScAddress scAddress;
        private ScAddress argScAddress;
    }

    class EventTimeSignature {
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
