package Models.Types;

public class BoolType implements Type {

    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    public String toString() {
        return "boolean ";
    }
}