package Models.Collections;

import java.util.Map;

public interface MyISemaphoreTable {

    void setSemaphoreTable(MyIDictionary<Integer, MyITuple> sem);

    MyIDictionary<Integer, MyITuple> getSemaphoreTable();

    int getSemaphoreAddress();

    void put(int index, MyITuple listPair);

    MyIDictionary<Integer, MyITuple> getContent();

    String toString();
}
