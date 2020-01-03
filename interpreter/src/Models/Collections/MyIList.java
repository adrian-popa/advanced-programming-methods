package Models.Collections;

import java.util.List;

public interface MyIList<T> {

    void add(T element);

    int size();

    T get(int i);

    List getContent();
}
