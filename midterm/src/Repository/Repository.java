package Repository;

import Exps.Exp;

public class Repository implements IRepository {
    private Exp exp;

    public Repository(Exp exp) {
        this.exp = exp;
    }

    public String getExp() {
        return exp.toString();
    }

    public int getResult() {
        return exp.eval();
    }
}
