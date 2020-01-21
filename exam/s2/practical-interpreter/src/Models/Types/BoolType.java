package Models.Types;

import Models.Values.BoolValue;

public class BoolType implements Type {

    @Override
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    @Override
    public BoolValue defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public String toString() {
        return "boolean ";
    }
}