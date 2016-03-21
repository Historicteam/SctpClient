package model.scparametr.scelement;


public class ScConstType extends ScElementTypeDecarator {
    public ScConstType(ScElementType scElementType) {
        super(scElementType);
        setValue((short)(getValue()|0x20&~0x40));
    }
}
