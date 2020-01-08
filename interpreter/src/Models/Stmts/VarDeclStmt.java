package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.PrgState;
import Models.Types.Type;
import Models.Values.Value;

public class VarDeclStmt implements IStmt {
    private final String name;
    private final Type type;

    public VarDeclStmt(String n, Type t) {
        name = n;
        type = t;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (symTable.isDefined(name))
            throw new MyException("Variable is already defined.");
        else {
            symTable.put(name, type.defaultValue());
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(name, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type + name;
    }
}
