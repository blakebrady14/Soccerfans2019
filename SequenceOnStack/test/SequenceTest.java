import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 * 
 * @author Put your name here
 * 
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     * 
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     * 
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     * 
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     * 
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     * 
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     * 
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

  //Because of requirements clause of add, do not need to test empty adds 
    @Test 
        public void testAddFullFull() { 
            Sequence<Integer> s1 = createFromArgs(1, 2, 3, 4); 
            Sequence<Integer> s1Expected = createFromArgs(5, 1, 2, 3, 4); 
            Sequence<Integer> s2 = createFromArgs(5, 6); 
            Sequence<Integer> s2Expected = createFromArgs(6); 
            s1.add(0, s2.entry(0)); 
            assertEquals(s1Expected, s1); 
            assertEquals(s2Expected, s2); 
     
        } 
     
    @Test 
        public void testAddEmptyFull() { 
            Sequence< Integer > s1 = createFromArgs(); 
            Sequence< Integer > s1Expected = createFromArgs(5); 
            Sequence< Integer > s2 = createFromArgs(5, 6); 
            Sequence< Integer > s2Expected = createFromArgs(6); 
            s1.add(0, s2.entry(0)); 
            assertEquals(s1Expected, s1); 
            assertEquals(s2Expected, s2); 
     
        } 
     
    @Test 
        public void testRemoveOneEntry() { 
            Sequence< Integer > s1 = createFromArgs(5); 
            Sequence< Integer > s1Expected = createFromArgs(); 
             
            int temp = s1.remove(0); 
            assertEquals(s1Expected, s1); 
      assertEquals(5, temp); 
             
     
        } 
     
     
     
    @Test 
        public void testRemoveMultipleEntry() { 
            Sequence< Integer > s1 = createFromArgs(5, 6); 
            Sequence< Integer > s1Expected = createFromArgs(6); 
             
            int temp = s1.remove(0); 
            assertEquals(s1Expected, s1); 
      assertEquals(5, temp); 
             
     
        } 
     
    @Test 
        public void testLengthMultipleEntry() { 
            Sequence< Integer > s1 = createFromArgs(5, 6); 
            int length = s1.length() 
     
     assertEquals(2, length); 
             
     
        } 
     
    @Test 
        public void testLengthNoEntry() { 
            Sequence< Integer > s1 = createFromArgs(); 
            int length = s1.length() 
     
     assertEquals(0, length); 
             
     
        } 
     
    @Test 
        public void testConstructorTest() { 
            Sequence< Integer > s1 = createFromArgsTest(5, 6); 
            Sequence<Integer> s1Expected = new Sequence1L<>(); 
     s2.add(0, 5); 
     s2.add(1, 6); 
     
     assertEquals(s1Expected, s1); 
             
     
        } 
    @Test 
        public void testConstructorRef() { 
            Sequence< Integer > s1 = createFromArgsRef(5, 6); 
            Sequence<Integer> s1Expected = new Sequence1L<>(); 
     s2.add(0, 5); 
     s2.add(1, 6); 
     
     assertEquals(s1Expected, s1); 
             
     
        } 

}
