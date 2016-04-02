package model.scparametr.scelementtype;


public class ScPermArcAccessType extends ScArcAccessTypeDecarator {
    public ScPermArcAccessType(ScArcAccessType scArcAccessType) {
        super(scArcAccessType);
        setValue((short)(getValue()|0x800&~0x400));
    }
}
