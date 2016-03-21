package model.scparametr.scelement;


public class ScTempArcAccessType extends ScArcAccessTypeDecarator{
    public ScTempArcAccessType(ScArcAccessType scArcAccessType) {
        super(scArcAccessType);
        setValue((short)(getValue()|0x400&~0x800));
    }
}
