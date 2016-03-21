package model.scparametr.scelement;


abstract public class ScElementTypeDecarator extends ScElementType{
    private ScElementType scElementType;

    public ScElementTypeDecarator(ScElementType scElementType) {
        super();
        this.scElementType = scElementType;
    }
}
