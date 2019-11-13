package Models.Values;

import Models.Types.BoolType;
import Models.Types.Type;

public class BoolValue implements Value {

    private final boolean val;

    public BoolValue() {
        val = false;
    }

    public BoolValue(boolean v) {
        val = v;
    }

    public boolean getValue() {
        return val;
    }

    public String toString() {
        return String.valueOf(val);
    }

    public Type getType() {
        return new BoolType();
    }
}