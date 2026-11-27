package edu.course.lab06;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void newStackIsEmpty() {
        Stack<String> stack = new Stack<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushAddsElement() {
        Stack<String> stack = new Stack<>();

        stack.push("Java");

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @Test
    void peekReturnsElementWithoutRemovingIt() {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void popRemovesLastElement() {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.pop());
        assertEquals(1, stack.size());
    }

    @Test
    void popOnEmptyStackThrowsException() {
        Stack<String> stack = new Stack<>();

        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void peekOnEmptyStackThrowsException() {
        Stack<String> stack = new Stack<>();

        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void pushNullThrowsException() {
        Stack<String> stack = new Stack<>();

        assertThrows(
                NullPointerException.class,
                () -> stack.push(null)
        );
    }

    @Test
    void stackWorksWithInteger() {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }
}