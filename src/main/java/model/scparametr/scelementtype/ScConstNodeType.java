package model.scparametr.scelementtype;


public class ScConstNodeType extends ScNodeTypeDecarator{
    public ScConstNodeType(ScNodeType scNodeType) {
        super(scNodeType);
        setValue((short)(getValue()|0x20&~0x40));
    }
}
