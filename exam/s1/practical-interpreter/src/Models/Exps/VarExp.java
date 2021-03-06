package Models.Exps;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Types.Type;
import Models.Values.Value;

public class VarExp implements Exp {
    private final String id;

    public VarExp(String name) {
        id = name;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
