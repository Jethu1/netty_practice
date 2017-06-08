package junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jet on 2017/6/2.
 */
public class CaculatorTest {
    private static Caculator calculator = new Caculator();

    @Before
    public void setUp() throws Exception {
        calculator.clear();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("end");
    }

    @Test
    public void add() throws Exception {
        calculator.add(2);
        calculator.add(3);
        assertEquals(5,calculator.getResult());
    }

    @Test
    public void substract() throws Exception {
        calculator.add(10);
        calculator.substract(3);
        assertEquals(7,calculator.getResult());
    }

    @Ignore("Multiply not yet implemented")
    @Test
    public void multiply() throws Exception {

    }

    @Test(timeout = 30)
    public void divide() throws Exception {
        calculator.add(8);
        calculator.divide(2);
        assertEquals(4,calculator.getResult());
    }

    @Test(timeout = 3000)
    public void squareRoot() throws Exception {
        calculator.squareRoot(4);
        assertEquals(2,calculator.getResult());
    }

    @Test(expected =  ArithmeticException.class)
    public void divideByZero(){
        calculator.divide(0);
    }

}