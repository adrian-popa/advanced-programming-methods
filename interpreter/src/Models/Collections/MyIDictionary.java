package Models.Collections;

import Exceptions.MyException;

import java.util.Map;

public interface MyIDictionary<K, V> {

    boolean isDefined(K key);

    V lookup(K key) throws MyException;

    void put(K key, V value);

    void remove(K key) throws MyException;

    Map<K, V> getContent();

    MyIDictionary<K, V> clone();
}
