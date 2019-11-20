package Models.Collections;

import Exceptions.MyException;

import java.util.HashMap;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private final HashMap<K, V> dict;

    public MyDictionary() {
        dict = new HashMap<>();
    }

    @Override
    public boolean isDefined(K key) {
        return dict.containsKey(key);
    }

    @Override
    public V lookup(K key) throws MyException {
        V value = dict.get(key);
        if (value == null)
            throw new MyException("Variable is not defined!");
        else
            return value;
    }

    @Override
    public void put(K key, V value) {
        dict.put(key, value);
    }

    @Override
    public void remove(K key) throws MyException {
        if (!dict.containsKey(key))
            throw new MyException("Key doesn't exist.");
        dict.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (K key : dict.keySet()) {
            s.append(key.toString()).append(":");
            V value = dict.get(key);
            s.append(value.toString()).append("\n");
        }

        return s.toString();
    }

    @Override
    public boolean isEmpty() {
        return dict.isEmpty();
    }
}
