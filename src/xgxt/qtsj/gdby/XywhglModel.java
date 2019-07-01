package xgxt.qtsj.gdby;

import org.apache.struts.upload.FormFile;

public class XywhglModel {
	FormFile uploadFile;// 上传文件	
	private String hdmc;     //活动名称
	private String fj;     //附件
	private String hddm;     //活动代码
	private String hdms;     //活动描述
	private String[] cdmc;     //场地名称
	private String[] wzmc;		//物资名称
	private String username;
	private String lxdh;
	private String stmc;
	private String sqly;
	private String xxsh;
	private String xxshyj;
	private String xxshsj;
	
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
	public String getHdms() {
		return hdms;
	}
	public void setHdms(String hdms) {
		this.hdms = hdms;
	}
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStmc() {
		return stmc;
	}
	public void setStmc(String stmc) {
		this.stmc = stmc;
	}
	
	
}
