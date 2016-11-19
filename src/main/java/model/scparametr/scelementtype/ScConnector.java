package model.scparametr.scelementtype;


public enum ScConnector implements ScElement{
    EDGE_COMMON_CONST(new ScConstConnectorType(new ScEdgeCommonType())),
    ARC_COMMON_CONST(new ScConstConnectorType(new ScArcCommonType())),
    ARC_ACCESS_FUZ_PERM_CONST(new ScConstConnectorType(new ScPermArcAccessType(new ScFuzArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_FUZ_TEMP_CONST(new ScConstConnectorType(new ScTempArcAccessType(new ScFuzArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_NEG_PERM_CONST(new ScConstConnectorType(new ScPermArcAccessType(new ScNegArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_NEG_TEMP_CONST(new ScConstConnectorType(new ScTempArcAccessType(new ScNegArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_POS_PERM_CONST(new ScConstConnectorType(new ScPermArcAccessType(new ScPosArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_POS_TEMP_CONST(new ScConstConnectorType(new ScTempArcAccessType(new ScPosArcAccessType(new ScArcAccessType())))),
    EDGE_COMMON_VAR(new ScVarConnectorType(new ScEdgeCommonType())),
    ARC_COMMON_VAR(new ScVarConnectorType(new ScArcCommonType())),
    ARC_ACCESS_FUZ_PERM_VAR(new ScVarConnectorType(new ScPermArcAccessType(new ScFuzArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_FUZ_TEMP_VAR(new ScVarConnectorType(new ScTempArcAccessType(new ScFuzArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_NEG_PERM_VAR(new ScVarConnectorType(new ScPermArcAccessType(new ScNegArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_NEG_TEMP_VAR(new ScVarConnectorType(new ScTempArcAccessType(new ScNegArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_POS_PERM_VAR(new ScVarConnectorType(new ScPermArcAccessType(new ScPosArcAccessType(new ScArcAccessType())))),
    ARC_ACCESS_POS_TEMP_VAR(new ScVarConnectorType(new ScTempArcAccessType(new ScPosArcAccessType(new ScArcAccessType())))),
    EDGE_COMMON(new ScEdgeCommonType()),
    ARC_COMMON(new ScArcCommonType()),
    ARC_ACCESS(new ScArcAccessType()),
    ;

    private ScConnectorType scConnectorType;

    ScConnector(ScConnectorType scConnectorType) {
        this.scConnectorType = scConnectorType;
    }

    public ScConnectorType get() {
        return scConnectorType;
    }
}
