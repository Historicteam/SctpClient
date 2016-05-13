package model.scparametr.scelementtype;


public class ScNegArcAccessType extends ScArcAccessTypeDecarator{
    public ScNegArcAccessType(ScArcAccessType scArcAccessType) {
        super(scArcAccessType);
        setValue((short)(scArcAccessType.getValue()|0x100&~0x80&~0x200));
    }
}
