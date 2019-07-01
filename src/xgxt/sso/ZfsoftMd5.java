/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-4 ����02:14:52 
 */
package xgxt.sso;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.utils.GetTime;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-6-4 ����02:14:52
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZfsoftMd5 {
	/** ʮ�����������ֵ��ַ���ӳ������ */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * ��inputString���ܡ�
	 * 
	 * @param inputString
	 *            �����ܵ��ַ���
	 * @return
	 */
	public static String getMd5(String inputString) {
		return encodeByMD5(inputString);
	}

	/**
	 * ��֤�����key�Ƿ���ȷ
	 * 
	 * @param key
	 *            ���ܺ��key
	 * @param inputString
	 *            ������ַ���
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
	 * ���ַ�������MD5����
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
	 * ת���ֽ�����Ϊ16�����ִ�
	 * 
	 * @param b
	 *            �ֽ�����
	 * @return ʮ�������ִ�
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * ��һ���ֽ�ת����16������ʽ���ַ���
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
