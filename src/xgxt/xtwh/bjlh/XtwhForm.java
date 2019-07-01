package xgxt.xtwh.bjlh;

import org.apache.struts.action.ActionForm;

public class XtwhForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xydm;

	private String zgh;

	private String nj;

	private String zydm;

	private String bmdm;
	
	private String zynjzxm;
	
	private String[] bjdm;

	public String[] getBjdm() {
		return bjdm;
	}

	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
	}

	public String getZynjzxm() {
		return zynjzxm;
	}

	public void setZynjzxm(String zynjzxm) {
		this.zynjzxm = zynjzxm;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

}
