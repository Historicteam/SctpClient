package model.scparametr.scelementtype;


public class ScTempArcAccessType extends ScArcAccessTypeDecarator{
    public ScTempArcAccessType(ScArcAccessType scArcAccessType) {
        super(scArcAccessType);
        setValue((short)(scArcAccessType.getValue()|0x400&~0x800));
    }
}
