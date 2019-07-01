package xgxt.rcsw.nthy;

import org.apache.struts.action.ActionForm;
import xgxt.utils.Pages;

public class XfqfglActionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pages pages = new Pages();
	
	private String pk;
	
	private String queryequals_nj;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String queryequals_xn;
	private String queryequals_sfqf;
	
	private String pkValue;
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequalsNj) {
		queryequals_nj = queryequalsNj;
	}
	public String getQuerylike_xh() {
		return querylike_xh;
	}
	public void setQuerylike_xh(String querylikeXh) {
		querylike_xh = querylikeXh;
	}
	public String getQuerylike_xm() {
		return querylike_xm;
	}
	public void setQuerylike_xm(String querylikeXm) {
		querylike_xm = querylikeXm;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequalsXydm) {
		queryequals_xydm = queryequalsXydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequalsZydm) {
		queryequals_zydm = queryequalsZydm;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequalsBjdm) {
		queryequals_bjdm = queryequalsBjdm;
	}
	public String getQueryequals_xn() {
		return queryequals_xn;
	}
	public void setQueryequals_xn(String queryequalsXn) {
		queryequals_xn = queryequalsXn;
	}
	public String getQueryequals_sfqf() {
		return queryequals_sfqf;
	}
	public void setQueryequals_sfqf(String queryequalsSfqf) {
		queryequals_sfqf = queryequalsSfqf;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	
}
