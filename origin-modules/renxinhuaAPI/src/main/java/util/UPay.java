package util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lc
 * on 2017/8/4.
 */
public class UPay {


    public static void main(String[] args) {
        UPay uPay = new UPay();
        uPay.queryMerBankShortCut();
    }

    //API签约请求
    //卡号【card_id】  媒介标识【media_id】银行预留手机号码   证件号【identity_code】  持卡人姓名【card_holder】
    public Map reqBindVerifyShortcut(String card_id,String media_id,String identity_code,String card_holder){
        Map map = new HashMap();
        map.put("service","req_bind_verify_shortcut");
        map.put("mer_id","50261");
        map.put("charset","UTF-8");
        map.put("res_format","HTML");
        map.put("sign_type","RSA");
        map.put("version","4.0");
        map.put("card_id",card_id);
        map.put("media_id",media_id);
        map.put("media_type","MOBILE");
        map.put("identity_type","IDENTITY_CARD");
        map.put("identity_code",identity_code);
        map.put("card_holder",card_holder);

        return getResult(map);
    }

    //API签约确认
    //注册流水号【bind_no】 验证码【verify_code】 商户用户标识【mer_cust_id】 卡号【card_id】
    // 媒介标识【media_id】银行预留手机号码   证件号【identity_code】  持卡人姓名【card_holder】
    public Map reqBindConfirmShortcut(String bind_no,String verify_code,String mer_cust_id,String card_id,
                                         String media_id,String identity_code,String card_holder){
        Map map = new HashMap();
        map.put("service","req_bind_confirm_shortcut");
        map.put("mer_id","50261");
        map.put("charset","UTF-8");
        map.put("res_format","HTML");
        map.put("sign_type","RSA");
        map.put("version","4.0");
        map.put("bind_no",bind_no);
        map.put("verify_code",verify_code);
        map.put("mer_cust_id",mer_cust_id);
        map.put("card_id",card_id);
        map.put("media_id",media_id);
        map.put("media_type","MOBILE");
        map.put("identity_type","IDENTITY_CARD");
        map.put("identity_code",identity_code);
        map.put("card_holder",card_holder);

        return getResult(map);
    }

    //商户API后台下单
    //商户唯一订单号【order_id】  商户订单日期【mer_date】格式YYYYMMDD   付款金额【amount】以分为单位
    public Map 	payReqShortcut(String order_id,String mer_date,String amount,String token){
        Map map = new HashMap();
        map.put("service","pay_req_shortcut");
        map.put("charset","UTF-8");
        map.put("mer_id","50261");
        map.put("sign_type","RSA");
        map.put("notify_url","http://106.14.11.68:8070/renxinhuaAPI/app/upay/getPlatNotifyData?order_id="+order_id+
                "&mer_date="+mer_date);
        map.put("res_format","HTML");
        map.put("version","4.0");
        map.put("goods_id","");
        map.put("goods_inf","");
        map.put("order_id",order_id);
        map.put("mer_date",mer_date);
        map.put("amount",amount);
        map.put("amt_type","RMB");
        map.put("mer_priv","");
        map.put("expand","");
        map.put("expire_time","");
        map.put("risk_expand","");
        map.put("split_category","");
        map.put("split_type","");
        map.put("split_data","");

        return getResult(map);
    }

    //商户API确认支付
    //联动交易号【trade_no】  商户用户标识【mer_cust_id】  用户业务协议号【usr_busi_agreement_id】 支付协议号【usr_pay_agreement_id】
    //备注mer_cust_id和usr_busi_agreement_id必填其一
    public Map 	agreementPayConfirmShortcut(String trade_no,String mer_cust_id,String usr_busi_agreement_id,String usr_pay_agreement_id){
        Map map = new HashMap();
        map.put("service","agreement_pay_confirm_shortcut");
        map.put("mer_id","50261");
        map.put("charset","UTF-8");
        map.put("res_format","HTML");
        map.put("sign_type","RSA");
        map.put("version","4.0");
        map.put("trade_no",trade_no);
        map.put("mer_cust_id",mer_cust_id);
        map.put("usr_busi_agreement_id",usr_busi_agreement_id);
        map.put("usr_pay_agreement_id",usr_pay_agreement_id);

        return getResult(map);
    }


    //查询商户支持的银行列表
    public Map queryMerBankShortCut(){
        Map map = new HashMap();
        map.put("service","query_mer_bank_shortcut");
        map.put("charset","UTF-8");
        map.put("mer_id","50261");
        map.put("sign_type","RSA");
        map.put("version","4.0");
        map.put("res_format","HTML");
        map.put("pay_type","DEBITCARD");

        return getResult(map);
    }

    //回调通知UPay平台
    //trade_no联动交易号  order_id订单号    mer_date商户订单日期   pay_date支付日期   amount付款金额(分为单位)
    //amt_type付款币种  pay_type支付方式   settle_date对账日期   trade_state交易状态
    public Map payResultNotify(String trade_no,String order_id,String mer_date,String pay_date,
                               String amount,String settle_date,String trade_state){
        Map map = new HashMap();
        map.put("service","pay_result_notify");
        map.put("mer_id","50261");
        map.put("sign_type","RSA");
        map.put("version","4.0");

        map.put("trade_no",trade_no);
        map.put("order_id",order_id);
        map.put("mer_date",mer_date);
        map.put("pay_date",pay_date);
        map.put("amount",amount);
        map.put("amt_type","RMB");
        map.put("pay_type","DEBITCARD");
        map.put("settle_date",settle_date);
        map.put("trade_state",trade_state);

        return getResult(map);
    }

    //订单查询
    public Map merOrderInfoQuery(String order_id,String mer_date){
        Map map = new HashMap();
        map.put("service","mer_order_info_query");
        map.put("mer_id","50261");
        map.put("sign_type","RSA");
        map.put("version","4.0");
        map.put("charset","UTF-8");
        map.put("res_format","HTML");

        map.put("order_id",order_id);
        map.put("mer_date",mer_date);

        return getResult(map);
    }

    private Map getResult(Map map){
        com.umpay.api.common.ReqData reqData = null;
        Map res = null;
        try {
            reqData = com.umpay.api.paygate.v40.Mer2Plat_v40.makeReqDataByGet(map);
            String reqUrl = reqData.getUrl();
            URL url = new URL(reqUrl);
            URLConnection conn = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection)conn;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();
                res = com.umpay.api.paygate.v40.Plat2Mer_v40.getResData(in);
            }else{
                System.out.println("平台响应状态异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
