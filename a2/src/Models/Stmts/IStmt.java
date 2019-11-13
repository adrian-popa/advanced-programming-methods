package Models.Stmts;

import Exceptions.MyException;

import Models.PrgState;

public interface IStmt {

    PrgState execute(PrgState state) throws MyException;

    String toString();
}