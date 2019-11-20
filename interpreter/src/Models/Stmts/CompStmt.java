package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIStack;
import Models.PrgState;

public class CompStmt implements IStmt {
    private final IStmt first;
    private final IStmt second;

    public CompStmt(IStmt f, IStmt s) {
        first = f;
        second = s;
    }

    public IStmt getFirst() {
        return first;
    }

    public IStmt getSecond() {
        return second;
    }

    @Override
    public void execute(PrgState state) {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }
}