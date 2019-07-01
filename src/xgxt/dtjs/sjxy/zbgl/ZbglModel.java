package xgxt.dtjs.sjxy.zbgl;

import xgxt.utils.Pages;

public class ZbglModel {

	// 通用分页
	Pages pages = new Pages();
	
	private String[] checkVal;// 批处理
	
	private String xh;// 学号

	private String xm;// 姓名

	private String xb;// 性别

	private String nj;// 年级

	private String xn;// 学年

	private String xq;// 学期

	private String nd;// 年度

	private String xydm;// 学院代码

	private String xymc;// 学院名称

	private String zydm;// 专业代码

	private String zymc;// 专业名称

	private String bjdm;// 班级代码

	private String bjmc;// 班级名称

	private String dzzmc;// 党总支名称
	
	private String khqk;// 考核情况
	
	private String[] sszb;// 支部名称
	
	private String[] id;// 'ID';

	private String[] sj;// '党支部书记';

	private String[] fsj;// '党支部副书记';

	private String[] zzwy;// '组织委员';

	private String[] xcwy;// '宣传委员';

	private String[] jjwy;// '纪检委员';
	
	private String zbmc;// 支部名称
	
	private String bz;// 备注
	
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

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

	public String getDzzmc() {
		return dzzmc;
	}

	public void setDzzmc(String dzzmc) {
		this.dzzmc = dzzmc;
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

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getFsj() {
		return fsj;
	}

	public void setFsj(String[] fsj) {
		this.fsj = fsj;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getJjwy() {
		return jjwy;
	}

	public void setJjwy(String[] jjwy) {
		this.jjwy = jjwy;
	}

	public String[] getSj() {
		return sj;
	}

	public void setSj(String[] sj) {
		this.sj = sj;
	}

	public String[] getXcwy() {
		return xcwy;
	}

	public void setXcwy(String[] xcwy) {
		this.xcwy = xcwy;
	}

	public String[] getZzwy() {
		return zzwy;
	}

	public void setZzwy(String[] zzwy) {
		this.zzwy = zzwy;
	}

	public String[] getSszb() {
		return sszb;
	}

	public void setSszb(String[] sszb) {
		this.sszb = sszb;
	}

	public String getZbmc() {
		return zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}

	public String getKhqk() {
		return khqk;
	}

	public void setKhqk(String khqk) {
		this.khqk = khqk;
	}
}
