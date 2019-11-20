package Repositories;

import Exceptions.MyException;
import Models.PrgState;

public interface IRepository {

    void add(PrgState p);

    PrgState getCrtPrg();

    void logPrgStateExec();
}