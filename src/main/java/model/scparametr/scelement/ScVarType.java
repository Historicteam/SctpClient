package model.scparametr.scelement;

public class ScVarType extends ScElementTypeDecarator{
    public ScVarType(ScElementType scElementType) {
        super(scElementType);
        setValue((short)(getValue()|0x40&~0x20));
    }

}
