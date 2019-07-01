package xgxt.dtjs.zjcm;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class ZjcmDtjsModel {

	/* 通用 */
	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件

	private String[] checkVal;// 批处理

	private String xysh;// 学院审核

	private String xxsh;// 学校审核

	private String userName;// 用户名

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

	private String lx;// 类型

	private String id;// ID

	private String zzzt;// 在职状态
	
	/* 培训信息 */
	private String pxjssj;// 培训开始时间

	private String pxkssj;// 培训结束时间

	private String pxjg;// 培训结果

	private String pxxmdm;// 培训项目代码

	private String zsyw;// 证书有无

	/* 发展对象 */
	private String kssj;// 开始时间

	private String jssj;// 结束时间
	
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

	public String getPxjg() {
		return pxjg;
	}

	public void setPxjg(String pxjg) {
		this.pxjg = pxjg;
	}

	public String getPxjssj() {
		return pxjssj;
	}

	public void setPxjssj(String pxjssj) {
		this.pxjssj = pxjssj;
	}

	public String getPxkssj() {
		return pxkssj;
	}

	public void setPxkssj(String pxkssj) {
		this.pxkssj = pxkssj;
	}

	public String getPxxmdm() {
		return pxxmdm;
	}

	public void setPxxmdm(String pxxmdm) {
		this.pxxmdm = pxxmdm;
	}

	public String getZsyw() {
		return zsyw;
	}

	public void setZsyw(String zsyw) {
		this.zsyw = zsyw;
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

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
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

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
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

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}
}
