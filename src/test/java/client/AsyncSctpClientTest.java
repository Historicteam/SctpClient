package client;

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
import java.util.concurrent.Future;

@RunWith(Theories.class)
public class AsyncSctpClientTest extends Assert {

    public static final Logger LOG = Logger.getLogger(AsyncSctpClientTest.class);

    public AsyncSctpClient client;


    @DataPoints
    public static AsyncSctpClient[] getAsyncSctpClient() throws IOException {
        return new AsyncSctpClient[]{new AsyncSctpClientImpl("192.168.50.155", 55770)};
    }

    @Theory
    public void testExecute(AsyncSctpClient client) throws Exception {
        LOG.info("---------Start test testPerform--------------");
        this.client = client;
        Future<SctpResponse> sctpResponseCreateElement = testCreateElement(new ScMaterialNodeType(new ScNodeType
                ()));
        Future<SctpResponse> sctpResponseCheckElement = testCheckElement((ScAddress) sctpResponseCreateElement.get().getParametr(0));
        Future<SctpResponse> sctpResponseGetTypeElement = testGetTypeElement((ScAddress) sctpResponseCreateElement
                .get().getParametr(0));
        Future<SctpResponse> sctpResponseSetIdentifier = testSetIdentifier((ScAddress) sctpResponseCreateElement.get().getParametr
                (0), new ScString("OSTIS"));
        Future<SctpResponse> sctpResponseGetElementById = testGetElementById(new ScString("OSTIS"));

        Future<SctpResponse> sctpResponseCreateLink = testCreateLink();
        Future<SctpResponse> sctpResponseSetLinkContent = testSetLinkContent((ScAddress) sctpResponseCreateLink.get().getParametr
                (0), new ScString("OSTIS"));

        Future<SctpResponse> sctpResponseGetLinkContent = testGetLinkContent((ScAddress) sctpResponseCreateLink.get().getParametr(0));
        Future<SctpResponse> sctpResponseGetLinksByContent = testGetLinksByContent(new ScString("OSTIS"));
        Future<SctpResponse> sctpResponseCreateArc = testCreateArc((ScAddress) sctpResponseCreateElement.get().getParametr(0),
                (ScAddress) sctpResponseCreateLink.get().getParametr
                        (0), new ScArcAccessType());
        Future<SctpResponse> sctpResponseGetArcElements = testGetArcElements((ScAddress) sctpResponseCreateArc.get().getParametr(0));
        Future<SctpResponse> sctpResponseFindConstruction5f_a_a_a_f = testFindConstruction5f_a_a_a_f((ScAddress)
                sctpResponseCreateElement.get().getParametr(0), new ScArcAccessType(), (ScAddress) sctpResponseCreateLink
                .get().getParametr(0));
        Future<SctpResponse> sctpResponseMakeSubscription = testMakeSubscription((ScAddress) sctpResponseCreateElement
                .get().getParametr(0));
        Future<SctpResponse> sctpResponseDeleteElement = testDeleteElement((ScAddress) sctpResponseCreateElement.get().getParametr
                (0));
        Future<SctpResponse> sctpResponseGetEvent = testGetEvent();
        Future<SctpResponse> sctpResponseDeleteSubscription = testDeleteSubscription((ScIdSubscription)
                sctpResponseMakeSubscription.get().getParametr(0));
        client.close();
        client.close();
        LOG.info(sctpResponseCreateElement.get());
        LOG.info(sctpResponseCreateLink.get());
        LOG.info(sctpResponseCheckElement.get());
        LOG.info(sctpResponseGetTypeElement.get());
        LOG.info(sctpResponseSetIdentifier.get());
        LOG.info(sctpResponseGetElementById.get());
        LOG.info(sctpResponseCreateLink.get());
        LOG.info(sctpResponseSetLinkContent.get());
        LOG.info(sctpResponseGetLinkContent.get());
        LOG.info(sctpResponseGetLinksByContent.get());
        LOG.info(sctpResponseCreateArc.get());
        LOG.info(sctpResponseGetArcElements.get());
        LOG.info(sctpResponseFindConstruction5f_a_a_a_f.get());
        LOG.info(sctpResponseMakeSubscription.get());
        LOG.info(sctpResponseDeleteElement.get());
        LOG.info(sctpResponseGetEvent.get());
        LOG.info(sctpResponseDeleteSubscription.get());
        LOG.info(client);
        LOG.info("--------------Finish test testPerform-------------");
    }

    public Future<SctpResponse> testCreateElement(ScNodeType scNodeType) throws IOException {
        CreateNodeSctpRequest createNodeSctpRequest = new CreateNodeSctpRequest(scNodeType);
        LOG.info(createNodeSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(createNodeSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }


    public Future<SctpResponse> testCheckElement(ScAddress scAddress) throws IOException {
        SctpRequest checkElementExistenceSctpRequest = new CheckElementExistenceSctpRequest(scAddress);
        LOG.info(checkElementExistenceSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(checkElementExistenceSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }

    public Future<SctpResponse> testGetTypeElement(ScAddress scAddress) throws IOException {
        SctpRequest getElementTypeStcpRequest = new GetElementTypeStcpRequest(scAddress);
        LOG.info(getElementTypeStcpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(getElementTypeStcpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }

    public Future<SctpResponse> testSetIdentifier(ScAddress scAddress, ScString scString) throws IOException {
        SctpRequest setIdentifierSctpRequest = new SetIdentifierSctpRequest(scAddress, scString);
        LOG.info(setIdentifierSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(setIdentifierSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }

    public Future<SctpResponse> testGetElementById(ScString scString) throws IOException {
        SctpRequest getElementByIdSctpRequest = new GetElementByIdSctpRequest(scString);
        LOG.info(getElementByIdSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(getElementByIdSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }


    public Future<SctpResponse> testCreateLink() throws IOException {
        SctpRequest createLinkSctpReques = new CreateLinkSctpRequest();
        LOG.info(createLinkSctpReques);
        Future<SctpResponse> sctpResponseFuture = client.execute(createLinkSctpReques);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }


    public Future<SctpResponse> testSetLinkContent(ScAddress scAddress, ScString scString) throws IOException {
        SctpRequest setLinkContentSctpRequest = new SetLinkContentSctpRequest(scAddress, scString);
        LOG.info(setLinkContentSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(setLinkContentSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }


    public Future<SctpResponse> testGetLinkContent(ScAddress scAddress) throws IOException {
        SctpRequest getLinkContentSctpRequest = new GetLinkContentSctpRequest(scAddress);
        LOG.info(getLinkContentSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(getLinkContentSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }


    public Future<SctpResponse> testGetLinksByContent(ScString scString) throws IOException {
        SctpRequest getLinksByContentSctpRequest = new GetLinksByContentSctpRequest(scString);
        LOG.info(getLinksByContentSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(getLinksByContentSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }


    public Future<SctpResponse> testCreateArc(ScAddress scAddressFirstElememt, ScAddress scAddressSecondElement,
                                ScConnectorType scConnectorType) throws IOException {
        SctpRequest createArcSctpRequest = new CreateArcSctpRequest(scConnectorType, scAddressFirstElememt,
                scAddressSecondElement);
        LOG.info(createArcSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(createArcSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }


    public Future<SctpResponse> testGetArcElements(ScAddress scAddress) throws IOException {
        SctpRequest getArcElementsSctpRequest = new GetArcVertexesSctpRequest(scAddress);
        LOG.info(getArcElementsSctpRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(getArcElementsSctpRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }

    public Future<SctpResponse> testMakeSubscription(ScAddress scAddress) throws IOException {
        SctpRequest makeSubscriptionSctpRequest = new MakeSubscriptionSctpRequest(ScEventType
                .SC_EVENT_REMOVE_ELEMENT, scAddress);
        LOG.info(makeSubscriptionSctpRequest);
        Future<SctpResponse> sctpResponse = client.execute(makeSubscriptionSctpRequest);
        LOG.info(sctpResponse);
        return sctpResponse;

    }

    public Future<SctpResponse> testDeleteElement(ScAddress scAddress) throws IOException {
        SctpRequest deleteElementSctpRequest = new DeleteElementSctpRequest(scAddress);
        LOG.info(deleteElementSctpRequest);
        Future sctpResponse = client.execute(deleteElementSctpRequest);
        LOG.info(sctpResponse);
        return sctpResponse;
    }


    public Future<SctpResponse> testGetEvent() throws IOException {
        SctpRequest getEventSctpRequest = new GetEventSctpRequest();
        LOG.info(getEventSctpRequest);
        Future<SctpResponse> sctpResponse = client.execute(getEventSctpRequest);
        LOG.info(sctpResponse);
        return sctpResponse;
    }

    public Future<SctpResponse> testDeleteSubscription(ScIdSubscription scIdSubscription) throws IOException {
        SctpRequest deleteSubscriptionRequest = new DeleteSubscriptionSctpRequest(scIdSubscription);
        LOG.info(deleteSubscriptionRequest);
        Future<SctpResponse> sctpResponseFuture = client.execute(deleteSubscriptionRequest);
        LOG.info(sctpResponseFuture);
        return sctpResponseFuture;
    }

    public Future<SctpResponse> testFindConstruction5f_a_a_a_f(ScAddress scAddressFirstElement, ScConnectorType
            scConnectorType, ScAddress scAddressSecond) throws IOException {
        SctpRequest findConstruction5f_a_fSctpRequest = new FindConstruction3f_a_fSctpRequest(scAddressFirstElement,
                scConnectorType, scAddressSecond);
        LOG.info(findConstruction5f_a_fSctpRequest);
        Future<SctpResponse> sctpResponse = client.execute(findConstruction5f_a_fSctpRequest);
        LOG.info(sctpResponse);
        return sctpResponse;
    }

    @Theory
    public void testExecute1(AsyncSctpClient client) throws Exception {

    }
}