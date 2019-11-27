package Models.Collections;

import java.util.Map;

public interface MyIHeap<T> {

    Integer allocate(T val);

    T get(Integer addr);

    void put(Integer addr, T val);

    T deallocate(Integer addr);

    Map<Integer, T> getContent();

    void setContent(Map<Integer, T> content);
}
