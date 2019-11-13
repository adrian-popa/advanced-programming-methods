package Models.Exps;

import Exceptions.MyException;

import Models.Collections.MyIDictionary;
import Models.Types.IntType;
import Models.Values.IntValue;
import Models.Values.Value;

public class ArithExp implements Exp {
    private final Exp e1;
    private final Exp e2;
    private int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(char operand, Exp exp1, Exp exp2) {
        e1 = exp1;
        e2 = exp2;

        switch (operand) {
            case '+':
                op = 1;
                break;
            case '-':
                op = 2;
                break;
            case '*':
                op = 3;
                break;
            case '/':
                op = 4;
                break;
        }
    }

    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl);

        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4)
                    if (n2 == 0)
                        throw new MyException("division by zero");
                    else
                        return new IntValue(n1 / n2);
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");

        return null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        switch (op) {
            case 1:
                s.append(e1).append("+").append(e2);
                break;
            case 2:
                s.append(e1).append("-").append(e2);
                break;
            case 3:
                s.append(e1).append("*").append(e2);
                break;
            case 4:
                s.append(e1).append("/").append(e2);
                break;
        }

        return s.toString();
    }
}