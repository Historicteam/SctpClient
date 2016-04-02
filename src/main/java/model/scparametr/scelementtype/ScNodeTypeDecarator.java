package model.scparametr.scelementtype;


abstract public class ScNodeTypeDecarator extends ScNodeType {
    private ScNodeType scNodeType;

    public ScNodeTypeDecarator(ScNodeType scNodeType) {
        this.scNodeType = scNodeType;
    }
}
