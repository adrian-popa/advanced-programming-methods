package Models.Stmts;

import Exceptions.MyException;

import Models.Collections.MyIDictionary;
import Models.Collections.MyIStack;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.Type;
import Models.Values.Value;

public class AssignStmt implements IStmt {
    private final String id;
    private final Exp exp;

    public AssignStmt(String i, Exp e) {
        id = i;
        exp = e;
    }

    public String toString() {
        return id + "=" + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl);
            Type typId = (symTbl.lookup(id)).getType();
            if ((val.getType()).equals(typId))
                symTbl.update(id, val);
            else
                throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match");
        } else
            throw new MyException("the used variable" + id + " was not declared before");

        return state;
    }
}