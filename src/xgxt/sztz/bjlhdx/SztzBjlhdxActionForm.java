/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-5-11 下午04:50:16</p>
 */
package xgxt.sztz.bjlhdx;

import org.apache.struts.action.ActionForm;

public class SztzBjlhdxActionForm extends ActionForm {
	private static final long serialVersionUID = 3033492483030499092L;
    String nj;
    String xydm;
    String zydm;
    String bjdm;
    String xh;
    String xm;
    String csf;//初始分
	public String getCsf() {
		return csf;
	}
	public void setCsf(String csf) {
		this.csf = csf;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
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
