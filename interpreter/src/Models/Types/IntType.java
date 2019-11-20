package Models.Types;

import Models.Values.IntValue;

public class IntType implements Type {

    @Override
    public boolean equals(Object another) {
        return another instanceof IntType;
    }

    @Override
    public IntValue defaultValue() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "int ";
    }
}