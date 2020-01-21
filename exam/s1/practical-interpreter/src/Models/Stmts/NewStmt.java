package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.RefType;
import Models.Types.Type;
import Models.Values.RefValue;
import Models.Values.Value;

public class NewStmt implements IStmt {
    private final String var_name;
    private final Exp exp;

    public NewStmt(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if (state.getSymTable().isDefined(var_name)) {
            Value v = state.getSymTable().lookup(var_name);
            if (v instanceof RefValue) {
                Value expVal = exp.eval(state.getSymTable(), state.getHeap());
                if (expVal.getType().equals(((RefValue) v).getLocationType())) {
                    int location = state.getHeap().allocate(expVal);
                    state.getSymTable().put(var_name, new RefValue(location, expVal.getType()));
                } else throw new MyException("Types are not equal");
            } else
        throw new MyException("Value isn't of type ReferenceType");
    } else
            throw new MyException("Variable not defined.");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return "new(" + var_name + "," + exp.toString() + ")";
    }
}
