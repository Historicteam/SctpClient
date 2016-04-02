package model.scparametr.scelementtype;

public class ScRoleNodeType extends ScNodeTypeDecarator {

    public ScRoleNodeType(ScNodeType scNodeType) {
        super(scNodeType);
        setValue((short)(getValue()|0x200&~0x100&~0x80&~0x400&~0x800&~0x1000&~0x2000));
    }
}
