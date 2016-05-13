package model.scparametr.scelementtype;

public class ScAbstractNodeType extends ScNodeTypeDecarator {
    public ScAbstractNodeType(ScNodeType scNodeType) {
        super(scNodeType);
        setValue((short)(scNodeType.getValue()|0x1000&~0x100&~0x200&~0x400&~0x800&~0x80&~0x2000));
    }
}
