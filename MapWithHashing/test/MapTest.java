import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /*
     * Testing constructor with entries
     */
    @Test
    public void testEmptyConstructorWithContents() {

        /*
         * Setup/The call
         */
        Map<String, String> n1 = this.createFromArgsTest("bill", "joe");
        Map<String, String> n1Expected = this.createFromArgsRef("bill", "joe");

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the empty constructor
     */
    @Test
    public void testEmptyConstructorWithoutContents() {

        /*
         * Setup/The call
         */
        Map<String, String> n1 = this.createFromArgsTest();
        Map<String, String> n1Expected = this.createFromArgsRef();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the add method when n1 has contents
     */
    @Test
    public void testAddFull() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("bill", "joe");
        Map<String, String> n1Expected = this.createFromArgsRef("bill", "joe",
                "sam", "john");

        /*
         * The call
         */
        n1.add("sam", "john");

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the add method when n1 is empty
     */
    @Test
    public void testAddEmpty() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest();
        Map<String, String> n1Expected = this.createFromArgsRef("joe", "bill");

        /*
         * The call
         */
        n1.add("joe", "bill");

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the rempve method when n1 is empty after the call
     */
    @Test
    public void testRemoveEmptyAfter() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill");

        Map<String, String> n1Expected = this.createFromArgsRef();

        /*
         * The call
         */
        Pair<String, String> removed = n1.remove("joe");

        /*
         * Evaluation
         */

        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the rempve method when n1 has contents after the call
     */
    @Test
    public void testRemoveFullAfter() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill", "harry",
                "james");
        Map<String, String> n1Expected = this.createFromArgsRef("harry",
                "james");

        /*
         * The call
         */

        n1.remove("joe");

        /*
         * Evaluation
         */

        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the removeAny method when n1 has contents after the removeAny
     * call
     */
    @Test
    public void testRemoveAnyFullAfter() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill", "harry",
                "james");
        Map<String, String> n1Expected = this.createFromArgsRef("joe", "bill",
                "harry", "james");

        /*
         * The call
         */

        Pair<String, String> removed = n1.removeAny();

        /*
         * Evaluation
         */
        assertEquals(true, n1Expected.hasKey(removed.key()));
        n1Expected.remove(removed.key());
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the rempveAny method when n1 has no contents after the removeAny
     * call
     */
    @Test
    public void testRemoveAnyEmptyAfter() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill");
        Map<String, String> n1Expected = this.createFromArgsTest();

        /*
         * The call
         */
        Pair<String, String> removed = n1.removeAny();

        /*
         * Evaluation
         */
        assertEquals("joe", removed.key());
        assertEquals("bill", removed.value());
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the value method with only one pair entry
     */
    @Test
    public void testValueOneCheck() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill");
        Map<String, String> n1Expected = this.createFromArgsTest("joe", "bill");

        /*
         * The call
         */
        String check = n1.value("joe");

        /*
         * Evaluation
         */
        assertEquals("bill", check);
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the value method with two pair entry
     */
    @Test
    public void testValueTwoCheck() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill", "carl",
                "joe");
        Map<String, String> n1Expected = this.createFromArgsTest("joe", "bill",
                "carl", "joe");

        /*
         * The call
         */
        String check = n1.value("joe");

        /*
         * Evaluation
         */
        assertEquals("bill", check);
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the hasKey method with two pair entry
     */
    @Test
    public void testHasKeyTwoCheck() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill", "carl",
                "joe");
        Map<String, String> n1Expected = this.createFromArgsTest("joe", "bill",
                "carl", "joe");

        /*
         * The call
         */
        boolean check = n1.hasKey("joe");

        /*
         * Evaluation
         */
        assertEquals(true, check);
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the hasKey method with one pair entry
     */
    @Test
    public void testHasKeyOneCheck() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill");
        Map<String, String> n1Expected = this.createFromArgsTest("joe", "bill");

        /*
         * The call
         */
        boolean check = n1.hasKey("joe");

        /*
         * Evaluation
         */
        assertEquals(true, check);
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the hasKey method with one pair entry
     */
    @Test
    public void testSizeEmpty() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest();
        Map<String, String> n1Expected = this.createFromArgsTest();

        /*
         * The call
         */
        int check1 = n1.size();
        int check2 = n1Expected.size();

        /*
         * Evaluation
         */
        assertEquals(0, check1);
        assertEquals(check2, check1);
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the hasKey method with one pair entry
     */
    @Test
    public void testSizeFull() {

        /*
         * Setup
         */
        Map<String, String> n1 = this.createFromArgsTest("joe", "bill");
        Map<String, String> n1Expected = this.createFromArgsTest("joe", "bill");

        /*
         * The call
         */
        int check1 = n1.size();
        int check2 = n1Expected.size();

        /*
         * Evaluation
         */
        assertEquals(1, check1);
        assertEquals(check2, check1);
        assertEquals(n1Expected, n1);

    }

}
