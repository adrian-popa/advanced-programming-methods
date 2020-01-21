package Models.Types;

import Models.Values.StringValue;
import Models.Values.Value;

public class StringType implements Type {

    @Override
    public boolean equals(Object second) {
        return second instanceof StringType;
    }

    @Override
    public String toString() {
        return "string ";
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }
}