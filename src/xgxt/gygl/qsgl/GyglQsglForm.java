package xgxt.gygl.qsgl;

import xgxt.gygl.GyglCommForm;

public class GyglQsglForm extends GyglCommForm {

	private static final long serialVersionUID = 1L;

	private String fpdx;// '分配对象';

	private String[] ssbh;// '宿舍编号';

	private String[] lddm;// '楼栋代码';

	private String[] cs;// '层数';

	private String[] qsh;// '寝室号';

	private String[] nj;// '年级';

	private String[] bmdm;// '部门代码';

	// =============自动分配======================
	private String[] xiaoqu;// '寝室号';

	private String[] yuanqu;// '年级';

	private String[] ld;// '部门代码';

	private String xb;// '性别';

	private String kfhz;// '可否混住';

	private String[] xfprs;// '需分配人数';

	private String[] cwh;// '床位号';

	private String sjly;// '数据类型';

	private String kffp;// '可否分配';

	// =============自动分配 end======================

	// =============手动分配======================
	private String[] rzzt;// '入住状态';

	private String wfpqs;// '未分配寝室';

	// =============手动分配 end======================

	public String getWfpqs() {
		return wfpqs;
	}

	public void setWfpqs(String wfpqs) {
		this.wfpqs = wfpqs;
	}

	public String[] getBmdm() {
		return bmdm;
	}

	public void setBmdm(String[] bmdm) {
		this.bmdm = bmdm;
	}

	public String[] getCs() {
		return cs;
	}

	public void setCs(String[] cs) {
		this.cs = cs;
	}

	public String getFpdx() {
		return fpdx;
	}

	public void setFpdx(String fpdx) {
		this.fpdx = fpdx;
	}

	public String[] getLddm() {
		return lddm;
	}

	public void setLddm(String[] lddm) {
		this.lddm = lddm;
	}

	public String[] getNj() {
		return nj;
	}

	public void setNj(String[] nj) {
		this.nj = nj;
	}

	public String[] getQsh() {
		return qsh;
	}

	public void setQsh(String[] qsh) {
		this.qsh = qsh;
	}

	public String[] getXfprs() {
		return xfprs;
	}

	public void setXfprs(String[] xfprs) {
		this.xfprs = xfprs;
	}

	public String getKfhz() {
		return kfhz;
	}

	public void setKfhz(String kfhz) {
		this.kfhz = kfhz;
	}

	public String[] getLd() {
		return ld;
	}

	public void setLd(String[] ld) {
		this.ld = ld;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String[] getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(String[] xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public String[] getYuanqu() {
		return yuanqu;
	}

	public void setYuanqu(String[] yuanqu) {
		this.yuanqu = yuanqu;
	}

	public String[] getCwh() {
		return cwh;
	}

	public void setCwh(String[] cwh) {
		this.cwh = cwh;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String[] getSsbh() {
		return ssbh;
	}

	public void setSsbh(String[] ssbh) {
		this.ssbh = ssbh;
	}

	public String[] getRzzt() {
		return rzzt;
	}

	public void setRzzt(String[] rzzt) {
		this.rzzt = rzzt;
	}

	public String getKffp() {
		return kffp;
	}

	public void setKffp(String kffp) {
		this.kffp = kffp;
	}

}
