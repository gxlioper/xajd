/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:30:11 
 */  
package com.zfsoft.xgxt.xpjpy.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013��-��������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:30:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszModel extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String pjkg ;//�������� 
	private String kssj ;//��ʼʱ�� 
	private String jssj ;//����ʱ�� 
	private String xn ;//ѧ�� 
	private String xq ;//ѧ�� 
	private String cpzcsh ;//�������ʼ����ʽ��1���꼶+רҵ��2���༶�� 
	private String zcflrfs ;//�۲��¼�뷽ʽ��1����ҳǩ��0����ҳǩ�� 
	private String pjfs ;//1:ѧ���۲� ѧ������ 2�� ѧ���۲� ѧ������ 3��ѧ���۲� ѧ������ 	
	
	private String pjzq;//��������
	private String zqmc;//��������
	private String zcmrpm;//�۲�Ĭ������ 
	private String zcxmjb;//�۲���Ŀ����0 ȫ�� �� 1 �꼶 ��2 ѧԺ
	
	private String kgzt;
	
	private String rownum;
	
	/**
	 * @return the zcxmjb
	 */
	public String getZcxmjb() {
		return zcxmjb;
	}
	/**
	 * @param zcxmjbҪ���õ� zcxmjb
	 */
	public void setZcxmjb(String zcxmjb) {
		this.zcxmjb = zcxmjb;
	}
	public String getPjkg() {
		return pjkg;
	}
	public void setPjkg(String pjkg) {
		this.pjkg = pjkg;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getCpzcsh() {
		return cpzcsh;
	}
	public void setCpzcsh(String cpzcsh) {
		this.cpzcsh = cpzcsh;
	}
	public String getPjfs() {
		return pjfs;
	}
	public void setPjfs(String pjfs) {
		this.pjfs = pjfs;
	}
	public String getZcflrfs() {
		return zcflrfs;
	}
	public void setZcflrfs(String zcflrfs) {
		this.zcflrfs = zcflrfs;
	}
	public String getPjzq() {
		return pjzq;
	}
	public void setPjzq(String pjzq) {
		this.pjzq = pjzq;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getZqmc() {
		return zqmc;
	}
	public void setZqmc(String zqmc) {
		this.zqmc = zqmc;
	}
	public String getZcmrpm() {
		return zcmrpm;
	}
	public void setZcmrpm(String zcmrpm) {
		this.zcmrpm = zcmrpm;
	}
	/**
	 * @return the kgzt
	 */
	public String getKgzt() {
		return kgzt;
	}
	/**
	 * @param kgztҪ���õ� kgzt
	 */
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}
	
	

}
