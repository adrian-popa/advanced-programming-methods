package Models.Collections;

import Exceptions.MyException;

public interface MyIDictionary<K, V> {

    boolean isDefined(K key);

    V lookup(K key) throws MyException;

    void put(K key, V value);

    void remove(K key) throws MyException;

    boolean isEmpty();
}