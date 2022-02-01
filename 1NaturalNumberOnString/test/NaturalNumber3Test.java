import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {

        /*
         * For the following "test" constructors, the method returns a new
         * NaturalNumber3 Object. For all of the following "ref" constructors,
         * the methods returns a new NaturalNumber1L object
         */
        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {

        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {

        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {

        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {

        return new NaturalNumber1L();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        return new NaturalNumber1L(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        return new NaturalNumber1L(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        return new NaturalNumber1L(n);
    }
    
    //_______________________________________________________________________________________________________________________________________________________
    
    
    import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {

        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {

        return new NaturalNumber3(i);

    }

    @Override
    protected final NaturalNumber constructorTest(String s) {

        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {

        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {

        return new NaturalNumber2();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        return new NaturalNumber2(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        return new NaturalNumber2(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        return new NaturalNumber2(n);
    }

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

    @Test
    public void test_from_nn_constructer() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(new NaturalNumber2(5));
        NaturalNumber expectedN = this.constructorRef(new NaturalNumber2(5));

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

    @Test
    public void test_from_nn_constructer_0() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(new NaturalNumber2(0));
        NaturalNumber expectedN = this.constructorRef(new NaturalNumber2(0));

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedN, n);

    }

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


}
