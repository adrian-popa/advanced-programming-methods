package Repositories;

import Models.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {

    void add(PrgState p);

    void logPrgStateExec(PrgState p) throws IOException;

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> list);
}
