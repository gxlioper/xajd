
package xgxt.pjpy.xbemy;

import org.apache.struts.action.ActionForm;

public class PjpyXbemyActionForm extends ActionForm {
	private static final long serialVersionUID = 7143149339702468761L;
	
	private String xydm;
	private String zydm;
	private String bjdm;
	private String nj;
	private String xn;
	private String xmdm;
	private String[] cbv;//审核查询页面的checkbox
	private String jg;//通过结果

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String[] getCbv() {
		return cbv;
	}

	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	
}
