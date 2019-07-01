package xgxt.pjpy.zzsf;

import org.apache.struts.action.ActionForm;

public class ZzsfPjpyForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String xydm;
	private String zydm;
	private String bjdm;
	private String nj;
	private String xq;
	private String xn;
	private String xh;
	private boolean disable;
	private String en;
	private String selectTab;
	
	public String getSelectTab() {
		return selectTab;
	}
	public void setSelectTab(String selectTab) {
		this.selectTab = selectTab;
	}
	public String getEn() {
		return en;
	}
	public void setEn(String en) {
		this.en = en;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	public boolean isDisable() {
		return disable;
	}
}
