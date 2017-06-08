package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

/**
 * Created by jet on 2017/6/2.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CaculatorTest.class,CaculatorTest1.class,CaculatorTest2.class})
public class CaculatorTestAll {

}