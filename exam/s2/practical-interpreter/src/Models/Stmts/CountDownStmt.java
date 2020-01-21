package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyILatch;
import Models.Collections.MyIList;
import Models.PrgState;
import Models.Types.Type;
import Models.Values.IntValue;
import Models.Values.Value;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStmt implements IStmt {
    private final String var;

    public CountDownStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        ReentrantLock lock = new ReentrantLock();

        if (state.getSymTable().isDefined(var)) {
            int foundIndex = ((IntValue) state.getSymTable().lookup(var)).getValue();

            MyILatch<Integer, Integer> latchTable = state.getLatchTable();
            MyIList<Value> outList = state.getOutList();

            lock.lock();

            if (latchTable.containsKey(foundIndex)) {
                if (latchTable.get(foundIndex) > 0) {
                    latchTable.put(foundIndex, latchTable.get(foundIndex) - 1);
                    outList.add(new IntValue(state.getId()));
                }
            }

            lock.unlock();
        } else
            throw new MyException("Variable not defined.");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "countDown(" + var + ")";
    }
}
