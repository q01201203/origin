package Test;

import com.origin.common.util.BASE64Util;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by lc on 2017/5/12.
 */
public class TestEncryptCode {

    public static void main(String[] args) {
        String str = new BASE64Encoder().encodeBuffer("Welcome to www.jb51.net".getBytes());
        System.out.println(str);
        try {
            String str2 = new String(new BASE64Decoder().decodeBuffer(str));
            System.out.println(str2);;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String[] strs = BASE64Util.decryptBASE64("MS4x").split("\\.");
            System.out.println(strs[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s = "ssdf";
        String[] strs = s.split(",");

        String s1 = "1";
        Integer i1 = 1;
        if (i1.equals(Integer.parseInt(s1))){
            System.out.println("1");
        }else{
            System.out.println("2");
        }
    }
}
