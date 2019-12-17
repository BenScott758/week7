package listGenerator.generator;

import linkedList.list.SingleLinkedList;
import arrayGenerator.scope.Scope;
import linkedList.list.ListAccessError;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Implements additional tests, above those defined in {@link arrayGenerator.generator.ArrayGeneratorTest}, to
 * check that:
 * <ul>
 *     <li> Values in generated arrays are within the specified arrayGenerator.scope</li>
 *     <li> All values in the arrayGenerator.scope are generated.  Note: this is a statistical test, and may produce a
 *          false fail.  See {@link #testCoversRange(Scope)} for details.</li>
 * </ul>
 */

abstract class ScopedListGeneratorTest<T> extends ListGeneratorTest<T> {
    ScopedListGenerator<T> getGenerator(Scope<T> scope)
    {
        return new ScopedListGenerator<T>(scope);
    }

    /**
     * Check that the array contents are within the specified range.
     * @param scope the arrayGenerator.scope to be tested
     * @param size the size of the array
     */
    void testWithinRange(Scope<T> scope, int size)
    {
        ScopedListGenerator<T> generator = getGenerator(scope);
        try
        {
            SingleLinkedList<T> array = generator.getList(size);
            for (T entry : array)
            {
                if (!scope.contains(entry))
                {
                    fail("Array entry is not within the expected bounds");
                }
            }
        }
        catch (Exception e)
        {
            fail(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    /**
     * When testing that a generated array contains all the values within the generator's arrayGenerator.scope,
     * only a statistical check can be carried out.  Since values are generated at random, there is
     * always a chance that a generated array, however large, may not contain all the values.
     *
     * However, the larger the generated array, the smaller this chance of a "false fail" is.
     *
     * ACCEPTABLE_FALSE_FAIL specifies the acceptable probability for such a false fail occuring.
     */
    private final static double ACCEPTABLE_FALSE_FAIL = 0.01;

    /**
     * Check that a generated array contains all the expected values.
     *
     * Note: since values are generated at random, there is always a chance that a
     * generated array may not contain all values within the range.
     *
     * The size of the generated array is set to
     *     1/c(n*H(n))
     * where
     *    c    is the probability that the array will not contain all values (i.e. the
     *         chance of a "false fail") - so the ACCEPTABLE_FALSE_FAIL above.
     *    n    is the number of values within the arrayGenerator.scope of this generator, and
     *    H(n) is the nth harmonic number (ie. 1/1 + 1/2 + ... + 1/n)
     *  H(n), in turn is approximately n*ln(n) + gamma*n, where gamma is approximately
     *  0.5772156649.
     *  See See <a href="https://en.wikipedia.org/wiki/Coupon_collector%27s_problem">https://en.wikipedia.org/wiki/Coupon_collector%27s_problem</a>.
     *
     * @param scope the arrayGenerator.scope to be tested.
     * @throws ListAccessError
     */
    void testCoversRange(Scope<T> scope) throws ListAccessError {
        double gamma = 0.5772156647;
        ScopedListGenerator<T> generator = getGenerator(scope);
        int testArraySize = (int)
                Math.ceil(
                        (1/ACCEPTABLE_FALSE_FAIL)*(scope.size()*Math.log(scope.size())+gamma*scope.size())
                );
        SingleLinkedList<T> array = generator.getList(testArraySize);
        Set<T> seen = new HashSet<T>();
        for (T entry : array) {
            if (!seen.contains(entry)) {
                if (!scope.contains(entry)) {
                    fail("Array entry is not within expected bounds.");
                } else {
                    seen.add(entry);
                    if (seen.size() == scope.size()) {
                        break;
                    }
                }
            }
        }
        if (seen.size() < scope.size()) {
            StringBuilder out = new StringBuilder();
            for (T entry: array)
                out.append(entry);

            fail("Array did not contain all expected values.\n"
                    + "Array is " + out.toString() + "\n"
                    + "Scope is " + scope + "\n"
                    + "Saw " + seen +"\n"
                    + "Note: there is a " + ACCEPTABLE_FALSE_FAIL + " chance that this is a false fail.  See the method documentation for details.");
        }
    }
}
