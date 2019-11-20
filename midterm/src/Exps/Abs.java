package Exps;

public class Abs implements Exp {
    private Exp exp1;

    public Abs(Exp exp1) {
        this.exp1 = exp1;
    }

    public int eval() {
        if (exp1.eval() >= 0)
            return exp1.eval();
        else
            return exp1.eval() * -1;
    }

    public String toString() {
        return "ABS(" + exp1.eval() + ")";
    }
}
