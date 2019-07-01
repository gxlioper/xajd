package com.zfsoft.xgxt.base.export.model;

import java.io.File;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * ����Model
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ImportModel extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ��������
	private String dryzbh; // ������֤���
	private String drmkdm; // ����ģ�����
	private String drmkmc; // ����ģ������
	private String drmkyzsm; // ����ģ����֤˵��
	private String drbm; // �������
	private String drbzwmc; // �������������
	private String zdm; // �ֶ���
	private String zdmc; // �ֶ�����
	private String zdlx; // �ֶ�����
	private String xssx; // ��ʾ˳��
	private String yzcs; // ��֤����
	private String drmbzdmc; // ����ģ���ֶ�����
	private String sfhbyz; // �Ƿ�ϲ���֤

	// ������֤
	private String yzmc; // ��֤����
	private String yzlmc; // ��֤������
	private String yzsm; // ��֤˵��,����������֤��Ϊ
	private String cshcssy; // ��ʼ����������
	private String yzlbm; // ��֤�������

	// ��ҵ������
	private FormFile importFile; // �����ļ�
	private String drzs; // ��������
	private String cgs; // �ɹ���
	private String cws; // ������
	private String hbdrs; // �ϲ�������
	private Object value; // ��������

	private String drwjgs; // �����ļ���ʽ
	private String drms; // ����ģʽ ��Ĭ��Ϊinsert������1Ϊupdate����
	private File updateFile;
	
	public String getDryzbh() {
		return dryzbh;
	}

	public void setDryzbh(String dryzbh) {
		this.dryzbh = dryzbh;
	}

	public String getDrmkdm() {
		return drmkdm;
	}

	public void setDrmkdm(String drmkdm) {
		this.drmkdm = drmkdm;
	}

	public String getDrmkmc() {
		return drmkmc;
	}

	public void setDrmkmc(String drmkmc) {
		this.drmkmc = drmkmc;
	}

	public String getDrmkyzsm() {
		return drmkyzsm;
	}

	public void setDrmkyzsm(String drmkyzsm) {
		this.drmkyzsm = drmkyzsm;
	}

	public String getDrbm() {
		return drbm;
	}

	public void setDrbm(String drbm) {
		this.drbm = drbm;
	}

	public String getDrbzwmc() {
		return drbzwmc;
	}

	public void setDrbzwmc(String drbzwmc) {
		this.drbzwmc = drbzwmc;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getZdmc() {
		return zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public String getYzlmc() {
		return yzlmc;
	}

	public void setYzlmc(String yzlmc) {
		this.yzlmc = yzlmc;
	}

	public String getDrzs() {
		return drzs;
	}

	public void setDrzs(String drzs) {
		this.drzs = drzs;
	}

	public String getCgs() {
		return cgs;
	}

	public void setCgs(String cgs) {
		this.cgs = cgs;
	}

	public String getCws() {
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getYzsm() {
		return yzsm;
	}

	public void setYzsm(String yzsm) {
		this.yzsm = yzsm;
	}

	public String getYzcs() {
		return yzcs;
	}

	public void setYzcs(String yzcs) {
		this.yzcs = yzcs;
	}

	public String getCshcssy() {
		return cshcssy;
	}

	public void setCshcssy(String cshcssy) {
		this.cshcssy = cshcssy;
	}

	public String getYzlbm() {
		return yzlbm;
	}

	public void setYzlbm(String yzlbm) {
		this.yzlbm = yzlbm;
	}

	public String getDrmbzdmc() {
		return drmbzdmc;
	}

	public void setDrmbzdmc(String drmbzdmc) {
		this.drmbzdmc = drmbzdmc;
	}

	public String getHbdrs() {
		return hbdrs;
	}

	public void setHbdrs(String hbdrs) {
		this.hbdrs = hbdrs;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getSfhbyz() {
		return sfhbyz;
	}

	public void setSfhbyz(String sfhbyz) {
		this.sfhbyz = sfhbyz;
	}

	/**
	 * @return the importFile
	 */
	public FormFile getImportFile() {
		return importFile;
	}

	/**
	 * @param importFileҪ���õ� importFile
	 */
	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}

	public String getDrwjgs() {
		return drwjgs;
	}

	public void setDrwjgs(String drwjgs) {
		this.drwjgs = drwjgs;
	}

	public String getDrms() {
		return drms;
	}

	public void setDrms(String drms) {
		this.drms = drms;
	}

	public File getUpdateFile() {
		return updateFile;
	}

	public void setUpdateFile(File updateFile) {
		this.updateFile = updateFile;
	}
	
	
}
