package xgxt.pjpy.czxx.zycj;

import xgxt.pjpy.tablesmodel.PjpyModel;
import xgxt.utils.Pages;

public class ZycjModel extends PjpyModel {

	private String cj;
	private String bz;
	private String userName;
	private String[] cjArr;
	private String[] cbv;
	private String[] hiddenCbv;
	private String[] pllb;
	private String[] plyy;
	private String[] plbz;
	private String[] plfs;
	private String pkValue;
	
	private Pages pages = new Pages();

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String[] getCbv() {
		return cbv;
	}

	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}

	public String getCj() {
		return cj;
	}

	public void setCj(String cj) {
		this.cj = cj;
	}

	public String[] getCjArr() {
		return cjArr;
	}

	public void setCjArr(String[] cjArr) {
		this.cjArr = cjArr;
	}

	public String[] getHiddenCbv() {
		return hiddenCbv;
	}

	public void setHiddenCbv(String[] hiddenCbv) {
		this.hiddenCbv = hiddenCbv;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getPlbz() {
		return plbz;
	}

	public void setPlbz(String[] plbz) {
		this.plbz = plbz;
	}

	public String[] getPlfs() {
		return plfs;
	}

	public void setPlfs(String[] plfs) {
		this.plfs = plfs;
	}

	public String[] getPllb() {
		return pllb;
	}

	public void setPllb(String[] pllb) {
		this.pllb = pllb;
	}

	public String[] getPlyy() {
		return plyy;
	}

	public void setPlyy(String[] plyy) {
		this.plyy = plyy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
