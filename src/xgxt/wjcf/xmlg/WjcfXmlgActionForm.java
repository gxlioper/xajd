package xgxt.wjcf.xmlg;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;
import xgxt.wjcf.WjcfActionForm;

public class WjcfXmlgActionForm extends WjcfActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Pages pages = new Pages();

	private String pkValue;
	private String[] keys;
	private FormFile uploadFile;
	private String operType;

	private String bjpyyj;
	private String fdyjdyj;
	private String xndshyj;
	private String xndsh;
	private String lswjjl;
	
	private String qtcfqk;
	private String xyyj;
	private String bzryj;
	private String xgcyj;
	private String xxyj;
	private String bz;
	public String getLswjjl() {
		return lswjjl;
	}

	public void setLswjjl(String lswjjl) {
		this.lswjjl = lswjjl;
	}

	public String getBjpyyj() {
		return bjpyyj;
	}

	public void setBjpyyj(String bjpyyj) {
		this.bjpyyj = bjpyyj;
	}

	public String getFdyjdyj() {
		return fdyjdyj;
	}

	public void setFdyjdyj(String fdyjdyj) {
		this.fdyjdyj = fdyjdyj;
	}

	public String getXndsh() {
		return xndsh;
	}

	public void setXndsh(String xndsh) {
		this.xndsh = xndsh;
	}

	public String getXndshyj() {
		return xndshyj;
	}

	public void setXndshyj(String xndshyj) {
		this.xndshyj = xndshyj;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBzryj() {
		return bzryj;
	}

	public void setBzryj(String bzryj) {
		this.bzryj = bzryj;
	}

	public String getQtcfqk() {
		return qtcfqk;
	}

	public void setQtcfqk(String qtcfqk) {
		this.qtcfqk = qtcfqk;
	}

	public String getXgcyj() {
		return xgcyj;
	}

	public void setXgcyj(String xgcyj) {
		this.xgcyj = xgcyj;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}
}
