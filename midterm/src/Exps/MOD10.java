package Exps;

public class MOD10 implements Exp {
    private Exp exp1;

    public MOD10(Exp exp1) {
        this.exp1 = exp1;
    }

    public int eval() {
        return exp1.eval() % 10;
    }

    public String toString() {
        return exp1.toString() + " MOD 10";
    }
}
