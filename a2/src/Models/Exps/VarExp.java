package Models.Exps;

import Exceptions.MyException;

import Models.Collections.MyIDictionary;
import Models.Values.Value;

public class VarExp implements Exp {
    private final String id;

    public VarExp(String name) {
        id = name;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        return tbl.lookup(id);
    }

    public String toString() {
        return id;
    }
}