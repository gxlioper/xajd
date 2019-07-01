/**
 * @部门:学工产品事业部
 * @日期：2015-6-9 下午03:49:39 
 */  
package com.zfsoft.extend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-9 下午03:49:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PlainSoueceQuery extends DataSourceQuery {

	private String plainValue;

	private Pattern pattern = Pattern.compile("\\[([^\\[]*),([^\\[]*)]");
	
	@Override
	public List<HashMap<String, String>> getData() {
		if(StringUtils.isBlank(plainValue)){
			return null;
		}
		List<HashMap<String, String>> value = new ArrayList<HashMap<String,String>>();
		Matcher matcher = pattern.matcher(plainValue);
		while(matcher.find()){
			String dm = matcher.group(1);
			String mc = matcher.group(2);
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("dm", dm);
			item.put("mc", mc);
			value.add(item);
		}
		return value;
	}

	/**
	 * @return the plainValue
	 */
	public String getPlainValue() {
		return plainValue;
	}

	/**
	 * @param plainValue要设置的 plainValue
	 */
	public void setPlainValue(String plainValue) {
		this.plainValue = plainValue;
	}

	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param plainValue
	 */
	public PlainSoueceQuery(String plainValue) {
		super();
		this.plainValue = plainValue;
	}

	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public PlainSoueceQuery() {
		super();
	}

	
	 
}
