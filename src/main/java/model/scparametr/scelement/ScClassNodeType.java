package model.scparametr.scelement;

public class ScClassNodeType extends ScNodeTypeDecarator {
    public ScClassNodeType(ScNodeType scNodeType) {
        super(scNodeType);
        setValue((short)(getValue()|0x800&~0x100&~0x200&~0x400&~0x80&~0x1000&~0x2000));
    }
}
