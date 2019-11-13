package Models.Values;

import Models.Types.Type;
import Models.Types.IntType;

public class IntValue implements Value {
    private final int val;

    public IntValue() {
        val = 0;
    }

    public IntValue(int v) {
        val = v;
    }

    public int getVal() {
        return val;
    }

    public String toString() {
        return String.valueOf(val);
    }

    public Type getType() {
        return new IntType();
    }
}