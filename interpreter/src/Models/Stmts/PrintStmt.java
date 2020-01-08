package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Collections.MyIList;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.Type;
import Models.Values.Value;

public class PrintStmt implements IStmt {
    private final Exp exp;

    public PrintStmt(Exp e) {
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> list = state.getOutList();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> hp = state.getHeap();
        Value val = exp.eval(symTbl, hp);
        list.add(val);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }
}
