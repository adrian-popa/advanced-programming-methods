package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyISemaphoreTable;
import Models.Collections.MyIStack;
import Models.Collections.MyITuple;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.IntType;
import Models.Types.Type;
import Models.Values.IntValue;
import Models.Values.Value;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStmt implements IStmt {
    private final Exp var;

    public AcquireStmt(Exp var) {
        this.var = var;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        ReentrantLock lock = new ReentrantLock();

        MyIStack<IStmt> stk = state.getExeStack();

        if (state.getSymTable().isDefined(var.toString())) {
            Value v = state.getSymTable().lookup(var.toString());
            if (v.getType().equals(new IntType())) {
                lock.lock();

                int foundIndex = ((IntValue) state.getSymTable().lookup(var.toString())).getValue();
                MyISemaphoreTable semaphoreTable = state.getSemaphoreTable();

                if (!semaphoreTable.getSemaphoreTable().isDefined(foundIndex))
                    throw new MyException("No such index in the sem table");
                else {
                    MyITuple entry = semaphoreTable.getSemaphoreTable().lookup(foundIndex);

                    int n1 = entry.getFirst();
                    List<Integer> list1 = entry.getSecond();
                    int n2 = entry.getThird();

                    int nl = list1.size();
                    if ((n1 - n2) > nl) {
                        if (list1.contains(state.getId()))
                            return null;
                        else {
                            entry.getSecond().add(state.getId());
                            semaphoreTable.getSemaphoreTable().put(foundIndex, entry);
                        }
                    } else
                        stk.push(new AcquireStmt(var));
                }

                lock.unlock();
            } else
                throw new MyException("Value isn't of type IntType");
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
        return "acquire(" + var + ")";
    }
}
