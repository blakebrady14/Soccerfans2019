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
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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

    //Testing createFromArgsTest
    @Test
    public void testConstructorTest() {
        Set<String> s1 = this.createFromArgsTest("5", "6");
        Set<String> s1Expected = this.createFromArgsTest("5", "6");
        assertEquals(s1Expected, s1);
    }

    //Testing createFromArgsRef
    @Test
    public void testConstructorRef() {
        Set<String> s1 = this.createFromArgsRef("5", "6");
        Set<String> s1Expected = this.createFromArgsRef("5", "6");
        assertEquals(s1Expected, s1);
    }

    //Testing add
    @Test
    public void testAdd1() {
        Set<String> s1 = this.createFromArgsTest("5");
        Set<String> s1Expected = this.createFromArgsTest("5", "6");
        s1.add("6");
        assertEquals(s1Expected, s1);
    }

    //Testing add with empty start
    @Test
    public void testAdd2() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsTest("6");
        s1.add("6");
        assertEquals(s1Expected, s1);
    }

    //Testing remove with empty finish
    @Test
    public void testRemove1() {
        Set<String> s1 = this.createFromArgsTest("6");
        Set<String> s1Expected = this.createFromArgsTest();
        String temp = s1.remove("6");
        assertEquals(s1Expected, s1);
        assertEquals("6", temp);
    }

    //Testing remove with multiple entries
    @Test
    public void testRemove2() {
        Set<String> s1 = this.createFromArgsTest("6", "7");
        Set<String> s1Expected = this.createFromArgsTest("7");
        String temp = "6";
        String temp2 = s1.remove(temp);
        assertEquals(s1Expected, s1);
        assertEquals("6", temp);
    }

    //Testing contains with contents
    @Test
    public void testContains1() {
        Set<String> s1 = this.createFromArgsTest("6");
        Set<String> s1Expected = this.createFromArgsTest("6");
        boolean temp = s1.contains("6");
        assertEquals(s1Expected, s1);
        assertEquals(true, temp);
    }

    //Testing contains with no contents
    @Test
    public void testContains2() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsTest();
        boolean temp = s1.contains("6");
        assertEquals(s1Expected, s1);
        assertEquals(false, temp);

    }

    //Testing size with no contents
    @Test
    public void testSize1() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsTest();
        int size = s1.size();
        assertEquals(s1Expected, s1);
        assertEquals(0, size);
    }

    //Testing size with contents
    @Test
    public void testSize2() {
        Set<String> s1 = this.createFromArgsTest("5");
        Set<String> s1Expected = this.createFromArgsTest("5");
        int size = s1.size();
        assertEquals(s1Expected, s1);
        assertEquals(1, size);
    }

    //Testing removeAny with multiple
    @Test
    public void testRemoveAny1() {
        Set<String> s1 = this.createFromArgsTest("5", "6");
        Set<String> s1Expected = this.createFromArgsTest("5", "6");
        String temp = s1.removeAny();
        assertEquals(true, s1Expected.contains(temp));
        s1Expected.remove(temp);
        assertEquals(s1Expected, s1);
    }

    //Testing removeAny with one entry
    @Test
    public void testRemoveAny2() {
        Set<String> s1 = this.createFromArgsTest("5");
        Set<String> s1Expected = this.createFromArgsTest("5");
        String temp = s1.removeAny();
        assertEquals(true, s1Expected.contains(temp));
        s1Expected.remove(temp);
        assertEquals(s1Expected, s1);

    }

}
