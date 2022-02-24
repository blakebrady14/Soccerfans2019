import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /*
     * ---------------------------ADD TESTS------------------------------
     */
    @Test
    public final void testAddEmpty() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");

        /*
         * The call
         */
        m.add("green");

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
    }

    /*
     * Testing add method with one entry already in m
     */
    @Test
    public final void testAddOneEntry() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue");

        /*
         * The call
         */
        m.add("blue");

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
    }

    /*
     * Testing add method with two entries already in m
     */
    @Test
    public final void testAddTwoEntries() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "red");

        /*
         * The call
         */
        m.add("red");

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
    }

    /*
     * -------------------CHANGE_TO_EXTRACTION_MODE TESTS-----------------
     */

    /*
     * Testing changeToExtractionMode with no entries
     */
    @Test
    public final void testChangeToExtractionModeZeroEntries() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        /*
         * The call
         */
        m.changeToExtractionMode();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
    }

    /*
     * Testing changeToExtractionMode with one entry
     */
    @Test
    public final void testChangeToExtractionModeOneEntry() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a");

        /*
         * The call
         */
        m.changeToExtractionMode();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
    }

    /*
     * Testing changeToExtractionMode with two entries
     */
    @Test
    public final void testChangeToExtractionModeTwoEntries() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b");

        /*
         * The call
         */
        m.changeToExtractionMode();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
    }

    /*
     * -------------------REMOVE_FIRST TESTS-----------------------------
     */

    /*
     * Testing removeFirst with one entry
     */
    @Test
    public final void testRemoveFirstOneEntry() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        /*
         * The call
         */
        String test = m.removeFirst();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals("a", test);
    }

    /*
     * Testing removeFirst with two entries
     */
    @Test
    public final void testRemoveFirstTwoEntries() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b");

        /*
         * The call
         */
        String test = m.removeFirst();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals("a", test);
    }

    /*
     * Testing removeFirst with many entries
     */
    @Test
    public final void testRemoveFirstManyEntries() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "c", "d", "e", "f");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "c", "d", "e", "f");

        /*
         * The call
         */
        String test = m.removeFirst();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals("a", test);
    }

    /*
     * -------------------IS_IN_INSERTION_MODE TESTS---------------------------
     */

    /*
     * Testing isInInsertionMode with no entries when it is true
     */
    @Test
    public final void testIsInInsertionModeNoEntriesTrue() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        /*
         * The call
         */
        boolean test = m.isInInsertionMode();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(true, test);
    }

    /*
     * Testing isInInsertionMode with entries when it is true
     */
    @Test
    public final void testIsInInsertionModeEntriesTrue() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a");

        /*
         * The call
         */
        boolean test = m.isInInsertionMode();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(true, test);
    }

    /*
     * Testing isInInsertionMode with no entries when it is false
     */
    @Test
    public final void testIsInInsertionModeNoEntriesFalse() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        /*
         * The call
         */
        boolean test = m.isInInsertionMode();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(false, test);
    }

    /*
     * Testing isInInsertionMode with entries when it is false
     */
    @Test
    public final void testIsInInsertionModeEntriesFalse() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a");

        /*
         * The call
         */
        boolean test = m.isInInsertionMode();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(false, test);
    }

    /*
     * -------------------ORDER TESTS-----------------------------
     */

    /*
     * Testing order with no entires
     */
    @Test
    public final void testOrderEmpty() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        /*
         * The call
         */
        Comparator<String> temp = m.order();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(ORDER, temp);
    }

    /*
     * Testing order with entries
     */
    @Test
    public final void testOrderFull() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a");

        /*
         * The call
         */
        Comparator<String> temp = m.order();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(ORDER, temp);
    }

    /*
     * --------------------------------SIZE TESTS-----------------------------
     */

    /*
     * Testing size with no entries
     */
    @Test
    public final void testSizeEmpty() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        /*
         * The call
         */
        int size = m.size();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(0, size);
    }

    /*
     * Testing size with one entries
     */
    @Test
    public final void testSizeOneEntry() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a");

        /*
         * The call
         */
        int size = m.size();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(1, size);
    }

    /*
     * Testing size with multiple entries
     */
    @Test
    public final void testSizeMultipleEntries() {

        /*
         * The setup
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c", "d", "e");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c", "d", "e");

        /*
         * The call
         */
        int size = m.size();

        /*
         * The evaluation
         */
        assertEquals(mExpected, m);
        assertEquals(5, size);
    }

}
