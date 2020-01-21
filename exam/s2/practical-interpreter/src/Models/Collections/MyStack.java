package Models.Collections;

import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private final Stack<T> stk;

    public MyStack() {
        stk = new Stack<>();
    }

    @Override
    public T pop() {
        return stk.pop();
    }

    @Override
    public void push(T value) {
        stk.push(value);
    }

    @Override
    public boolean isEmpty() {
        return stk.empty();
    }

    @Override
    public List<T> getValues() {
        return stk.subList(0, stk.size());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (stk.clone() instanceof Stack) {
            // noinspection unchecked
            Stack<T> stkCopy = (Stack<T>) stk.clone();

            while (!stkCopy.isEmpty()) {
                T val = stkCopy.pop();
                s.append(val.toString()).append("\n");
            }
        }

        return s.toString();
    }
}
