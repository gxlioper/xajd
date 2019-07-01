package xgxt.pjpy.ghxy.qsryf;

import xgxt.utils.Pages;

public class GhxyQsryfModel {
	private String xmfz[];     //项目分值
	private String pc[];     //批次
	private String xn[];     //学年
	private String xq[];     //学期
	private String plssbh[];     //批量班级代码
	private String xmdm[];     //项目代码
	private String checkVal[]; //
	private String lddm;
	private String qsh;
	private String cs;
	private String nj;
	private String queryequals_lddm;
	private String queryequals_cs;
	private String queryequals_qsh;
	
	private Pages pages=new Pages();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getPc() {
		return pc;
	}

	public void setPc(String[] pc) {
		this.pc = pc;
	}

	public String[] getXmdm() {
		return xmdm;
	}

	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}

	public String[] getXmfz() {
		return xmfz;
	}

	public void setXmfz(String[] xmfz) {
		this.xmfz = xmfz;
	}

	public String[] getXn() {
		return xn;
	}

	public void setXn(String[] xn) {
		this.xn = xn;
	}

	public String[] getXq() {
		return xq;
	}

	public void setXq(String[] xq) {
		this.xq = xq;
	}

	public String[] getPlssbh() {
		return plssbh;
	}

	public void setPlssbh(String[] plssbh) {
		this.plssbh = plssbh;
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

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getQueryequals_cs() {
		return queryequals_cs;
	}

	public void setQueryequals_cs(String queryequals_cs) {
		this.queryequals_cs = queryequals_cs;
	}

	public String getQueryequals_lddm() {
		return queryequals_lddm;
	}

	public void setQueryequals_lddm(String queryequals_lddm) {
		this.queryequals_lddm = queryequals_lddm;
	}

	public String getQueryequals_qsh() {
		return queryequals_qsh;
	}

	public void setQueryequals_qsh(String queryequals_qsh) {
		this.queryequals_qsh = queryequals_qsh;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	

}
