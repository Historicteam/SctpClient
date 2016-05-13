package model.scparametr.scelementtype;

public class ScMaterialNodeType extends ScNodeTypeDecarator {
    public ScMaterialNodeType(ScNodeType scNodeType) {
        super(scNodeType);
        setValue((short)(scNodeType.getValue()|0x2000&~0x100&~0x200&~0x400&~0x800&~0x1000&~0x80));
    }
}
