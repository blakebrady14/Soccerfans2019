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

        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {

        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {

        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorRef() {

        return new NaturalNumber1L();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        return new NaturalNumber1L();
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        return new NaturalNumber1L();
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        return new NaturalNumber1L();
    }

}
