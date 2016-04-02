package model.scparametr.scelementtype;


abstract public class ScArcAccessTypeDecarator extends ScArcAccessType{
    private ScArcAccessType scArcAccessType;

    public ScArcAccessTypeDecarator(ScArcAccessType scArcAccessType) {
        this.scArcAccessType = scArcAccessType;
    }
}
