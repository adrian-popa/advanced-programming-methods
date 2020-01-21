package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Collections.MyILatch;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.Type;
import Models.Values.IntValue;
import Models.Values.Value;

import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStmt implements IStmt {
    private final String var;
    private final Exp exp;

    static int freeLocation = -1;

    public NewLatchStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        ReentrantLock lock = new ReentrantLock();

        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        MyILatch<Integer, Integer> latchTable = state.getLatchTable();

        Value number = exp.eval(symTable, heap);
        int n = ((IntValue) number).getValue();

        lock.lock();

        ++freeLocation;
        latchTable.put(freeLocation, n);

        symTable.put(this.var, new IntValue(freeLocation));

        lock.unlock();

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "newLatch(" + var + "," + exp.toString() + ")";
    }

}
