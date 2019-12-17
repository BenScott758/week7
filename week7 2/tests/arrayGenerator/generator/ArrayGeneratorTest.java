package arrayGenerator.generator;

import linkedList.list.ListAccessError;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Provides some tests for array generators:
 */

abstract class ArrayGeneratorTest<T> {

    abstract ArrayGenerator<T> getGenerator();

    /**
     * This test makes sure that a generator does produce an array of the required length.
     *
     * @param size the length of the array that should be generated
     */
    private void testSize(int size) {
        ArrayGenerator generator = getGenerator();
        assertEquals(size,generator.getArray(size).length);
    }
    /**
     * This test makes sure that every item is readable in the list. Also used for testing access times.
     *
     * @param size the length of the array that should be generated
     */
    private void testRead(int size, boolean useIterator) throws ListAccessError
    {
        ArrayGenerator<T> generator = getGenerator();
        T[] list = generator.getArray(size);

        if (useIterator)
            for (T item : list)
                item.toString();

        else
            for (int i = 0; i < size; i++)
                list[i].toString();
    }

    @Test
    void testZero() {
        testSize(0);
    }

    @Test
    void testOne() {
        testSize(1);
    }

    @Test
    void testTwo() {
        testSize(2);
    }

    @Test
    void testTwentyThree() {
        testSize(23);
    }

    @Test
    void testLarge() {
        testSize(3628751);
    }

    @Test
    void testMinusOne() {
        assertThrows(NegativeArraySizeException.class, ()->testSize(-1));
    }

    @Test
    void testMinusFiftyEight() {
        assertThrows(NegativeArraySizeException.class, ()->testSize(-58));
    }
}