package xgxt.dtjs.sjxy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class SjxyDtjsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	/* 通用 */
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

	private String bz;// 备注

	/* 党总支 */
	private String dzzmc;// 党总支名称

	private String khqk;// 考核情况

	/* 党支部 */
	private String[] id;// 'ID';

	private String[] sszb;// 支部名称

	private String[] sj;// '党支部书记';

	private String[] fsj;// '党支部副书记';

	private String[] zzwy;// '组织委员';

	private String[] xcwy;// '宣传委员';

	private String[] jjwy;// '纪检委员';

	/* 入党申请 */
	private String djsqsj;// 递交申请时间

	private String zbmc;// 所属支部

	private String lx;// 类型

	/* 入党积极分子 */
	private String zzzt;// 在职状态

	private String xsccdm;// 学生层次

	/* 预备党员 */

	private String kssj;// 开始时间

	private String jssj;// 结束时间

	private String zzlx;// '转正类型';

	/* 正式党员 */
	private String zzdw;// '组织单位';

	private String zzsj;// '转正时间';

	private String rdsj;// '入党时间';

	private String ybdykssj;// 预备党员开始时间

	private String ybdyjssj;// 预备党员结束时间

	/* 数据统计 */
	private String pycc;

	private String rdsjdks;

	private String rdsjdjs;

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

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getDjsqsj() {
		return djsqsj;
	}

	public void setDjsqsj(String djsqsj) {
		this.djsqsj = djsqsj;
	}

	public String getDzzmc() {
		return dzzmc;
	}

	public void setDzzmc(String dzzmc) {
		this.dzzmc = dzzmc;
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

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
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

	public String getRdsj() {
		return rdsj;
	}

	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
	}

	public String[] getSj() {
		return sj;
	}

	public void setSj(String[] sj) {
		this.sj = sj;
	}

	public String[] getSszb() {
		return sszb;
	}

	public void setSszb(String[] sszb) {
		this.sszb = sszb;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String[] getXcwy() {
		return xcwy;
	}

	public void setXcwy(String[] xcwy) {
		this.xcwy = xcwy;
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

	public String getXsccdm() {
		return xsccdm;
	}

	public void setXsccdm(String xsccdm) {
		this.xsccdm = xsccdm;
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

	public String getYbdyjssj() {
		return ybdyjssj;
	}

	public void setYbdyjssj(String ybdyjssj) {
		this.ybdyjssj = ybdyjssj;
	}

	public String getYbdykssj() {
		return ybdykssj;
	}

	public void setYbdykssj(String ybdykssj) {
		this.ybdykssj = ybdykssj;
	}

	public String getZbmc() {
		return zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
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

	public String getZzdw() {
		return zzdw;
	}

	public void setZzdw(String zzdw) {
		this.zzdw = zzdw;
	}

	public String getZzlx() {
		return zzlx;
	}

	public void setZzlx(String zzlx) {
		this.zzlx = zzlx;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public String[] getZzwy() {
		return zzwy;
	}

	public void setZzwy(String[] zzwy) {
		this.zzwy = zzwy;
	}

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public String getKhqk() {
		return khqk;
	}

	public void setKhqk(String khqk) {
		this.khqk = khqk;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getRdsjdjs() {
		return rdsjdjs;
	}

	public void setRdsjdjs(String rdsjdjs) {
		this.rdsjdjs = rdsjdjs;
	}

	public String getRdsjdks() {
		return rdsjdks;
	}

	public void setRdsjdks(String rdsjdks) {
		this.rdsjdks = rdsjdks;
	}

}
