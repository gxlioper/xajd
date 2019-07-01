
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
 * 字符串检验及操作的工具类
 */
public class StringUtils {
	/**
	 * 判断一个字符串是否为null或""
	 * @param arg
	 * @return 为空：true ；非空：false
	 */
	public static boolean isNull(String str){
		return (str == null) || (str.trim().length()==0) ? true : false;
	}
	
	public static boolean isNotNull(String str){
		return (str == null) || (str.trim().length()==0) ? false : true;
	}
	
	/**
	 *判读数组是否是空的 
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
	 * 判断str2 是否与str1 相同（忽略大小写）
	 * @param str1
	 * @param str2
	 * @return 相同则返回true，否则false
	 * @throws NullStringException 如果str1是空，则抛出空字符串异常
	 */
	public static boolean isIgnoreCaseEqual(String str1,String str2) throws NullStringException{
		if(str1 == null){
			throw new NullStringException();
		} else {
			return str1.equalsIgnoreCase(str2);
		}
	}
	
	/**
	 * 判断str2 是否与str1 完全相同
	 * @param str1
	 * @param str2
	 * @return 相同则返回true，否则false
	 * @throws NullStringException 如果str1是空，则抛出空字符串异常
	 */
	public static boolean isEqual(String str1,String str2) throws NullStringException{
		if(str1 == null){
			throw new NullStringException();
		} else {
			return str1.equals(str2);
		}
	}
	
	
	/**
	 * 判断str2 是否与str1 完全相同
	 * @param str1
	 * @param str2
	 * @return 相同则返回true，否则false
	 * @throws NullStringException 如果str1是空，则抛出空字符串异常
	 */
	public static boolean equals(String str1,String str2){
		if(str1 == null){
			return str1 == str2;
		} else {
			return str1.equals(str2);
		}
	}
	/**
	 * 把传入的字符串的分隔成字符串数组
	 * @param stringValue    需要分隔的字符串
	 * @param splitStrType   分隔标志
	 * @return String[]    
	 */
	public static String[] splitStr(String stringValue, String splitStrType) {
		if(stringValue == null) return null;
		return stringValue.split(splitStrType);
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
	 * 把字符串中的全角字符串转换成半角字符串
	 * 如果仅有半角字符串，则不变
	 * @param str
	 * @return
	 */
	public static String Q2BChange(String str){
		String result = "";
		int  code = 0;
		String inputStr = str.replaceAll("^[ |　]*","").replaceAll("[ |　]$","").replaceAll("[ |　]","");
		for(int i=0;i<inputStr.length();i++){
			code = inputStr.codePointAt(i);
			if(code>=65281 && code<65373){
				//“65248”是转换码距
				result += new String(new int[]{ code-65248 },0,1);
			} else if(code>0 && code<65373){
				result += inputStr.charAt(i);
			}
		}
		return result;
	}
	
	/**
	 * 将字符串中的特殊字符去除
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
	 * n个字符串拼接为一个字符串
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
	 * n个字符串数组拼接为一个字符串数组
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
	 * 判断字符串在数组里是否存在的方法，希望进行改进
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
	 * 获取Guid
	 * @author 屈朋辉
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
	 * 将字符串数据中的内容组合成一个字符串，用指定的分隔符分割
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
	 * 判断后一数组内容是否在前一数组中都有
	 * */
	public static boolean contains(String[] firArr, String[] sedArr){

		List<String> firList = Arrays.asList(firArr);
		List<String> sedList = Arrays.asList(sedArr);
		
		return firList.containsAll(sedList);
	}
	/**
	 * 传入的字符串，按指定的格式进行分割
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
	 * 字符串在数组中的索引
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
	 * @描述:数据转换，将传入对象中相关属性值的\n转为<br/>,服务于文本域换行
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 上午11:39:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param o
	 * @return
	 * @throws Exception
	 * Object 返回类型 
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
	 * 数据转换，将传入对象中相关属性值的\n转为<br/>,服务于文本域换行【去除首尾空格】
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
				String newValue = ((String)value).replaceAll("\\n", "<br/>").trim(); // 修改：【去除首尾空格】
				t.getMethod(setMethodName,String.class).invoke(o, newValue);
			}
			
		}
		
		return o;
	}
	
	
	
	/**
	 * 
	 * @描述:数据转换，将传入对象中相关属性值的<br/>转为\n,服务于文本域换行
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 上午11:38:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param o
	 * @return
	 * @throws Exception
	 * Object 返回类型 
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
				//增加跨站脚本处理
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
	 * @描述:数据转换(查看用，不转换br)
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 上午11:38:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param o
	 * @return
	 * @throws Exception
	 * Object 返回类型 
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
	 * @描述：比较两个string的大小
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月5日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param strA
	 * @param strB
	 * @return  StrA>StrB 返回true 其他情况返回false
	 * boolean 返回类型
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
			return Float.parseFloat(strB)>Float.parseFloat(strA); //负数值交换
		}
		return Float.parseFloat(strA)>Float.parseFloat(strB);

	}
	
	/**
	 * 
	 * @描述:转换string为integer
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-26 下午07:05:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * Integer 返回类型 
	 */
	public static Integer paseStringToInteger(String str){
		return isNull(str)?0:Integer.parseInt(str);
	}
	
	/**
	 * 
	 * @描述:数据转换，将传入对象中相关属性值的\n转为<br/>,服务于文本域换行
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 上午11:38:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param o
	 * @return
	 * @throws Exception
	 * Object 返回类型 
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
	 * @描述:阿拉伯数字转星期
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-19 下午05:24:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param i
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String numberToChinese(int i){
		switch (i) {
		case 1:
			return "星期一";
		case 2:
			return "星期二";
		case 3:
			return "星期三";
		case 4:
			return "星期四";
		case 5:
			return "星期五";
		case 6:
			return "星期六";
		case 7:
			return "星期日";
		default:
			return "星期一";
			
		}
	}

  /**
   * 
   * @描述: 【通用】解决url传值乱码问题，前台必须用encodeURI()方法编码,传值为整个对象
   * @作者：yxy[工号：1206]
   * @日期：2016-9-20 上午11:13:08
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param Object o
   * @return
   * @throws Exception
   * Object 返回类型 
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
   * @描述:【通用】解决url传值乱码问题，前台必须用encodeURI()方法编码,传值为字符串
   * @作者：yxy[工号：1206]
   * @日期：2016-9-20 上午11:58:31
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param str
   * @return
   * @throws Exception
   * String 返回类型 
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
   * @描述: \n转<br/>,空格转&nbsp;,用于文本域查看时显示
   * @作者：yxy[工号：1206]
   * @日期：2016-9-21 上午11:53:26
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param o
   * @return
   * HashMap<String,String> 返回类型 
   * @throws
   */
  public static HashMap<String,String> formatMap(Map o){
	    Map<String,String> map = (Map) o;
		Map<String,String> newMap = new HashMap<String, String>();
		
		for (Map.Entry<String, String> entry : map.entrySet()){
			String key = entry.getKey();
			String value = (entry.getValue()==null)?"":String.valueOf(entry.getValue());
			//增加跨站脚本处理
			//value=Encode.forHtml(value);
			if (!isNull(value)){
				newMap.put(key, value.replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;"));
			}
		}
		return (HashMap<String, String>) newMap;
  }
  
	  /** 
	 * @描述:判断是否为数字(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 上午09:04:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public static boolean isNumeric(String str){
		boolean isNum = str.trim().matches("[0-9]+");
		return isNum;
	  }
	public static boolean isNumber(String str){   
		 Pattern pattern=Pattern.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"); // 判断小数点后2位的数字的正则表达式  
	     Matcher match=pattern.matcher(str);   
	     if(match.matches()==false){   
	        return false;   
	     }else{   
	        return true;   
	     }   
	 }  

	  /** 
	 * @描述:转换数字格式为中文数字
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 上午09:04:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public static String formatChNum(String num) {	

		// 金额汉字大写
		String[] dx = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾",
				"佰", "仟", "万", "亿" };

		// 金额汉字消协
		String[] xx = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
				"百", "千", "万", "亿" };
		
		String chineseNum = MoneyFormat.format(String.valueOf(num));
		
		chineseNum = chineseNum.replace("元整", "");
		
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
	 * @描述：excel列名数字->字母
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年9月13日
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	 * @描述: 将数组中所有的字段编码全部转为utf-8
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-19 上午11:42:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String[] 返回类型 
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
