package Models.Exps;

import Exceptions.MyException;

import Models.Collections.MyIDictionary;
import Models.Types.BoolType;
import Models.Values.BoolValue;
import Models.Values.Value;

public class LogicExp implements Exp {
    private final Exp e1;
    private final Exp e2;
    private final int op; // 1-and, 2-or

    public LogicExp(String operand, Exp exp1, Exp exp2) {
        e1 = exp1;
        e2 = exp2;
        if (operand.equals("and"))
            op = 1;
        else
            op = 2;
    }

    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value nr1 = e1.eval(tbl);
        if (nr1.getType() instanceof BoolType) {
            Value nr2 = e2.eval(tbl);
            if (nr2.getType() instanceof BoolType) {
                BoolValue bv1, bv2;
                bv1 = (BoolValue) nr1;
                bv2 = (BoolValue) nr2;
                boolean b1, b2;
                b1 = bv1.getValue();
                b2 = bv2.getValue();
                if (op == 1)
                    return new BoolValue(b1 && b2);
                else
                    return new BoolValue(b1 || b2);
            } else
                throw new MyException("Second parameter is not BOOLEAN.");
        } else
            throw new MyException("First parameter is not BOOLEAN");
    }

    public String toString() {
        if (op == 1)
            return e1.toString() + " and " + e2.toString();
        else
            return e1.toString() + " or " + e2.toString();
    }
}