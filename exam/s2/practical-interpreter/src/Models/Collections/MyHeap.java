package Models.Collections;

import Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements MyIHeap<T> {
    private HashMap<Integer, T> dict;
    private Integer addr;

    public MyHeap() {
        dict = new HashMap<>();
        addr = 0;
    }

    @Override
    public Integer allocate(T val) {
        addr += 1;
        dict.put(addr, val);
        return addr;
    }

    @Override
    public T lookup(Integer addr) throws MyException {
        T val = dict.get(addr);
        if (val == null)
            throw new MyException("Address doesnt have a value!");
        else
            return val;
    }

    @Override
    public void put(Integer addr, T val) {
        dict.put(addr, val);
    }

    @Override
    public T deallocate(Integer addr) {
        return dict.remove(addr);
    }

    @Override
    public Map<Integer, T> getContent() {
        return dict;
    }

    @Override
    public void setContent(Map<Integer, T> content) {
        dict = (HashMap<Integer, T>) content;
    }

    @Override
    public String toString() {
        return dict.toString();
    }
}
