package xgxt.pjpy.tyb.zhszcp.model;

import xgxt.utils.String.StringUtils;

public class PjpyZhszfsModel {
	private String[] xh;
	private String[] fs;
	private String[] dm;
	
	private String jb;
	private String xn;
	private String xq;
	private String nd;
	
	public String getJb() {
		return jb;
	}
	public void setJb(String jb) {
		this.jb = jb;
	}
	public String[] getXh() {
		return xh;
	}
	public void setXh(String[] xh) {
		this.xh = xh;
	}
	public String[] getFs() {
		return fs;
	}
	public void setFs(String[] fs) {
		this.fs = fs;
	}
	public String[] getDm() {
		return dm;
	}
	public void setDm(String[] dm) {
		this.dm = dm;
	}
	public String getXn() {
		return StringUtils.isNull(xn) ? "нч" : xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return StringUtils.isNull(xq) ? "нч" : xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getNd() {
		return StringUtils.isNull(nd) ? "нч" : nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}

	
}
