/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.pjpy.ghxy.szfgl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * MyEclipse Struts
 * Creation date: 08-28-2010
 * 
 * XDoclet definition:
 * @struts.form name="ghxySzfglForm"
 */
public class GhxySzfglForm extends ActionForm {
	/*
	 * Generated Methods
	 */
	private String queryequals_bjdm;
	private String queryequals_zydm;
	private String queryequals_xydm;
	private String querylike_xm;
	private String querylike_xh;
	private String queryequals_xb;
	private String queryequals_nj;
	private String xydm;
	private String xh;
	
	private Pages pages=new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}
	public String getQueryequals_xb() {
		return queryequals_xb;
	}
	public void setQueryequals_xb(String queryequals_xb) {
		this.queryequals_xb = queryequals_xb;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getQuerylike_xh() {
		return querylike_xh;
	}
	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}
	public String getQuerylike_xm() {
		return querylike_xm;
	}
	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
}