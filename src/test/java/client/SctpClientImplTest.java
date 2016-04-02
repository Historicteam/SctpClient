package client;

import model.scparametr.ScAddress;
import model.scparametr.ScEventType;
import model.scparametr.ScString;
import model.scparametr.scelementtype.*;
import model.scresponce.SctpResponse;
import model.stcprequest.*;
import model.stcprequest.FindConstructionSctpRequest.FindConstruction5f_a_a_a_fSctpRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class SctpClientImplTest extends Assert {

    private ConsSctpClient sctpClient;

    @Before
    public void setUp() throws Exception {
        sctpClient = new ConsSctpClientImpl("192.168.50.154", 55770);
    }

    @After
    public void tearDown() throws Exception {
        sctpClient.close();
    }

    @Test
    public void testCreateElement() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        assertEquals(createNodeSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(createNodeSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }

    @Test
    public void testCheckElement() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        SctpRequest checkElementExistenceSctpRequest = new CheckElementExistenceSctpRequest((ScAddress) sctpResponse
                .getParametr(0));
        sctpResponse = sctpClient.execute(checkElementExistenceSctpRequest);
        assertEquals(checkElementExistenceSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(checkElementExistenceSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }

    @Test
    public void testGetTypeElement() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new
                ScClassNodeType(new ScNodeType())));
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        SctpRequest getElementTypeStcpRequest = new GetElementTypeStcpRequest((ScAddress) sctpResponse.getParametr(0));
        sctpResponse = sctpClient.execute(getElementTypeStcpRequest);
        assertEquals(getElementTypeStcpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(getElementTypeStcpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }

    @Test
    public void testSetIdentifier() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new
                ScClassNodeType(new ScNodeType())));
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        SctpRequest setIdentifierSctpRequest = new SetIdentifierSctpRequest((ScAddress) sctpResponse.getParametr(0),
                new ScString("Pushkin"));
        sctpResponse = sctpClient.execute(setIdentifierSctpRequest);
        System.out.println(setIdentifierSctpRequest);
        System.out.println(sctpResponse);
        assertEquals(setIdentifierSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println("---------//-----------");
    }

    @Test
    public void testGetElementById() throws IOException {
        SctpRequest getElementByIdSctpRequest = new GetElementByIdSctpRequest(new ScString("Pushkin"));
        SctpResponse sctpResponse = sctpClient.execute(getElementByIdSctpRequest);
        assertEquals(getElementByIdSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(getElementByIdSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }

    @Test
    public void testCreateLink() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        SctpResponse sctpResponse = sctpClient.execute(createLinkSctpReques);
        assertEquals(createLinkSctpReques.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(createLinkSctpReques);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");

    }

    @Test
    public void testSetLinkContent() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        SctpResponse sctpResponse = sctpClient.execute(createLinkSctpReques);
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest((ScAddress) sctpResponse.getParametr(0)
                , new ScString("Test"));
        sctpResponse = sctpClient.execute(setLinkContentSctpRequest);
        assertEquals(setLinkContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(setLinkContentSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }

    @Test
    public void testGetLinkContent() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        SctpResponse sctpResponse = sctpClient.execute(createLinkSctpReques);
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest((ScAddress) sctpResponse.getParametr(0)
                , new ScString("Test"));
        SctpResponse sctpResponse2 = sctpClient.execute(setLinkContentSctpRequest);
        SctpRequest getLinkContentSctpRequest = new GetLinkContentSctpRequest((ScAddress) sctpResponse.getParametr(0));
        sctpResponse = sctpClient.execute(getLinkContentSctpRequest);
        assertEquals(getLinkContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(getLinkContentSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");

    }

    @Test
    public void testGetLinksByContent() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        SctpResponse sctpResponse = sctpClient.execute(createLinkSctpReques);
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest((ScAddress) sctpResponse.getParametr(0)
                , new ScString("MIHAS"));
        sctpResponse = sctpClient.execute(setLinkContentSctpRequest);
        SctpRequest getLinksByContentSctpRequest = new GetLinksByContentSctpRequest(new ScString("MIHAS"));
        sctpResponse = sctpClient.execute(getLinksByContentSctpRequest);
        assertEquals(getLinksByContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(getLinksByContentSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }

    @Test
    public void testCreateArc() throws IOException {
        CreateNodeSctpRequest createFirstNodeSctpRequest = new CreateNodeSctpRequest(new
                ScNodeType
                ());
        SctpResponse firstSctpResponse = sctpClient.execute(createFirstNodeSctpRequest);
        CreateNodeSctpRequest createSecondNodeSctpRequest = new CreateNodeSctpRequest(new
                ScNodeType
                ());
        SctpResponse secondSctpResponse = sctpClient.execute(createSecondNodeSctpRequest);
        SctpRequest createArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), (ScAddress)
                firstSctpResponse.getParametr(0)
                , (ScAddress) secondSctpResponse.getParametr(0));
        SctpResponse sctpResponse = sctpClient.execute(createArcSctpRequest);
        assertEquals(createArcSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(createArcSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");

    }

    @Test
    public void testGetArcElements() throws IOException {
        CreateNodeSctpRequest createFirstNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new
                ScNodeType
                ()));
        SctpResponse firstSctpResponse = sctpClient.execute(createFirstNodeSctpRequest);
        CreateNodeSctpRequest createSecondNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new
                ScNodeType
                ()));
        SctpResponse secondSctpResponse = sctpClient.execute(createSecondNodeSctpRequest);
        SctpRequest createArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), (ScAddress)
                firstSctpResponse.getParametr(0)
                , (ScAddress) secondSctpResponse.getParametr(0));
        SctpResponse sctpResponse = sctpClient.execute(createArcSctpRequest);
        SctpRequest getArcElementsSctpRequest = new GetArcVertexesSctpRequest((ScAddress) sctpResponse.getParametr(0));
        sctpResponse = sctpClient.execute(getArcElementsSctpRequest);
        assertEquals(getArcElementsSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(getArcElementsSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }

    @Test
    public void testMakeSubscription() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        SctpRequest makeSubscriptionSctpRequest = new MakeSubscriptionSctpRequest(ScEventType
                .SC_EVENT_REMOVE_ELEMENT, (ScAddress) sctpResponse.getParametr(0));
        sctpResponse = sctpClient.execute(makeSubscriptionSctpRequest);
        assertEquals(makeSubscriptionSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(makeSubscriptionSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }

    @Test
    public void testDeleteElement() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        SctpRequest deleteElementSctpRequest = new DeleteElementSctpRequest((ScAddress) sctpResponse.getParametr(0));
        sctpResponse = sctpClient.execute(deleteElementSctpRequest);
        System.out.println(deleteElementSctpRequest);
        System.out.println(sctpResponse);
        assertEquals(deleteElementSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println("---------//-----------");
    }


    public void testGetEvent() throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType
                ()));
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        SctpRequest makeSubscriptionSctpRequest = new MakeSubscriptionSctpRequest(ScEventType
                .SC_EVENT_REMOVE_ELEMENT, (ScAddress) sctpResponse.getParametr(0));
        sctpResponse = sctpClient.execute(makeSubscriptionSctpRequest);
        System.out.println(makeSubscriptionSctpRequest);
        SctpRequest getEventSctpRequest = new GetEventSctpRequest();
        sctpResponse = sctpClient.execute(getEventSctpRequest);
        assertEquals(getEventSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(getEventSctpRequest);
        System.out.println(sctpResponse);
        System.out.println("---------//-----------");
    }
    @Test
    public void testFindConstruction5f_a_a_a_f() throws IOException {
        //TODO не работает
        CreateNodeSctpRequest createFirstNodeSctpRequest = new CreateNodeSctpRequest(new ScNodeType());
        SctpResponse firstSctpResponse = sctpClient.execute(createFirstNodeSctpRequest);
        CreateNodeSctpRequest createSecondNodeSctpRequest = new CreateNodeSctpRequest(new ScNodeType());
        SctpResponse secondSctpResponse = sctpClient.execute(createSecondNodeSctpRequest);
        CreateNodeSctpRequest createThirdNodeSctpRequest = new CreateNodeSctpRequest(new ScNodeType());
        SctpResponse thirdSctpResponse = sctpClient.execute(createThirdNodeSctpRequest);
        SctpRequest createFirstArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), (ScAddress)firstSctpResponse.getParametr(0), (ScAddress) secondSctpResponse.getParametr(0));
        SctpResponse sctpFirstArcResponse = sctpClient.execute(createFirstArcSctpRequest);
        SctpRequest createSecondArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), (ScAddress)thirdSctpResponse.getParametr(0), (ScAddress) sctpFirstArcResponse.getParametr(0));
        SctpResponse sctpSecondArcResponse = sctpClient.execute(createSecondArcSctpRequest);
        SctpRequest findConstruction5f_a_a_a_fSctpRequest = new FindConstruction5f_a_a_a_fSctpRequest((ScAddress)firstSctpResponse.getParametr(0), new ScArcCommonType(), new ScNodeType(), new ScArcAccessType(), (ScAddress) thirdSctpResponse.getParametr(0));
        SctpResponse sctpResponse = sctpClient.execute(findConstruction5f_a_a_a_fSctpRequest);
        assertEquals(findConstruction5f_a_a_a_fSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        System.out.println(findConstruction5f_a_a_a_fSctpRequest);
        System.out.println(sctpResponse);
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