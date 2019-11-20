package Controllers;

import Exceptions.MyException;
import Models.Collections.MyIStack;
import Models.PrgState;
import Models.Stmts.IStmt;
import Repositories.IRepository;

import java.io.IOException;

public class Controller {
    private final IRepository repo;

    public Controller(IRepository r) {
        repo = r;
    }

    private void oneStep(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty())
            throw new MyException("PrgState stack is empty");
        IStmt currentStmt = stk.pop();
        currentStmt.execute(state);
    }

    public void allSteps() throws MyException, IOException {
        PrgState prg = repo.getCrtPrg();
        repo.logPrgStateExec();

        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            repo.logPrgStateExec();
        }
    }
}