package org.example;

public class Queue<T> {
    private final int capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
    }

    public int length() {
        return 0;
    }

    public int capacity() {
        return capacity;
    }
}
