package Models.Stmts;

import Exceptions.MyException;

import Models.PrgState;

public class NopStmt implements IStmt {

    public NopStmt() {
    }

    public String toString() {
        return "nop";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }
}