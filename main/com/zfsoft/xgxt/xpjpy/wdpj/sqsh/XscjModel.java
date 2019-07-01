/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��4��11�� ����2:34:58 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import xgxt.action.Base;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: ѧ���ɼ�ʵ���࣬����ѧ���ɼ����ܵ��������ݳ���
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��4��11�� ����2:34:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XscjModel {
	private String xh;
	private String xm;
	private String pjcj;	//ƽ���ɼ�
	private String pjjd;	//ƽ������
	private String pjcjpm;	//��ƽ���ɼ���������������Ϊ��ƽ�������������������������û�иģ�
	//�ɼ�map��������һѧ�ڳɼ��͵ڶ�ѧ�ڳɼ�
	//�����󣬰������㣬[0]�ɼ���[1]����
	private Map<String,Map<String,String[]>> cjMap = new LinkedHashMap<String,Map<String,String[]>>();
	
	
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param xh
	 * @param xm
	 */
	public XscjModel(String xh, String xm,String pjcj,String pjjd,String pjcjpm) {
		super();
		this.xh = xh;
		this.xm = xm;
		this.pjcj = pjcj;
		this.pjjd = pjjd;
		this.pjcjpm = pjcjpm;
		//����ֱ��д��2��ѧ���ˣ����Ը�Ϊ�����ݿ�ȡѧ�ڴ���������
//		this.cjMap.put("01", new LinkedHashMap<String,String>());
//		this.cjMap.put("02", new LinkedHashMap<String,String>());
		
		for(HashMap<String,String> map:Base.getXqList()){
//			this.cjMap.put(map.get("xqdm"), new LinkedHashMap<String,String[]>());
//			this.cjMap.put(map.get("xqdm"), new TreeMap<String,String[]>(com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.SIMPLIFIED_CHINESE)));
			this.cjMap.put(map.get("xqdm"), new TreeMap<String,String[]>());
		}
		
	}
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public XscjModel() {
		super();
	}
	/**
	 * @return the cjMap
	 */
	public Map<String, Map<String, String[]>> getCjMap() {
		return cjMap;
	}
	/**
	 * @param cjMapҪ���õ� cjMap
	 */
	public void setCjMap(Map<String, Map<String, String[]>> cjMap) {
		this.cjMap = cjMap;
	}
	/**
	 * @return the pjcj
	 */
	public String getPjcj() {
		return pjcj;
	}
	/**
	 * @param pjcjҪ���õ� pjcj
	 */
	public void setPjcj(String pjcj) {
		this.pjcj = pjcj;
	}
	/**
	 * @return the pjjd
	 */
	public String getPjjd() {
		return pjjd;
	}
	/**
	 * @param pjjdҪ���õ� pjjd
	 */
	public void setPjjd(String pjjd) {
		this.pjjd = pjjd;
	}
	/**
	 * @return the pjcjpm
	 */
	public String getPjcjpm() {
		return pjcjpm;
	}
	/**
	 * @param pjcjpmҪ���õ� pjcjpm
	 */
	public void setPjcjpm(String pjcjpm) {
		this.pjcjpm = pjcjpm;
	}
	
	
}
