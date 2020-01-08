package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIStack;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.BoolType;
import Models.Types.Type;
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
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        Value cond = exp.eval(state.getSymTable(), state.getHeap());

        if (!(cond.getType() instanceof BoolType))
            throw new MyException("Condition " + cond + "is not of boolean type!");
        else {
            BoolValue condBool = (BoolValue) cond;
            if (condBool.getValue())
                stk.push(thenS);
            else
                stk.push(elseS);
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.clone());
            elseS.typecheck(typeEnv.clone());
            return typeEnv;
        } else
            throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }
}
