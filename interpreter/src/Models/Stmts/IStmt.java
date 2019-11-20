package Models.Stmts;

import Exceptions.MyException;
import Models.PrgState;

import java.io.IOException;

public interface IStmt {

    void execute(PrgState state) throws MyException, IOException;

    String toString();
}