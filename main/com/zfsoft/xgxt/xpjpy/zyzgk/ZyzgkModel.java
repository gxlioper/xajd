/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-17 ����07:14:28 
 */  
package com.zfsoft.xgxt.xpjpy.zyzgk;

import java.util.List;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-11-17 ����07:14:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyzgkModel extends ActionForm{
	
	private static final long serialVersionUID = -1313339430724700820L;
	
	private String zydm;
	private String pjxn;
	private String kcmc;
	private String[] zgks;
	private String xydm;
	
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydmҪ���õ� zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the pjxn
	 */
	public String getPjxn() {
		return pjxn;
	}
	/**
	 * @param pjxnҪ���õ� pjxn
	 */
	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}
	/**
	 * @return the kcmc
	 */
	public String getKcmc() {
		return kcmc;
	}
	/**
	 * @param kcmcҪ���õ� kcmc
	 */
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	/**
	 * @return the zgks
	 */
	public String[] getZgks() {
		return zgks;
	}
	/**
	 * @param zgksҪ���õ� zgks
	 */
	public void setZgks(String[] zgks) {
		this.zgks = zgks;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydmҪ���õ� xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	
	
}
