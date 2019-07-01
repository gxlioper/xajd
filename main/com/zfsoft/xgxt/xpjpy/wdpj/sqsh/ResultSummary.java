/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��4��10�� ����9:14:58 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-�ҵ�����-�������-ѧ���ɼ����ܵ���
 * @�๦������: ѧ���ɼ����ܽ����ӳ��
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��4��10�� ����9:14:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ResultSummary {

	private Map<String,ClassSummary> resultMap;	//�洢�����

	/**
	 * @���� �����캯����ͬʱ��ʼ�������Map����
	 */
	public ResultSummary() {
		resultMap = new HashMap<String,ClassSummary>();
	}

	/**
	 * @return the resultMap
	 */
	public Map<String,ClassSummary> getResultMap() {
		return resultMap;
	}
	
	/**
	 * @����:������������һ����¼��������¼���ݸ���id��Ӧ�Ĺؼ�ֵ���ֵ���ͬ�༶�����У�
	 * 		  ����ڽ�������Ѿ����ڴ����Ӧ��¼�İ༶��ֱ�Ӽ����¼�¼��
	 * 		  �����½��༶������Ӽ�¼
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��10�� ����11:01:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param map
	 * void �������� 
	 * @throws
	 */
	public void setResultMap(String id,HashMap<String,String> map) {
		String key = map.get(id);	//�༶����
		String xqmc = map.get("xqmc");
		ClassSummary classSummary = resultMap.get(key);
		if(classSummary==null){
			String xn = map.get("xn");
			String xymc = map.get("xymc");
			String zymc = map.get("zymc");
			String bjdm = map.get("bjdm");
			String bjmc = map.get("bjmc");
			classSummary = new ClassSummary(xn, xqmc, xymc, zymc,bjdm, bjmc);
			resultMap.put(key, classSummary);
		}else{
			if(StringUtils.isNotNull(classSummary.getXqmc())&&(!classSummary.getXqmc().equals(xqmc))&&(!classSummary.getXqmc().contains("��"))){
				classSummary.setXqmc(classSummary.getXqmc()+"��"+xqmc);
			}
		}
		classSummary.addRecord(map);
	}

	/**
	 * @����:�����������Ӷ�����¼��ÿ����¼���ݸ���id��Ӧ�Ĺؼ�ֵ���ֵ���ͬ�༶�����У�
	 * 		  ����ڽ�������Ѿ����ڴ����Ӧ��¼�İ༶��ֱ�Ӽ����¼�¼��
	 * 		  �����½��༶������Ӽ�¼
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��10�� ����11:02:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param list
	 * @return
	 * ResultSummary �������� 
	 * @throws
	 */
	public ResultSummary setResultMap(String id,List<HashMap<String,String>> list) {
		for(HashMap<String,String> map:list){
			this.setResultMap(id, map);
		}
		return this;
	}
	
	
	
}
