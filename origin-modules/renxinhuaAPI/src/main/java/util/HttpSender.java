package util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

public class HttpSender {

    public static String batchSend(String url, String un, String pw, String phone, String msg,
                                   String rd, String ex) throws Exception {
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "send", false));
            method.setQueryString(new NameValuePair[] {
                    new NameValuePair("un", un),
                    new NameValuePair("pw", pw),
                    new NameValuePair("phone", phone),
                    new NameValuePair("rd", rd),
                    new NameValuePair("msg", msg),
                    new NameValuePair("ex", ex),
            });
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }

    public static int send(String phone){
        String url = "http://sms.253.com/msg/";// 应用地址
        String un = "N3353057";// 账号
        String pw = "x0qLwVW76e2702";// 密码
        //String phone = "";// 手机号码，多个号码使用","分割
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("【任信花】用户您好，你的验证码是");
        int validcode = (int)((Math.random()*9+1)*100000);
        stringBuffer.append(validcode);
        stringBuffer.append("，感谢您的使用");
        String msg = stringBuffer.toString();// 短信内容
        String rd = "1";// 是否需要状态报告，需要1，不需要0
        String ex = null;// 扩展码

        try {
            String returnString = HttpSender.batchSend(url, un, pw, phone, msg, rd, ex);
            System.out.println("renxinhua 253 = "+returnString);
            if (returnString.charAt(returnString.indexOf(",")+1) == '0'){
                return validcode;
            }else {
                return -1;
            }
            // TODO 处理返回值,参见HTTP协议文档
        } catch (Exception e) {
            // TODO 处理异常
            System.out.println("renxinhua 253 Exception");
            e.printStackTrace();
            return -1;
        }
    }
}
