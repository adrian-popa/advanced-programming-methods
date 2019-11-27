package Models.Collections;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements MyIHeap<T> {
    private HashMap<Integer, T> map;
    private Integer key;

    public MyHeap() {
        map = new HashMap<>();
        key = 0;
    }

    @Override
    public Integer allocate(T val) {
        key += 1;
        map.put(key, val);
        return key;
    }

    @Override
    public T get(Integer addr) {
        return map.get(addr);
    }

    @Override
    public void put(Integer addr, T val) {
        map.put(addr, val);
    }

    @Override
    public T deallocate(Integer addr) {
        return map.remove(addr);
    }

    @Override
    public Map<Integer, T> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Integer, T> content) {
        map = (HashMap<Integer, T>) content;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
