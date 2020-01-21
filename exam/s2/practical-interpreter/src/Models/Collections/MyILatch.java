package Models.Collections;

import java.util.Collection;
import java.util.HashMap;

public interface MyILatch<K, V> {

    void put(K key, V value);

    V get(K key);

    Collection<K> keys();

    Collection<V> values();

    void remove(K key);

    boolean containsKey(K key);

    String toString();

    HashMap<K, V> getContent();
}
