package edu.course.lab06;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Stack<T> {

    private final List<T> elements = new ArrayList<>();

    public void push(T value) {
        Objects.requireNonNull(value);
        elements.add(value);
    }

    public T pop() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return elements.remove(elements.size() - 1);
    }

    public T peek() {
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return elements.get(elements.size() - 1);
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}