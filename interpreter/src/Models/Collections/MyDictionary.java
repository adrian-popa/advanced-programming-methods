package Models.Collections;

import Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

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
    public Map<K, V> getContent() {
        return dict;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public MyIDictionary<K, V> clone() {
        MyIDictionary<K, V> copy = new MyDictionary<>();
        for (K k : dict.keySet())
            copy.put(k, dict.get(k));
        return copy;
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
}
