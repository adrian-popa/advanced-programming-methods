package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyILatch;
import Models.PrgState;
import Models.Types.Type;
import Models.Values.IntValue;

public class AwaitStmt implements IStmt {
    private final String var;

    public AwaitStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if (state.getSymTable().isDefined(var)) {
            int foundIndex = ((IntValue) state.getSymTable().lookup(var)).getValue();

            MyILatch<Integer, Integer> latchTable = state.getLatchTable();

            if (!latchTable.containsKey(foundIndex))
                throw new MyException("No such index in the latch table");
            else {
                if (latchTable.get(foundIndex) != 0)
                    state.getExeStack().push(this);
            }
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
        return "await(" + var + ")";
    }
}
