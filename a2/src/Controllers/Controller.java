package Controllers;

import Exceptions.MyException;

import Models.Collections.MyIStack;
import Models.PrgState;
import Models.Stmts.IStmt;

import Repositories.IRepository;

public class Controller {
    private final IRepository repo;

    public Controller(IRepository r) {
        repo = r;
    }

    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        if (stk.isEmpty())
            throw new MyException("PrgState stack is empty");
        IStmt currentStmt = stk.pop();
        return currentStmt.execute(state);
    }

    public String allSteps() throws MyException {
        StringBuilder s = new StringBuilder();

        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface

        // here you can display the prg state
        while (!prg.getStk().isEmpty()) {
            oneStep(prg);
            s.append(prg.toString());
        }

        return s.toString();
    }
}