/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-11 ����11:58:04 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-9-11 ����11:58:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JJgzForm extends ActionForm {
	
	private Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	//���
	private String sid;
	//����
	private String gzmc;
	//����
	private String gznr;
	//ʱ��
	private String 	jlsj;
	//�Ƿ񷢲�
	private String sffb;
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
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
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sidҪ���õ� sid
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the gzmc
	 */
	public String getGzmc() {
		return gzmc;
	}
	/**
	 * @param gzmcҪ���õ� gzmc
	 */
	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}
	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}
	/**
	 * @param gznrҪ���õ� gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}
	/**
	 * @param jlsjҪ���õ� jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}
	/**
	 * @return the sffb
	 */
	public String getSffb() {
		return sffb;
	}
	/**
	 * @param sffbҪ���õ� sffb
	 */
	public void setSffb(String sffb) {
		this.sffb = sffb;
	}

}
