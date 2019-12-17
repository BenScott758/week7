package listGenerator.generator;

import org.junit.jupiter.api.Test;

import arrayGenerator.scope.CharacterScope;
import arrayGenerator.scope.Scope;
import arrayGenerator.scope.StringScope;
import linkedList.list.ListAccessError;
import listGenerator.generator.ListGenerator;

import java.util.HashSet;
import java.util.Set;

class StringListGeneratorTest extends ScopedListGeneratorTest<String>
{
    @Override
    ListGenerator<String> getGenerator() {
        // TODO Auto-generated method stub
        return getGenerator(new StringScope());
    }

    @Test
    void testInRangeDogCatMouse() {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("Dog");
        alphabet.add("Cat");
        alphabet.add("Mouse");
        testWithinRange(new StringScope(alphabet),150);
    }

    @Test
    void testInSingletonRangeApartment()
    {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("Apartment");
        testWithinRange(new StringScope(alphabet),10);
    }

    @Test
    void testInAlphabet()
    {
        testWithinRange(new StringScope(),150);
    }

    @Test
    void testCoversAlphabet() throws ListAccessError
    {
        testCoversRange(new StringScope());
    }

    @Test
    void testCoversSingletonCastries() throws ListAccessError
    {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("Castries");
        testCoversRange(new StringScope(alphabet));
    }
}
