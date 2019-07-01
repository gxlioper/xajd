package xgxt.pjpy.ghxy.qsryf;

import java.io.Serializable;

import xgxt.utils.Pages;

public class QueryModel implements Serializable{
	private String xn;
	private String xq;
	private String nj;
	private String yqqdm;
	private String pc;
	private String lddm;
	private String cs;
	private String qsh;
	private Pages pages=new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
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
	public String getYqqdm() {
		return yqqdm;
	}
	public void setYqqdm(String yqqdm) {
		this.yqqdm = yqqdm;
	}
}
