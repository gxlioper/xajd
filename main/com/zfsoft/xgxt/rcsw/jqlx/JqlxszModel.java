/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-30 ����03:24:41 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import org.apache.struts.action.ActionForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ������У����
 * @���ߣ�945
 * @ʱ�䣺 2013-12-30 ����03:24:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JqlxszModel extends ActionForm {

	private static final long serialVersionUID = 3047822587143156629L;
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String jqlx; // ��������
	private String isopen;// ��ǰʱ���Ƿ���
	private String fjid;// ����id
	private String lxkssj;
	private String lxjssj;
	private String uploadid;// �����ݿ��ֶΣ����Ҹ�����
	
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}

	/**
	 * @param splcҪ���õ�
	 *            splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}

	/**
	 * @param sqkssjҪ���õ�
	 *            sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}

	/**
	 * @param sqjssjҪ���õ�
	 *            sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getJqlx() {
		return jqlx;
	}

	public void setJqlx(String jqlx) {
		this.jqlx = jqlx;
	}

	/**
	 * @return the isopen
	 */
	public String getIsopen() {
		return isopen;
	}

	/**
	 * @param isopenҪ���õ�
	 *            isopen
	 */
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}

	/**
	 * @param sqkgҪ���õ�
	 *            sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}

	public String getFjid() {
		return fjid;
	}

	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	public String getLxkssj() {
		return lxkssj;
	}

	public void setLxkssj(String lxkssj) {
		this.lxkssj = lxkssj;
	}

	public String getLxjssj() {
		return lxjssj;
	}

	public void setLxjssj(String lxjssj) {
		this.lxjssj = lxjssj;
	}

	public String getUploadid() {
		return uploadid;
	}

	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}

}
