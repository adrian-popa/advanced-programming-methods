package Models.Collections;

public interface MyIList<T> {

    void add(T element);

    int size();

    T get(int i);
}
