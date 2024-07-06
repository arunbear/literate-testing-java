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
        if (queue.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(queue.removeFirst());
        }
    }

    public void enqueue(T item) {
        if (queue.size() == capacity) {
           return;
        }
        queue.add(item);
    }
}
