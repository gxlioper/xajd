/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2014-2-26 ����09:27:02 
 */
package com.zfsoft.xgxt.xtwh.yhsjfw;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ϵͳ����
 * @�๦������: �û����ݷ�Χ
 * @���ߣ� ligl
 * @ʱ�䣺 2014-2-26 ����09:27:02
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class YhsjfwModel extends ActionForm {

	private static final long serialVersionUID = 2766816538055718857L;
	private String yhm;// ������
	private String sjfwdm;// ���ݷ�Χ����
	private String sjfwmc;// ���ݷ�Χ����

	private String ids;//

	public YhsjfwModel() {
		super();
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getSjfwdm() {
		return sjfwdm;
	}

	public void setSjfwdm(String sjfwdm) {
		this.sjfwdm = sjfwdm;
	}

	public String getSjfwmc() {
		return sjfwmc;
	}

	public void setSjfwmc(String sjfwmc) {
		this.sjfwmc = sjfwmc;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
