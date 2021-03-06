package Exps;

public class ADDMOD10 implements Exp {
    private Exp exp1;
    private Exp exp2;

    public ADDMOD10(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public int eval() {
        return (exp1.eval() + exp2.eval()) % 10;
    }

    public String toString() {
        return exp1.toString() + " ADD10 " + exp2.toString();
    }
}
