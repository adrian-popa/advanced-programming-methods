package Models.Stmts;

import Exceptions.MyException;
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