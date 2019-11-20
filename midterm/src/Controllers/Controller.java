package Controllers;

import Repository.IRepository;

public class Controller {
    private IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public String getExp() {
        return repository.getExp();
    }

    public int getResult() {
        return repository.getResult();
    }
}
