package xgxt.pjpy.hntx.dypy;

import xgxt.dtjs.gdby.tygl.BasicExtendForm;
import xgxt.utils.Pages;

public class DypyForm extends BasicExtendForm{
	
	Pages pages = new Pages();
	private String xh;     	// 学号
	private String xn;			// 学年
	private String xmdm[];		// 项目代码
	private String xmjf[];		// 项目加分
	private String xmmc[];		// 项目名称
	private String bl[];		// 比率
	
	public String[] getBl() {
		return bl;
	}
	public void setBl(String[] bl) {
		this.bl = bl;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String[] getXmdm() {
		return xmdm;
	}
	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}
	public String[] getXmjf() {
		return xmjf;
	}
	public void setXmjf(String[] xmjf) {
		this.xmjf = xmjf;
	}
	public String[] getXmmc() {
		return xmmc;
	}
	public void setXmmc(String[] xmmc) {
		this.xmmc = xmmc;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	
}
