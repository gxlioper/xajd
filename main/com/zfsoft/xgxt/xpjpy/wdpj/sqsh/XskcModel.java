/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��17�� ����4:48:06 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.util.Set;
import java.util.TreeSet;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: ѧ���ɼ����ܣ�ѧ���γ�Model
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��17�� ����4:48:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XskcModel {
	
	private String xqmc;
//	private Set<String> kcSet = new LinkedHashSet<String>();
	private Set<String> kcSet = new TreeSet<String>();
//	private Set<String> kcSet = new TreeSet<String>(com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.SIMPLIFIED_CHINESE));
	
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the kcSet
	 */
	public Set<String> getKcSet() {
		return kcSet;
	}
	/**
	 * @param kcSetҪ���õ� kcSet
	 */
	public void setKcSet(Set<String> kcSet) {
		this.kcSet = kcSet;
	}
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param xqmc
	 */
	public XskcModel(String xqmc) {
		super();
		this.xqmc = xqmc;
	}
	
	
}
