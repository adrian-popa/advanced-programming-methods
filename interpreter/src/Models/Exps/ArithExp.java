package Models.Exps;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Types.IntType;
import Models.Values.IntValue;
import Models.Values.Value;

public class ArithExp implements Exp {
    private final Exp exp1;
    private final Exp exp2;
    private final char op;

    public ArithExp(char op, Exp exp1, Exp exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value v1, v2;
        v1 = exp1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = exp2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                switch (op) {
                    case '+':
                        return new IntValue(n1 + n2);
                    case '-':
                        return new IntValue(n1 - n2);
                    case '*':
                        return new IntValue(n1 * n2);
                    case '/':
                        if (n2 == 0)
                            throw new MyException("division by zero");
                        else
                            return new IntValue(n1 / n2);
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