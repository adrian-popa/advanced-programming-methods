package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.RefType;
import Models.Types.Type;
import Models.Values.RefValue;
import Models.Values.Value;

public class wH implements IStmt {
    private final String var_name;
    private final Exp exp;

    public wH(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if (state.getSymTable().isDefined(this.var_name)) {
            Value v = state.getSymTable().lookup(this.var_name);
            if (v instanceof RefValue) {
                int addr = ((RefValue) v).getAddr();
                Value ev = this.exp.eval(state.getSymTable(), state.getHeap());
                if (ev.getType().equals(((RefValue) v).getLocationType()))
                    state.getHeap().put(addr, ev);
                else
                    throw new MyException("Incompatible types.");
            } else
                throw new MyException("Value not of type Reference type");
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
            throw new MyException("wH stmt: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return "wH(" + var_name + "," + exp.toString() + ")";
    }
}
