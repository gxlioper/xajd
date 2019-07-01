package xgxt.dtjs.czxx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class CzxxDtjsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	/* 通用 */
	Pages pages = new Pages();

	SearchModel searchModel = new SearchModel();

	FormFile uploadFile;// 上传文件

	User user=new User();
	
	private boolean fdyQx;
	
	private boolean bzrQx;
	
	private String[] checkVal;// 批处理

	private String[] tyxh;// 团员学号

	private String sfty;

	private String bzrsh;// 班主任审核

	private String xysh;// 学院审核

	private String xxsh;// 学校审核

	private String isBzr;// 是否是班主任

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

	private String pxxmdm;// 培训项目代码

	private String pxcj;// 培训成绩

	private String lx;// 类型

	private String id;// ID

	private String zhdj;// 转换等级

	private String zhsj;// 转换时间

	private String[] rtrq;// 入团日期

	private String[] rtdd;// 入团地点

	/* 入党申请 */
	private String sqsj;// 申请时间

	/* 入党积极分子 */
	private String zzzt;// 在职状态

	private String xsccdm;// 学生层次

	/* 发展对象 */

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

	/* 思想汇报 */
	private String wjm;// '文件名';

	private String tjsj;// '提交时间';

	private String scdz;// '上传地址';

	private String tjr;// '提交人';

	/* 党课名单 */
	private String[] pxmdxh;// 培训名单学号

	private String pxsj;// 培训时间

	/* 培训信息 */

	private String pxmc;// 培训名称

	private String pxdd;// 培训地点

	private String pxnr;// 培训内容

	private String[] dkcj;// 党课成绩

	// ===========================广东白云-党课培训成绩 sjf==========================
	private String[] xxtd; // 学习态度

	private String[] xxjl; // 学习纪律

	private String[] xxxg; // 学习效果

	private String xxtdbl; // 学习态度比率

	private String xxjlbl; // 学习纪律比率

	private String xxxgbl; // 学习效果比率

	// ===========================广东白云-党课培训成绩 =============================

	// ===========================团员信息修改 sjf===================================
	private String save_xh;

	private String save_rtrq;

	private String save_bz;

	private String save_rtdd;

	// ===================================end======================================

	private String queryequals_xydm;

	private String lwjjfzsj;

	private String dnzw;

	private String db;

	private String zb;

	// =====================团员注册信息=========================
	private String zcsj;
	
	private String[] pkV;

	public String[] getPkV() {
		return pkV;
	}

	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}

	// =====================团员注册信息=========================
	public String getZb() {
		return zb;
	}

	public void setZb(String zb) {
		this.zb = zb;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getDnzw() {
		return dnzw;
	}

	public void setDnzw(String dnzw) {
		this.dnzw = dnzw;
	}

	public String getLwjjfzsj() {
		return lwjjfzsj;
	}

	public void setLwjjfzsj(String lwjjfzsj) {
		this.lwjjfzsj = lwjjfzsj;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getXxjlbl() {
		return xxjlbl;
	}

	public String[] getRtdd() {
		return rtdd;
	}

	public void setRtdd(String[] rtdd) {
		this.rtdd = rtdd;
	}

	public String getSave_xh() {
		return save_xh;
	}

	public void setSave_xh(String saveXh) {
		save_xh = saveXh;
	}

	public String getSave_rtrq() {
		return save_rtrq;
	}

	public void setSave_rtrq(String saveRtrq) {
		save_rtrq = saveRtrq;
	}

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String saveBz) {
		save_bz = saveBz;
	}

	public String getSave_rtdd() {
		return save_rtdd;
	}

	public void setSave_rtdd(String saveRtdd) {
		save_rtdd = saveRtdd;
	}

	public void setXxjlbl(String xxjlbl) {
		this.xxjlbl = xxjlbl;
	}

	public String getXxtdbl() {
		return xxtdbl;
	}

	public void setXxtdbl(String xxtdbl) {
		this.xxtdbl = xxtdbl;
	}

	public String getXxxgbl() {
		return xxxgbl;
	}

	public void setXxxgbl(String xxxgbl) {
		this.xxxgbl = xxxgbl;
	}

	public String[] getDkcj() {
		return dkcj;
	}

	public void setDkcj(String[] dkcj) {
		this.dkcj = dkcj;
	}

	public String getScdz() {
		return scdz;
	}

	public void setScdz(String scdz) {
		this.scdz = scdz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
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

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getTjr() {
		return tjr;
	}

	public void setTjr(String tjr) {
		this.tjr = tjr;
	}

	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	public String[] getPxmdxh() {
		return pxmdxh;
	}

	public void setPxmdxh(String[] pxmdxh) {
		this.pxmdxh = pxmdxh;
	}

	public String getPxsj() {
		return pxsj;
	}

	public void setPxsj(String pxsj) {
		this.pxsj = pxsj;
	}

	public String getBzrsh() {
		return bzrsh;
	}

	public void setBzrsh(String bzrsh) {
		this.bzrsh = bzrsh;
	}

	public String getIsBzr() {
		return isBzr;
	}

	public void setIsBzr(String isBzr) {
		this.isBzr = isBzr;
	}

	public String getPxcj() {
		return pxcj;
	}

	public void setPxcj(String pxcj) {
		this.pxcj = pxcj;
	}

	public String getPxxmdm() {
		return pxxmdm;
	}

	public void setPxxmdm(String pxxmdm) {
		this.pxxmdm = pxxmdm;
	}

	public String getSfty() {
		return sfty;
	}

	public void setSfty(String sfty) {
		this.sfty = sfty;
	}

	public String[] getTyxh() {
		return tyxh;
	}

	public void setTyxh(String[] tyxh) {
		this.tyxh = tyxh;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getPxmc() {
		return pxmc;
	}

	public void setPxmc(String pxmc) {
		this.pxmc = pxmc;
	}

	public String getPxdd() {
		return pxdd;
	}

	public void setPxdd(String pxdd) {
		this.pxdd = pxdd;
	}

	public String getPxnr() {
		return pxnr;
	}

	public void setPxnr(String pxnr) {
		this.pxnr = pxnr;
	}

	public String getZhdj() {
		return zhdj;
	}

	public void setZhdj(String zhdj) {
		this.zhdj = zhdj;
	}

	public String getZhsj() {
		return zhsj;
	}

	public void setZhsj(String zhsj) {
		this.zhsj = zhsj;
	}

	public String[] getXxjl() {
		return xxjl;
	}

	public void setXxjl(String[] xxjl) {
		this.xxjl = xxjl;
	}

	public String[] getXxtd() {
		return xxtd;
	}

	public void setXxtd(String[] xxtd) {
		this.xxtd = xxtd;
	}

	public String[] getXxxg() {
		return xxxg;
	}

	public void setXxxg(String[] xxxg) {
		this.xxxg = xxxg;
	}

	public String[] getRtrq() {
		return rtrq;
	}

	public void setRtrq(String[] rtrq) {
		this.rtrq = rtrq;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	
	public String getZcsj() {
		return zcsj;
	}

	public void setZcsj(String zcsj) {
		this.zcsj = zcsj;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isBzrQx() {
		return bzrQx;
	}

	public void setBzrQx(boolean bzrQx) {
		this.bzrQx = bzrQx;
	}

	public boolean isFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(boolean fdyQx) {
		this.fdyQx = fdyQx;
	}

}
