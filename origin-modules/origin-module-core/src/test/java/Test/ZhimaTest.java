package Test;

import com.origin.core.util.ZhimaUtil;
import org.junit.Test;

/**
 * Created by lc on 2017/6/12.
 */
public class ZhimaTest {


    //@Test
    public void test1(){
        //ZhimaUtil result = new ZhimaUtil();
    }

    //@Test
    public void test2(){
        ZhimaUtil result = new ZhimaUtil();
        result.zhimaCreditScoreGet("");
    }

    //500222199301225210
    //@Test
    public void test3(){
        ZhimaUtil result = new ZhimaUtil();
        result.zhimaCustomerCertificationInitialize("周维科","500222199301225210");
    }

    @Test
    public void test(){
    }
}
