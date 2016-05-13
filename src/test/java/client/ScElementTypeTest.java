package client;

import model.scparametr.scelementtype.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;


public class ScElementTypeTest extends Assert {

    public static final Logger LOG = Logger.getLogger(ScElementTypeTest.class);


    @Test
    public void createcNode(){
        ScElementType scNode = new ScNodeType();
        LOG.info(scNode.getValue());
        assertEquals(scNode.getValue(),(short)1);
    }

    @Test
    public void createcConstNode(){
        ScElementType scConstNode = new ScConstNodeType(new ScNodeType());
        LOG.info(scConstNode.getValue());
        assertEquals(scConstNode.getValue(),(short)33);
    }

    @Test
    public void createcVarNode(){
        ScElementType scVarNode = new ScVarNodeType(new ScNodeType());
        LOG.info(scVarNode.getValue());
        assertEquals(scVarNode.getValue(),(short)65);
    }

    @Test
    public void createVarAbstractScNode(){
        ScElementType scConstAbstractNode = new ScVarNodeType(new ScAbstractNodeType(new ScNodeType()));
        LOG.info(scConstAbstractNode.getValue());
        assertEquals(scConstAbstractNode.getValue(),(short)4161);
    }

    @Test
    public void createVarMaterialScNode(){
        ScElementType scConstMaterialNode = new ScVarNodeType(new ScMaterialNodeType(new ScNodeType()));
        LOG.info(scConstMaterialNode.getValue());
        assertEquals(scConstMaterialNode.getValue(),(short)8257);
    }

    @Test
    public void createVarStructScNode(){
        ScElementType scConstStructNode = new ScVarNodeType(new ScStructNodeType(new ScNodeType()));
        LOG.info(scConstStructNode.getValue());
        assertEquals(scConstStructNode.getValue(),(short)321);
    }

    @Test
    public void createVarTupleScNode(){
        ScElementType scConstTupleNode = new ScVarNodeType(new ScTupleNodeType(new ScNodeType()));
        LOG.info(scConstTupleNode.getValue());
        assertEquals(scConstTupleNode.getValue(),(short)193);
    }

    @Test
    public void createVarRoleScNode(){
        ScElementType scConstRoleNode = new ScVarNodeType(new ScRoleNodeType(new ScNodeType()));
        LOG.info(scConstRoleNode.getValue());
        assertEquals(scConstRoleNode.getValue(),(short)577);
    }


    @Test
    public void createVarNoRoleScNode(){
        ScElementType scConstNoRoleNode = new ScVarNodeType(new ScNoroleNodeType(new ScNodeType()));
        LOG.info(scConstNoRoleNode.getValue());
        assertEquals(scConstNoRoleNode.getValue(),(short)1089);
    }

    @Test
    public void createVarClassScNode(){
        ScElementType scConstClassNode = new ScVarNodeType(new ScClassNodeType(new ScNodeType()));
        LOG.info(scConstClassNode.getValue());
        assertEquals(scConstClassNode.getValue(),(short)2113);
    }

    @Test
    public void createConstAbstractScNode(){
        ScElementType scConstAbstractNode = new ScConstNodeType(new ScAbstractNodeType(new ScNodeType()));
        LOG.info(scConstAbstractNode.getValue());
        assertEquals(scConstAbstractNode.getValue(),(short)4129);
    }

    @Test
    public void createConstMaterialScNode(){
        ScElementType scConstMaterialNode = new ScConstNodeType(new ScMaterialNodeType(new ScNodeType()));
        LOG.info(scConstMaterialNode.getValue());
        assertEquals(scConstMaterialNode.getValue(),(short)8225);
    }

    @Test
    public void createConstStructScNode(){
        ScElementType scConstStructNode = new ScConstNodeType(new ScStructNodeType(new ScNodeType()));
        LOG.info(scConstStructNode.getValue());
        assertEquals(scConstStructNode.getValue(),(short)289);
    }

    @Test
    public void createConstTupleScNode(){
        ScElementType scConstTupleNode = new ScConstNodeType(new ScTupleNodeType(new ScNodeType()));
        LOG.info(scConstTupleNode.getValue());
        assertEquals(scConstTupleNode.getValue(),(short)161);
    }

    @Test
    public void createConstRoleScNode(){
        ScElementType scConstRoleNode = new ScConstNodeType(new ScRoleNodeType(new ScNodeType()));
        LOG.info(scConstRoleNode.getValue());
        assertEquals(scConstRoleNode.getValue(),(short)545);
    }


    @Test
    public void createConstNoRoleScNode(){
        ScElementType scConstNoRoleNode = new ScConstNodeType(new ScNoroleNodeType(new ScNodeType()));
        LOG.info(scConstNoRoleNode.getValue());
        assertEquals(scConstNoRoleNode.getValue(),(short)1057);
    }

    @Test
    public void createConstClassScNode(){
        ScElementType scConstClassNode = new ScConstNodeType(new ScClassNodeType(new ScNodeType()));
        LOG.info(scConstClassNode.getValue());
        assertEquals(scConstClassNode.getValue(),(short)2081);
    }


    @Test
    public void createCommonArc(){
        ScElementType scArcCommonType = new ScArcCommonType();
        LOG.info(scArcCommonType.getValue());
        assertEquals(scArcCommonType.getValue(),(short)8);
    }

    @Test
    public void createEdgeCommonType(){
        ScElementType scEdgeCommonType = new ScEdgeCommonType();
        LOG.info(scEdgeCommonType.getValue());
        assertEquals(scEdgeCommonType.getValue(),(short)4);
    }

    @Test
    public void createArcAccessType(){
        ScElementType scArcAccessType = new ScArcAccessType();
        LOG.info(scArcAccessType.getValue());
        assertEquals(scArcAccessType.getValue(),(short)16);
    }

    @Test
    public void createConstCommonArc(){
        ScElementType scConstArcCommonType = new ScConstConnectorType(new ScArcCommonType());
        LOG.info(scConstArcCommonType.getValue());
        assertEquals(scConstArcCommonType.getValue(),(short)40);
    }

    @Test
    public void createConstEdgeCommonType(){
        ScElementType scConstEdgeCommonType = new ScConstConnectorType(new ScEdgeCommonType());
        LOG.info(scConstEdgeCommonType.getValue());
        assertEquals(scConstEdgeCommonType.getValue(),(short)36);
    }

    @Test
    public void createConstPosPermArcAccessType(){
        ScElementType scConsPosPermArcAccessType = new ScConstConnectorType(new ScPermArcAccessType(new ScPosArcAccessType(new ScArcAccessType())));
        LOG.info(scConsPosPermArcAccessType.getValue());
        assertEquals(scConsPosPermArcAccessType.getValue(),(short)2224);
    }

    @Test
    public void createConstPosTempArcAccessType(){
        ScElementType scConsPosTempArcAccessType = new ScConstConnectorType(new ScTempArcAccessType(new ScPosArcAccessType(new ScArcAccessType())));
        LOG.info(scConsPosTempArcAccessType.getValue());
        assertEquals(scConsPosTempArcAccessType.getValue(),(short)1200);
    }


    @Test
    public void createConstNegPermArcAccessType(){
        ScElementType scConsNegPermArcAccessType = new ScConstConnectorType(new ScPermArcAccessType(new ScNegArcAccessType(new ScArcAccessType())));
        LOG.info(scConsNegPermArcAccessType.getValue());
        assertEquals(scConsNegPermArcAccessType.getValue(),(short)2352);
    }

    @Test
    public void createConstNegTempArcAccessType(){
        ScElementType scConsNegTempArcAccessType = new ScConstConnectorType(new ScTempArcAccessType(new ScNegArcAccessType(new ScArcAccessType())));
        LOG.info(scConsNegTempArcAccessType.getValue());
        assertEquals(scConsNegTempArcAccessType.getValue(),(short)1328);
    }



    @Test
    public void createVarCommonArc(){
        ScElementType scVarArcCommonType = new ScVarConnectorType(new ScArcCommonType());
        LOG.info(scVarArcCommonType.getValue());
        assertEquals(scVarArcCommonType.getValue(),(short)72);
    }

    @Test
    public void createVarEdgeCommonType(){
        ScElementType scVarEdgeCommonType = new ScVarConnectorType(new ScEdgeCommonType());
        LOG.info(scVarEdgeCommonType.getValue());
        assertEquals(scVarEdgeCommonType.getValue(),(short)68);
    }


    @Test
    public void createVarFuzPermArcAccessType(){
        ScElementType scConsFuzPermArcAccessType = new ScVarConnectorType(new ScPermArcAccessType(new ScFuzArcAccessType(new ScArcAccessType())));
        LOG.info(scConsFuzPermArcAccessType.getValue());
        assertEquals(scConsFuzPermArcAccessType.getValue(),(short)2640);
    }

    @Test
    public void createVarFuzTempArcAccessType(){
        ScElementType scVarFuzTempArcAccessType = new ScVarConnectorType(new ScTempArcAccessType(new ScFuzArcAccessType(new ScArcAccessType())));
        LOG.info(scVarFuzTempArcAccessType.getValue());
        assertEquals(scVarFuzTempArcAccessType.getValue(),(short)1616);
    }


    @Test
    public void createVarPosPermArcAccessType(){
        ScElementType scVarPosPermArcAccessType = new ScVarConnectorType(new ScPermArcAccessType(new ScPosArcAccessType(new ScArcAccessType())));
        LOG.info(scVarPosPermArcAccessType.getValue());
        assertEquals(scVarPosPermArcAccessType.getValue(),(short)2256);
    }

    @Test
    public void creatVarPosTempArcAccessType(){
        ScElementType scVarPosTempArcAccessType = new ScVarConnectorType(new ScTempArcAccessType(new ScPosArcAccessType(new ScArcAccessType())));
        LOG.info(scVarPosTempArcAccessType.getValue());
        assertEquals(scVarPosTempArcAccessType.getValue(),(short)1232);
    }


    @Test
    public void createVarNegPermArcAccessType(){
        ScElementType scVarNegPermArcAccessType = new ScVarConnectorType(new ScPermArcAccessType(new ScNegArcAccessType(new ScArcAccessType())));
        LOG.info(scVarNegPermArcAccessType.getValue());
        assertEquals(scVarNegPermArcAccessType.getValue(),(short)2384);
    }

    @Test
    public void createVarNegTempArcAccessType(){
        ScElementType scVarNegTempArcAccessType = new ScVarConnectorType(new ScTempArcAccessType(new ScNegArcAccessType(new ScArcAccessType())));
        LOG.info(scVarNegTempArcAccessType.getValue());
        assertEquals(scVarNegTempArcAccessType.getValue(),(short)1360);
    }








}
