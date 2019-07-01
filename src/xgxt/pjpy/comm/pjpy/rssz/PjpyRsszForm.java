package xgxt.pjpy.comm.pjpy.rssz;

import xgxt.pjpy.comm.pjpy.PjpyCommForm;

public class PjpyRsszForm extends PjpyCommForm{
	private String szfw; // 设置范围
	private String xmfw; // 项目范围
	private String xmxz; // 项目性质
	private String xmlx; // 项目类型
	private String xmdm; // 项目代码
	private String fpfs = "bl";
	private String bl;	 // 比例
	private String rs;   // 人数
	private String blxs; // 保留小数
	private String[] qdrs; // 确定人数
	private String[] pks; 
	private String nj;
	private String xydm;	// 学院代码
	private String zydm;	// 专业代码
	private String bjdm;    // 班级代码

	private String[] params = new String[]{};

	public String getSzfw() {
		return szfw;
	}

	public void setSzfw(String szfw) {
		this.szfw = szfw;
	}

	public String getXmfw() {
		return xmfw;
	}

	public void setXmfw(String xmfw) {
		this.xmfw = xmfw;
	}

	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getFpfs() {
		return fpfs;
	}

	public void setFpfs(String fpfs) {
		this.fpfs = fpfs;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String getBlxs() {
		return blxs;
	}

	public void setBlxs(String blxs) {
		this.blxs = blxs;
	}

	public String[] getQdrs() {
		return qdrs;
	}

	public void setQdrs(String[] qdrs) {
		this.qdrs = qdrs;
	}

	public String[] getPks() {
		return pks;
	}

	public void setPks(String[] pks) {
		this.pks = pks;
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
}
