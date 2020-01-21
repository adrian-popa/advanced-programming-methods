package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyStack;
import Models.PrgState;
import Models.Types.BoolType;
import Models.Types.Type;

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
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + this.stmt.toString() + ")";
    }
}
