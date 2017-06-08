package junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jet on 2017/6/2.
 */

public class CaculatorTest1 {
    private static  Caculator calculator =  new Caculator();

    @Before
    public void setUp() throws Exception {
        calculator.clear();
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test

    public void square1() {

        calculator.square(2);

        assertEquals(4, calculator.getResult());

    }



    @Test

    public void square2() {

        calculator.square(0);

        assertEquals(0, calculator.getResult());

    }


    @Test

    public void square3() {

        calculator.square(-3);

        assertEquals(9, calculator.getResult());

    }


}