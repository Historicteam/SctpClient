package model.scparametr.scelement;


public class ScNegArcAccessType extends ScArcAccessTypeDecarator{
    public ScNegArcAccessType(ScArcAccessType scArcAccessType) {
        super(scArcAccessType);
        setValue((short)(getValue()|0x100&~0x80&~0x200));
    }
}
