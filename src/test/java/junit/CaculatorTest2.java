package junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by jet on 2017/6/2.
 */
@RunWith(Parameterized.class)
public class CaculatorTest2 {
    private static Caculator calculator = new Caculator();
    private int param;
    private int result;

    @Parameterized.Parameters
    public  static Collection data(){
        return Arrays.asList(new Object[][]{
                {2,4},{0,0},{-3,9}
        })  ;
    }

    public CaculatorTest2(int param,int result){
        this.param = param;
        this.result = result;
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void square() throws Exception {
        calculator.square(param);
        assertEquals(result,calculator.getResult());

    }

}