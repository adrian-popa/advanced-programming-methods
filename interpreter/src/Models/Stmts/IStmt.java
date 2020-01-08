package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.PrgState;
import Models.Types.Type;

import java.io.IOException;

public interface IStmt {

    PrgState execute(PrgState state) throws MyException, IOException;

    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;

    String toString();
}
