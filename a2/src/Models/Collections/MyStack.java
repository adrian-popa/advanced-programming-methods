package Models.Collections;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private final Stack<T> stk;

    public MyStack() {
        stk = new Stack<>();
    }

    public T pop() {
        return stk.pop();
    }

    public void push(T value) {
        stk.push(value);
    }

    public boolean isEmpty() {
        return stk.empty();
    }

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