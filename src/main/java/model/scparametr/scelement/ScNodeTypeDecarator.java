package model.scparametr.scelement;


abstract public class ScNodeTypeDecarator extends ScNodeType {
    private ScNodeType scNodeType;

    public ScNodeTypeDecarator(ScNodeType scNodeType) {
        this.scNodeType = scNodeType;
    }
}
