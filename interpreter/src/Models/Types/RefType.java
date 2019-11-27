package Models.Types;

import Models.Values.RefValue;
import Models.Values.Value;

public class RefType implements Type {
    private final Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public boolean equals(Object another) {
        if (another instanceof RefType)
            return inner.equals(((RefType) another).inner);
        else
            return false;
    }

    public Value defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }
}