package Models.Stmts;

import Exceptions.MyException;

import Models.Collections.MyIDictionary;
import Models.PrgState;
import Models.Types.IntType;
import Models.Types.Type;
import Models.Values.BoolValue;
import Models.Values.IntValue;
import Models.Values.Value;

public class VarDeclStmt implements IStmt {
    private final String name;
    private final Type type;

    public VarDeclStmt(String n, Type t) {
        name = n;
        type = t;
    }

    public String toString() {
        return type + name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (symTable.isDefined(name))
            throw new MyException("Variable is already defined.");
        else {
            if (type.equals(new IntType())) {
                Value v = new IntValue();
                symTable.put(name, v);
            } else {
                Value v = new BoolValue();
                symTable.put(name, v);
            }
        }
        return state;
    }
}
