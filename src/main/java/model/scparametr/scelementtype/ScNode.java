package model.scparametr.scelementtype;


public enum ScNode implements ScElement {
    ABSTRACT_CONST(new ScConstNodeType(new ScAbstractNodeType(new ScNodeType()))),
    CONST(new ScConstNodeType(new ScNodeType())),
    CLASS_CONST(new ScConstNodeType(new ScClassNodeType(new ScNodeType()))),
    MATERIAL_CONST(new ScConstNodeType(new ScMaterialNodeType(new ScNodeType()))),
    NOROL_CONST(new ScConstNodeType(new ScNoroleNodeType(new ScNodeType()))),
    ROL_CONST(new ScConstNodeType(new ScRoleNodeType(new ScNodeType()))),
    STRUCT_CONST(new ScConstNodeType(new ScStructNodeType(new ScNodeType()))),
    TUPLE_CONST(new ScConstNodeType(new ScTupleNodeType(new ScNodeType()))),
    ABSTRACT_VAR(new ScVarNodeType(new ScAbstractNodeType(new ScNodeType()))),
    VAR(new ScVarNodeType(new ScNodeType())),
    CLASS_VAR(new ScVarNodeType(new ScClassNodeType(new ScNodeType()))),
    MATERIAL_VAR(new ScVarNodeType(new ScMaterialNodeType(new ScNodeType()))),
    NOROL_VAR(new ScVarNodeType(new ScNoroleNodeType(new ScNodeType()))),
    ROL_VAR(new ScVarNodeType(new ScStructNodeType(new ScNodeType()))),
    STRUCT_VAR(new ScVarNodeType(new ScRoleNodeType(new ScNodeType()))),
    TUPLE_VAR(new ScVarNodeType(new ScTupleNodeType(new ScNodeType()))),
    _(new ScNodeType()),
    ;
    private ScNodeType scNodeType;
    ScNode(ScNodeType scNodeType) {
        this.scNodeType = scNodeType;
    }

    public ScNodeType get() {
        return scNodeType;
    }
}
