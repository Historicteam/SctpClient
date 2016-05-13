package model.scparametr.scelementtype;


public class ScPosArcAccessType extends ScArcAccessTypeDecarator {
    public ScPosArcAccessType(ScArcAccessType scArcAccessType) {
        super(scArcAccessType);
        setValue((short)(scArcAccessType.getValue()|0x80&~0x100&~0x200));
    }
}
