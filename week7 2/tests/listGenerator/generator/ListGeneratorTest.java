package listGenerator.generator;

import linkedList.list.SingleLinkedList;
import linkedList.list.ListAccessError;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This is the testing which provides some tests for list generators:
 */

abstract class ListGeneratorTest<T>
{

    abstract ListGenerator<T> getGenerator();

    /**
     * This test makes sure that a generator does produce an array of the required length.
     *
     * @param size this is the length of the array that should be generated
     */
    private void testSize(int size) throws ListAccessError
    {
        ListGenerator<T> generator = getGenerator();

        assertEquals(size,generator.getList(size).getSize());
    }


    /**
     * This test is used for testing access times and makes sure that every item is legible in the list.
     *
     * @param size the length of the array that ought to be generated
     */
    private void testRead(int size, boolean useIterator) throws ListAccessError
    {
        ListGenerator<T> generator = getGenerator();
        SingleLinkedList<T> list = generator.getList(size);

        if (useIterator)
            for (T item : list)
                item.toString();

        else
            for (int i = 0; i < size; i++)
                list.get(i);
    }

    @Test
    void testZero() throws ListAccessError
    {
        testSize(0);
    }

    @Test
    void testOne() throws ListAccessError
    {
        testSize(1);
    }

    @Test
    void testTwo() throws ListAccessError
    {
        testSize(2);
    }

    @Test
    void testTwentyThree() throws ListAccessError
    {
        testSize(23);
    }

    @Test
    void testLarge() throws ListAccessError
    {
        testSize(3628751);
    }

    @Test
    void testRead() throws ListAccessError
    {
        testRead(10000, false);
    }
    @Test
    void testReadIterator() throws ListAccessError
    {
        testRead(10000, true);
    }

    @Test
    void testMinusOne()
    {
        assertThrows(NegativeArraySizeException.class, ()->testSize(-1));
    }

    @Test
    void testMinusFiftyEight()
    {
        assertThrows(NegativeArraySizeException.class, ()->testSize(-58));
    }
}