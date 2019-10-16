package Controller;

import Model.IObject;
import Repository.Repository;

public class Controller {

    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public void addObject(IObject object) throws Exception {
        this.repository.addObject(object);
    }

    public void removeObject(int index) throws Exception {
        this.repository.removeObject(index);
    }

    public String getByVolume(int volume) {
        return this.repository.getByVolume(volume);
    }
}
