/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��22�� ����10:09:42 
 */  
package xsgzgl.gyjc.jcsd;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ĿForm
 * @���ߣ� �����[����:1599]
 * @ʱ�䣺 2018��5��22�� ����10:09:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxxmForm extends ActionForm {
	private String dm;
	private String mc;
	private String jbz;
	private String type;
	private Pages pages = new Pages();
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
	private String[] dms;
	/**
	 * @return the dm
	 */
	public String getDm() {
		return dm;
	}
	/**
	 * @param dmҪ���õ� dm
	 */
	public void setDm(String dm) {
		this.dm = dm;
	}
	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @param mcҪ���õ� mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * @return the jbz
	 */
	public String getJbz() {
		return jbz;
	}
	/**
	 * @param jbzҪ���õ� jbz
	 */
	public void setJbz(String jbz) {
		this.jbz = jbz;
	}
	/**
	 * @return the dms
	 */
	public String[] getDms() {
		return dms;
	}
	/**
	 * @param dmsҪ���õ� dms
	 */
	public void setDms(String[] dms) {
		this.dms = dms;
	}

}
