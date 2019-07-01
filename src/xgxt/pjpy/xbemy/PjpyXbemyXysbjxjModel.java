
package xgxt.pjpy.xbemy;

/**
 * @author 
 * 用于西北二民院的学院上报奖学金查询的model 
 * model中的域一定要与查询PjpyXbemyDAO：getXysbjxjSearch中用到的字段对应，
 * 也就是必须与相应的数据库结构对应
 */
public class PjpyXbemyXysbjxjModel {
	
	private String xydm;
	private String zydm;
	private String bjdm;
	private String nj;
	private String xn;
	private String xmdm;
	private String jg;//通过结果
	
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
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
