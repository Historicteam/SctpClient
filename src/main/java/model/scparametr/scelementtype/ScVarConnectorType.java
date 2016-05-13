package model.scparametr.scelementtype;

public class ScVarConnectorType extends ScConnectorTypeDecarator {
    public ScVarConnectorType(ScConnectorType scConnectorType) {
        super(scConnectorType);
        setValue((short)(scConnectorType.getValue()|0x40&~0x20));
    }
}
