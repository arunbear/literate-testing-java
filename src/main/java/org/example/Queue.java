package org.example;

import java.util.ArrayList;
import java.util.Optional;

public class Queue<T> {
    private final int capacity;
    private final ArrayList<T> queue = new ArrayList<>();

    public Queue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    public int length() {
        return queue.size();
    }

    public int capacity() {
        return capacity;
    }

    public Optional<T> dequeue() {
        return Optional.empty();
    }

    public void enqueue(T item) {
        queue.add(item);
    }
}
