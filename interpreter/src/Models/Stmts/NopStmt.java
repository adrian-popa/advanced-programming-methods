package Models.Stmts;

import Models.PrgState;

public class NopStmt implements IStmt {

    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
