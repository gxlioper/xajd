package xgxt.pjpy.comm.pjpy.sjsz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class PjpySjszForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private String doType ;
	
	private String[] primarykey_cbv;
	
	private String pjxn;
	
	private String pjxq;
	
	private String pjnd;
	
	private String xmmc;
	
	private String xmdm;
	
	private String[] sqkssj;
	
	private String[] sqjssj;
	
	private String[] sqkzkg;
	
	private String[] shkssj;
	
	private String[] shjssj;
	
	private String[] shkzkg;
	
	private String save_sqkssj;
	
	private String save_sqjssj;
	
	private String save_sqkzkg;
	
	private String save_shkssj;
	
	private String save_shjssj;
	
	private String save_shkzkg;

	public String[] getShjssj() {
		return shjssj;
	}

	public void setShjssj(String[] shjssj) {
		this.shjssj = shjssj;
	}

	public String[] getShkssj() {
		return shkssj;
	}

	public void setShkssj(String[] shkssj) {
		this.shkssj = shkssj;
	}

	public String[] getShkzkg() {
		return shkzkg;
	}

	public void setShkzkg(String[] shkzkg) {
		this.shkzkg = shkzkg;
	}

	public String[] getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String[] sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String[] getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String[] sqkssj) {
		this.sqkssj = sqkssj;
	}

	public String[] getSqkzkg() {
		return sqkzkg;
	}

	public void setSqkzkg(String[] sqkzkg) {
		this.sqkzkg = sqkzkg;
	}

	public String getPjnd() {
		return pjnd;
	}

	public void setPjnd(String pjnd) {
		this.pjnd = pjnd;
	}

	public String getPjxn() {
		return pjxn;
	}

	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}

	public String getPjxq() {
		return pjxq;
	}

	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}

	public void setPrimarykey_cbv(String[] primarykey_cbv) {
		this.primarykey_cbv = primarykey_cbv;
	}

	public String getSave_shjssj() {
		return save_shjssj;
	}

	public void setSave_shjssj(String save_shjssj) {
		this.save_shjssj = save_shjssj;
	}

	public String getSave_shkssj() {
		return save_shkssj;
	}

	public void setSave_shkssj(String save_shkssj) {
		this.save_shkssj = save_shkssj;
	}

	public String getSave_shkzkg() {
		return save_shkzkg;
	}

	public void setSave_shkzkg(String save_shkzkg) {
		this.save_shkzkg = save_shkzkg;
	}

	public String getSave_sqjssj() {
		return save_sqjssj;
	}

	public void setSave_sqjssj(String save_sqjssj) {
		this.save_sqjssj = save_sqjssj;
	}

	public String getSave_sqkssj() {
		return save_sqkssj;
	}

	public void setSave_sqkssj(String save_sqkssj) {
		this.save_sqkssj = save_sqkssj;
	}

	public String getSave_sqkzkg() {
		return save_sqkzkg;
	}

	public void setSave_sqkzkg(String save_sqkzkg) {
		this.save_sqkzkg = save_sqkzkg;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
}
