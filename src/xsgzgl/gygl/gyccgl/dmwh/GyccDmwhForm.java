package xsgzgl.gygl.gyccgl.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class GyccDmwhForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String dm;
	private String mc;
	private String sx;
	private String shcddm;
	private String shcdmc;
	private String je;
	private String type;
	private String cxlx;
	private Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getCxlx() {
		return cxlx;
	}
	public void setCxlx(String cxlx) {
		this.cxlx = cxlx;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
	public String getShcddm() {
		return shcddm;
	}
	public void setShcddm(String shcddm) {
		this.shcddm = shcddm;
	}
	public String getShcdmc() {
		return shcdmc;
	}
	public void setShcdmc(String shcdmc) {
		this.shcdmc = shcdmc;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	
}
