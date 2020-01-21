package Models.Collections;

import java.util.ArrayList;
import java.util.List;

public class MyTuple implements MyITuple {

    private Integer first;
    private List<Integer> second;
    private Integer third;

    public MyTuple() {
        first = 0;
        second = new ArrayList<>();
        third = 0;
    }

    public MyTuple(Integer f, List<Integer> s, Integer t) {
        first = f;
        second = s;
        third = t;
    }

    @Override
    public int getFirst() {
        return first;
    }

    @Override
    public List<Integer> getSecond() {
        return second;
    }

    @Override
    public int getThird() {
        return third;
    }

    @Override
    public String toString() {
        return "(" + first + " , " + second.toString() + ", " + third + ")";
    }
}
