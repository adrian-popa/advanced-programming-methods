package Models.Collections;

import Exceptions.MyException;

import java.util.Map;

public interface MyIHeap<T> {

    Integer allocate(T val);

    T lookup(Integer addr) throws MyException;

    void put(Integer addr, T val);

    T deallocate(Integer addr);

    Map<Integer, T> getContent();

    void setContent(Map<Integer, T> content);
}
