import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Blake Brady and Tomasz Frelek
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /*
     * Testing the empty constructor
     */
    @Test
    public void testConstructorTestEmpty() {

        /*
         * Setup/The call
         */

        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n1Expected = this.constructorRef();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the int constructor with small number
     */
    @Test
    public void testConstructorTestIntSmall() {

        /*
         * Setup/The call
         */

        NaturalNumber n1 = this.constructorTest(1);
        NaturalNumber n1Expected = this.constructorRef(1);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the int constructor with big number
     */
    @Test
    public void testConstructorTestIntBig() {

        /*
         * Setup/The call
         */

        NaturalNumber n1 = this.constructorTest(123456);
        NaturalNumber n1Expected = this.constructorRef(123456);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing int constructor with integer 0
     */
    @Test
    public void testConstructorTestIntZero() {

        /*
         * Setup/The call
         */

        NaturalNumber n1 = this.constructorTest(0);
        NaturalNumber n1Expected = this.constructorRef(0);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the string constructor with small value
     */
    @Test
    public void testConstructorTestStringSmall() {

        /*
         * Setup/The call
         */

        NaturalNumber n1 = this.constructorTest("1");
        NaturalNumber n1Expected = this.constructorRef("1");

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the string constructor with big value
     */
    @Test
    public void testConstructorTestStringBig() {

        /*
         * Setup/The call
         */

        NaturalNumber n1 = this.constructorTest("123456");
        NaturalNumber n1Expected = this.constructorRef("123456");

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the string constructor with String 0
     */
    @Test
    public void testConstructorTestStringZero() {

        /*
         * Setup/The call
         */

        NaturalNumber n1 = this.constructorTest("0");
        NaturalNumber n1Expected = this.constructorRef("0");

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the NN constructor with a small value
     */
    @Test
    public void testConstructorTestNN() {

        /*
         * Setup/The call
         */

        NaturalNumber n1Expected = this.constructorRef(1);
        NaturalNumber n1 = this.constructorTest(n1Expected);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the NN constructor with 0
     */
    @Test
    public void testConstructorTestNNZero() {

        /*
         * Setup/The call
         */

        NaturalNumber n1Expected = this.constructorRef(0);
        NaturalNumber n1 = this.constructorTest(n1Expected);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing the NN constructor with a big value
     */
    @Test
    public void testConstructorTestNNBig() {

        /*
         * Setup/The call
         */

        NaturalNumber n1Expected = this.constructorRef("53453453534");
        NaturalNumber n1 = this.constructorTest(n1Expected);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing multiplyBy10 with zero and zero for the argument
     */
    @Test
    public void testMultiplyBy10ZeroZero() {

        /*
         * Setup
         */

        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n1Expected = this.constructorTest();

        /*
         * Setup
         */

        n1.multiplyBy10(0);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing multiplyBy10 with int 15 and int 6 for the argument
     */
    @Test
    public void testMultiplyBy10IntNumNum() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(15);
        NaturalNumber n1Expected = this.constructorTest(156);

        /*
         * Setup
         */
        n1.multiplyBy10(6);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing multiplyBy10 with string constructor NN 15 and 6 for the argument
     */
    @Test
    public void testMultiplyBy10NumNum() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest("15");
        NaturalNumber n1Expected = this.constructorTest(156);

        /*
         * Setup
         */
        n1.multiplyBy10(6);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing multiplyBy10 with NN constructor and NN 15 and 0
     */
    @Test
    public void testMultiplyBy10NNNumZero() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(15);
        NaturalNumber n1Expected = this.constructorTest(150);

        /*
         * Setup
         */
        n1.multiplyBy10(0);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing multiplyBy10 with NN 0 and argument 9
     */
    @Test
    public void testMultiplyBy10NNZeroNum() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(0);
        NaturalNumber n1Expected = this.constructorRef(9);

        /*
         * Setup
         */
        n1.multiplyBy10(9);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing multiplyBy10 with NN 10 and argument 9
     */
    @Test
    public void testMultiplyBy10NNTenNum() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(10);
        NaturalNumber n1Expected = this.constructorTest(109);

        /*
         * Setup
         */
        n1.multiplyBy10(9);

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);

    }

    /*
     * Testing divideBy10 with NN 0
     */
    @Test
    public void testDivideBy10ZeroZero() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n1Expected = this.constructorTest();

        /*
         * Setup
         */
        int temp = n1.divideBy10();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);
        assertEquals(0, temp);

    }

    /*
     * Testing divideBy10 with NN 0
     */
    @Test
    public void testDivideBy10TenZero() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(10);
        NaturalNumber n1Expected = this.constructorTest(1);

        /*
         * Setup
         */
        int temp = n1.divideBy10();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);
        assertEquals(0, temp);

    }

    /*
     * Testing divideBy10 with NN 17
     */
    @Test
    public void testDivideBy10NumRemainer() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(17);
        NaturalNumber n1Expected = this.constructorTest(1);

        /*
         * Setup
         */
        int temp = n1.divideBy10();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);
        assertEquals(7, temp);

    }

    /*
     * Testing divideBy10 with NN 100
     */
    @Test
    public void testDivideBy10NumZero() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(100);
        NaturalNumber n1Expected = this.constructorTest(10);

        /*
         * Setup
         */
        int temp = n1.divideBy10();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);
        assertEquals(0, temp);

    }

    /*
     * Testing isZero with empty constructor
     */
    @Test
    public void testisZeroEmpty() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n1Expected = this.constructorTest();

        /*
         * Setup
         */
        boolean temp = n1.isZero();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);
        assertEquals(true, temp);

    }

    /*
     * Testing isZero with int constructor 0
     */
    @Test
    public void testisZeroIntZero() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(0);
        NaturalNumber n1Expected = this.constructorTest();

        /*
         * Setup
         */
        boolean temp = n1.isZero();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);
        assertEquals(true, temp);

    }

    /*
     * Testing isZero with String constructor 0
     */
    @Test
    public void testisZeroStringZero() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest("0");
        NaturalNumber n1Expected = this.constructorTest();

        /*
         * Setup
         */
        boolean temp = n1.isZero();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);
        assertEquals(true, temp);

    }

    /*
     * Testing isZero with String constructor 0
     */
    @Test
    public void testisZeroNum() {

        /*
         * Setup
         */
        NaturalNumber n1 = this.constructorTest(145);
        NaturalNumber n1Expected = this.constructorTest(145);

        /*
         * Setup
         */
        boolean temp = n1.isZero();

        /*
         * Evaluation
         */
        assertEquals(n1Expected, n1);
        assertEquals(false, temp);

    }

    /*
     * Testing empty constructor
     */
    @Test
    public void test_empty_constructer() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber expectedN = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing int constructor
     */
    @Test
    public void test_from_int_constructer() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber expectedN = this.constructorRef(5);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing int constructor with 0
     */
    @Test
    public void test_from_int_constructer_0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber expectedN = this.constructorRef(0);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing string constructor
     */
    @Test
    public void test_from_string_constructer() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("5");
        NaturalNumber expectedN = this.constructorRef("5");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing string constructor with 0
     */
    @Test
    public void test_from_string_constructer_0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber expectedN = this.constructorRef("0");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing multiplyBy10 with NN 1 and argument 5
     */
    @Test
    public void test_mult_by_10() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber expectedN = this.constructorRef(1);

        /*
         * Call method under test
         */
        n.multiplyBy10(5);
        expectedN.multiplyBy10(5);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing multBy10 with NN 0 and argument 0
     */
    @Test
    public void test_mult_by_10_0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber expectedN = this.constructorRef();

        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        expectedN.multiplyBy10(0);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing divideBy10 with NN 51
     */
    @Test
    public void test_div_by_10() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(51);
        NaturalNumber expectedN = this.constructorRef(51);

        /*
         * Call method under test
         */
        n.divideBy10();
        expectedN.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing divideBy10 empty
     */
    @Test
    public void test_div_by_10_0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber expectedN = this.constructorRef();

        /*
         * Call method under test
         */
        n.divideBy10();
        expectedN.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    /*
     * Testing isZero with zero
     */
    @Test
    public void test_isZero_0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber expectedN = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN.isZero(), n.isZero());

    }

    /*
     * Testing isZero with value 5
     */
    @Test
    public void test_isZero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber expectedN = this.constructorRef(5);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN.isZero(), n.isZero());

    }

}
