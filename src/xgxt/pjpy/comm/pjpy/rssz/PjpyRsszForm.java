package xgxt.pjpy.comm.pjpy.rssz;

import xgxt.pjpy.comm.pjpy.PjpyCommForm;

public class PjpyRsszForm extends PjpyCommForm{
	private String szfw; // ���÷�Χ
	private String xmfw; // ��Ŀ��Χ
	private String xmxz; // ��Ŀ����
	private String xmlx; // ��Ŀ����
	private String xmdm; // ��Ŀ����
	private String fpfs = "bl";
	private String bl;	 // ����
	private String rs;   // ����
	private String blxs; // ����С��
	private String[] qdrs; // ȷ������
	private String[] pks; 
	private String nj;
	private String xydm;	// ѧԺ����
	private String zydm;	// רҵ����
	private String bjdm;    // �༶����

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
