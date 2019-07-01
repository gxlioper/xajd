package xgxt.pjpy.wxsz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * 2010.4.21 qph
 * 评奖评优－先进集体评选专用
 */
public class XjjtForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Pages pages = new Pages();
	
	private String jtmc;
	
	private String xq;
	
	private String xn;
	
	private String jtrs;
	
	private String xydm;
	
	private String fzls;
	
	private String zysj;
	
	private String yxyj;
	
	private String xgcyj;
	
	private String xyyj;
	
	private String xysh;
	
	private String xxsh;
	
	public String getFzls() {
		return fzls;
	}
	public void setFzls(String fzls) {
		this.fzls = fzls;
	}
	public String getJtmc() {
		return jtmc;
	}
	public void setJtmc(String jtmc) {
		this.jtmc = jtmc;
	}
	public String getJtrs() {
		return jtrs;
	}
	public void setJtrs(String jtrs) {
		this.jtrs = jtrs;
	}
	public String getXgcyj() {
		return xgcyj;
	}
	public void setXgcyj(String xgcyj) {
		this.xgcyj = xgcyj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXyyj() {
		return xyyj;
	}
	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}
	public String getYxyj() {
		return yxyj;
	}
	public void setYxyj(String yxyj) {
		this.yxyj = yxyj;
	}
	public String getZysj() {
		return zysj;
	}
	public void setZysj(String zysj) {
		this.zysj = zysj;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	
	
}
