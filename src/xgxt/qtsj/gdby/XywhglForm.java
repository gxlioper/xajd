package xgxt.qtsj.gdby;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class XywhglForm extends ActionForm{
	Pages pages = new Pages();

	FormFile uploadFile;// �ϴ��ļ�	
	private String hdmc;     //�����
	private String fj;     //����
	private String hddm;     //�����
	private String hdms;     //�����
	private String[] cdmc;     //��������
	private String[] wzmc;		//��������
	private String username;
	private String lxdh;
	private String stmc;
	private String sqly;
	private String xxsh = "δ���";
	private String xxshyj = "";
	private String xxshsj = "";
	private String queryequals_hddm;
	private String[] primarykey_cbv;
	
	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}
	public void setPrimarykey_cbv(String[] primarykey_cbv) {
		this.primarykey_cbv = primarykey_cbv;
	}
	public String getQueryequals_hddm() {
		return queryequals_hddm;
	}
	public void setQueryequals_hddm(String queryequals_hddm) {
		this.queryequals_hddm = queryequals_hddm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshsj() {
		return xxshsj;
	}
	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String[] getWzmc() {
		return wzmc;
	}
	public void setWzmc(String[] wzmc) {
		this.wzmc = wzmc;
	}
	public String[] getCdmc() {
		return cdmc;
	}
	public void setCdmc(String[] cdmc) {
		this.cdmc = cdmc;
	}
	public String getFj() {
		return fj;
	}
	public void setFj(String fj) {
		this.fj = fj;
	}
	public String getHddm() {
		return hddm;
	}
	public void setHddm(String hddm) {
		this.hddm = hddm;
	}
	public String getHdmc() {
		return hdmc;
	}
	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getHdms() {
		return hdms;
	}
	public void setHdms(String hdms) {
		this.hdms = hdms;
	}
	public String getStmc() {
		return stmc;
	}
	public void setStmc(String stmc) {
		this.stmc = stmc;
	}	
}
