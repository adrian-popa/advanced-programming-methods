package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.PrgState;
import Models.Types.Type;

public class NopStmt implements IStmt {

    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
