package ee.bcs.valiit.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson3Test {

    @Test
    void sum() {
        int[] x = {1, 2, 3};
        assertEquals(6, Lesson3.sum(x));
        int[] z = {2, 4, 6};
        assertEquals(12, Lesson3.sum(z));
    }

    @Test
    void factorial() {
        assertEquals(40320, Lesson3.factorial(8));
        assertEquals(0, Lesson3.factorial(0));
    }

    @Test
    void sort() {
        int[] a = {6, 2, 3, 8};
        int[] z = {8, 6, 3, 2};
        assertArrayEquals(z, Lesson3.sort(a));
    }

    @Test
    void reverseString() {
        assertEquals("siin olin mina ", Lesson3.reverseString("mina olin siin"));  ;
    }

    @Test
    void isPrime() {
        assertTrue(Lesson3.isPrime(5));
        assertFalse(Lesson3.isPrime(6));
    }

}