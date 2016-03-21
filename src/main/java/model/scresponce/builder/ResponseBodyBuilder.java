package model.scresponce.builder;

import model.scparametr.*;
import model.scparametr.scelement.ScElementType;
import model.scresponce.SctpResponse;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


final class ResponseBodyBuilder {

    public static class SingletonHolder {
        public static final ResponseBodyBuilder HOLDER_INSTANCE = new ResponseBodyBuilder();
    }

    public static ResponseBodyBuilder getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private ResponseBodyBuilder() {
    }

    public List<ScParameter> build(final byte[] byteParametr, final SctpResponse sctpResponse) {
        if (!checkReturnCode(sctpResponse)) {
            return Collections.EMPTY_LIST;
        } else {
            return create(byteParametr, sctpResponse);
        }
    }


    private boolean checkReturnCode(SctpResponse sctpResponse) {
        switch (sctpResponse.getSctpCodeReturn()) {
            case SUCCESSFUL:
                return true;
            case NOT_FIND:
                return false;
            case UNSUCCESSFULLY:
                return false;
            case PERMISSION_DENIED:
                return false;
            default:
                throw new IllegalArgumentException("Not support answer= " + sctpResponse.getSctpCodeReturn());
        }
    }


    private List<ScParameter> create(byte[] byteParametrs, SctpResponse sctpResponse) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteParametrs);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        switch (sctpResponse.getSctpCommandType()) {
            case CHECK_ELEMENT_COMMAND:
                return Collections.EMPTY_LIST;
            case GET_ELEMENT_TYPE_COMMAND:
                return new ElementTypeBuilder().build(byteBuffer);
            case DELETE_ELEMENT_COMMAND:
                return Collections.EMPTY_LIST;
            case CREATE_NODE_COMMAND:
                return new CreateNodeBuilder().build(byteBuffer);
            case CREATE_LINK_COMMAND:
                return new CreateNodeBuilder().build(byteBuffer);
            case CREATE_ARC_COMMAND:
                return new CreateNodeBuilder().build(byteBuffer);
            case GET_ARC_VERTEXES_COMMAND:
                return new GetArcVertexesBuilder().build(byteBuffer);
            case GET_LINK_CONTENT_COMMAND:
                return new LinkContentBuilder().build(byteBuffer);
            case FIND_LINKS_COMMAND:
                return new FindLinksBuilder().build(byteBuffer);
            case SET_LINK_CONTENT_COMMAND:
                return Collections.EMPTY_LIST;
            case MAKE_SUBSCRIPTION_COMMAND:
                return new MakeSubscriptionBuilder().build(byteBuffer);
            case DELETE_SUBSCRIPTION_COMMAND:
                return new MakeSubscriptionBuilder().build(byteBuffer);
            case FIND_ELEMENT_BY_SYSIDTF_COMMAND:
                return new CreateNodeBuilder().build(byteBuffer);
            case SET_SYSIDTF_COMMAND:
                return Collections.EMPTY_LIST;
            case FIND_CONSTRUCTIONS_COMMAND:
                return new FindConstructionResponseBuilder().build(byteBuffer);
            case STATISTICS_COMMAND:
                return Collections.EMPTY_LIST;
            case VERSION_COMMAND:
                return Collections.EMPTY_LIST;
            case SHUTDOWN_COMMAND:
                return Collections.EMPTY_LIST;
            case CREATE_CONSTRUCTION:
                return Collections.EMPTY_LIST;
            case GET_EVENT_COMMAND:
                return new EventCommandBuilder().build(byteBuffer);
            case ITERATE_CONSTRUCTION_COMMAND:
                return Collections.EMPTY_LIST;
            default:
                // TODO: 0x02 Ask ElementTypes and add to ScElementType enum
                // TODO: 0x08 - undefined on sc-machine wiki
                // TODO: 0x0e - Event subscription
                // TODO: 0x0f - Delete event subscription
                // TODO: 0x0d - Complex construction iteration
                // TODO: 0x10 - Passed event inquiry
                // TODO:0xa2 - Server statistics in time interval
                // TODO:0xa3 - Ask protocol version(ask encoding)
                throw new IllegalArgumentException("Not support command= " + sctpResponse.getSctpCommandType());
        }

    }


    private class ElementTypeBuilder {
        public List<ScParameter> build(ByteBuffer byteParametrs) {
            List<ScParameter> scParametrsList = new ArrayList<>();
            scParametrsList.add(new ScElementType(byteParametrs.getShort()));
            return scParametrsList;
        }
    }

    private class CreateNodeBuilder {
        public List<ScParameter> build(ByteBuffer byteParametrs) {
            List<ScParameter> scParametrsList = new ArrayList<>();
            scParametrsList.add(new ScAddress(byteParametrs.getInt()));
            return scParametrsList;
        }
    }


    private class GetArcVertexesBuilder {
        public List<ScParameter> build(ByteBuffer byteParametrs) {
            List<ScParameter> scParametrsList = new ArrayList<>();
            scParametrsList.add(new ScAddress(byteParametrs.getInt()));
            scParametrsList.add(new ScAddress(byteParametrs.getInt()));
            return scParametrsList;
        }
    }

    private class LinkContentBuilder {
        public List<ScParameter> build(ByteBuffer byteParametrs) {
            List<ScParameter> scParametrsList = new ArrayList<>();
            scParametrsList.add(new ScString(new String(byteParametrs.array())));
            return scParametrsList;
        }
    }

    private class FindLinksBuilder {
        public List<ScParameter> build(ByteBuffer byteParametrs) {
            List<ScParameter> scParametrsList = new ArrayList<>();
            int size = byteParametrs.getInt();
            scParametrsList.add(new ScContentSize(size));
            for (int i = 0; i < size; i++) {
                scParametrsList.add(new ScAddress(byteParametrs.getInt()));
            }
            return scParametrsList;
        }
    }

    private class MakeSubscriptionBuilder {
        public List<ScParameter> build(ByteBuffer byteParametrs) {
            List<ScParameter> scParametrsList = new ArrayList<>();
            scParametrsList.add(new ScIdSubscription(byteParametrs.getInt()));
            return scParametrsList;
        }
    }

    private class FindConstructionResponseBuilder {
        public List<ScParameter> build(ByteBuffer byteParametrs) {
            List<ScParameter> scParametrsList = new ArrayList<>();
            //TODO переписать
            int size = byteParametrs.getInt();
            int lenght = byteParametrs.array().length;
            scParametrsList.add(new ScContentSize(size));
            byte[] parametrs = byteParametrs.array();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < ((lenght - 4) / size/4); j++) {
                    scParametrsList.add(new ScAddress(byteParametrs.getInt()));
                }
            }
            return scParametrsList;
        }
    }

    private class EventCommandBuilder {
        public List<ScParameter> build(ByteBuffer byteParametrs) {
            List<ScParameter> scParametrsList = new ArrayList<ScParameter>();
            int size = byteParametrs.getInt();
            scParametrsList.add(new ScContentSize(size));
            for (int i = 0; i < size; i++) {
                scParametrsList.add(new ScIdSubscription(byteParametrs.getInt()));
                scParametrsList.add(new ScAddress(byteParametrs.getInt()));
                scParametrsList.add(new ScAddress(byteParametrs.getInt()));
            }
            return scParametrsList;
        }
    }
}
