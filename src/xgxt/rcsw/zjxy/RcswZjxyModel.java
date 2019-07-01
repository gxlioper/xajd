package xgxt.rcsw.zjxy;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class RcswZjxyModel { /* 通用 */
	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件

	private String[] checkVal;//批处理
	
	private String[] xhzgh;// 学号职工号
	
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

	private String pkValue;// 主键值
	
	private String bmdm;//部门代码
	
	private String zgh;// '职工号';
	
	private String zw;// '职位';

	// ----------实物发放-------------
	private String xmlx;// '项目类型';

	private String[] ffrq;// '发放人群';

	private String ffsj;// '发放时间';
	
	private String ffbz;// '发放备注';
	
	private String ffr ;//'发放人';
	
	private String isff ;//'是否发放';
	
	private String userDep;//用户部门;
	
	private String userName;//用户名;
	
	private String userType;//用户类型
	
	private String show_xn;
	
	private String show_xq;
	
	private String show_nd;
	
	private String show_xmlx;
	
	private String show_ffsj;
	
	private String sfff;
	
	private String xmdm;
	
	private String sfzh;
	
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getSfff() {
		return sfff;
	}

	public void setSfff(String sfff) {
		this.sfff = sfff;
	}

	public String getShow_nd() {
		return show_nd;
	}

	public void setShow_nd(String show_nd) {
		this.show_nd = show_nd;
	}

	public String getShow_ffsj() {
		return show_ffsj;
	}

	public void setShow_ffsj(String show_ffsj) {
		this.show_ffsj = show_ffsj;
	}

	public String getShow_xmlx() {
		return show_xmlx;
	}

	public void setShow_xmlx(String show_xmlx) {
		this.show_xmlx = show_xmlx;
	}

	public String getShow_xn() {
		return show_xn;
	}

	public void setShow_xn(String show_xn) {
		this.show_xn = show_xn;
	}

	public String getShow_xq() {
		return show_xq;
	}

	public void setShow_xq(String show_xq) {
		this.show_xq = show_xq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}

	public String getIsff() {
		return isff;
	}

	public void setIsff(String isff) {
		this.isff = isff;
	}

	public String getFfr() {
		return ffr;
	}

	public void setFfr(String ffr) {
		this.ffr = ffr;
	}

	public String getFfbz() {
		return ffbz;
	}

	public void setFfbz(String ffbz) {
		this.ffbz = ffbz;
	}

	public String[] getFfrq() {
		return ffrq;
	}

	public void setFfrq(String[] ffrq) {
		this.ffrq = ffrq;
	}

	public String getFfsj() {
		return ffsj;
	}

	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
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

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
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

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
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

	public String[] getXhzgh() {
		return xhzgh;
	}

	public void setXhzgh(String[] xhzgh) {
		this.xhzgh = xhzgh;
	}
	
}
