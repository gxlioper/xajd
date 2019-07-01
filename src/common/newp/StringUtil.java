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
	 * ��⴫��Ķ����Ƿ�Ϊ�գ����Ϊnull��
	 * ��ת���ɿ��ַ���""�� ������ת�����ַ���
	 * @param obj
	 */
	public static String objectToString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * �ж�һ���ַ����Ƿ�Ϊnull������"",���ߡ� �� 
	 * ���м��ж���ո������������ַ���"null"������true 
	 * @param str
	 * 
	 */
	public static boolean isNull(String str) {
		return (str == null)
				|| ("".equalsIgnoreCase(str.replaceAll("\\s*", "")) 
						|| "null".equalsIgnoreCase(str));
	}

	/**
	 * ɾ��������ַ�����ǰ��ո�,���Ϊnull����null�� 
	 * ���򷵻ش������ַ���
	 * @param str
	 */
	public static String trim(Object str) {
		if (str == null) {
			return null;
		}
		return str.toString().trim();
	}
	
	/**
	 * ����������ַ�������ָ��encode���ַ���
	 * ���sEncode��tEncode��Ϊnull���"ISO-8859-1"תΪ "GBK"
	 * @param str       Դ��
	 * @param sEncode   ��str�л�ȡbytecode��encode
	 * @param tEncode   ��ת���ɵ��ַ���
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
	 * ���ַ������㵽�̶����ȣ�
	 * ���sEncode��tEncode��Ϊnull���"ISO-8859-1"תΪ "GBK"
	 * @param str    Դ��
	 * @param len    lenָ�������ַ��ĳ���
	 * @param flag   ָ������߲�λ�����ұ߲�λ��0����ߣ�1��Ϊ�ұߣ�
	 * @param instr  ռλ��������Ϊ1λ�ģ�
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
	
	/** �ַ�ת��ΪASCII 
	 *  @param c �ַ�
	 */
	public static int toASCII(char c) {
		int i = c;
		return i;
	}
	
	/** ȡ���ַ����ֽڳ��� 
	 *  @param str �ַ���
	 */
	public static int getByteLength(String str) {
		return ((str.getBytes()).length);
	}
	
	/** ��ָ���ַ����ָ�������ַ���
	 *  @param str �ַ�
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
	
	/** ��ָ���ַ����ָ�������ַ����������ַ���ǰ������зָ����
	 *  @param str �ַ�
	 */
	public static String arraySplitStr��(String[] array,String splitFlag) {
		if(ArrayUtil.isNull(array)){
			return null;
		}else{
			return objectToString(splitFlag) + arraySplitStr1(array , splitFlag) 
					+ objectToString(splitFlag);
		}
		
	}
	
	/**
	 * �Ѵ�����ַ������Ҷ˵�splitFlag�ص�
	 * @param src          �����ֵ 
	 * @param splitFlag    �ָ���־��
	 * @return             
	 */
	public static String rtrim(String src,String splitFlag){
		return src.substring(0, src.lastIndexOf(splitFlag));
	}
	
	/**
	 * �Ѵ�����ַ�������˵�splitFlag�ص�
	 * @param src          �����ֵ 
	 * @param splitFlag    �ָ���־��
	 * @return             
	 */
	public static String ltrim(String src,String splitFlag){
		return src.substring(splitFlag.length());
	}
	
	/**
	 * �Ѵ�����ַ��������˵�splitFlag�ص�
	 * @param src          �����ֵ 
	 * @param splitFlag    �ָ���־��
	 * @return             
	 */
    public static String trim(String src,String splitFlag){
    	String temp = src.substring(splitFlag.length());
		return temp.substring(0, temp.lastIndexOf(splitFlag));
	}
    


	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ����ȥ��ȫ��ǵĿո񣬶���Unicode�е��������ַ�����ʶ��
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
	 * @����: ��֤���ڸ�ʽ�Ƿ���ȷ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-24 ����09:09:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param formatStr
	 * @param time
	 * @return
	 * boolean �������� 
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
		if(m.find()){ //ע�⣺m.findֻ����һ�Σ��ڶ��ε��ú�Ϊfalse
			return true;
		}else{
			return false;
		}
	}
		
}
