/**
 * @部门:学工产品事业部
 * @日期：2014-6-4 下午02:14:52 
 */
package xgxt.sso;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.utils.GetTime;

/**
 * @系统名称: 学生工作管理系统
 * @作者： 张昌路[工号:982]
 * @时间： 2014-6-4 下午02:14:52
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZfsoftMd5 {
	/** 十六进制下数字到字符的映射数组 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 把inputString加密。
	 * 
	 * @param inputString
	 *            待加密的字符串
	 * @return
	 */
	public static String getMd5(String inputString) {
		return encodeByMD5(inputString);
	}

	/**
	 * 验证输入的key是否正确
	 * 
	 * @param key
	 *            加密后的key
	 * @param inputString
	 *            输入的字符串
	 * @return
	 */
	public static boolean authenticateKey(String key, String inputString) {
		if (key.equals(encodeByMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对字符串进行MD5编码
	 * 
	 * @param originString
	 * @return
	 */
	private static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] results = md.digest(originString.getBytes());
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 十六进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 将一个字节转化成16进制形式的字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String currDate=sdf.format(new Date());
		System.out.println(currDate);
		System.out.println(ZfsoftMd5.authenticateKey("16A714051F176A938C68AD02197C0C39".toUpperCase(), "20000090"+"2014-06-12"+"zfssokey"));
	}
}
