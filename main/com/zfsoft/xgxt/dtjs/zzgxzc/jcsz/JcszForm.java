/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��1��25�� ����9:03:53 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.jcsz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž�����֯��ϵת������ģ��
 * @�๦������: ��������ActionForm
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��1��25�� ����9:03:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszForm extends ActionForm{
	private static final long serialVersionUID = 4581001712403281509L;
	
	private String id;
	private String sqkg;
	private String kssj;
	private String jssj;
	private String splc;
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}
	/**
	 * @param kssjҪ���õ� kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssjҪ���õ� jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	
	
}
