package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIStack;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.BoolType;
import Models.Values.BoolValue;
import Models.Values.Value;

public class IfStmt implements IStmt {
    private final Exp exp;
    private final IStmt thenS;
    private final IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public void execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        Value cond = exp.eval(state.getSymTable());

        if (!(cond.getType() instanceof BoolType))
            throw new MyException("Condition " + cond + "is not of boolean type!");
        else {
            BoolValue condBool = (BoolValue) cond;
            if (condBool.getValue())
                stk.push(thenS);
            else
                stk.push(elseS);
        }
    }

    @Override
    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }
}