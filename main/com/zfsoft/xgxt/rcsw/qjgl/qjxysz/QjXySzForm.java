/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-3 ����10:58:18 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjxysz;

import org.apache.struts.action.ActionForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ�yxy[����:1206]
 * @ʱ�䣺 2015-11-3 ����10:58:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjXySzForm extends ActionForm {
	private String id;//ID
	private String mc;//Э������
	private String content;//Э������
	private String bjsj;//�༭ʱ��
	private String bjr;//�༭��

	/**
	 * @return the bjr
	 */
	public String getBjr() {
		return bjr;
	}

	/**
	 * @param bjrҪ���õ� bjr
	 */
	public void setBjr(String bjr) {
		this.bjr = bjr;
	}

	/**
	 * @return the bjsj
	 */
	public String getBjsj() {
		return bjsj;
	}

	/**
	 * @param bjsjҪ���õ� bjsj
	 */
	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param contentҪ���õ�
	 *            content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param idҪ���õ�
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mcҪ���õ�
	 *            mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}

}
