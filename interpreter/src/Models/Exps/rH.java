package Models.Exps;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Values.RefValue;
import Models.Values.Value;

public class rH implements Exp {
    private final Exp exp;

    public rH(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value v = this.exp.eval(tbl, hp);
        if (v instanceof RefValue) {
            Integer addr = ((RefValue) v).getAddr();
            return hp.lookup(addr);
        } else
            throw new MyException("Value is not of type reference value.");
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }
}
