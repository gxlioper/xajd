
package xgxt.utils.String;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;

import xgxt.action.Base;
import xgxt.utils.date.MoneyFormat;

/**
 * �ַ������鼰�����Ĺ�����
 */
public class StringUtils {
	/**
	 * �ж�һ���ַ����Ƿ�Ϊnull��""
	 * @param arg
	 * @return Ϊ�գ�true ���ǿգ�false
	 */
	public static boolean isNull(String str){
		return (str == null) || (str.trim().length()==0) ? true : false;
	}
	
	public static boolean isNotNull(String str){
		return (str == null) || (str.trim().length()==0) ? false : true;
	}
	
	/**
	 *�ж������Ƿ��ǿյ� 
	 */
	public static boolean isArrayNotNull(String[] array){
		if(array == null || array.length<1){
			return false;
		}else{
			for(int i=0;i<array.length;i++){
				if(StringUtils.isNull(array[i])){
					return false;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * �ж�str2 �Ƿ���str1 ��ͬ�����Դ�Сд��
	 * @param str1
	 * @param str2
	 * @return ��ͬ�򷵻�true������false
	 * @throws NullStringException ���str1�ǿգ����׳����ַ����쳣
	 */
	public static boolean isIgnoreCaseEqual(String str1,String str2) throws NullStringException{
		if(str1 == null){
			throw new NullStringException();
		} else {
			return str1.equalsIgnoreCase(str2);
		}
	}
	
	/**
	 * �ж�str2 �Ƿ���str1 ��ȫ��ͬ
	 * @param str1
	 * @param str2
	 * @return ��ͬ�򷵻�true������false
	 * @throws NullStringException ���str1�ǿգ����׳����ַ����쳣
	 */
	public static boolean isEqual(String str1,String str2) throws NullStringException{
		if(str1 == null){
			throw new NullStringException();
		} else {
			return str1.equals(str2);
		}
	}
	
	
	/**
	 * �ж�str2 �Ƿ���str1 ��ȫ��ͬ
	 * @param str1
	 * @param str2
	 * @return ��ͬ�򷵻�true������false
	 * @throws NullStringException ���str1�ǿգ����׳����ַ����쳣
	 */
	public static boolean equals(String str1,String str2){
		if(str1 == null){
			return str1 == str2;
		} else {
			return str1.equals(str2);
		}
	}
	/**
	 * �Ѵ�����ַ����ķָ����ַ�������
	 * @param stringValue    ��Ҫ�ָ����ַ���
	 * @param splitStrType   �ָ���־
	 * @return String[]    
	 */
	public static String[] splitStr(String stringValue, String splitStrType) {
		if(stringValue == null) return null;
		return stringValue.split(splitStrType);
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
	 * ���ַ����е�ȫ���ַ���ת���ɰ���ַ���
	 * ������а���ַ������򲻱�
	 * @param str
	 * @return
	 */
	public static String Q2BChange(String str){
		String result = "";
		int  code = 0;
		String inputStr = str.replaceAll("^[ |��]*","").replaceAll("[ |��]$","").replaceAll("[ |��]","");
		for(int i=0;i<inputStr.length();i++){
			code = inputStr.codePointAt(i);
			if(code>=65281 && code<65373){
				//��65248����ת�����
				result += new String(new int[]{ code-65248 },0,1);
			} else if(code>0 && code<65373){
				result += inputStr.charAt(i);
			}
		}
		return result;
	}
	
	/**
	 * ���ַ����е������ַ�ȥ��
	 * @param String str
	 * @return String
	 * */
	public static String cleanString(String str){
		String[] escapeStr = {"\\*","\\+","/","\\>","\\<","\\=","\\'","\""};
		for(int i=0; i<escapeStr.length; i++){
			Pattern p = Pattern.compile(escapeStr[i]);
			str = p.matcher(str).replaceAll("");
		}
		return str;
	}
	
	
	/**
	 * n���ַ���ƴ��Ϊһ���ַ���
	 * @param String...
	 * @return String
	 * */
	public static String joinStr(String...sqlFrag){
		StringBuilder sb = new StringBuilder();
		for(String s : sqlFrag){
			sb.append(s);
		}
		return sb.toString();
	}
	
	/**
	 * n���ַ�������ƴ��Ϊһ���ַ�������
	 * @param String...
	 * @return String
	 * */
	public static String[] joinStrArr(String[]...tmpArr){
		int strArrLength = 0;
		for(String[] s : tmpArr){
			if(s != null && s.length>0){
				strArrLength += s.length;
			}
		}
		
		String[] resultArr = new String[strArrLength];
		int m = 0;
		for(int i=0; i<tmpArr.length; i++){
			String[] s = tmpArr[i];
			if(s != null && s.length>0){
				for(int j=0; j<s.length; j++){
					resultArr[m++] = s[j];
				}
			}
		}
		
		return resultArr;
	}
	
	
	 /**
	 * �ж��ַ������������Ƿ���ڵķ�����ϣ�����иĽ�
	 * @param str
	 * @return
	 */
	public static boolean stringExistArray(String str,String[] strs){
		for(int i=0; null != strs && i<strs.length;i++){
			if(str.equalsIgnoreCase(strs[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ��ȡGuid
	 * @author �����
	 * @return
	 */
	public static String getGuid() {
		String[] str = new String[] { "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "0" };
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i<32;i++) {
			sb.append(str[random.nextInt(str.length-1)]);
		}
		return sb.toString();
	}
	
	/**
	 * ���ַ��������е�������ϳ�һ���ַ�������ָ���ķָ����ָ�
	 * @param arr
	 * @param splitStr
	 * @return String
	 * @author lr
	 * */
	public static String joinStrByArray(String[] arr,String splitStr){
		StringBuffer sb = new StringBuffer();
		if(arr != null && arr.length>0){
			for(String str : arr){
				sb.append(str);
				sb.append(splitStr);
			}
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	/**
	 * �жϺ�һ���������Ƿ���ǰһ�����ж���
	 * */
	public static boolean contains(String[] firArr, String[] sedArr){

		List<String> firList = Arrays.asList(firArr);
		List<String> sedList = Arrays.asList(sedArr);
		
		return firList.containsAll(sedList);
	}
	/**
	 * ������ַ�������ָ���ĸ�ʽ���зָ�
	 * lyl
	 */
	public static List<String> arrayStrByString(String arr,String splitStr){
		List<String> resultList = new ArrayList<String>();
		String[] art = arr.split(splitStr);
		 for(int i=0;i<art.length;i++){
			  resultList.add(art[i]);
			 }
		return resultList;
	}
	
	public static String joinArr(String[] arr){
		
		return ArrayUtils.toString(arr).replace("{", "").replace("}", "");
	}
	
	
	
	/**
	 * �ַ����������е�����
	 * @param str
	 * @param arr
	 * @return
	 */
	public static int getStrIndexInArray(String str,String[] arr){
		int index = -1;
		
		for(int i = 0 ; i < arr.length ; i++){
			if (arr[i].equals(str)){
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	
	
	/**
	 * 
	 * @����:����ת����������������������ֵ��\nתΪ<br/>,�������ı�����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����11:39:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param o
	 * @return
	 * @throws Exception
	 * Object �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Object formatBean(Object o) throws Exception{
		
		if (o == null){
			return null;
		}
		
		Class t = o.getClass();
		Field[] fields = t.getDeclaredFields();
		
		for (Field field : fields){
			
			String fieldName = field.getName();
			
			if ("serialVersionUID".equals(fieldName)){
				continue;
			}
			
			String before = field.getType().getName().equals("boolean") ? "is" : "get";
			
			String getMethodName = new StringBuffer(before).append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			String setMethodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
		
			Object value = t.getMethod(getMethodName).invoke(o);
			
			if (value instanceof String){
				String newValue = ((String)value).replaceAll("\\n", "<br/>");
				
				t.getMethod(setMethodName,String.class).invoke(o, newValue);
			}
		
		}
		
		return o;
	}
	/**
	 * ����ת����������������������ֵ��\nתΪ<br/>,�������ı����С�ȥ����β�ո�
	 */
	@SuppressWarnings("unchecked")
	public static Object formatBeanTrim(Object o) throws Exception{
		
		if (o == null){
			return null;
		}
		
		Class t = o.getClass();
		Field[] fields = t.getDeclaredFields();
		
		for (Field field : fields){
			
			String fieldName = field.getName();
			
			if ("serialVersionUID".equals(fieldName)){
				continue;
			}
			
			String before = field.getType().getName().equals("boolean") ? "is" : "get";
			
			String getMethodName = new StringBuffer(before).append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			String setMethodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			
			Object value = t.getMethod(getMethodName).invoke(o);
			
			if (value instanceof String){
				String newValue = ((String)value).replaceAll("\\n", "<br/>").trim(); // �޸ģ���ȥ����β�ո�
				t.getMethod(setMethodName,String.class).invoke(o, newValue);
			}
			
		}
		
		return o;
	}
	
	
	
	/**
	 * 
	 * @����:����ת����������������������ֵ��<br/>תΪ\n,�������ı�����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����11:38:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param o
	 * @return
	 * @throws Exception
	 * Object �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Object formatData(Object o) throws Exception{
		
		if (o == null){
			return null;
		}
		 
		if (o instanceof Map){
			
			Map<String,String> map = (Map) o;
			Map<String,String> newMap = new HashMap<String, String>();
			
			for (Map.Entry<String, String> entry : map.entrySet()){
				String key = entry.getKey();
				String value = (entry.getValue()==null)?"":String.valueOf(entry.getValue());
				//���ӿ�վ�ű�����
				//value=Encode.forHtml(value);
				if (!isNull(value)){
					newMap.put(key, value.replaceAll("<br/>", "\\\n"));
				}
			}
			return newMap;
			
		} else {
			Class t = o.getClass();
			Field[] fields = t.getDeclaredFields();
			
			for (Field field : fields){
				
				String fieldName = field.getName();
				
				if ("serialVersionUID".equals(fieldName)){
					continue;
				}
				
				String getMethodName = new StringBuffer("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
				String setMethodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			
				Object value = t.getMethod(getMethodName).invoke(o);
				
				if (value instanceof String){
					String newValue = ((String)value).replaceAll("<br/>", "\\\n");
					//newValue=Encode.forHtml(newValue);
					t.getMethod(setMethodName,String.class).invoke(o, newValue);
				}
			
			}
			
			return o;
		}
		
	}
	
	/**
	 * 
	 * @����:����ת��(�鿴�ã���ת��br)
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����11:38:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param o
	 * @return
	 * @throws Exception
	 * Object �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Object formatDataCk(Object o) throws Exception{
		
		if (o == null){
			return null;
		}
		
		if (o instanceof Map){
			
			Map<String,String> map = (Map) o;
			Map<String,String> newMap = new HashMap<String, String>();
			
//			for (Map.Entry<String, String> entry : map.entrySet()){
//				String key = entry.getKey();
//				String value = entry.getValue();
//			}
			return newMap;
			
		} else {
			Class t = o.getClass();
			Field[] fields = t.getDeclaredFields();
			
			for (Field field : fields){
				
				String fieldName = field.getName();
				
				if ("serialVersionUID".equals(fieldName)){
					continue;
				}
				
				String getMethodName = new StringBuffer("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
				String setMethodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			
				Object value = t.getMethod(getMethodName).invoke(o);
				
//				if (value instanceof String){
//					String newValue = ((String)value).replaceAll("<br/>", "\\\n");
//					t.getMethod(setMethodName,String.class).invoke(o, newValue);
//				}
			
			}
			
			return o;
		}
		
	}
	
	/**
	 * @�������Ƚ�����string�Ĵ�С
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��5�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param strA
	 * @param strB
	 * @return  StrA>StrB ����true �����������false
	 * boolean ��������
	 */
	public static boolean compareStr (String strA,String strB){
		if(isNull(strA)||isNull(strB)){
			return false;
		}else if(strA.startsWith("-")&&!strB.startsWith("-")){
			return false;
		}else if(!strA.startsWith("-")&&strB.startsWith("-")){
			return true;
		}else if(strA.startsWith("-")&&strB.startsWith("-")){
			strA=strA.substring(1,strA.length());
			strB=strB.substring(1,strB.length());
			return Float.parseFloat(strB)>Float.parseFloat(strA); //����ֵ����
		}
		return Float.parseFloat(strA)>Float.parseFloat(strB);

	}
	
	/**
	 * 
	 * @����:ת��stringΪinteger
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-26 ����07:05:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * Integer �������� 
	 */
	public static Integer paseStringToInteger(String str){
		return isNull(str)?0:Integer.parseInt(str);
	}
	
	/**
	 * 
	 * @����:����ת����������������������ֵ��\nתΪ<br/>,�������ı�����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����11:38:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param o
	 * @return
	 * @throws Exception
	 * Object �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static Object formatDataView(Object o) throws Exception{
		
		if (o == null){
			return null;
		}
		
		if (o instanceof Map){
			
			Map<String,String> map = (Map) o;
			Map<String,String> newMap = new HashMap<String, String>();
			
			for (Map.Entry<String, String> entry : map.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if (!isNull(value)){
					newMap.put(key, value.replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;"));
				}
			}
			return newMap;
			
		} else {
			Class t = o.getClass();
			Field[] fields = t.getDeclaredFields();
			
			for (Field field : fields){
				
				String fieldName = field.getName();
				
				if ("serialVersionUID".equals(fieldName)){
					continue;
				}
				
				String getMethodName = new StringBuffer("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
				String setMethodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			
				Object value = t.getMethod(getMethodName).invoke(o);
				
				if (value instanceof String){
					String newValue = ((String)value).replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;");
					t.getMethod(setMethodName,String.class).invoke(o, newValue);
				}
			
			}
			
			return o;
		}
		
	}
	
	/**
	 * 
	 * @����:����������ת����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-19 ����05:24:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param i
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String numberToChinese(int i){
		switch (i) {
		case 1:
			return "����һ";
		case 2:
			return "���ڶ�";
		case 3:
			return "������";
		case 4:
			return "������";
		case 5:
			return "������";
		case 6:
			return "������";
		case 7:
			return "������";
		default:
			return "����һ";
			
		}
	}

  /**
   * 
   * @����: ��ͨ�á����url��ֵ�������⣬ǰ̨������encodeURI()��������,��ֵΪ��������
   * @���ߣ�yxy[���ţ�1206]
   * @���ڣ�2016-9-20 ����11:13:08
   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
   * @param Object o
   * @return
   * @throws Exception
   * Object �������� 
   * @throws
   */
  public static Object formatGbkToUtf(Object o) throws Exception{
		
		if (o == null){
			return null;
		}
		 
		if (o instanceof Map){
			
			Map<String,String> map = (Map) o;
			Map<String,String> newMap = new HashMap<String, String>();
			
			for (Map.Entry<String, String> entry : map.entrySet()){
				String key = entry.getKey();
				String value = (entry.getValue()==null)?"":String.valueOf(entry.getValue());
				if (!isNull(value)){
					value = URLDecoder.decode(URLDecoder.decode(value,"UTF-8"),"UTF-8");
					newMap.put(key, value);
				}
			}
			return newMap;
			
		} else {
			Class t = o.getClass();
			Field[] fields = t.getDeclaredFields();
			
			for (Field field : fields){
				
				String fieldName = field.getName();
				
				if ("serialVersionUID".equals(fieldName)){
					continue;
				}
				
				String getMethodName = new StringBuffer("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
				String setMethodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
			
				Object value = t.getMethod(getMethodName).invoke(o);
				
				if (value instanceof String){
					String newValue = URLDecoder.decode(URLDecoder.decode((String)value,"UTF-8"),"UTF-8");
					
					//newValue=Encode.forHtml(newValue);
					t.getMethod(setMethodName,String.class).invoke(o, newValue);
				}
			
			}
			
			return o;
		}
		
	}
  
  /**
   * 
   * @����:��ͨ�á����url��ֵ�������⣬ǰ̨������encodeURI()��������,��ֵΪ�ַ���
   * @���ߣ�yxy[���ţ�1206]
   * @���ڣ�2016-9-20 ����11:58:31
   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
   * @param str
   * @return
   * @throws Exception
   * String �������� 
   * @throws
   */
  public static String formatGbkToUtf(String str) throws Exception{
	  String value = null;
	  if(isNotNull(str)){
		  value = URLDecoder.decode(URLDecoder.decode(str,"UTF-8"),"UTF-8");
	  }else{
		  return null;
	  }
	  return value;
		
	}
  
  /**
   * 
   * @����: \nת<br/>,�ո�ת&nbsp;,�����ı���鿴ʱ��ʾ
   * @���ߣ�yxy[���ţ�1206]
   * @���ڣ�2016-9-21 ����11:53:26
   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
   * @param o
   * @return
   * HashMap<String,String> �������� 
   * @throws
   */
  public static HashMap<String,String> formatMap(Map o){
	    Map<String,String> map = (Map) o;
		Map<String,String> newMap = new HashMap<String, String>();
		
		for (Map.Entry<String, String> entry : map.entrySet()){
			String key = entry.getKey();
			String value = (entry.getValue()==null)?"":String.valueOf(entry.getValue());
			//���ӿ�վ�ű�����
			//value=Encode.forHtml(value);
			if (!isNull(value)){
				newMap.put(key, value.replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;"));
			}
		}
		return (HashMap<String, String>) newMap;
  }
  
	  /** 
	 * @����:�ж��Ƿ�Ϊ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����09:04:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public static boolean isNumeric(String str){
		boolean isNum = str.trim().matches("[0-9]+");
		return isNum;
	  }
	public static boolean isNumber(String str){   
		 Pattern pattern=Pattern.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"); // �ж�С�����2λ�����ֵ�������ʽ  
	     Matcher match=pattern.matcher(str);   
	     if(match.matches()==false){   
	        return false;   
	     }else{   
	        return true;   
	     }   
	 }  

	  /** 
	 * @����:ת�����ָ�ʽΪ��������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����09:04:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public static String formatChNum(String num) {	

		// ���ִ�д
		String[] dx = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��", "ʰ",
				"��", "Ǫ", "��", "��" };

		// ������Э
		String[] xx = { "��", "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ",
				"��", "ǧ", "��", "��" };
		
		String chineseNum = MoneyFormat.format(String.valueOf(num));
		
		chineseNum = chineseNum.replace("Ԫ��", "");
		
		if(!Base.isNull(chineseNum)){
			
			char[] charNum =  new char[chineseNum.length()];
			String [] arrNum =  new String[chineseNum.length()];
			
			for (int i = 0; i < chineseNum.length(); i++) {
				charNum[i] = chineseNum.charAt(i);
			}
			
			for (int i = 0; i < charNum.length; i++) {

				for (int j = 0; j < dx.length; j++) {

					if (String.valueOf(charNum[i]).equalsIgnoreCase(dx[j])) {
						arrNum[i] = xx[j];
					}
				}
			}
			
			if (arrNum != null && arrNum.length > 0) {
				chineseNum = "";
				for (String number : arrNum) {
					chineseNum += number;
				}
			}
		}
		
		return chineseNum;
	}
	
	/**
	 * @������excel��������->��ĸ
	 * @���ߣ�zhuon[����:1391]
	 * @���ڣ�2017��9��13��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public static String getExcelColumnLabel(int index) {
		String rs = "";
		do {
			index--;
			rs = ((char) (index % 26 + (int) 'A')) + rs;
			index = (int) ((index - index % 26) / 26);
		} while (index > 0);
		return rs;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �����������е��ֶα���ȫ��תΪutf-8
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-19 ����11:42:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public static String[] getArraydecodeToUtf8(String[] paraArray) throws Exception{
		List<String> paraList = new ArrayList<String>();
		if(paraArray != null){
			for (int i = 0; i < paraArray.length; i++) {
				paraList.add(URLDecoder.decode(URLDecoder.decode(paraArray[i],"UTF-8"),"UTF-8"));
			}
		}
		return paraList.toArray(new String[]{});
	}
	
}
