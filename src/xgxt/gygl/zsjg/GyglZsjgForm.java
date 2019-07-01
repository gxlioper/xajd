package xgxt.gygl.zsjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.gygl.GyglCommForm;
import xgxt.utils.Pages;

public class GyglZsjgForm extends GyglCommForm {
	/* Õ®”√ */
	
	private static final long serialVersionUID = 1L;
	
	private String[] checkVal;
	
	private String fpdx;
	
	private String xnfw;

	private String []lddmArr;
	
	private String []csArr;
	
	private String []qshArr;
	
	private String []cwhArr;
	
	private String []xhArr;
	
	private String []xsxhArr;
	
	private String []ydxsxhArr;
	
	private String dclx;

	private String czxq;
	
	private String czyq;
	
	private String lddm;
	
	public String getCzxq() {
		return czxq;
	}

	public void setCzxq(String czxq) {
		this.czxq = czxq;
	}

	public String getCzyq() {
		return czyq;
	}

	public void setCzyq(String czyq) {
		this.czyq = czyq;
	}

	public String[] getYdxsxhArr() {
		return ydxsxhArr;
	}

	public void setYdxsxhArr(String[] ydxsxhArr) {
		this.ydxsxhArr = ydxsxhArr;
	}

	public String[] getXsxhArr() {
		return xsxhArr;
	}

	public void setXsxhArr(String[] xsxhArr) {
		this.xsxhArr = xsxhArr;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getCsArr() {
		return csArr;
	}

	public void setCsArr(String[] csArr) {
		this.csArr = csArr;
	}

	public String[] getCwhArr() {
		return cwhArr;
	}

	public void setCwhArr(String[] cwhArr) {
		this.cwhArr = cwhArr;
	}

	public String getFpdx() {
		return fpdx;
	}

	public void setFpdx(String fpdx) {
		this.fpdx = fpdx;
	}

	public String[] getLddmArr() {
		return lddmArr;
	}

	public void setLddmArr(String[] lddmArr) {
		this.lddmArr = lddmArr;
	}

	public String[] getQshArr() {
		return qshArr;
	}

	public void setQshArr(String[] qshArr) {
		this.qshArr = qshArr;
	}

	public String[] getXhArr() {
		return xhArr;
	}

	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}

	public String getXnfw() {
		return xnfw;
	}

	public void setXnfw(String xnfw) {
		this.xnfw = xnfw;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getDclx() {
		return dclx;
	}

	public void setDclx(String dclx) {
		this.dclx = dclx;
	}
	
}