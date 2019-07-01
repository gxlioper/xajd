/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��3��30�� ����4:58:39 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����sql�������һ�Զ�ӳ�� 
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��3��30�� ����4:58:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class OtmMapping {
	private Map<String,List<Map<String,String>>> resultMap;	//�洢�����
	
	/**
	 * @���� �����캯����ͬʱ��ʼ�������Map����
	 */
	public OtmMapping(){
		resultMap = new HashMap<String,List<Map<String,String>>>();
	}

	/**
	 * @return the resultMap
	 */
	public Map<String, List<Map<String, String>>> getResultMap() {
		return resultMap;
	}

	/**
	 * @����:������������һ����¼��������¼���ݸ���id��Ӧ�Ĺؼ�ֵ���ֵ���ͬlist�У�
	 * 		  ����ڽ�������Ѿ����ڴ����Ӧ��¼��list��ֱ�Ӽ����¼�¼��
	 * 		  �����½�list������Ӽ�¼
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��30�� ����5:59:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @param map
	 * void �������� 
	 * @throws
	 */
	public void setResultMap(String id,HashMap<String,String> map) {
		String key = map.get(id);
		List<Map<String, String>> list = resultMap.get(key);
		if(list==null){
			list = new ArrayList<Map<String,String>>();
			resultMap.put(key, list);
		}
		list.add(map);
	}
	
	/**
	 * @����:�����������Ӷ�����¼��ÿ����¼����id��Ӧ�Ĺؼ�ֵ���ֵ���ͬlist�У�
	 * 		  ����ڽ�������Ѿ����ڴ����Ӧ��¼��list��ֱ�Ӽ����¼�¼��
	 * 		  �����½�list������Ӽ�¼
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��30�� ����5:58:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @param list
	 * void �������� 
	 * @throws
	 */
	public OtmMapping setResultMap(String id,List<HashMap<String,String>> list){
		for(HashMap<String,String> map:list){
			this.setResultMap(id, map);
		}
		return this;
	}
	
	
}
