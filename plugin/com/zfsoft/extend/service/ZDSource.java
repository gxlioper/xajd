/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-5 ����04:58:30 
 */  
package com.zfsoft.extend.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-5 ����04:58:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZDSource {	
	
   public static final String METHOD_DB = "db";
   
   public static final String METHOD_CLASS = "method";

   public static final String METHOD_PLAIN = "plain";
	
   private String method;
	
   private String source;

   private static Pattern db_p = Pattern.compile("\\[([^\\[]*),([^\\[]*),([^\\[]*),([^\\[]*)]");
   
   private static Pattern method_p = Pattern.compile("\\[([^\\[]*),([^\\[]*),([^\\[]*)]");
   
	public DataSourceQuery getDataSouceQueryer(){
		DataSourceQuery queryer = null;
		
		if(StringUtils.equalsIgnoreCase(METHOD_DB, method)){
		    if(StringUtils.isNotBlank(source)){
		    	Matcher matcher = db_p.matcher(source);
		    	if(matcher.find()){
		    		queryer = new DBQueryDescriptor(matcher.group(1),new String[]{matcher.group(2),matcher.group(3),matcher.group(4)});
		    	}
		    }
		}else if(StringUtils.equalsIgnoreCase(METHOD_CLASS, method)){
			 if(StringUtils.isNotBlank(source)){
				 try {
				    Matcher matcher = method_p.matcher(source);
			    	if(matcher.find()){
			    		Class forName = Class.forName(matcher.group(1));
						queryer = new MethodQueryDescriptor(forName, matcher.group(2), matcher.group(3));
			    	}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			 }
		}else if(StringUtils.equalsIgnoreCase(METHOD_PLAIN, method)){
			queryer = new PlainSoueceQuery(source);
		}
		return queryer;
	}
	
	
	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public ZDSource() {
		super();
	}



	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param method
	 * @param source
	 */
	public ZDSource(String method, String source) {
		super();
		this.method = method;
		this.source = source;
	}



	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param methodҪ���õ� method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param sourceҪ���õ� source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
}
