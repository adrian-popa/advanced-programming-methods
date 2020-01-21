package Models.Collections;

import java.util.Map;

public class MySemaphoreTable implements MyISemaphoreTable {
    private int addr = 1;
    private MyIDictionary<Integer, MyITuple> table;

    public MySemaphoreTable() {
        table = new MyDictionary<>();
    }

    @Override
    public void setSemaphoreTable(MyIDictionary<Integer, MyITuple> sem) {
        table = sem;
    }

    @Override
    public MyIDictionary<Integer, MyITuple> getSemaphoreTable() {
        return table;
    }

    @Override
    public int getSemaphoreAddress() {
        addr *= 10;
        return addr / 10;
    }

    @Override
    public void put(int index, MyITuple listPair) {
        table.put(index, listPair);
    }

    @Override
    public MyIDictionary<Integer, MyITuple> getContent() {
        return table;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (MyITuple entry : table.getContent().values()) {
            s.append("(");
            s.append(entry.getFirst());
            s.append(",{");
            for (Integer intg : entry.getSecond())
                s.append(intg).append(" ");
            s.append("},");
            s.append(entry.getThird());
            s.append(")");
            s.append("\n");
        }
        return s.toString();
    }
}
