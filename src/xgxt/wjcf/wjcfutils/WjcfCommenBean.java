
package xgxt.wjcf.wjcfutils;

import java.io.Serializable;

public class WjcfCommenBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xydm = "";//学院代码
	private String zydm = "";//专业代码
	private String bjdm = "";//班级代码
	private String xq = "";//学期
	private String nj = "";//年级
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
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
