/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-3 ����10:05:21 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-3 ����10:05:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjmdExportModel extends ActionForm{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String xn;	//ѧ��
	private String xq;	//ѧ��
	private String[] xmlx;//��Ŀ����
	private String[] xmxz;//��Ŀ����
	private String[] xydm; //ѧԺ����
	private String[] xmmc; //��Ŀ����
	
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	
	/**
	 * @return the xmlx
	 */
	public String[] getXmlx() {
		return xmlx;
	}
	/**
	 * @param xmlxҪ���õ� xmlx
	 */
	public void setXmlx(String[] xmlx) {
		this.xmlx = xmlx;
	}
	public String[] getXmxz() {
		return xmxz;
	}
	public void setXmxz(String[] xmxz) {
		this.xmxz = xmxz;
	}
	/**
	 * @return the xydm
	 */
	public String[] getXydm() {
		return xydm;
	}
	/**
	 * @param xydmҪ���õ� xydm
	 */
	public void setXydm(String[] xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the xmmc
	 */
	public String[] getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String[] xmmc) {
		this.xmmc = xmmc;
	}
	
	
	
}
