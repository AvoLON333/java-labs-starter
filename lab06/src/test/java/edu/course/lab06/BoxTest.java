package edu.course.lab06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxTest {

    @Test
    void storesString() {
        Box<String> box = new Box<>("Hello");

        assertEquals("Hello", box.get());

        box.set("Java");

        assertEquals("Java", box.get());
    }

    @Test
    void storesInteger() {
        Box<Integer> box = new Box<>(10);

        assertEquals(10, box.get());

        box.set(20);

        assertEquals(20, box.get());
    }

    @Test
    void preventsWrongType() {
        Box<String> box = new Box<>("Hello");

        assertEquals("Hello", box.get());
    }
}