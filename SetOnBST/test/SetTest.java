import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    //Testing createFromArgsTest
    @Test
    public void testConstructorTestWithContents() {
        Set<String> s1 = this.createFromArgsTest("5", "6");
        Set<String> s1Expected = this.createFromArgsTest("5", "6");
        assertEquals(s1Expected, s1);
    }

    //Testing createFromArgsTest
    @Test
    public void testConstructorTestEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsTest();
        assertEquals(s1Expected, s1);
    }

    //Testing add with two entries
    @Test
    public void testAddTwoEntries() {
        Set<String> s1 = this.createFromArgsTest("5");
        Set<String> s1Expected = this.createFromArgsTest("5", "6");
        s1.add("6");
        assertEquals(s1Expected, s1);
    }

    //Testing add with one entry
    @Test
    public void testAddOneEntry() {
        Set<String> s1 = this.createFromArgsTest("5", "7");
        Set<String> s1Expected = this.createFromArgsTest("5", "6", "7");
        s1.add("6");
        assertEquals(s1Expected, s1);
    }

    //Testing add with empty start
    @Test
    public void testAddEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsTest("6");
        s1.add("6");
        assertEquals(s1Expected, s1);
    }

    //Testing remove with one entry
    @Test
    public void testRemoveOneEntry() {
        Set<String> s1 = this.createFromArgsTest("6");
        Set<String> s1Expected = this.createFromArgsTest();
        String temp = s1.remove("6");
        assertEquals(s1Expected, s1);
        assertEquals("6", temp);
    }

    //Testing remove with two entries
    @Test
    public void testRemoveTwoEntries() {
        Set<String> s1 = this.createFromArgsTest("6", "7");
        Set<String> s1Expected = this.createFromArgsTest("7");
        String temp = "6";
        String temp2 = s1.remove(temp);
        assertEquals(s1Expected, s1);
        assertEquals("6", temp);
    }

    //Testing remove with three entries
    @Test
    public void testRemoveThreeEntries() {
        Set<String> s1 = this.createFromArgsTest("6", "7", "8");
        Set<String> s1Expected = this.createFromArgsTest("7", "8");
        String temp = "6";
        String temp2 = s1.remove(temp);
        assertEquals(s1Expected, s1);
        assertEquals("6", temp);
    }

    //Testing contains with no contents, expecting false
    @Test
    public void testContainsNotIn() {
        Set<String> s1 = this.createFromArgsTest("5", "6", "7");
        Set<String> s1Expected = this.createFromArgsTest("5", "6", "7");
        boolean temp = s1.contains("4");
        assertEquals(s1Expected, s1);
        assertEquals(false, temp);

    }

    //Testing contains with two entries, expecting true
    @Test
    public void testContainsTwoEntries() {
        Set<String> s1 = this.createFromArgsTest("6", "7");
        Set<String> s1Expected = this.createFromArgsTest("6", "7");
        boolean temp = s1.contains("6");
        assertEquals(s1Expected, s1);
        assertEquals(true, temp);
    }

    //Testing contains with one entry, expecting true
    @Test
    public void testContainsOneEntry() {
        Set<String> s1 = this.createFromArgsTest("6");
        Set<String> s1Expected = this.createFromArgsTest("6");
        boolean temp = s1.contains("6");
        assertEquals(s1Expected, s1);
        assertEquals(true, temp);
    }

    //Testing contains with no contents, expecting false
    @Test
    public void testContainsNoEntries() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsTest();
        boolean temp = s1.contains("6");
        assertEquals(s1Expected, s1);
        assertEquals(false, temp);

    }

    //Testing size with no contents
    @Test
    public void testSizeEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsTest();
        int size = s1.size();
        assertEquals(s1Expected, s1);
        assertEquals(0, size);
    }

    //Testing size with contents
    @Test
    public void testSizeOneEntry() {
        Set<String> s1 = this.createFromArgsTest("5");
        Set<String> s1Expected = this.createFromArgsTest("5");
        int size = s1.size();
        assertEquals(s1Expected, s1);
        assertEquals(1, size);
    }

    //Testing size with contents
    @Test
    public void testSizeTwoEntry() {
        Set<String> s1 = this.createFromArgsTest("5", "6");
        Set<String> s1Expected = this.createFromArgsTest("5", "6");
        int size = s1.size();
        assertEquals(s1Expected, s1);
        assertEquals(2, size);
    }

    //Testing removeAny with two entries
    @Test
    public void testRemoveAnyTwoEntries() {
        Set<String> s1 = this.createFromArgsTest("5", "6");
        Set<String> s1Expected = this.createFromArgsTest("5", "6");
        String temp = s1.removeAny();
        assertEquals(true, s1Expected.contains(temp));
        s1Expected.remove(temp);
        assertEquals(s1Expected, s1);
    }

    //Testing removeAny with one entry
    @Test
    public void testRemoveAnyOneEntry() {
        Set<String> s1 = this.createFromArgsTest("5");
        Set<String> s1Expected = this.createFromArgsTest("5");
        String temp = s1.removeAny();
        assertEquals(true, s1Expected.contains(temp));
        s1Expected.remove(temp);
        assertEquals(s1Expected, s1);

    }

    //Testing removeAny with three entries
    @Test
    public void testRemoveAnyThreeEntries() {
        Set<String> s1 = this.createFromArgsTest("5", "6", "7");
        Set<String> s1Expected = this.createFromArgsTest("5", "6", "7");
        String temp = s1.removeAny();
        assertEquals(true, s1Expected.contains(temp));
        s1Expected.remove(temp);
        assertEquals(s1Expected, s1);

    }
}
