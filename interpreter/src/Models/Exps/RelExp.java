package Models.Exps;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Types.IntType;
import Models.Values.BoolValue;
import Models.Values.IntValue;
import Models.Values.Value;

public class RelExp implements Exp {
    private final Exp exp1;
    private final Exp exp2;
    private final String op;

    public RelExp(String op, Exp exp1, Exp exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value v1 = exp1.eval(tbl, hp);
        if (v1.getType() instanceof IntType) {
            Value v2 = exp2.eval(tbl, hp);
            if (v2.getType() instanceof IntType) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                switch (op) {
                    case ">=":
                        return new BoolValue(n1 >= n2);
                    case ">":
                        return new BoolValue(n1 > n2);
                    case "<=":
                        return new BoolValue(n1 <= n2);
                    case "<":
                        return new BoolValue(n1 < n2);
                    case "==":
                        return new BoolValue(n1 == n2);
                    case "!=":
                        return new BoolValue(n1 != n2);
                }
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");

        return null;
    }

    @Override
    public String toString() {
        return exp1.toString() + op + exp2.toString();
    }
}
