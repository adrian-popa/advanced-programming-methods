package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.BoolType;
import Models.Types.Type;
import Models.Values.BoolValue;
import Models.Values.Value;

public class WhileStmt implements IStmt {
    private final Exp exp;
    private final IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> hp = state.getHeap();

        Value v = exp.eval(symTbl, hp);
        if (v.getType() instanceof BoolType) {
            BoolValue bv = (BoolValue) v;
            if (bv.getValue()) {
                state.getExeStack().push(new WhileStmt(exp, stmt));
                state.getExeStack().push(stmt);
            }
        } else
            throw new MyException("Condition exp is not a boolean.");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv.clone());
        if (typexp.equals(new BoolType()))
            return typeEnv;
        else
            throw new MyException("While: condition expression is not boolean");
    }

    @Override
    public String toString() {
        return "(while(" + exp.toString() + ")" + stmt.toString() + ")";
    }
}
