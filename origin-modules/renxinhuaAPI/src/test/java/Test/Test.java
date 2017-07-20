package Test;

import com.origin.core.util.StringUtil;
import jodd.util.URLDecoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lc on 2017/6/13.
 */
public class Test {

    static int test()
    {
        int x = 1;
        try
        {
            x++;
            System.out.println("x1 = "+x);
            return x;
        }
        finally
        {
            ++x;
            System.out.println("x2 = "+x);
            return x;
        }
    }

    //@org.junit.Test
    public void testFinally(){
        System.out.println("x = "+Test.test());
    }

    //@org.junit.Test
    public void testDate(){
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtil.getNow("yyyyMMddhhmmssSSS"));
        sb.append(",");
        Random r = new Random();
        for(int i=0;i<13;i++){
            int num = r.nextInt(10);
            sb.append(num);
        }
        System.out.println("number = "+sb);
        System.out.println("uuid = "+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                + UUID.randomUUID().toString());
    }

    //@org.junit.Test
    public void testDecode(){
        String result = "open_id=268806749671786140099668503&error_message=%E6%93%8D%E4%BD%9C%E6%88%90%E5%8A%9F&state=mobile%3A13637857084pwd%3A110110&error_code=SUCCESS&app_id=1003280&success=true";
        Map map = StringUtil.urlSplit(result);
        for (Object s: map.keySet()) {
            System.out.println(s.toString()+"="+map.get(s));
        }
        System.out.println(map.get("open_id"));
        String sss = "133233%2C21sdf2%2Csdfkfksld";
        String[] ssss = sss.split("\\%2C");
        System.out.println(ssss[0]);
        //String string = "open_id=268806749671786140099668503&error_message=%E6%93%8D%E4%BD%9C%E6%88%90%E5%8A%9F&state=13637857084%2C110110%2C201706141742041435%2C%E5%91%A8%E7%BB%B4%E7%A7%91%2C500222199301225210&error_code=SUCCESS&app_id=1003280&success=true";
        String string = "'%22+onmouseover=alert()+d='%22";
        String ss = URLDecoder.decode(string, "utf-8");
        System.out.println(ss);
    }

    @org.junit.Test
    public void testDateIncre(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
            date = sdf.parse("2012-07-25 21:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_YEAR, 15);
        System.out.println(sdf.format(ca.getTime()));
        System.out.println("date = "+date);
    }
}
