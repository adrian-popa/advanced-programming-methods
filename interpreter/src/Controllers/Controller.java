package Controllers;

import Exceptions.MyException;
import Models.Collections.MyIStack;
import Models.PrgState;
import Models.Stmts.IStmt;
import Models.Values.RefValue;
import Models.Values.Value;
import Repositories.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            prg.getHeap().setContent(safeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getContent().values(), prg.getHeap().getContent().values()),
                    prg.getHeap().getContent()));
            repo.logPrgStateExec();
        }
    }

    private Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heapValues) {
        return Stream.concat(
                heapValues.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {
                            RefValue v1 = (RefValue) v;
                            return v1.getAddr();
                        }),
                symTableValues.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {
                            RefValue v1 = (RefValue) v;
                            return v1.getAddr();
                        })
        ).collect(Collectors.toList());
//        return symTableValues.stream()
//                .filter(v -> v instanceof RefValue)
//                .map(v -> {
//                    RefValue v1 = (RefValue) v;
//                    return v1.getAddr();
//                })
//                .collect(Collectors.toList());
    }
}