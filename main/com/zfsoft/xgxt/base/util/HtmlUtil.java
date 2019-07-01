/**
 * @部门:学工产品事业部
 * @日期：2013-6-3 下午06:49:12 
 */  
package com.zfsoft.xgxt.base.util;

import java.util.HashMap;
import java.util.Map;

import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 
 * @类功能描述: 
 * @作者： ligl 
 * @时间： 2013-6-3 下午06:49:12 
 * @版本： V1.0
 * @修改记录:   
 */

public class HtmlUtil {

		/**
		 * 
		 * @描述:对字符串进行转义
		 * @作者：ligl
		 * @日期：2013-6-3 下午06:31:57
		 * @修改记录: 
		 * @param str
		 * @return
		 * String 返回类型 
		 * @throws
		 */
		public static String xmlZy(String str){
			if(str != null){				
				str = str.replace("&", "&amp;");//
				str = str.replace("<", "&lt;");//
				str = str.replace(">", "&gt;");//
				str = str.replaceAll("&lt;br/&gt;", "<w:br/>");//freemarker换行
				str = str.replaceAll("\n", "<w:br/>");//freemarker换行
				str = str.replaceAll("&#8226;", "·");//freemarker换行
				
			}
			return str;
		}
		
		public static String wordHh(String str){
			if(str !=null){
				str = str.replaceAll("\r\n", "<w:br/>");
				str = str.replaceAll("/n", "<w:br/>");
			}
			return str;
		}

		  /**
		   * 
		   * @描述:XML字符串转义
		   * @作者：tgj[工号：1075]
		   * @日期：2017-6-5 下午02:45:37
		   * @修改记录: 修改者名字-修改日期-修改内容
		   * @param o
		   * @return
		   * HashMap<String,String> 返回类型 
		   * @throws
		   */
		  public static HashMap<String,String> formatXmlMap(Map o){
			    Map<String,String> map = (Map) o;
				Map<String,String> newMap = new HashMap<String, String>();
				
				for (Map.Entry<String, String> entry : map.entrySet()){
					String key = entry.getKey();
					String value = (entry.getValue()==null)?"":String.valueOf(entry.getValue());

					if (StringUtils.isNotNull(value)){
						newMap.put(key, xmlZy(value));
					}
				}
				return (HashMap<String, String>) newMap;
		  }
}
