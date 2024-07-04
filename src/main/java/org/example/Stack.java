package org.example;

public class Stack<T> {

    public int depth() {
        return 0;
    }

    public T top() {
        throw new IllegalStateException();
    }

    public void pop() {
        throw new IllegalStateException();
    }
}
