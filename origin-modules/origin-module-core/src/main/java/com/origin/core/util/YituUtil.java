package com.origin.core.util;

import com.origin.common.util.HttpUtils;
import org.apache.http.HttpResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lc on 2017/6/29.
 */
public class YituUtil {

    //身份证识别
    public String idCardRecognize(String base64){
        String host = "http://yituocr.market.alicloudapi.com";
        String path = "/face/basic/ocr";
        String method = "POST";
        String appcode = "83b63fb6c8704f298c7fbeb925447810";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        System.out.println("before = "+base64);
        base64 = base64.replaceAll("\n|\r","");
        System.out.println("after = "+base64);
        String bodys = "{\"user_info\": {\"image_content\": \""+base64+"\"},\"options\": {\"auto_rotate\": true,\"ocr_mode\": 3,\"ocr_type\": 1}}";

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
                result = new String(bytes,"utf-8");
            }
            System.out.println("renxinhua result = "+result);
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

    //身份证识别
    public String faceThanIdcard(String idcardBase64,String faceBase64){
        String host = "https://aliapi-yitu1v1.yitutech.com";
        String path = "/face/v1/algorithm/recognition/face_pair_verification";
        String method = "POST";
        String appcode = "83b63fb6c8704f298c7fbeb925447810";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        idcardBase64 = idcardBase64.replaceAll("\n|\r","");
        System.out.println("renxinhua idcardBase64 = "+idcardBase64);
        faceBase64 = faceBase64.replaceAll("\n|\r","");
        System.out.println("renxinhua faceBase64 = "+faceBase64);
        String bodys = "{\"database_image_content\": \""+idcardBase64+"\",\"database_image_type\": 2," +
                "\"query_image_package\": \""+faceBase64+"\",\"query_image_type\": 2," +
                "\"query_image_package_return_image_list\":true, " + "\"query_image_package_check_same_person\":true, " +
                "\"auto_rotate_for_database\":true, " + "\"true_negative_rate\": \"99.9\"}";

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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer sb = new StringBuffer();
            String s = null;
            while ((s = bufferedReader.readLine())!=null){
                sb.append(s);
            }
            result = sb.toString();
            System.out.println("renxinhua result = "+result);
            bufferedReader.close();
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
