package model.scparametr.scelementtype;


abstract public class ScConnectorTypeDecarator extends ScConnectorType{
    private ScConnectorType scConnectorType;

    public ScConnectorTypeDecarator(ScConnectorType scConnectorType) {
        super();
        this.scConnectorType = scConnectorType;
    }
}
