/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-11 ����11:57:29 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-9-11 ����11:57:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JJalForm extends ActionForm {
	private Pages pages = new Pages();

	private static final long serialVersionUID = 1L;
	
	private String type;
	
	//���
	private String sid;
	//����
	private String jjdx;
	//ʱ��
	private String jjsj;
	//ѧ��
	private String fdxk;
	//����
	private String jjms;
	//��¼����
	private String jlrq;
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
	 * @return the jjdx
	 */
	public String getJjdx() {
		return jjdx;
	}
	/**
	 * @param jjdxҪ���õ� jjdx
	 */
	public void setJjdx(String jjdx) {
		this.jjdx = jjdx;
	}
	/**
	 * @return the jjsj
	 */
	public String getJjsj() {
		return jjsj;
	}
	/**
	 * @param jjsjҪ���õ� jjsj
	 */
	public void setJjsj(String jjsj) {
		this.jjsj = jjsj;
	}
	/**
	 * @return the fdxk
	 */
	public String getFdxk() {
		return fdxk;
	}
	/**
	 * @param fdxkҪ���õ� fdxk
	 */
	public void setFdxk(String fdxk) {
		this.fdxk = fdxk;
	}
	/**
	 * @return the jjms
	 */
	public String getJjms() {
		return jjms;
	}
	/**
	 * @param jjmsҪ���õ� jjms
	 */
	public void setJjms(String jjms) {
		this.jjms = jjms;
	}
	/**
	 * @return the jlrq
	 */
	public String getJlrq() {
		return jlrq;
	}
	/**
	 * @param jlrqҪ���õ� jlrq
	 */
	public void setJlrq(String jlrq) {
		this.jlrq = jlrq;
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
