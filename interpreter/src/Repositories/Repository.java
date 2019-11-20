package Repositories;

import Models.Collections.MyIList;
import Models.Collections.MyList;
import Models.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {
    private final MyIList<PrgState> programs;
    private final int currentIndex;
    private final String logFilePath;

    public Repository(PrgState program, String logFilePath) {
        currentIndex = 0;
        programs = new MyList<>();
        programs.add(program);
        this.logFilePath = logFilePath;
    }

    @Override
    public void add(PrgState p) {
        programs.add(p);
    }

    @Override
    public PrgState getCrtPrg() {
        return programs.get(currentIndex);
    }

    @Override
    public void logPrgStateExec() throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.print(getCrtPrg());
        logFile.close();
    }
}