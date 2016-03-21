package client;

import model.scparametr.ScAddress;
import model.scparametr.ScEventType;
import model.scparametr.ScString;
import model.scparametr.scelement.ScArcAccessType;
import model.scparametr.scelement.ScArcCommonType;
import model.scparametr.scelement.ScMaterialNodeType;
import model.scparametr.scelement.ScNodeType;
import model.scresponce.SctpResponse;
import model.stcprequest.*;
import model.stcprequest.FindConstructionSctpRequest.FindConstruction5f_a_a_a_fSctpRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SctpClientImplTest extends Assert {

    private SctpClient sctpClient;

    @Before
    public void setUp() throws Exception {
        sctpClient = new SctpClientImpl("192.168.50.154", 55770);
    }

    @After
    public void tearDown() throws Exception {
        sctpClient.close();
    }



    @Test
    public void testExecute() throws Exception {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(new ScMaterialNodeType(new ScNodeType()));
        SctpResponse sctpResponse = sctpClient.execute(createNodeSctpRequest);
        System.out.println(createNodeSctpRequest);
        System.out.println(sctpResponse);
        Integer addressNode = (Integer) sctpResponse.getParametr(0).get();
        SctpRequest checkElementExistenceSctpRequest = new CheckElementExistenceSctpRequest((ScAddress) sctpResponse.getParametr(0));
        sctpResponse = sctpClient.execute(checkElementExistenceSctpRequest);
        System.out.println(checkElementExistenceSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest getElementTypeStcpRequest = new GetElementTypeStcpRequest(new ScAddress(addressNode));
        sctpResponse = sctpClient.execute(getElementTypeStcpRequest);
        System.out.println(getElementTypeStcpRequest);
        System.out.println(sctpResponse);
        SctpRequest setIdentifierSctpRequest = new SetIdentifierSctpRequest(new ScAddress(addressNode), new ScString("test"));
        sctpResponse = sctpClient.execute(setIdentifierSctpRequest);
        System.out.println(setIdentifierSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest getElementByIdSctpRequest = new GetElementByIdSctpRequest(new ScString("test"));
        sctpResponse = sctpClient.execute(getElementByIdSctpRequest);
        System.out.println(getElementByIdSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        sctpResponse = sctpClient.execute(createLinkSctpReques);
        System.out.println(createLinkSctpReques);
        System.out.println(sctpResponse);
        Integer addressLink = (Integer) sctpResponse.getParametr(0).get();
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest(new ScAddress(addressLink), new ScString("Test"));
        sctpResponse = sctpClient.execute(setLinkContentSctpRequest);
        System.out.println(setLinkContentSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest getLinkContentSctpRequest = new GetLinkContentSctpRequest(new ScAddress(addressLink));
        sctpResponse = sctpClient.execute(getLinkContentSctpRequest);
        System.out.println(getLinkContentSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest getLinksByContentSctpRequest = new GetLinksByContentSctpRequest(new ScString("Test"));
        sctpResponse = sctpClient.execute(getLinksByContentSctpRequest);
        System.out.println(getLinksByContentSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest createArcSctpRequest = new CreateArcSctpRequest(new ScArcCommonType(), new ScAddress(addressNode), new ScAddress(addressLink));
        sctpResponse = sctpClient.execute(createArcSctpRequest);
        System.out.println(createArcSctpRequest);
        System.out.println(sctpResponse);
        Integer addressArc = (Integer) sctpResponse.getParametr(0).get();
        SctpRequest getArcElementsSctpRequest = new GetArcVertexesSctpRequest(new ScAddress(addressArc));
        sctpResponse = sctpClient.execute(getArcElementsSctpRequest);
        System.out.println(getArcElementsSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest makeSubscriptionSctpRequest = new MakeSubscriptionSctpRequest(ScEventType.SC_EVENT_REMOVE_ELEMENT, new ScAddress(addressNode));
        sctpResponse = sctpClient.execute(makeSubscriptionSctpRequest);
        System.out.println(makeSubscriptionSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest deleteElementSctpRequest = new DeleteElementSctpRequest(new ScAddress(addressNode));
        sctpResponse = sctpClient.execute(deleteElementSctpRequest);
        System.out.println(deleteElementSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest getEventSctpRequest = new GetEventSctpRequest();
        sctpResponse = sctpClient.execute(getEventSctpRequest);
        System.out.println(getEventSctpRequest);
        System.out.println(sctpResponse);
        SctpRequest getElementByIdSctpRequest2 = new GetElementByIdSctpRequest(new ScString("Pushkin"));
        sctpResponse = sctpClient.execute(getElementByIdSctpRequest2);
        System.out.println(getElementByIdSctpRequest2);
        System.out.println(sctpResponse);
        Integer addressPushkin = (Integer) sctpResponse.getParametr(0).get();
        SctpRequest getElementByIdSctpRequest3 = new GetElementByIdSctpRequest(new ScString("nrel_killer*"));
        sctpResponse = sctpClient.execute(getElementByIdSctpRequest3);
        System.out.println(getElementByIdSctpRequest3);
        System.out.println(sctpResponse);
        Integer addressNrel_killer = (Integer) sctpResponse.getParametr(0).get();
        SctpRequest findConstruction5f_a_a_a_fSctpRequest = new FindConstruction5f_a_a_a_fSctpRequest(new ScAddress(addressPushkin), new ScArcCommonType(), new ScNodeType(), new ScArcAccessType(), new ScAddress(addressNrel_killer));
        sctpResponse = sctpClient.execute(findConstruction5f_a_a_a_fSctpRequest);
        System.out.println(findConstruction5f_a_a_a_fSctpRequest);
        System.out.println(sctpResponse);
        Integer addressDuntes = (Integer) sctpResponse.getParametr(4).get();
        SctpRequest getElementByIdSctpRequest4 = new GetElementByIdSctpRequest(new ScString("nrel_idtf"));
        sctpResponse = sctpClient.execute(getElementByIdSctpRequest4);
        System.out.println(getElementByIdSctpRequest4);
        System.out.println(sctpResponse);
        Integer nrel_system_idtf = (Integer) sctpResponse.getParametr(0).get();
        SctpRequest findConstruction5f_a_a_a_fSctpRequest2 = new FindConstruction5f_a_a_a_fSctpRequest(new ScAddress(addressPushkin), new ScArcCommonType(), new ScNodeType(), new ScArcAccessType(), new ScAddress(nrel_system_idtf));
        sctpResponse = sctpClient.execute(findConstruction5f_a_a_a_fSctpRequest2);
        System.out.println(findConstruction5f_a_a_a_fSctpRequest2);
        System.out.println(sctpResponse);
    }

    @Test
    public void testExecute1() throws Exception {

    }

    @Test
    public void testClose() throws Exception {

    }


    public static class CallBackImpl implements CallBack {
        private SctpResponse response;

        public void success(SctpResponse responce) {
            this.response=responce;
            System.out.println(responce);
            System.out.println("Успешно");
        }

        public void error(SctpResponse responce) {
            this.response=responce;
            System.out.println(responce);
            System.out.println("Ошибка");
        }

        public SctpResponse getResponse() {
            return response;
        }
    }
}