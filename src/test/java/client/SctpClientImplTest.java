package client;

import model.scparametr.ScAddress;
import model.scparametr.ScEventType;
import model.scparametr.ScString;
import model.scparametr.scelementtype.*;
import model.scresponce.SctpResponse;
import model.stcprequest.*;
import model.stcprequest.FindConstructionSctpRequest.FindConstruction5f_a_a_a_fSctpRequest;
import org.apache.log4j.Logger;
import org.junit.*;

import java.io.IOException;

public class SctpClientImplTest extends Assert {

    public static final Logger LOG = Logger.getLogger(SctpClientImplTest.class);

    private ConsSctpClient sctpClient;

    @Before
    public void setUp() throws IOException {
        sctpClient = new ConsSctpClientImpl("192.168.50.154", 55770);
        LOG.info(sctpClient);
    }

    @After
    public void tearDown() throws IOException {
        sctpClient.close();
        LOG.info(sctpClient);
    }

    @Test
    public void testCreateElement() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(createNodeSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testCheckElement() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        LOG.info(sctpResponse);
        SctpRequest checkElementExistenceSctpRequest = new CheckElementExistenceSctpRequest((ScAddress) sctpResponse
                .getParametr(0));
        LOG.info(checkElementExistenceSctpRequest);
        sctpResponse = sctpClient.execute(checkElementExistenceSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(checkElementExistenceSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());

    }

    @Test
    public void testGetTypeElement() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new
                ScClassNodeType(new ScNodeType())));
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        LOG.info(sctpResponse);
        SctpRequest getElementTypeStcpRequest = new GetElementTypeStcpRequest((ScAddress) sctpResponse.getParametr(0));
        LOG.info(getElementTypeStcpRequest);
        sctpResponse = sctpClient.execute(getElementTypeStcpRequest);
        assertEquals(getElementTypeStcpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        LOG.info(sctpResponse);
    }


    @Test
    public void testSetIdentifier() throws IOException {
        //Тест не работает потому что запрос setIdentifierSctpRequest возврощает ответ не соответсвующий спецификации, а именно тип возврощаемой команды
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new
                ScClassNodeType(new ScNodeType())));
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        SctpRequest setIdentifierSctpRequest = new SetIdentifierSctpRequest((ScAddress) sctpResponse.getParametr(0),
                new ScString("Pushkin"));
        LOG.info(setIdentifierSctpRequest);
        sctpResponse = sctpClient.execute(setIdentifierSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(setIdentifierSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testGetElementById() throws IOException {
        //Первый раз не срабатывает скорей всего потому что не правильно работает setIdentifierSctpRequest запрос
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScNodeType());
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        LOG.info(sctpResponse);
        ScAddress scAddressNode = (ScAddress) sctpResponse.getParametr(0);
        ScString scString = new ScString("Pushkin");
        SctpRequest setIdentifierSctpRequest = new SetIdentifierSctpRequest(scAddressNode, scString);
        LOG.info(setIdentifierSctpRequest);
        sctpClient.execute(setIdentifierSctpRequest);
        SctpRequest getElementByIdSctpRequest = new GetElementByIdSctpRequest(new ScString("Pushkin"));
        LOG.info(getElementByIdSctpRequest);
        sctpResponse = sctpClient.execute(getElementByIdSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getElementByIdSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testCreateLink() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        LOG.info(createLinkSctpReques);
        SctpResponse sctpResponse = sctpClient.execute(createLinkSctpReques);
        LOG.info(sctpResponse);
        assertEquals(createLinkSctpReques.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testSetLinkContent() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        LOG.info(createLinkSctpReques);
        SctpResponse sctpResponse = sctpClient.execute(createLinkSctpReques);
        LOG.info(sctpResponse);
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest((ScAddress) sctpResponse.getParametr(0)
                , new ScString("Test"));
        LOG.info(setLinkContentSctpRequest);
        sctpResponse = sctpClient.execute(setLinkContentSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(setLinkContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testGetLinkContent() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        LOG.info(createLinkSctpReques);
        SctpResponse sctpResponse = sctpClient.execute(createLinkSctpReques);
        LOG.info(sctpResponse);
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest((ScAddress) sctpResponse.getParametr(0)
                , new ScString("Test"));
        LOG.info(setLinkContentSctpRequest);
        SctpResponse sctpResponse2 = sctpClient.execute(setLinkContentSctpRequest);
        LOG.info(sctpResponse2);
        SctpRequest getLinkContentSctpRequest = new GetLinkContentSctpRequest((ScAddress) sctpResponse.getParametr(0));
        LOG.info(getLinkContentSctpRequest);
        sctpResponse = sctpClient.execute(getLinkContentSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getLinkContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testGetLinksByContent() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        LOG.info(createLinkSctpReques);
        SctpResponse sctpResponse = sctpClient.execute(createLinkSctpReques);
        LOG.info(sctpResponse);
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest((ScAddress) sctpResponse.getParametr(0)
                , new ScString("MIHAS"));
        LOG.info(setLinkContentSctpRequest);
        sctpResponse = sctpClient.execute(setLinkContentSctpRequest);
        LOG.info(sctpResponse);
        SctpRequest getLinksByContentSctpRequest = new GetLinksByContentSctpRequest(new ScString("MIHAS"));
        LOG.info(getLinksByContentSctpRequest);
        sctpResponse = sctpClient.execute(getLinksByContentSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getLinksByContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testCreateArc() throws IOException {
        //Тест может не пройти в зависимости от использованной версии sc-machine. В некоторых версиях ответ на запрос CreateArcSctpRequest не соответсвует спецификации, а именно не возврощаеться адрес созданного элемента
        CreateNodeSctpRequest createFirstNodeSctpRequest = new CreateNodeSctpRequest(new
                ScNodeType
                ());
        LOG.info(createFirstNodeSctpRequest);
        SctpResponse firstSctpResponse = sctpClient.execute(createFirstNodeSctpRequest);
        LOG.info(firstSctpResponse);
        CreateNodeSctpRequest createSecondNodeSctpRequest = new CreateNodeSctpRequest(new
                ScNodeType
                ());
        LOG.info(createSecondNodeSctpRequest);
        SctpResponse secondSctpResponse = sctpClient.execute(createSecondNodeSctpRequest);
        LOG.info(secondSctpResponse);
        SctpRequest createArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), (ScAddress)
                firstSctpResponse.getParametr(0)
                , (ScAddress) secondSctpResponse.getParametr(0));
        LOG.info(createArcSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createArcSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(createArcSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testGetArcElements() throws IOException {
        CreateNodeSctpRequest createFirstNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new
                ScNodeType
                ()));
        LOG.info(createFirstNodeSctpRequest);
        SctpResponse firstSctpResponse = sctpClient.execute(createFirstNodeSctpRequest);
        CreateNodeSctpRequest createSecondNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new
                ScNodeType
                ()));
        LOG.info(firstSctpResponse);
        SctpResponse secondSctpResponse = sctpClient.execute(createSecondNodeSctpRequest);
        LOG.info(secondSctpResponse);
        SctpRequest createArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), (ScAddress)
                firstSctpResponse.getParametr(0)
                , (ScAddress) secondSctpResponse.getParametr(0));
        LOG.info(createArcSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createArcSctpRequest);
        LOG.info(sctpResponse);
        SctpRequest getArcElementsSctpRequest = new GetArcVertexesSctpRequest((ScAddress) sctpResponse.getParametr(0));
        LOG.info(getArcElementsSctpRequest);
        sctpResponse = sctpClient.execute(getArcElementsSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getArcElementsSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    @Test
    public void testMakeSubscription() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        LOG.info(sctpResponse);
        SctpRequest makeSubscriptionSctpRequest = new MakeSubscriptionSctpRequest(ScEventType
                .SC_EVENT_REMOVE_ELEMENT, (ScAddress) sctpResponse.getParametr(0));
        LOG.info(makeSubscriptionSctpRequest);
        sctpResponse = sctpClient.execute(makeSubscriptionSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(makeSubscriptionSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());

    }


    @Test
    public void testDeleteElement() throws IOException {
        //Тест не работает потому что запрос deleteElementSctpRequest возврощает ответ не соответсвующий спецификации, а именно тип возврощаемой команды
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        LOG.info(sctpResponse);
        SctpRequest deleteElementSctpRequest = new DeleteElementSctpRequest((ScAddress) sctpResponse.getParametr(0));
        LOG.info(deleteElementSctpRequest);
        sctpResponse = sctpClient.execute(deleteElementSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(deleteElementSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }


    public void testGetEvent() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        LOG.info(sctpResponse);
        SctpRequest makeSubscriptionSctpRequest = new MakeSubscriptionSctpRequest(ScEventType
                .SC_EVENT_REMOVE_ELEMENT, (ScAddress) sctpResponse.getParametr(0));
        LOG.info(makeSubscriptionSctpRequest);
        sctpResponse = sctpClient.execute(makeSubscriptionSctpRequest);
        LOG.info(sctpResponse);
        SctpRequest getEventSctpRequest = new GetEventSctpRequest();
        LOG.info(getEventSctpRequest);
        sctpResponse = sctpClient.execute(getEventSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getEventSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());

    }

    @Test
    public void testFindConstruction5f_a_a_a_f() throws IOException {
        //TODO не работает
        CreateNodeSctpRequest createFirstNodeSctpRequest = new CreateNodeSctpRequest(new ScNodeType());
        LOG.info(createFirstNodeSctpRequest);
        SctpResponse firstSctpResponse = sctpClient.execute(createFirstNodeSctpRequest);
        LOG.info(firstSctpResponse);
        CreateNodeSctpRequest createSecondNodeSctpRequest = new CreateNodeSctpRequest(new ScNodeType());
        LOG.info(createSecondNodeSctpRequest);
        SctpResponse secondSctpResponse = sctpClient.execute(createSecondNodeSctpRequest);
        LOG.info(secondSctpResponse);
        CreateNodeSctpRequest createThirdNodeSctpRequest = new CreateNodeSctpRequest(new ScNodeType());
        LOG.info(createThirdNodeSctpRequest);
        SctpResponse thirdSctpResponse = sctpClient.execute(createThirdNodeSctpRequest);
        LOG.info(thirdSctpResponse);
        SctpRequest createFirstArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), (ScAddress)
                firstSctpResponse.getParametr(0), (ScAddress) secondSctpResponse.getParametr(0));
        LOG.info(createFirstArcSctpRequest);
        SctpResponse sctpFirstArcResponse = sctpClient.execute(createFirstArcSctpRequest);
        LOG.info(sctpFirstArcResponse);
        SctpRequest createSecondArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), (ScAddress)
                thirdSctpResponse.getParametr(0), (ScAddress) sctpFirstArcResponse.getParametr(0));
        LOG.info(createSecondArcSctpRequest);
        SctpResponse sctpSecondArcResponse = sctpClient.execute(createSecondArcSctpRequest);
        LOG.info(sctpSecondArcResponse);
        SctpRequest findConstruction5f_a_a_a_fSctpRequest = new FindConstruction5f_a_a_a_fSctpRequest((ScAddress)
                firstSctpResponse.getParametr(0), new ScArcCommonType(), new ScNodeType(), new ScArcAccessType(),
                (ScAddress) thirdSctpResponse.getParametr(0));
        LOG.info(findConstruction5f_a_a_a_fSctpRequest);
        SctpResponse sctpResponse = sctpClient.execute(findConstruction5f_a_a_a_fSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(findConstruction5f_a_a_a_fSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
    }

    public static class CallBackImpl implements CallBack {
        private SctpResponse response;

        public void success(SctpRequest sctpRequest, SctpResponse responce) {
            this.response = responce;
            System.out.println(responce);
            System.out.println("Успешно");
        }

        public void unsuccess(SctpRequest sctpRequest, SctpResponse responce) {
            this.response = responce;
            System.out.println(responce);
            System.out.println("Ошибка");
        }

        public SctpResponse getResponse() {
            return response;
        }
    }
}