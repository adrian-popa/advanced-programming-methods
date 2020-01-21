package Models.Collections;

import java.util.Collection;
import java.util.HashMap;

public class MyLatch<K, V> implements MyILatch<K, V> {
    private HashMap<K, V> table;

    public MyLatch() {
        table = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        table.put(key, value);
    }

    @Override
    public V get(K key) {
        return table.get(key);
    }

    @Override
    public Collection<K> keys() {
        return table.keySet();
    }

    @Override
    public Collection<V> values() {
        return table.values();
    }

    @Override
    public void remove(K key) {
        table.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return table.containsKey(key);
    }

    @Override
    public HashMap<K, V> getContent() {
        return table;
    }

    public String toString() {
        return "LatchTable{" + " " + table.toString() + " " + "}";
    }
}
