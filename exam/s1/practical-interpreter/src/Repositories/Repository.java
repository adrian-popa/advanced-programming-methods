package Repositories;

import Models.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> programs;
    private int currentIndex;
    private final String logFilePath;

    public Repository(PrgState program, String logFilePath) {
        programs = new ArrayList<>();
        programs.add(program);
        currentIndex = 0;
        this.logFilePath = logFilePath;
    }

    @Override
    public void add(PrgState p) {
        programs.add(p);
    }

    @Override
    public void logPrgStateExec(PrgState p) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, currentIndex != 0)));
        logFile.print(p);
        logFile.close();
        currentIndex += 1;
    }

    @Override
    public List<PrgState> getPrgList() {
        return programs;
    }

    @Override
    public void setPrgList(List<PrgState> list) {
        programs = list;
    }

    @Override
    public PrgState getPrgById(int id) {
        for (PrgState p : programs)
            if (p.getId() == id)
                return p;
        return null;
    }
}
