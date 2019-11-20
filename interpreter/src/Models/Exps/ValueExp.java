package Models.Exps;

import Models.Collections.MyIDictionary;
import Models.Values.Value;

public class ValueExp implements Exp {
    private final Value e;

    public ValueExp(Value exp) {
        e = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }
}