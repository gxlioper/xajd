/*
 * 创建日期 2006-7-22
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.base;

import java.security.MessageDigest;



/**
 * @author bat_zzj
 * 
 * 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class Encrypt {
	private String key;

	public Encrypt() {
		this.key = "Encrypt01";
	}
//
	public String encrypt(String PlainStr) {
//		String PStr, KeyStr, NewStr;
//		char PChar, KeyChar;
//		int Pos, i;
////		int intLen;
//		String Side1, Side2;
//		NewStr = "";
//		Pos = 0;
//		for (i = 0; i < PlainStr.length(); i++) {
//			PStr = PlainStr.substring(i, i + 1);
//			KeyStr = key.substring(Pos, Pos + 1);
//			PChar = PStr.charAt(0);
//			KeyChar = KeyStr.charAt(0);
//
//			if ((((int) PChar ^ (int) KeyChar) < 32)
//					|| (((int) PChar ^ (int) KeyChar) > 126)
//					|| (((int) PChar) < 0) || (((int) PChar) > 255)) {
//				NewStr = NewStr + PStr;
//			} else {
//
//				PChar = (char) ((int) PChar ^ (int) KeyChar);
//				NewStr = NewStr + String.valueOf(PChar);
//			}
//			Pos++;
//			if (Pos == key.length()) {
//				Pos = 0;
//			}
//		}
//		if ((NewStr.length() % 2) == 0) {
//			Side1 = NewStr.substring(0, NewStr.length() / 2);
//			Side2 = NewStr.substring(NewStr.length() / 2, NewStr.length());
//			StringBuffer s1 = new StringBuffer(Side1);
//			s1.reverse();
//			Side1 = String.valueOf(s1);
//			StringBuffer s2 = new StringBuffer(Side2);
//			s2.reverse();
//			Side2 = String.valueOf(s2);
//			NewStr = Side1 + Side2;
//		}
//		return NewStr;
		return "{MD5}" + testHA2(PlainStr, "md5");
	}

	public String decrypt(String PlainStr) {
		String PStr, KeyStr, NewStr;
		char PChar, KeyChar;
		int Pos, i;
//		int intLen;
		String Side1, Side2;
		NewStr = "";
		Pos = 0;
		if ((PlainStr.length() % 2) == 0) {
			Side1 = PlainStr.substring(0, PlainStr.length() / 2);
			Side2 = PlainStr
			.substring(PlainStr.length() / 2, PlainStr.length());
			StringBuffer s1 = new StringBuffer(Side1);
			s1.reverse();
			Side1 = String.valueOf(s1);
			StringBuffer s2 = new StringBuffer(Side2);
			s2.reverse();
			Side2 = String.valueOf(s2);
			PlainStr = Side1 + Side2;
		}

		for (i = 0; i < PlainStr.length(); i++) {
			PStr = PlainStr.substring(i, i + 1);
			KeyStr = key.substring(Pos, Pos + 1);
			PChar = PStr.charAt(0);
			KeyChar = KeyStr.charAt(0);

			if ((((int) PChar ^ (int) KeyChar) < 32)
					|| (((int) PChar ^ (int) KeyChar) > 126)
					|| (((int) PChar) < 0) || (((int) PChar) > 255)) {
				NewStr = NewStr + PStr;
			} else {

				PChar = (char) ((int) PChar ^ (int) KeyChar);
				NewStr = NewStr + String.valueOf(PChar);
			}
			Pos++;
			if (Pos == key.length()) {
				Pos = 0;
			}
		}
		return NewStr;
	}
	
	public static String testHA2(String data, String ha) {
		byte[] buffer = null;
		MessageDigest messageDigest = null;
		String s = "";
		try {
			buffer = data.getBytes("UTF-8");
			messageDigest = MessageDigest.getInstance(ha);
			messageDigest.update(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		buffer = messageDigest.digest();
		s = new sun.misc.BASE64Encoder().encode(buffer);
		return s;
	}


	public static void main(String[] args) {
		Encrypt e = new Encrypt();
//		System.out.println(e.encrypt("VZvHND"));
		System.out.println(e.encrypt("beede0060d3b3a58"));
	}
}