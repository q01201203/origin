package com.origin.core.util;

import com.origin.common.util.HttpUtils;
import org.apache.http.HttpResponse;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lc on 2017/6/20.
 */
public class IdCardRecognitionUtil {

    public static final String TYPE_FRONT = "3";
    public static final String TYPE_BACK = "2";

    public static String recognize(String pic,String type){
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
        querys.put("typeid", type);
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("pic", pic);

        String result = "error";
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
         System.out.println(response.getStatusLine().getStatusCode());
         if (response.getStatusLine().getStatusCode()!=200){
             return result;
         }
         InputStream inputStream = response.getEntity().getContent();
         BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
         int length = 512;
         byte[] bytes = new byte[length];

         while (bufferedInputStream.read(bytes,0,length)!=-1){
             result = new String(bytes);
             System.out.println("result = "+result);
         }
         bufferedInputStream.close();
         inputStream.close();

         //获取response的body
         //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
