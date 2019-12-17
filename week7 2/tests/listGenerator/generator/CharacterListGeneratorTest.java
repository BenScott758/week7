package listGenerator.generator;

import org.junit.jupiter.api.Test;
import arrayGenerator.scope.CharacterScope;

import linkedList.list.ListAccessError;


class CharacterListGeneratorTest extends ScopedListGeneratorTest<Character>
{
    @Override
    ListGenerator<Character> getGenerator() {
        // TODO Auto-generated method stub
        return getGenerator(new CharacterScope());
    }

    @Test
    void testInRangeABCD() {
        testWithinRange(new CharacterScope("LMNOP"),150);
    }

    @Test
    void testInSingletonRangeZ()
    {
        testWithinRange(new CharacterScope("Z"),10);
    }

    @Test
    void testInAlphabet()
    {
        testWithinRange(new CharacterScope(),150);
    }

    @Test
    void testCoversAlphabet() throws ListAccessError {
        testCoversRange(new CharacterScope());
    }

    @Test
    void testCoversSingletonV() throws ListAccessError
    {
        testCoversRange(new CharacterScope("V"));
    }
}
