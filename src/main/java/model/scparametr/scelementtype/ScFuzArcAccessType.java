package model.scparametr.scelementtype;


public class ScFuzArcAccessType extends ScArcAccessTypeDecarator{
    public ScFuzArcAccessType(ScArcAccessType scArcAccessType) {
        super(scArcAccessType);
        setValue((short)(getValue()|0x200&~0x100&~0x80));
    }
}
