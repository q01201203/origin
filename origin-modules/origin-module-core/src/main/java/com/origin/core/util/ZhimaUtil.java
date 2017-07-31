package com.origin.core.util;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.*;
import com.antgroup.zmxy.openplatform.api.response.*;
import jodd.util.URLDecoder;

import java.util.Random;

/**
 * Created by lc on 2017/6/12.
 */
public class ZhimaUtil {

    //芝麻开放平台地址
    private String gatewayUrl     = "https://zmopenapi.zmxy.com.cn/openapi.do";
    //商户应用 Id
    private String appId          = "1003280";
    //商户 RSA 私钥
    private String privateKey     = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMZ9dr2IHgeygOHx" +
            "LYV7S2QejNGpTauBIUVmOeH5uSerk8i/1dWKkSDnyzA45iI1DJYgHoAcs8QY4snI" +
            "BlwozlNQ8/W7TcF8FZPGRx6l49vCa05SN5OnG385ABs6QEDGzmXouXcaut/00VLF" +
            "rQSh7TkrKm4laoaQxv6CR7TNugYbAgMBAAECgYAiD3mGwIrWq0zueKJlG2kufweP" +
            "GsxXN8try0T3gBDDu++aLUEWabbJvLzjQ4BKMmw8frp9JW+7oMGZ+pcMzXvKBuCu" +
            "c+davqozdKROFT+JFIpJhvHBD0z2cHw1WFgBtEqmi9nV0N9d1DuvFhDEbSyPwPBs" +
            "/C12OAU0A8H0zeyEwQJBAOc5+NcLXQMcg9AwS9OsKVPq2ll8febdJ0U46F9imjLL" +
            "9S6Z7KpWatxL/6en3NezLhoqZ8SjbEcDvYfA+D3SOGsCQQDbwZstPnZdRVFdZWMb" +
            "esv4gmi+bTuyW3EBPUziTTEFF10jjkVP/t66bU0//KFwspQRxR1DuJ2xE8py/gFb" +
            "SpURAkBbyu7XZ+Jv1sUZQ+MGUAVEFCrEdaCBou/xEgtlun4ehkt6SY7jfFUFyYHf" +
            "a8tHMhGuP4FcVcgpVPnLx7aEzEyrAkEAsJ4p2XMaLlxHyIhz3uISjOhCxsmD8RUM" +
            "/bIG2NqxSyY79RejHWf6Gls+eu7P0YO0/ZMusf7uviLp0wHSlUycAQJAI0iFWHKS" +
            "p3BCFiQJrgwRv+v4zwC6Rct3sjaWe8bK2xmZfH7EkJJN72vfFj3i4/rLlOd5ZY/l" +
            "LKYJzk2Ie9XOrA==";
    //芝麻 RSA 公钥
    private String zhimaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIHKa1sVYxOuQRI76/9uKOyAC5WqB7f0i+hshq8mh/MttF3JtPmcLDPtqR9ufVJmetDnuK41QA/tFqM27Bq9gt3xOSz8Vq/Wq3HEc8ZJjGMhQWdFDvglkIx/D8NaDQV1Y1WSPq8pGQHuQhuaY6ZuxF5jdjh2kGx97uWnG5TZD3HwIDAQAB";

    //芝麻授权
    public String  zhimaAuthInfoAuthorize(String name,String identityCard,String token) {
        ZhimaAuthInfoAuthorizeRequest req = new ZhimaAuthInfoAuthorizeRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setIdentityType("2");// 必要参数
        req.setIdentityParam("{\"name\":\""+name+"\",\"certType\":\"IDENTITY_CARD\",\"certNo\":\""+identityCard+"\"}");// 必要参数
        StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append(",");
        sb.append(identityCard);
        sb.append(",");
        sb.append(token);
        req.setBizParams("{\"auth_code\":\"M_H5\",\"channelType\":\"app\",\"state\":\""+sb+"\"}");//
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(req);
            System.out.println("renxinhua zhima url = "+url);
            return url;
        } catch (ZhimaApiException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String  zhimaAuthInfoAuthquery(String name,String identityCard) {
        ZhimaAuthInfoAuthqueryRequest req = new ZhimaAuthInfoAuthqueryRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setIdentityType("2");// 必要参数
        //req.setIdentityParam("{\"openId\":\"268801234567890123456\"}");// 必要参数
        req.setIdentityParam("{\"name\":\""+name+"\",\"certType\":\"IDENTITY_CARD\",\"certNo\":\""+identityCard+"\"}");
        req.setAuthCategory("C2B");// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaAuthInfoAuthqueryResponse response =(ZhimaAuthInfoAuthqueryResponse)client.execute(req);
            System.out.println("renxinhua authorized = "+response.getAuthorized()+" openid = "+response.getOpenId() +
                    " isSuccess = "+response.isSuccess()+" getErrorCode = "+response.getErrorCode()+" getErrorMessage = " +
                    response.getErrorMessage());
            if (response.getAuthorized()){
                return response.getOpenId();
            }else{
                return "-1";
            }
        } catch (ZhimaApiException e) {
            e.printStackTrace();
            return "-2";
        }
    }

    private String getTransactionId() {
        StringBuffer transactionId = new StringBuffer();
        transactionId.append(StringUtil.getNow("yyyyMMddhhmmssSSS"));
        Random r = new Random();
        for(int i=0;i<13;i++){
            int num = r.nextInt(10);
            transactionId.append(num);
        }
        return transactionId.toString();
    }

    public String getResult(String params,String sign) {
        //String params = "Srnlsy8O0SRZ1wYkFBQQPwLh66o0tscIsL7tAEQz%2Fk%2BNW7QeMSKzDFNmh2B%2Fbaq6GnBEXkQTj4PZqLYBQjBvlCRkgRkSx4g%2FP4lJ6JW2UjAQWTN0djSi28NDyQQSP772YZiLtcw%2Fp%2FHmDb2Uhr24Jp2gPxfdzV9gGDl0uY1gNmYxqfG9tsqlNhnL%2FqTZU4KB%2FHt%2Fz3vaogxAZzo3YiB%2Fj47t97%2FUkoNzk2bWvtV12d275dwgYg31MVMKOUc%2BG%2ByRQsZMsDBKoZfhqFoADnuD0i0a%2FguFZcppxxZKCVIpveT29JtkHFXfYC8F2jZaRH5zXQa1odi%2BRv%2Bt%2BF08O1NrnA%3D%3D";
        //从回调URL中获取params参数，此处为示例值
        //String sign = "BvAYHmZ29xkuIJoNPomGJCUWUDuSufHOPTTlXpzYtY4e3i9mL6QpwPUDuK0d0F9yPxaHBAVq8YEveNoBPnsryFPE1rL3810a5pcHb8WZb02RVqCbc5j5La3SBdHY1fZRUlQYKPY0KwkipkTZmOXZAHIPU3hgynt1m%2BsAscxonIk%3D";
        //从回调URL中获取sign参数，此处为示例值
        //判断串中是否有%，有则需要decode
        if(params.indexOf("%") != -1) {
            params = URLDecoder.decode(params, "utf-8");
        }
        if(sign.indexOf("%") != -1) {
            sign = URLDecoder.decode(sign, "utf-8");
        }
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String result = client.decryptAndVerifySign(params, sign);
            if(result.indexOf("%") != -1) {
                result = URLDecoder.decode(result, "utf-8");
            }
            System.out.println("renxinhua zhima result = "+result);
            return result;
        } catch (ZhimaApiException e) {
            e.printStackTrace();
            return "error";
        }
    }

    //初始化
    public String  zhimaCustomerCertificationInitialize(String name,String identityCard) {
        ZhimaCustomerCertificationInitializeRequest req = new ZhimaCustomerCertificationInitializeRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setTransactionId(getTransactionId());// 必要参数
        req.setProductCode("w1010100000000002978");// 必要参数
        req.setBizCode("FACE");// 必要参数
        req.setIdentityParam("{\"identity_type\":\"CERT_INFO\",\"cert_type\":\"IDENTITY_CARD\",\"cert_name\":\""+name+"\",\"cert_no\":\""+identityCard+"\"}");// 必要参数
        req.setMerchantConfig("{\"need_user_authorization\":\"false\"}");//
        req.setExtBizParam("{}");// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            ZhimaCustomerCertificationInitializeResponse response =(ZhimaCustomerCertificationInitializeResponse)client.execute(req);
            System.out.println(""+response.isSuccess());
            if (response.isSuccess()){
                System.out.println(response.getBizNo());
                return response.getBizNo();
            }else {
                System.out.println(response.getErrorCode());
                System.out.println(response.getErrorMessage());
                return "error";
            }
        } catch (ZhimaApiException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String  zhimaCustomerCertificationCertify(String token ,String bizNo) {
        ZhimaCustomerCertificationCertifyRequest req = new ZhimaCustomerCertificationCertifyRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setBizNo(bizNo);// 必要参数
        req.setReturnUrl("http://106.14.11.68:8070/origin/app/user/zhimaCertificationCallback?token="+token+"&");// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        try {
            String url = client.generatePageRedirectInvokeUrl(req);
            System.out.println(url);
            return url;
        } catch (ZhimaApiException e) {
            e.printStackTrace();
            return "error";
        }
    }

    //芝麻信用评分
    public String[] zhimaCreditScoreGet(String openId) {
        ZhimaCreditScoreGetRequest req = new ZhimaCreditScoreGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setTransactionId(getTransactionId());// 必要参数
        req.setProductCode("w1010100100000000001");// 必要参数
        req.setOpenId(openId);// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        String[] strings = new String[4];
        try {
            ZhimaCreditScoreGetResponse response =(ZhimaCreditScoreGetResponse)client.execute(req);
            System.out.println("renxinhua score = "+response.getZmScore()+" bizno = "+response.getBizNo()+" isSuccess = "+response.isSuccess() +
                    " getErrorCode = "+response.getErrorCode()+" getErrorMessage = "+response.getErrorMessage());
            strings[0] = String.valueOf(response.isSuccess());
            strings[1] = response.getErrorMessage();
            strings[2] = response.getBizNo();
            strings[3] = response.getZmScore();
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return strings;
    }

    //欺诈评分[0-100]
    public String[]  zhimaCreditAntifraudScoreGet(String certNo,String name,String mobile) {
        ZhimaCreditAntifraudScoreGetRequest req = new ZhimaCreditAntifraudScoreGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setProductCode("w1010100003000001100");// 必要参数
        req.setTransactionId(getTransactionId());// 必要参数
        req.setCertType("IDENTITY_CARD");// 必要参数
        req.setCertNo(certNo);// 必要参数
        req.setName(name);// 必要参数
        req.setMobile(mobile);//
        /*req.setEmail("jnlxhy@alitest.com");//
        req.setBankCard("20110602436748024138");//
        req.setAddress("杭州市西湖区天目山路266号");//
        req.setIp("101.247.161.1");//
        req.setMac("44-45-53-54-00-00");//
        req.setWifimac("00-00-00-00-00-E0");//
        req.setImei("868331011992179");//*/
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        String[] strings = new String[4];
        try {
            ZhimaCreditAntifraudScoreGetResponse response =(ZhimaCreditAntifraudScoreGetResponse)client.execute(req);
            System.out.println(""+response.isSuccess());
            System.out.println(response.getBizNo());
            System.out.println(""+response.getScore());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
            strings[0] = String.valueOf(response.isSuccess());
            strings[1] = response.getErrorMessage();
            strings[2] = response.getBizNo();
            strings[3] = String.valueOf(response.getScore());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return strings;
    }

    //行业关注名单
    public String[]  zhimaCreditWatchlistiiGet(String openId) {
        ZhimaCreditWatchlistiiGetRequest req = new ZhimaCreditWatchlistiiGetRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setProductCode("w1010100100000000022");// 必要参数
        req.setTransactionId(getTransactionId());// 必要参数
        req.setOpenId(openId);// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        String[] strings = new String[4];
        try {
            ZhimaCreditWatchlistiiGetResponse response =(ZhimaCreditWatchlistiiGetResponse)client.execute(req);
            System.out.println(""+response.isSuccess());
            System.out.println(response.getBizNo());
            System.out.println(""+response.getDetails());
            System.out.println(""+response.getIsMatched());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
            strings[0] = String.valueOf(response.isSuccess());
            strings[1] = response.getErrorMessage();
            strings[2] = response.getBizNo();
            strings[3] = String.valueOf(response.getIsMatched());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return strings;
    }

    //欺诈关注清单
    public String[]  zhimaCreditAntifraudRiskList(String certNo,String name,String mobile) {
        ZhimaCreditAntifraudRiskListRequest req = new ZhimaCreditAntifraudRiskListRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setProductCode("w1010100003000001283");// 必要参数
        req.setTransactionId(getTransactionId());// 必要参数
        req.setCertType("IDENTITY_CARD");// 必要参数
        req.setCertNo(certNo);// 必要参数
        req.setName(name);// 必要参数
        req.setMobile(mobile);//
        /*req.setEmail("jnlxhy@alitest.com");//
        req.setBankCard("20110602436748024138");//
        req.setAddress("杭州市西湖区天目山路266号");//
        req.setIp("101.247.161.1");//
        req.setMac("44-45-53-54-00-00");//
        req.setWifimac("00-00-00-00-00-E0");//
        req.setImei("868331011992179");// */
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        String[] strings = new String[5];
        try {
            ZhimaCreditAntifraudRiskListResponse response =(ZhimaCreditAntifraudRiskListResponse)client.execute(req);
            System.out.println(""+response.isSuccess());
            System.out.println(""+response.getBizNo());
            System.out.println(response.getHit());
            System.out.println(""+response.getRiskCode());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
            strings[0] = String.valueOf(response.isSuccess());
            strings[1] = response.getErrorMessage();
            strings[2] = response.getBizNo();
            strings[3] = response.getHit();
            strings[4] = String.valueOf(response.getRiskCode());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return strings;
    }

    //欺诈信息验证
    public String[]  zhimaCreditAntifraudVerify(String certNo,String name,String mobile) {
        ZhimaCreditAntifraudVerifyRequest req = new ZhimaCreditAntifraudVerifyRequest();
        req.setChannel("apppc");
        req.setPlatform("zmop");
        req.setProductCode("w1010100000000002859");// 必要参数
        req.setTransactionId(getTransactionId());// 必要参数
        req.setCertNo(certNo);// 必要参数
        req.setCertType("IDENTITY_CARD");// 必要参数
        req.setName(name);// 必要参数
        req.setMobile(mobile);//
        /*req.setEmail("jnlxhy@alitest.com");//
        req.setBankCard("20110602436748024138");//
        req.setAddress("杭州市西湖区天目山路266号");//
        req.setIp("101.247.161.1");//
        req.setMac("44-45-53-54-00-00");//
        req.setWifimac("00-00-00-00-00-E0");//
        req.setImei("868331011992179");//*/
        DefaultZhimaClient client = new DefaultZhimaClient(gatewayUrl, appId, privateKey, zhimaPublicKey);
        String[] strings = new String[4];
        try {
            ZhimaCreditAntifraudVerifyResponse response =(ZhimaCreditAntifraudVerifyResponse)client.execute(req);
            System.out.println(""+response.isSuccess());
            System.out.println(response.getBizNo());
            System.out.println(""+response.getVerifyCode());
            System.out.println(response.getErrorCode());
            System.out.println(response.getErrorMessage());
            strings[0] = String.valueOf(response.isSuccess());
            strings[1] = response.getErrorMessage();
            strings[2] = response.getBizNo();
            strings[3] = String.valueOf(response.getVerifyCode());
        } catch (ZhimaApiException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
