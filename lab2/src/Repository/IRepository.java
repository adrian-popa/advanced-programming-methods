package Repository;

import Model.IObject;

public interface IRepository {

    void addObject(IObject object) throws Exception;

    void removeObject(int index) throws Exception;

    String getByVolume(int volume);
}
