package Exps;

public class Neg implements Exp {
    private Exp exp1;

    public Neg(Exp exp1) {
        this.exp1 = exp1;
    }

    public int eval() {
        return exp1.eval() * -1;
    }

    public String toString() {
        return "NG " + exp1.toString();
    }

}
