package Models.Types;

public class IntType implements Type {

    public boolean equals(Object another) {
        return another instanceof IntType;
    }

    public String toString() {
        return "int ";
    }
}