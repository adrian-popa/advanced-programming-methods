package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIList;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Values.Value;

public class PrintStmt implements IStmt {
    private final Exp exp;

    public PrintStmt(Exp e) {
        exp = e;
    }

    @Override
    public void execute(PrgState state) throws MyException {
        MyIList<Value> list = state.getOutList();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        Value val = exp.eval(symTbl);
        list.add(val);
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }
}
