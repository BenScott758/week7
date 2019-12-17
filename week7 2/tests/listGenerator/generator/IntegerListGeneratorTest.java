package listGenerator.generator;

import org.junit.jupiter.api.Test;

import arrayGenerator.scope.CharacterScope;
import arrayGenerator.scope.IntegerScope;
import arrayGenerator.scope.Scope;
import linkedList.list.ListAccessError;
import listGenerator.generator.ListGenerator;

class IntegerListGeneratorTest extends ScopedListGeneratorTest<Integer>
{
    @Override
    ListGenerator<Integer> getGenerator()
    {
        // TODO Auto-generated method stub
        return getGenerator(new IntegerScope());
    }

    @Test
    void testInRangeThreeToEightySeven()
    {
        testWithinRange(new IntegerScope(3,87),150);
    }

    @Test
    void testInSingletonRangeFour()
    {
        testWithinRange(new IntegerScope(4,4),10);
    }

    @Test
    void testInSingletonRangeZero()
    {
        testWithinRange(new IntegerScope(0,0),150);
    }

    @Test
    void testCoversZeroToNine() throws ListAccessError
    {
        testCoversRange(new IntegerScope(0,9));
    }

    @Test
    void testCoversSingletonZero() throws ListAccessError
    {
        testCoversRange(new IntegerScope(0,0));
    }
}