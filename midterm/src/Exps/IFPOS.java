package Exps;

public class IFPOS implements Exp {
    private Exp exp1;
    private Exp exp2;

    public IFPOS(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public int eval() {
        if (exp1.eval() >= 0)
            return exp2.eval();
        else
            return new Abs(exp1).eval();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (exp1 instanceof ValueExp)
            s.append(exp1.toString());
        else
            s.append("(").append(exp1.toString()).append(")");

        s.append(" IFPOS ");

        if (exp2 instanceof ValueExp)
            s.append(exp2.toString());
        else
            s.append("(").append(exp2.toString()).append(")");

        return s.toString();
    }
}
