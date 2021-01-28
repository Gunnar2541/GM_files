package ee.bcs.valiit.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Lesson1MathUtilTest {

    @Test
    void test() {
        int result = Lesson1MathUtil.min(3, 4);
        assertEquals(3, result);
        int result2 = Lesson1MathUtil.min(3, 1);
        assertEquals(1, result2);

    }

    @Test
    void max() {
        assertEquals(5, Lesson1MathUtil.max(3, 5));
        assertEquals(3, Lesson1MathUtil.max(3, -5));

        double expected = 5;
        double result = 5;
        assertEquals(expected, result, 0);
    }

    @Test
    void abs() {
        assertEquals(18, Lesson1MathUtil.abs(-18));
        assertEquals(0, Lesson1MathUtil.abs(0));
    }

    @Test
    void isEven() {
        assertFalse(Lesson1MathUtil.isEven(3));
        assertEquals(false, Lesson1MathUtil.isEven(5)); //nii pole õige, kuigi tulemus on õige
        assertTrue(Lesson1MathUtil.isEven(8));
    }


    @Test
    void maxer() {
        assertEquals(5, Lesson1MathUtil.maxer(3, 5, -1));
        assertEquals(100, Lesson1MathUtil.maxer(3, -5, 100));
        assertEquals(250, Lesson1MathUtil.maxer(250, -5, 100));
    }


    @Test
    void min3() {
        assertEquals(-1, Lesson1MathUtil.min3(3, 5, -1));
        assertEquals(3, Lesson1MathUtil.min3(3, 5, 100));
        assertEquals(-5, Lesson1MathUtil.min3(3, -5, 100));
    }
}
