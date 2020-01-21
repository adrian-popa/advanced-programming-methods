package Models.Stmts;

import Exceptions.MyException;
import Models.Collections.MyIDictionary;
import Models.Collections.MyIHeap;
import Models.Collections.MyISemaphoreTable;
import Models.Collections.MyTuple;
import Models.Exps.Exp;
import Models.PrgState;
import Models.Types.IntType;
import Models.Types.RefType;
import Models.Types.Type;
import Models.Values.IntValue;
import Models.Values.Value;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class NewSemaphoreStmt implements IStmt {
    private final Exp var;
    private final Exp exp1;
    private final Exp exp2;

    public NewSemaphoreStmt(Exp var, Exp exp1, Exp exp2) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        ReentrantLock lock = new ReentrantLock();

        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();
        MyISemaphoreTable semTable = state.getSemaphoreTable();

        Value number1 = exp1.eval(symTable, heap);
        Value number2 = exp2.eval(symTable, heap);
        int n1 = ((IntValue) number1).getValue();
        int n2 = ((IntValue) number2).getValue();
        int addr = semTable.getSemaphoreAddress();
        semTable.put(addr, new MyTuple(n1, new ArrayList<>(), n2));

        if (symTable.isDefined(var.toString()) && symTable.lookup(var.toString()).getType().equals(new IntType())) {
            lock.lock();
            symTable.put(var.toString(), new IntValue(addr));
            lock.unlock();
        } else
            throw new MyException("Error when creating a semaphore.");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var.toString());
        Type typexp1 = exp1.typecheck(typeEnv);
        Type typexp2 = exp2.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp1)) && typevar.equals(new RefType(typexp2)))
            return typeEnv;
        else
            throw new MyException("NEW Semaphore stmt: right hand side and left hand side have different types");
    }

    @Override
    public String toString() {
        return "newSemaphore(" + var + "," + exp1.toString() + ',' + exp2.toString() + ")";
    }

}
