/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����02:37:31 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����������ģ��
 * @�๦������: ����ת����������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����02:37:31 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazccsszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String sqkg;/*���뿪��*/
	private String splc;/*��������*/
	private String sqkssj;/*���뿪ʼʱ��*/
	private String sqjssj;/*�������ʱ��*/
	private String isopen ;/*��ǰʱ���Ƿ���*/
	private String fjid;// �����Դ�Э����id
	private String uploadid;// �����ݿ��ֶΣ����Ҹ�����
	
	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @param fjidҪ���õ� fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
	/**
	 * @return the uploadid
	 */
	public String getUploadid() {
		return uploadid;
	}
	/**
	 * @param uploadidҪ���õ� uploadid
	 */
	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
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
	 * @param sqkssjҪ���õ� sqkssj
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
	 * @param sqjssjҪ���õ� sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the isopen
	 */
	public String getIsopen() {
		return isopen;
	}
	/**
	 * @param isopenҪ���õ� isopen
	 */
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
}
