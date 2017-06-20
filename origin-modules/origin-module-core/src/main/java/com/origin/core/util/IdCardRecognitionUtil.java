package com.origin.core.util;

import com.origin.common.util.HttpUtils;
import org.apache.http.HttpResponse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lc on 2017/6/20.
 */
public class IdCardRecognitionUtil {

     public static void recognize(String pic){
         String host = "http://jisusfzsb.market.alicloudapi.com";
         String path = "/idcardrecognition/recognize";
         String method = "POST";
         String appcode = "83b63fb6c8704f298c7fbeb925447810";
         Map<String, String> headers = new HashMap<String, String>();
         //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
         headers.put("Authorization", "APPCODE " + appcode);
         //根据API的要求，定义相对应的Content-Type
         headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
         Map<String, String> querys = new HashMap<String, String>();
         querys.put("typeid", "2");
         Map<String, String> bodys = new HashMap<String, String>();
         bodys.put("pic", pic);


         try {
             /**
              * 重要提示如下:
              * HttpUtils请从
              * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
              * 下载
              *
              * 相应的依赖请参照
              * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
              */
             HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
             System.out.println(response.toString());
             InputStream inputStream = response.getEntity().getContent();
             int length = 1024;
             byte[] bytes = new byte[length];
             while (inputStream.read(bytes,0,length)!=-1){
                 String s = new String(bytes);
                 System.out.println("result = "+s);
             }
             inputStream.close();

             //获取response的body
             //System.out.println(EntityUtils.toString(response.getEntity()));
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
