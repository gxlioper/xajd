/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:30:23 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh;

import com.zfsoft.xgxt.rcsw.xszbb.comm.XszbbForm;





/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(ѧ��֤��������ά��) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-16 ����05:16:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbblxwhForm extends XszbbForm{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = -8998807497101093040L;
	private String type;
	private String xszbblxdm;//��Ϊ�������
	private String xszbblxmc;//��Ϊ��������
	private String zjlxbs;   //֤�����ͱ�ʶ
	
	private String shlc;
	
	
	
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlcҪ���õ� shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the xszbblxdm
	 */
	public String getXszbblxdm() {
		return xszbblxdm;
	}
	/**
	 * @param xszbblxdmҪ���õ� xszbblxdm
	 */
	public void setXszbblxdm(String xszbblxdm) {
		this.xszbblxdm = xszbblxdm;
	}
	/**
	 * @return the xszbblxmc
	 */
	public String getXszbblxmc() {
		return xszbblxmc;
	}
	/**
	 * @param xszbblxmcҪ���õ� xszbblxmc
	 */
	public void setXszbblxmc(String xszbblxmc) {
		this.xszbblxmc = xszbblxmc;
	}
	public String getZjlxbs() {
		return zjlxbs;
	}
	public void setZjlxbs(String zjlxbs) {
		this.zjlxbs = zjlxbs;
	}


	
}
