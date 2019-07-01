package common.newp;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 检测传入的对象是否为空，如果为null，
	 * 则转换成空字符串""， 否则将起转化成字符串
	 * @param obj
	 */
	public static String objectToString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 判断一个字符串是否为null，或者"",或者“ ” 
	 * （中间有多个空格的情况）或者字符串"null"均返回true 
	 * @param str
	 * 
	 */
	public static boolean isNull(String str) {
		return (str == null)
				|| ("".equalsIgnoreCase(str.replaceAll("\\s*", "")) 
						|| "null".equalsIgnoreCase(str));
	}

	/**
	 * 删除输入的字符串的前后空格,如果为null返回null， 
	 * 否则返回处理后的字符串
	 * @param str
	 */
	public static String trim(Object str) {
		if (str == null) {
			return null;
		}
		return str.toString().trim();
	}
	
	/**
	 * 根据输入的字符串生成指定encode的字符串
	 * 如果sEncode和tEncode都为null则从"ISO-8859-1"转为 "GBK"
	 * @param str       源串
	 * @param sEncode   从str中获取bytecode的encode
	 * @param tEncode   欲转换成的字符集
	 */
	public static String changeEncode(String str,String sEncode,String tEncode){
		String r = str;
		try {
			if(sEncode == null && tEncode == null){
				str = new String(str.getBytes("ISO-8859-1"), "GBK");
			}else{
				r = new String(str.getBytes(sEncode),tEncode);
			}		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 将字符串补足到固定长度，
	 * 如果sEncode和tEncode都为null则从"ISO-8859-1"转为 "GBK"
	 * @param str    源串
	 * @param len    len指定返回字符的长度
	 * @param flag   指定是左边补位还是右边补位（0是左边，1均为右边）
	 * @param instr  占位符（必须为1位的）
	 */
	public static String toLengthStr(String str, int len ,int flag , String instr) {
		int length = 0;
		if(str != null){
			length = str.length();
		}
		for (int i = 0; i < (len - length); i++) {
			if(flag == 0){
				str = instr + str;
			}else{
				str = str + instr;
			}			
		}
		return str;
	}
	
	/** 字符转化为ASCII 
	 *  @param c 字符
	 */
	public static int toASCII(char c) {
		int i = c;
		return i;
	}
	
	/** 取得字符串字节长度 
	 *  @param str 字符串
	 */
	public static int getByteLength(String str) {
		return ((str.getBytes()).length);
	}
	
	/** 用指定字符串分割数组成字符串
	 *  @param str 字符
	 */
	public static String arraySplitStr1(String[] array,String splitFlag) {
		String str = "";
		if(ArrayUtil.isNull(array)){
			return null;
		}else{
			for(int i = 0 ; i < array.length ; i++){
				str += objectToString(splitFlag) + array[i];
			}
		}
		return str;
	}
	
	/** 用指定字符串分割数组成字符串（返回字符串前后均带有分割符）
	 *  @param str 字符
	 */
	public static String arraySplitStr２(String[] array,String splitFlag) {
		if(ArrayUtil.isNull(array)){
			return null;
		}else{
			return objectToString(splitFlag) + arraySplitStr1(array , splitFlag) 
					+ objectToString(splitFlag);
		}
		
	}
	
	/**
	 * 把传入的字符串的右端的splitFlag截掉
	 * @param src          传入的值 
	 * @param splitFlag    分隔标志符
	 * @return             
	 */
	public static String rtrim(String src,String splitFlag){
		return src.substring(0, src.lastIndexOf(splitFlag));
	}
	
	/**
	 * 把传入的字符串的左端的splitFlag截掉
	 * @param src          传入的值 
	 * @param splitFlag    分隔标志符
	 * @return             
	 */
	public static String ltrim(String src,String splitFlag){
		return src.substring(splitFlag.length());
	}
	
	/**
	 * 把传入的字符串的两端的splitFlag截掉
	 * @param src          传入的值 
	 * @param splitFlag    分隔标志符
	 * @return             
	 */
    public static String trim(String src,String splitFlag){
    	String temp = src.substring(splitFlag.length());
		return temp.substring(0, temp.lastIndexOf(splitFlag));
	}
    


	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 仅能去除全半角的空格，对于Unicode中的其他空字符不能识别
	 * @param str
	 *         
	 */
	public static String Q2BChange(String str){
		String result = "";
		int  code = 0;
		//
		String inputStr = str.replaceAll("[\\p{javaWhitespace}|\\p{Space}]+", " ").trim();
		for(int i=0;i<inputStr.length();i++){
			code = inputStr.codePointAt(i);
			if(code >= 65281 && code < 65373){
				result += new String(new int[]{ code-65248 },0,1);
			} else {
				result += inputStr.charAt(i);
			}
		}
		return result;
	}
	
	
	
	//
	public static String decodeURIPath(String fileName){
		String path = null;
		try {
			path = new URI(StringUtil.class.getClassLoader().getResource(fileName).getPath()).toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 
	 * @描述: 验证日期格式是否正确
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-24 上午09:09:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param formatStr
	 * @param time
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public static boolean checkDateFormatAndValite(String formatStr,String time){
		  try {
			    SimpleDateFormat format = new SimpleDateFormat(formatStr);
	            Date ndate = format.parse(time);
	            String str = format.format(ndate);
	            System.out.println(ndate);
	            System.out.println(str);
	            System.out.println("time=" + time);
	            //success
	            if (str.equals(time))
	                return true;
	            //datetime is not validate
	            else
	                return false;
	        } catch (Exception e) {
	            e.printStackTrace();
	            //format error
	            return false;
	        }
	}
	
	
	public static boolean checkTelePhone(String phone){
		String regExp ="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}|[0]{1}[0-9]{2,3}-[0-9]{7,8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phone);
		if(m.find()){ //注意：m.find只能用一次，第二次调用后都为false
			return true;
		}else{
			return false;
		}
	}
		
}
