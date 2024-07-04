package org.example;

import java.util.ArrayList;

public class Stack<T> {

    private final ArrayList<T> stack = new ArrayList<>();

    public int depth() {
        return stack.size();
    }

    public T top() {
        if (stack.isEmpty())
            throw new IllegalStateException();
        return stack.getLast();
    }

    public void pop() {
        if (stack.isEmpty())
            throw new IllegalStateException();
        stack.removeLast();
    }

    public void push(T item) {
        stack.add(item);
    }
}
