package Models.Exps;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Types.BoolType;
import Models.Types.IntType;
import Models.Types.Type;
import Models.Values.BoolValue;
import Models.Values.Value;

public class LogicExp implements Exp {
    private final Exp exp1;
    private final Exp exp2;
    private final String op;

    public LogicExp(String op, Exp exp1, Exp exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value v1 = exp1.eval(tbl, hp);
        if (v1.getType() instanceof BoolType) {
            Value v2 = exp2.eval(tbl, hp);
            if (v2.getType() instanceof BoolType) {
                BoolValue bv1 = (BoolValue) v1;
                BoolValue bv2 = (BoolValue) v2;
                boolean b1, b2;
                b1 = bv1.getValue();
                b2 = bv2.getValue();
                if (op.equals("and"))
                    return new BoolValue(b1 && b2);
                else
                    return new BoolValue(b1 || b2);
            } else
                throw new MyException("Second parameter is not BOOLEAN.");
        } else
            throw new MyException("First parameter is not BOOLEAN");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = exp1.typecheck(typeEnv);
        typ2 = exp2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not BOOLEAN");
        } else
            throw new MyException("first operand is not BOOLEAN");
    }

    @Override
    public String toString() {
        return exp1.toString() + op + exp2.toString();
    }
}
