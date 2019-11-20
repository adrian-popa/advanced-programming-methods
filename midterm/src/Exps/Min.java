package Exps;

public class Min implements Exp {
    private Exp exp1;
    private Exp exp2;

    public Min(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public int eval() {
        if (exp1.eval() <= exp2.eval())
            return exp1.eval();
        else
            return exp2.eval();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        if (exp1 instanceof ValueExp)
            s.append(exp1.toString());
        else
            s.append("(").append(exp1.toString()).append(")");

        s.append(" MIN ");

        if (exp2 instanceof ValueExp)
            s.append(exp2.toString());
        else
            s.append("(").append(exp2.toString()).append(")");

        return s.toString();
    }

}
