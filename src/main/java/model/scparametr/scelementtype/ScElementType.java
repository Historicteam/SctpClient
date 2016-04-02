package model.scparametr.scelementtype;


import model.scparametr.ScParameter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/*
sc_type_node = 0x1
sc_type_link = 0x2
sc_type_edge_common = 0x4
sc_type_arc_common = 0x8
sc_type_arc_access = 0x10

// sc-element constant
sc_type_const = 0x20
sc_type_var = 0x40

// sc-element positivity
sc_type_arc_pos = 0x80
sc_type_arc_neg = 0x100
sc_type_arc_fuz = 0x200

// sc-element premanently
sc_type_arc_temp = 0x400
sc_type_arc_perm = 0x800

// struct node types
sc_type_node_tuple = (0x80)
sc_type_node_struct = (0x100)
sc_type_node_role = (0x200)
sc_type_node_norole = (0x400)
sc_type_node_class = (0x800)
sc_type_node_abstract = (0x1000)
sc_type_node_material = (0x2000)
*/

public class ScElementType implements ScParameter<Short> {
    short value=0;
    final static public int SIZE = 2;

    public ScElementType() {
    }

    public ScElementType(short value) {
        this.value = value;
    }

    public byte[] getBytes(){
        ByteBuffer tempBuffer = ByteBuffer.allocate(SIZE);
        tempBuffer.order(ByteOrder.LITTLE_ENDIAN);
        tempBuffer.putShort(value);
        return tempBuffer.array();
    }



    public int getByteSize(){
        return SIZE;
    }

    @Override
    public Short get() {
        return value;
    }

    public short getValue() {
        return value;
    }

    protected void setValue(short value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ScElementType{" +
                "value=" + value +
                '}';
    }
}
