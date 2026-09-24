package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseToolkitTest {
    /**
     * EvenNumber
     */ 
    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);

        assertTrue(result);
    }

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }
    /**
     * isPrime
     */ 
    @Test
    void testIsPrime() {
        assertFalse(CourseToolkit.isPrime(1));
        assertTrue(CourseToolkit.isPrime(2));
        assertTrue(CourseToolkit.isPrime(5));
        assertFalse(CourseToolkit.isPrime( 24));
        assertFalse(CourseToolkit.isPrime(49));
    }
    /**
     * isPalindrome
     */
    @Test
    void isPalindrome() {
        boolean result1 = CourseToolkit.isPalindrome("кабак");

        boolean result2 = CourseToolkit.isPalindrome("Кабак");

        boolean result3 = CourseToolkit.isPalindrome(" кабак");

        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
    }
    /**
     * Average
     */
    @Test
    void average() {
        int[] values = {10, 20, 30};
        double result = CourseToolkit.average(values);
        assertEquals(20.0, result);
    }

    @Test
    void calculatesAverageForNegativeNumbers() {
        int[] values = {-10, -20, -30};
        double result = CourseToolkit.average(values);
        assertEquals(-20.0, result);
    }

    @Test
    void calculatesFractionalAverage() {
        int[] values = {1, 2};
        double result = CourseToolkit.average(values);
        assertEquals(1.5, result);
    }

    @Test
    void averageDoesNotModifyArray() {
        int[] values = {10, 20, 30};

        CourseToolkit.average(values);

        assertEquals(10, values[0]);
        assertEquals(20, values[1]);
        assertEquals(30, values[2]);
    }

    @Test
    void averageThrowsExceptionForNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CourseToolkit.average(null)
        );
    }

    @Test
    void averageThrowsExceptionForEmptyArray() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CourseToolkit.average(new int[0])
        );
    }

}



    