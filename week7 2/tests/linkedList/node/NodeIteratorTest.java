package linkedList.node;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import arrayGenerator.scope.IntegerScope;
import linkedList.list.SingleLinkedList;
import linkedList.list.ListAccessError;
import listGenerator.generator.ScopedListGenerator;

public class NodeIteratorTest
{
    @Test
    public void TestIterator() throws ListAccessError
    {
        ScopedListGenerator<Integer> generator = new ScopedListGenerator<Integer>(new IntegerScope());
        SingleLinkedList<Integer> list = generator.getList(0);

        for (int i=0; i<10; i++)
            list.add(i);

        Iterator<Integer> iterator = list.iterator();
        assertNotNull(iterator == null);
        assertTrue(iterator.hasNext());

        int c = 0;
        for (Integer i : list)
        {
            if (i != c)
                fail(c + " != " + i + " is in place at " + c);

            c++;
        }

        if (c != 9)
            fail("Failure to read list with iterator.");
    }
}
