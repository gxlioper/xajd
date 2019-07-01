/**
 * @部门:学工产品事业部
 * @日期：2015-6-5 下午04:58:30 
 */  
package com.zfsoft.extend.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-5 下午04:58:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
	 * @描述 ：TODO描述下当前构造方法
	 */
	public ZDSource() {
		super();
	}



	/**
	 * @描述 ：TODO描述下当前构造方法
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
	 * @param method要设置的 method
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
	 * @param source要设置的 source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
}
