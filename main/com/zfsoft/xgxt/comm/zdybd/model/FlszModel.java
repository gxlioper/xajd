package com.zfsoft.xgxt.comm.zdybd.model;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Զ����
 * @�๦������: �������ñ�
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-26 ����03:50:38
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class FlszModel extends ActionForm {

	private static final long serialVersionUID = -2924542695182937526L;
	private String flszid;// ��������id
	private String gndm;// ���ܴ���
	private String flflszid;// ����id
	private String flmc;// ��������
	private String flsm;// ����˵��
	private String bdls;// ������ Ϊ�������ֶ��������ã���������Ƭ��(�ɳ����Զ�����)
	private String bdms;// ��ģʽ 1:������¼ģʽ:2:������¼ģʽ;3:�����Զ������
	private String sfqy;// �Ƿ����� 1:��,0:��
	private String sfzk;// �Ƿ�չ�� 1:��,0:��
	private String sfmk;// �Ƿ�ģ�� 1:��,0:��
	private String bdszz;// ������ֵ
	private String xsxx;// ��ʾ˳��
	private String gnlx;// ��������
	private String yzsz;// ��֤����

	public FlszModel() {
		super();
	}

	public String getFlszid() {
		return flszid;
	}

	public void setFlszid(String flszid) {
		this.flszid = flszid;
	}

	public String getGndm() {
		return gndm;
	}

	public void setGndm(String gndm) {
		this.gndm = gndm;
	}

	public String getFlflszid() {
		return flflszid;
	}

	public void setFlflszid(String flflszid) {
		this.flflszid = flflszid;
	}

	public String getFlmc() {
		return flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	public String getFlsm() {
		return flsm;
	}

	public void setFlsm(String flsm) {
		this.flsm = flsm;
	}

	public String getBdls() {
		return bdls;
	}

	public void setBdls(String bdls) {
		this.bdls = bdls;
	}

	public String getBdms() {
		return bdms;
	}

	public void setBdms(String bdms) {
		this.bdms = bdms;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getSfzk() {
		return sfzk;
	}

	public void setSfzk(String sfzk) {
		this.sfzk = sfzk;
	}

	public String getSfmk() {
		return sfmk;
	}

	public void setSfmk(String sfmk) {
		this.sfmk = sfmk;
	}

	public String getBdszz() {
		return bdszz;
	}

	public void setBdszz(String bdszz) {
		this.bdszz = bdszz;
	}

	public String getXsxx() {
		return xsxx;
	}

	public void setXsxx(String xsxx) {
		this.xsxx = xsxx;
	}

	public String getGnlx() {
		return gnlx;
	}

	public void setGnlx(String gnlx) {
		this.gnlx = gnlx;
	}

	public String getYzsz() {
		return yzsz;
	}

	public void setYzsz(String yzsz) {
		this.yzsz = yzsz;
	}

}
