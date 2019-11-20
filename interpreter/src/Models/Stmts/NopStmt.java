package Models.Stmts;

import Models.PrgState;

public class NopStmt implements IStmt {

    public NopStmt() {
    }

    @Override
    public void execute(PrgState state) {
    }

    @Override
    public String toString() {
        return "nop";
    }
}