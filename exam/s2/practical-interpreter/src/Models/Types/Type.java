package Models.Types;

import Models.Values.Value;

public interface Type {

    boolean equals(Object another);

    Value defaultValue();

    String toString();
}