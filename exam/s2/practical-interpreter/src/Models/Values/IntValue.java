package Models.Values;

import Models.Types.IntType;
import Models.Types.Type;

public class IntValue implements Value {
    private final int value;

    public IntValue(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}