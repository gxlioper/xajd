/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:30:11 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.cssz;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��羺������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-4-1 ����08:55:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BfjsCsszModel extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String sqkg ;//���뿪�� 
	private String kssj ;//��ʼʱ�� 
	private String jssj ;//����ʱ�� 
	private String xn ;//ѧ�� 
	private String xq ;//ѧ�� 

	private String jsfs ;//������ʽ 	
	
	private String pdzq;//��������
	private String zqmc;//��������
	private String jsxmjb;//������Ŀ����0 ȫ�� �� 1 �꼶 ��2 ѧԺ
	
	
	private String kgzt;
	
	private String rownum;

	public String getSqkg() {
		return sqkg;
	}

	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
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

	public String getJsfs() {
		return jsfs;
	}

	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}

	public String getPdzq() {
		return pdzq;
	}

	public void setPdzq(String pdzq) {
		this.pdzq = pdzq;
	}

	public String getZqmc() {
		return zqmc;
	}

	public void setZqmc(String zqmc) {
		this.zqmc = zqmc;
	}

	public String getKgzt() {
		return kgzt;
	}

	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getJsxmjb() {
		return jsxmjb;
	}

	public void setJsxmjb(String jsxmjb) {
		this.jsxmjb = jsxmjb;
	}
	
	
	
	

}
