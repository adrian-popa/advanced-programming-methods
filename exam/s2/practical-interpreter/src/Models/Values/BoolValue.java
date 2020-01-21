package Models.Values;

import Models.Types.BoolType;
import Models.Types.Type;

public class BoolValue implements Value {
    private final boolean value;

    public BoolValue(boolean v) {
        value = v;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}