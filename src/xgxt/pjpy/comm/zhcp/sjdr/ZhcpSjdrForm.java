package xgxt.pjpy.comm.zhcp.sjdr;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;

import xgxt.pjpy.comm.pjpy.PjpyCommForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_���ݵ���_Form��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class ZhcpSjdrForm extends PjpyCommForm {

	private static final long serialVersionUID = 1L;

	List<HashMap<String, String>> xmList;// ��Ŀ�б�

	FormFile uploadFile;

	FormFile impFilePath;

	FormFile checkFilePath;

	private String nj;// �꼶

	private String xydm;// ѧԺ����

	private String zydm;// רҵ����

	private String bjdm;// �༶����

	private String czxm;// ������Ŀ

	private String xmmc;// ��Ŀ����

	private String lyb;// ��Դ��

	private String dcxs;// ������ʽ

	private String filePath;// ����·��
	
	private String wjmc;//�ļ�����
	
	private String bmlx;//��������

	private String[] pk;// ����

	private String[] fs;// ����

	List<HashMap<String, String>> bmList;// �����б�

	public List<HashMap<String, String>> getBmList() {
		return bmList;
	}

	public void setBmList(List<HashMap<String, String>> bmList) {
		this.bmList = bmList;
	}

	public String[] getFs() {
		return fs;
	}

	public void setFs(String[] fs) {
		this.fs = fs;
	}

	public String[] getPk() {
		return pk;
	}

	public void setPk(String[] pk) {
		this.pk = pk;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public List<HashMap<String, String>> getXmList() {
		return xmList;
	}

	public void setXmList(List<HashMap<String, String>> xmList) {
		this.xmList = xmList;
	}

	public String getLyb() {
		return lyb;
	}

	public void setLyb(String lyb) {
		this.lyb = lyb;
	}

	public String getDcxs() {
		return dcxs;
	}

	public void setDcxs(String dcxs) {
		this.dcxs = dcxs;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FormFile getCheckFilePath() {
		return checkFilePath;
	}

	public void setCheckFilePath(FormFile checkFilePath) {
		this.checkFilePath = checkFilePath;
	}

	public FormFile getImpFilePath() {
		return impFilePath;
	}

	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getWjmc() {
		return wjmc;
	}

	public void setWjmc(String wjmc) {
		this.wjmc = wjmc;
	}

	public String getBmlx() {
		return bmlx;
	}

	public void setBmlx(String bmlx) {
		this.bmlx = bmlx;
	}

}
