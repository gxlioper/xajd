package com.zfsoft.xgxt.comm.zdybd.model;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Զ����
 * @�๦������: �ֶζ����
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-26 ����03:50:38
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class ZddyModel extends ActionForm {

	private static final long serialVersionUID = -2924542695182937526L;
	private String type;
	private String zddyid;// �ֶζ���id
	private String flszid;// ��������id
	private String zd;// �ֶ�
	private String bdmc;// ������
	private String zdsm;// �ֶ�˵��
	private String zdlx;// �ֶ����� 0:����ʾ
	private String szlx;// ��������
	private String szz;// ����ֵ ���ֶ��롰�ֶ����͡�������Դ���͡����ʹ��"
	private String yzlx;// ��֤����
	private String zbwz;// ����λ��
	private String yxwk;// ����Ϊ��
	private String yxxg;// �����޸�
	
	private String dataJson;//
	private String lb;//
	private String gndm;//

	public ZddyModel() {
		super();
	}

	public String getGndm() {
		return gndm;
	}

	public void setGndm(String gndm) {
		this.gndm = gndm;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public String getZddyid() {
		return zddyid;
	}

	public void setZddyid(String zddyid) {
		this.zddyid = zddyid;
	}

	public String getFlszid() {
		return flszid;
	}

	public void setFlszid(String flszid) {
		this.flszid = flszid;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getBdmc() {
		return bdmc;
	}

	public void setBdmc(String bdmc) {
		this.bdmc = bdmc;
	}

	public String getZdsm() {
		return zdsm;
	}

	public void setZdsm(String zdsm) {
		this.zdsm = zdsm;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public String getSzlx() {
		return szlx;
	}

	public void setSzlx(String szlx) {
		this.szlx = szlx;
	}

	public String getSzz() {
		return szz;
	}

	public void setSzz(String szz) {
		this.szz = szz;
	}

	public String getYzlx() {
		return yzlx;
	}

	public void setYzlx(String yzlx) {
		this.yzlx = yzlx;
	}

	public String getZbwz() {
		return zbwz;
	}

	public void setZbwz(String zbwz) {
		this.zbwz = zbwz;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYxwk() {
		return yxwk;
	}

	public void setYxwk(String yxwk) {
		this.yxwk = yxwk;
	}

	public String getYxxg() {
		return yxxg;
	}

	public void setYxxg(String yxxg) {
		this.yxxg = yxxg;
	}

}
