package Models.Stmts;

import Exceptions.MyException;
import Models.PrgState;

public class NopStmt implements IStmt {

    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public String toString() {
        return "nop";
    }
}