
package xgxt.wjcf.csmz;

import java.io.Serializable;

public class CxcfSqSaveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 43024028283543469L;

	private String xh;//学号
	private String cfwh;//处分文号
	private String cfsj;//处分时间
	private String bz;//备注
	private String cflbdm;//处分类别代码
	private String cflbmc;//处分类别
	private String cfyydm;//处分原因代码
	private String cfyymc;//处分原因
	private String xn;//学年
	private String xq;//学期
	private String cxsj;//撤消时间
	
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getCflbdm() {
		return cflbdm;
	}
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getCfyydm() {
		return cfyydm;
	}
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getCxsj() {
		return cxsj;
	}
	public void setCxsj(String cxsj) {
		this.cxsj = cxsj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
}
