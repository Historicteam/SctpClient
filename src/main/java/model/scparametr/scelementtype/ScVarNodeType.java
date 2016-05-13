package model.scparametr.scelementtype;

public class ScVarNodeType extends ScNodeTypeDecarator{
    public ScVarNodeType(ScNodeType scNodeType) {
        super(scNodeType);
        setValue((short)(scNodeType.getValue()|0x40&~0x20));
    }

}
