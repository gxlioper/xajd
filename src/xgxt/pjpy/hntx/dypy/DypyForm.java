package xgxt.pjpy.hntx.dypy;

import xgxt.dtjs.gdby.tygl.BasicExtendForm;
import xgxt.utils.Pages;

public class DypyForm extends BasicExtendForm{
	
	Pages pages = new Pages();
	private String xh;     	// ѧ��
	private String xn;			// ѧ��
	private String xmdm[];		// ��Ŀ����
	private String xmjf[];		// ��Ŀ�ӷ�
	private String xmmc[];		// ��Ŀ����
	private String bl[];		// ����
	
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
