package xgxt.comm;

import java.io.UnsupportedEncodingException;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 中文转拼音
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * 
 * @version 1.0
 */

public class CnToEnUtil {

	private static final int GB_SP_DIFF = 160;

	private static final int[] secPosvalueList = { 1601, 1637, 1833, 2078,
			2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730,
			3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };

	private static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'w', 'x', 'y', 'z' };

	public static String getFirstLetter(String oriStr) {
		String str = oriStr.toLowerCase();
		StringBuffer buffer = new StringBuffer();
		char ch;
		char[] temp;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			temp = new char[] { ch };
			byte[] uniCode = null;
			try {
				uniCode = new String(temp).getBytes("GBK");
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			if (uniCode[0] < 128 && uniCode[0] > 0) {
				buffer.append(temp);
			} else {
				buffer.append(convert(uniCode));
			}
		}
		return buffer.toString().substring(0, 1);
	}

	private static char convert(byte[] bytes) {

		char result = '-';
		int secPosvalue = 0;
		int i;
		for (i = 0; i < bytes.length; i++) {
			bytes[i] -= GB_SP_DIFF;
		}
		secPosvalue = bytes[0] * 100 + bytes[1];
		for (i = 0; i < 23; i++) {
			if (secPosvalue >= secPosvalueList[i]
					&& secPosvalue < secPosvalueList[i + 1]) {
				result = firstLetter[i];
				break;
			}
		}
		return result;
	}

	public static void main(String s[]) {

		System.out.print(getFirstLetter("进大煞风景圣诞红猪可桢"));
	}
}
