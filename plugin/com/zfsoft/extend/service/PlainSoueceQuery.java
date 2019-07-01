/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-9 ����03:49:39 
 */  
package com.zfsoft.extend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-9 ����03:49:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	 * @param plainValueҪ���õ� plainValue
	 */
	public void setPlainValue(String plainValue) {
		this.plainValue = plainValue;
	}

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param plainValue
	 */
	public PlainSoueceQuery(String plainValue) {
		super();
		this.plainValue = plainValue;
	}

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public PlainSoueceQuery() {
		super();
	}

	
	 
}
