/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-3 ����06:49:12 
 */  
package com.zfsoft.xgxt.base.util;

import java.util.HashMap;
import java.util.Map;

import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: 
 * @�๦������: 
 * @���ߣ� ligl 
 * @ʱ�䣺 2013-6-3 ����06:49:12 
 * @�汾�� V1.0
 * @�޸ļ�¼:   
 */

public class HtmlUtil {

		/**
		 * 
		 * @����:���ַ�������ת��
		 * @���ߣ�ligl
		 * @���ڣ�2013-6-3 ����06:31:57
		 * @�޸ļ�¼: 
		 * @param str
		 * @return
		 * String �������� 
		 * @throws
		 */
		public static String xmlZy(String str){
			if(str != null){				
				str = str.replace("&", "&amp;");//
				str = str.replace("<", "&lt;");//
				str = str.replace(">", "&gt;");//
				str = str.replaceAll("&lt;br/&gt;", "<w:br/>");//freemarker����
				str = str.replaceAll("\n", "<w:br/>");//freemarker����
				str = str.replaceAll("&#8226;", "��");//freemarker����
				
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
		   * @����:XML�ַ���ת��
		   * @���ߣ�tgj[���ţ�1075]
		   * @���ڣ�2017-6-5 ����02:45:37
		   * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		   * @param o
		   * @return
		   * HashMap<String,String> �������� 
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
