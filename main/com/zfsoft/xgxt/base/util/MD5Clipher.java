/**
 * 
 */
package com.zfsoft.xgxt.base.util;

/**
 * MD5加密方式
 * 
 * @author zhangxiaobin
 *
 */
public class MD5Clipher {

	// MD5加密方法，使用该方法进行加密
	public static String encrypt(String inStr)
	{
		StringBuffer hexValue = new StringBuffer();
 		try {
 			java.security.MessageDigest md5 = java.security.MessageDigest.getInstance("MD5");
 			md5.reset();
 			md5.update(inStr.getBytes("UTF-8"));
 			byte[] md5Bytes = md5.digest();
 	 		
 	 		for (int i = 0; i < md5Bytes.length; i++) {
 	 			int val = ((int) md5Bytes[i]) & 0xff;
 	 			if (val < 16)
 	 				hexValue.append("0");
 	 			hexValue.append(Integer.toHexString(val));
 	 		}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}	
 		return hexValue.toString();
	}
	
	public static void main(String[] args) {
		System.out.println("ddd:"+encrypt("0019641453176361zfsoft"));
	}
}
