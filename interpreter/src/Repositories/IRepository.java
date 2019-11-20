package Repositories;

import Models.PrgState;

import java.io.IOException;

public interface IRepository {

    void add(PrgState p);

    PrgState getCrtPrg();

    void logPrgStateExec() throws IOException;
}