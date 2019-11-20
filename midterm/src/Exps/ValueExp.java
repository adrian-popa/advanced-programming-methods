package Exps;

public class ValueExp implements Exp {
    private int e;

    public ValueExp(int exp) {
        this.e = exp;
    }

    public int eval() {
        return e;
    }

    public String toString() {
        return String.valueOf(e);
    }

}
