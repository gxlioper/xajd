package xgxt.studentInfo.zjlg;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class ZjlgXsxxForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	private String nj;

	private String nd;

	private String xn;

	private String zydm;

	private String xydm;

	private String bjdm;

	private String zymc;

	private String xymc;

	private String bjmc;

	private String xq;

	private String xh;

	private String xm;

	private String xb;

	private String jlxm;// '交流项目';

	private String cgsj;// '出国时间';

	private String hgsj;// /'回国时间';

	private String bz;// '备注';

	private String rwsj;// '入伍时间';

	private String twsj;// /'退伍时间';

	private String fyqjbx;// '服役期间表现';

	private String cxjl;// '诚信记录';

	private String jlsj;// '记录时间';

	private String[] checkVal;

	// 通用分页
	Pages pages = new Pages();

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCgsj() {
		return cgsj;
	}

	public void setCgsj(String cgsj) {
		this.cgsj = cgsj;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getHgsj() {
		return hgsj;
	}

	public void setHgsj(String hgsj) {
		this.hgsj = hgsj;
	}

	public String getJlxm() {
		return jlxm;
	}

	public void setJlxm(String jlxm) {
		this.jlxm = jlxm;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
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

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getFyqjbx() {
		return fyqjbx;
	}

	public void setFyqjbx(String fyqjbx) {
		this.fyqjbx = fyqjbx;
	}

	public String getRwsj() {
		return rwsj;
	}

	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}

	public String getTwsj() {
		return twsj;
	}

	public void setTwsj(String twsj) {
		this.twsj = twsj;
	}

	public String getCxjl() {
		return cxjl;
	}

	public void setCxjl(String cxjl) {
		this.cxjl = cxjl;
	}

	public String getJlsj() {
		return jlsj;
	}

	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}

}
