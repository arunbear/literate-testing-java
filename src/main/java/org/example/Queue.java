package org.example;

import java.util.Optional;

public class Queue<T> {
    private final int capacity;

    public Queue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    public int length() {
        return 0;
    }

    public int capacity() {
        return capacity;
    }

    public Optional<T> dequeue() {
        return Optional.empty();
    }
}
