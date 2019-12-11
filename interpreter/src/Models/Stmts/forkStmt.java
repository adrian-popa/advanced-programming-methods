package Models.Stmts;

import Models.Collections.MyStack;
import Models.PrgState;

public class forkStmt implements IStmt {
    private final IStmt stmt;

    public forkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) {
        return new PrgState(new MyStack<>(), state.getSymTable().clone(), state.getOutList(), state.getFileTable(), state.getHeap(), stmt);
    }

    @Override
    public String toString() {
        return "fork(" + this.stmt.toString() + ")";
    }
}
