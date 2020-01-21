package Models;

import Exceptions.MyException;
import Models.Collections.*;
import Models.Stmts.IStmt;
import Models.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<String, BufferedReader> fileTable;
    private MyIHeap<Value> heap;
    private MyILatch<Integer, Integer> latchTable;
    private int id;
    private static int globalId = 1;

    public PrgState(IStmt prg) {
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        out = new MyList<>();
        fileTable = new MyDictionary<>();
        heap = new MyHeap<>();
        latchTable = new MyLatch<>();

        id = 1;
        exeStack.push(prg);
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot,
                    MyIDictionary<String, BufferedReader> ftbl, MyIHeap<Value> h, MyILatch<Integer, Integer> latchtbl, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ftbl;
        heap = h;
        latchTable = latchtbl;
        // originalProgram = deepCopy(prg); //recreate the entire original prg
        id = getGlobalId();
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

    public MyIHeap<Value> getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap<Value> h) {
        heap = h;
    }

    public MyILatch<Integer, Integer> getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(MyILatch<Integer, Integer> latchtbl) {
        latchTable = latchtbl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException, IOException {
        if (exeStack.isEmpty())
            throw new MyException("PrgState stack is empty");
        IStmt currentStmt = exeStack.pop();
        return currentStmt.execute(this);
    }

    public synchronized int getGlobalId() {
        globalId *= 10;
        return globalId;
    }

    public String toString() {
        return "PrgState with id: " + id + "\n" +
                "ExeStack:\n" + exeStack.toString() +
                "SymTable:\n" + symTable.toString() +
                "Out:\n" + out.toString() + "\n" +
                "FileTable:\n" + fileTable.toString() +
                "Heap:\n" + heap.toString() + "\n" +
                "LatchTable:\n" + latchTable.toString() + "\n";
    }
}
