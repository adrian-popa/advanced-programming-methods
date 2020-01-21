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

public class CondAssignStmt implements IStmt {
    private final String id;
    private final Exp exp1;
    private final Exp exp2;
    private final Exp exp3;

    public CondAssignStmt(String i, Exp e1, Exp e2, Exp e3) {
        id = i;
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getExeStack();

        IStmt ifStmt = new IfStmt(exp1, new AssignStmt(id, exp2), new AssignStmt(id, exp3));
        stk.push(ifStmt);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return id + "=" + "(" + exp1.toString() + ")?" + exp2.toString() + ":" + exp3.toString();
    }

}
