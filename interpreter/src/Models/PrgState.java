package Models;

import Models.Collections.*;
import Models.Stmts.IStmt;
import Models.Values.Value;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<String, BufferedReader> fileTable;

    public PrgState(IStmt prg) {
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        out = new MyList<>();
        fileTable = new MyDictionary<>();

        exeStack.push(prg);
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> s) {
        exeStack = s;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> d) {
        symTable = d;
    }

    public MyIList<Value> getOutList() {
        return out;
    }

    public void setOutList(MyIList<Value> l) {
        out = l;
    }

    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<String, BufferedReader> d) {
        fileTable = d;
    }

    public String toString() {
        return "ExeStack:\n" + exeStack.toString() +
                "SymTable:\n" + symTable.toString() +
                "Out:\n" + out.toString() + "\n" +
                "FileTable:\n" + fileTable.toString() + "\n";
    }
}
