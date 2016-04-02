package model.scparametr.scelementtype;

public class ScConstConnectorType extends ScConnectorTypeDecarator{
    public ScConstConnectorType(ScConnectorType scConnectorType) {
        super(scConnectorType);
        setValue((short)(getValue()|0x20&~0x40));
    }
}
