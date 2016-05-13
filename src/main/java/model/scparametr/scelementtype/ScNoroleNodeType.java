package model.scparametr.scelementtype;

public class ScNoroleNodeType extends ScNodeTypeDecarator {
    public ScNoroleNodeType(ScNodeType scNodeType) {
        super(scNodeType);
        setValue((short)(scNodeType.getValue()|0x400&~0x100&~0x200&~0x80&~0x800&~0x1000&~0x2000));
    }

}
