package Models;

import Models.Collections.MyIDictionary;
import Models.Collections.MyIList;
import Models.Collections.MyIStack;
import Models.Stmts.IStmt;
import Models.Values.Value;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    // IStmt originalProgram; // optional field, but good to have

    public MyIStack<IStmt> getStk() {
        return exeStack;
    }

    public void setMyIStack(MyIStack<IStmt> s) {
        exeStack = s;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setMyIDictionary(MyIDictionary<String, Value> d) {
        symTable = d;
    }

    public MyIList<Value> getOutList() {
        return out;
    }

    public void setMyIList(MyIList<Value> l) {
        out = l;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        // originalProgram = deepCopy(prg); // recreate the entire original prg
        stk.push(prg);
    }

    public String toString() {
        return "ExeStack:\n" + exeStack.toString() + "\n" +
                "SymTable:\n" + symTable.toString() + "\n" +
                "Out:\n" + out.toString() + "\n";
    }
}
