package Test;

import org.junit.*;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/16.
 */
public class JUnitLifecycle {

    private static int counter = 0;

    @BeforeClass
    public static void suiteSetup(){
        assert 0==counter;
        counter++;
        System.out.println("counter1 = "+counter);
    }

    public JUnitLifecycle(){
        assert Arrays.asList(1,5).contains(counter)==true;
        counter++;
        System.out.println("counter2 = "+counter);
    }

    @Before
    public void prepareTest(){
        assert Arrays.asList(2,6).contains(counter);
        counter++;
        System.out.println("counter3 = "+counter);
    }

    @Test
    public void peformFirstTest(){
        assert Arrays.asList(3,7).contains(counter);
        counter++;
        System.out.println("counter4 = "+counter);
    }

    @Test
    public void performSecondTest(){
        assert Arrays.asList(3,7).contains(counter);
        //Assert.assertEquals("error",7,2);
        counter++;
        System.out.println("counter5 = "+counter);
    }

    @After
    public void cleanupTest(){
        assert Arrays.asList(4,8).contains(counter);
        counter++;
        System.out.println("counter6 = "+counter);
    }

    @AfterClass
    public static void suiteFinished(){
        assert 9==counter;
        System.out.println("counter7 = "+counter);
    }

}
