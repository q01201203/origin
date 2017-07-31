package Test;


import org.junit.Test;
import util.HttpSender;

public class HttpSenderTest {

	//@Test
	public void testMsg(){
		String url = "http://sms.253.com/msg/";// 应用地址
		String un = "N3353057";// 账号
		String pw = "x0qLwVW76e2702";// 密码
		String phone = "";// 手机号码，多个号码使用","分割
		String msg = "【253云通讯】用户您好，你的验证码是123456";// 短信内容
		String rd = "1";// 是否需要状态报告，需要1，不需要0
		String ex = null;// 扩展码

		try {
			String returnString = HttpSender.batchSend(url, un, pw, phone, msg, rd, ex);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}

	//@Test
	public void testRandom(){
		for (int i = 0; i < 100; i++) {
			System.out.println("随机数为" +(int)((Math.random()*9+1)*100000));
		}
	}

	//@Test
	public void test2(){
		String s = "2222222,0";
		char successCode = '0';
		System.out.println("index 0 = " +s.charAt(s.indexOf(",")+1));
		System.out.println("1 = "+successCode);
		boolean success = (s.charAt(s.indexOf(",")+1) == '0');
		System.out.println("s = " +success);
	}

	@Test
	public void test(){
	}
}