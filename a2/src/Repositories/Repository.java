package Repositories;

import Models.Collections.MyIList;
import Models.Collections.MyList;
import Models.PrgState;

public class Repository implements IRepository {
    private final MyIList<PrgState> programs;
    private final int current;

    public Repository(PrgState program) {
        current = 0;
        programs = new MyList<>();
        programs.add(program);
    }

    public void add(PrgState p) {
        programs.add(p);
    }

    public PrgState getCrtPrg() {
        return programs.get(current);
    }
}