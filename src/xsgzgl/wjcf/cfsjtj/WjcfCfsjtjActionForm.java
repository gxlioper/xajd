package xsgzgl.wjcf.cfsjtj;

import xgxt.comm.CommForm;

public class WjcfCfsjtjActionForm extends CommForm {
	private String cftjlx;
	private String xn;
	private String ksxn;
	private String jsxn;
	private String dqxn;
	private String doType;
	
	
	public String getKsxn() {
		return ksxn;
	}

	public void setKsxn(String ksxn) {
		this.ksxn = ksxn;
	}

	public String getJsxn() {
		return jsxn;
	}

	public void setJsxn(String jsxn) {
		this.jsxn = jsxn;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public void setCftjlx(String cftjlx) {
		this.cftjlx = cftjlx;
	}

	public String getCftjlx() {
		return cftjlx;
	}

	public void setDqxn(String dqxn) {
		this.dqxn = dqxn;
	}

	public String getDqxn() {
		return dqxn;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}

	public String getDoType() {
		return doType;
	}

}
