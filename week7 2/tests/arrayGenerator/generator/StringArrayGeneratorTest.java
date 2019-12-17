package arrayGenerator.generator;

import org.junit.jupiter.api.Test;
import arrayGenerator.scope.Scope;
import arrayGenerator.scope.StringScope;

import java.util.HashSet;
import java.util.Set;

/**
 * Some basic tests for string array generators.
 *
 * @author Hugh Osborne
 * @version October 2019
 */

class StringArrayGeneratorTest extends ScopedArrayGeneratorTest<String> {

    @Override
    ScopedArrayGenerator<String> getGenerator(Scope<String> scope) {
        return new StringArrayGenerator(scope);
    }

    @Override
    ArrayGenerator<String> getGenerator() {
        return new StringArrayGenerator();
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
    void testCoversAlphabet()
    {
        testCoversRange(new StringScope());
    }

    @Test
    void testCoversSingletonCastries()
    {
        Set<String> alphabet = new HashSet<String>();
        alphabet.add("Castries");
        testCoversRange(new StringScope(alphabet));
    }
}