package Models.Collections;

import Exceptions.MyException;

public interface MyIDictionary<K, V> {

    boolean isDefined(K key);

    V lookup(K key) throws MyException;

    void update(K key, V value);

    void put(K key, V value);

    boolean isEmpty();
}