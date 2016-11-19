package client;

import exception.IllegalCommand;
import exception.IllegalReturnCode;
import model.scparametr.*;
import model.scparametr.scelementtype.ScArcAccessType;
import model.scparametr.scelementtype.ScConnectorType;
import model.scparametr.scelementtype.ScMaterialNodeType;
import model.scparametr.scelementtype.ScNodeType;
import model.scresponce.SctpResponse;
import model.stcprequest.*;
import model.stcprequest.FindConstructionSctpRequest.FindConstruction3f_a_fSctpRequest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Theories.class)
public class ConstSctpClientTest extends Assert {

    public static final Logger LOG = Logger.getLogger(ConstSctpClientTest.class);

    public ConsSctpClient client;


    @DataPoints
    public static ConsSctpClient[] getConsSctpClient() throws IOException {
        return new ConsSctpClient[]{new ConsSctpClientImpl("192.168.20.128", 55770), new AdvancedSctpClient("192.168.20.128", 55770)};
    }

    @Theory
    public void testPerform(ConsSctpClient client) throws IOException, IllegalReturnCode, IllegalCommand {
        LOG.info("---------Start test testPerform--------------");
        this.client = client;
        SctpResponse sctpResponseCreateElement = testCreateElement(new ScMaterialNodeType(new ScNodeType
                ()));
        SctpResponse sctpResponseCheckElement = testCheckElement((ScAddress) sctpResponseCreateElement.getParametr(0));
        SctpResponse sctpResponseGetTypeElement = testGetTypeElement((ScAddress) sctpResponseCreateElement
                .getParametr(0));
        assertEquals(new ScMaterialNodeType(new ScNodeType()).get(), sctpResponseGetTypeElement.getParametr(0).get());
        SctpResponse sctpResponseSetIdentifier = testSetIdentifier((ScAddress) sctpResponseCreateElement.getParametr
                (0), new ScString("OSTIS"));
        SctpResponse sctpResponseGetElementById = testGetElementById(new ScString("OSTIS"));
        SctpResponse sctpResponseCreateLink = testCreateLink();
        SctpResponse sctpResponseSetLinkContent = testSetLinkContent((ScAddress) sctpResponseCreateLink.getParametr
                (0), new ScString("OSTIS"));
        SctpResponse sctpResponseGetLinkContent = testGetLinkContent((ScAddress) sctpResponseCreateLink.getParametr(0));
        assertEquals(sctpResponseGetLinkContent.getParametr(0).get(), "OSTIS");
        SctpResponse sctpResponseGetLinksByContent = testGetLinksByContent(new ScString("OSTIS"));
        SctpResponse sctpResponseCreateArc = testCreateArc((ScAddress) sctpResponseCreateElement.getParametr(0),
                (ScAddress) sctpResponseCreateLink.getParametr
                        (0), new ScArcAccessType());
        SctpResponse sctpResponseGetArcElements = testGetArcElements((ScAddress) sctpResponseCreateArc.getParametr(0));
        SctpResponse sctpResponseFindConstruction5f_a_a_a_f = testFindConstruction5f_a_a_a_f((ScAddress)
                sctpResponseCreateElement.getParametr(0), new ScArcAccessType(), (ScAddress) sctpResponseCreateLink
                .getParametr(0));
        SctpResponse sctpResponseMakeSubscription = testMakeSubscription((ScAddress) sctpResponseCreateElement
                .getParametr(0));
        SctpResponse sctpResponseDeleteElement = testDeleteElement((ScAddress) sctpResponseCreateElement.getParametr
                (0));
        SctpResponse sctpResponseGetEvent = testGetEvent();
        SctpResponse sctpResponseDeleteSubscription = testDeleteSubscription((ScIdSubscription)
                sctpResponseMakeSubscription.getParametr(0));
        client.close();
        LOG.info(client);
        LOG.info("--------------Finish test testPerform-------------");

    }

    public SctpResponse testCreateElement(ScNodeType scNodeType) throws IOException, IllegalCommand, IllegalReturnCode {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(scNodeType);
        LOG.info(createNodeSctpRequest);
        SctpResponse sctpResponse = client.perform(createNodeSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(createNodeSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(createNodeSctpRequest.getId(), sctpResponse.getId());
        assertEquals(createNodeSctpRequest.getSize(), 2);
        assertEquals(sctpResponse.getSize(), 4);
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }


    public SctpResponse testCheckElement(ScAddress scAddress) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest checkElementExistenceSctpRequest = new CheckElementExistenceSctpRequest(scAddress);
        LOG.info(checkElementExistenceSctpRequest);
        SctpResponse sctpResponse = client.perform(checkElementExistenceSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(checkElementExistenceSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }

    public SctpResponse testGetTypeElement(ScAddress scAddress) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest getElementTypeStcpRequest = new GetElementTypeStcpRequest(scAddress);
        LOG.info(getElementTypeStcpRequest);
        SctpResponse sctpResponse = client.perform(getElementTypeStcpRequest);
        assertEquals(getElementTypeStcpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        LOG.info(sctpResponse);
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }

    public SctpResponse testSetIdentifier(ScAddress scAddress, ScString scString) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest setIdentifierSctpRequest = new SetIdentifierSctpRequest(scAddress, scString);
        LOG.info(setIdentifierSctpRequest);
        SctpResponse sctpResponse = client.perform(setIdentifierSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(setIdentifierSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        return sctpResponse;
    }

    public SctpResponse testGetElementById(ScString scString) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest getElementByIdSctpRequest = new GetElementByIdSctpRequest(scString);
        LOG.info(getElementByIdSctpRequest);
        SctpResponse sctpResponse = client.perform(getElementByIdSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getElementByIdSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }


    public SctpResponse testCreateLink() throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        LOG.info(createLinkSctpReques);
        SctpResponse sctpResponse = client.perform(createLinkSctpReques);
        LOG.info(sctpResponse);
        assertEquals(createLinkSctpReques.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }


    public SctpResponse testSetLinkContent(ScAddress scAddress, ScString scString) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest(scAddress
                , scString);
        LOG.info(setLinkContentSctpRequest);
        SctpResponse sctpResponse = client.perform(setLinkContentSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(setLinkContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }


    public SctpResponse testGetLinkContent(ScAddress scAddress) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest getLinkContentSctpRequest = new GetLinkContentSctpRequest(scAddress);
        LOG.info(getLinkContentSctpRequest);
        SctpResponse sctpResponse = client.perform(getLinkContentSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getLinkContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }


    public SctpResponse testGetLinksByContent(ScString scString) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest getLinksByContentSctpRequest = new GetLinksByContentSctpRequest(scString);
        LOG.info(getLinksByContentSctpRequest);
        SctpResponse sctpResponse = client.perform(getLinksByContentSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getLinksByContentSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }


    public SctpResponse testCreateArc(ScAddress scAddressFirstElememt, ScAddress scAddressSecondElement,
                                      ScConnectorType scConnectorType) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest createArcSctpRequest = new CreateArcSctpRequest(scConnectorType, scAddressFirstElememt,
                scAddressSecondElement);
        LOG.info(createArcSctpRequest);
        SctpResponse sctpResponse = client.perform(createArcSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(createArcSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }


    public SctpResponse testGetArcElements(ScAddress scAddress) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest getArcElementsSctpRequest = new GetArcVertexesSctpRequest(scAddress);
        LOG.info(getArcElementsSctpRequest);
        SctpResponse sctpResponse = client.perform(getArcElementsSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getArcElementsSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }

    public SctpResponse testMakeSubscription(ScAddress scAddress) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest makeSubscriptionSctpRequest = new MakeSubscriptionSctpRequest(ScEventType
                .SC_EVENT_REMOVE_ELEMENT, scAddress);
        LOG.info(makeSubscriptionSctpRequest);
        SctpResponse sctpResponse = client.perform(makeSubscriptionSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(makeSubscriptionSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;

    }

    public SctpResponse testDeleteElement(ScAddress scAddress) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest deleteElementSctpRequest = new DeleteElementSctpRequest(scAddress);
        LOG.info(deleteElementSctpRequest);
        SctpResponse sctpResponse = client.perform(deleteElementSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(deleteElementSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }


    public SctpResponse testGetEvent() throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest getEventSctpRequest = new GetEventSctpRequest();
        LOG.info(getEventSctpRequest);
        SctpResponse sctpResponse = client.perform(getEventSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(getEventSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }

    public SctpResponse testDeleteSubscription(ScIdSubscription scIdSubscription) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest deleteSubscriptionRequest = new DeleteSubscriptionSctpRequest(scIdSubscription);
        LOG.info(deleteSubscriptionRequest);
        SctpResponse sctpResponse = client.perform(deleteSubscriptionRequest);
        LOG.info(sctpResponse);
        assertEquals(deleteSubscriptionRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        assertEquals(sctpResponse.getSctpCodeReturn(), SctpCodeReturn.SUCCESSFUL);
        return sctpResponse;
    }

    public SctpResponse testFindConstruction5f_a_a_a_f(ScAddress scAddressFirstElement, ScConnectorType
            scConnectorType, ScAddress scAddressSecond) throws IOException, IllegalCommand, IllegalReturnCode {
        SctpRequest findConstruction5f_a_fSctpRequest = new FindConstruction3f_a_fSctpRequest(scAddressFirstElement,
                scConnectorType, scAddressSecond);
        LOG.info(findConstruction5f_a_fSctpRequest);
        SctpResponse sctpResponse = client.perform(findConstruction5f_a_fSctpRequest);
        LOG.info(sctpResponse);
        assertEquals(findConstruction5f_a_fSctpRequest.getSctpCommandType(), sctpResponse.getSctpCommandType());
        return sctpResponse;
    }


}