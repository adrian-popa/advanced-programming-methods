package Controllers;

import Exceptions.MyException;
import Models.PrgState;
import Models.Values.RefValue;
import Models.Values.Value;
import Repositories.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private final IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository r) {
        repo = r;
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws MyException, InterruptedException {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        repo.setPrgList(prgList);
    }

    public void allStep() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        while (prgList.size() > 0) {
            conservativeGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }

        executor.shutdownNow();
        prgList = removeCompletedPrg(repo.getPrgList());
        repo.setPrgList(prgList);
    }

    private void conservativeGarbageCollector(List<PrgState> prgList) {
        prgList.forEach(p -> p.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(p.getSymTable().getContent().values(),
                p.getHeap().getContent().values()), p.getHeap().getContent()))
        );
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

    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
}
