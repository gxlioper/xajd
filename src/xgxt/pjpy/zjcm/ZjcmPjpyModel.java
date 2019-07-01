package xgxt.pjpy.zjcm;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class ZjcmPjpyModel {

	/* 通用 */
	Pages pages = new Pages();

	FormFile uploadFile;// 上传文件

	private String tableName;// 操作表
	
	private String fdyQx;// 辅导员权限

	private String bzrQx;// 班主任权限

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

	private String xqdm;// 校区代码

	private String lddm;// 楼栋代码

	private String cs;// 层数

	private String qsh;// 寝室号
	
	/* 评奖评优 测评小组 */
	private String[] cpxy;// 测评学院

	private String[] zwdm;// 职务代码

	private String zhfkg;// 综合分开关

	private String jxjkg;// 奖学金开关

	/* 评奖评优 智育分 */
	private String pycc;// 培养层次

	private String[] xfjdxh;// 同步者学号

	private String[] xfjd;// 学分绩点

	/* 评奖评优 综合分 */
	private String dyfbl;// 德育分比例

	private String zyfbl;// 智育分比例

	private String tyfbl;// 体育分比例

	private String nlfbl;// 能力分比例

	private String[] pjxh;// 评奖学号

	private String[] dyf;// 德育分

	private String[] zyf;// 智育分

	private String[] tyf;// 体育分

	private String[] nlf;// 能力分

	/* 评奖评优 文理科设置 */
	private String[] wlkbjdm;// 文理科班级代码

	private String bjlx;// 班级类型

	/* 评奖评优 条件设置 */
	private String jxjdm;// 奖学金代码

	private String jxjlbdm;// 奖学金类别代码

	private String rychdm;// 荣誉称号代码

	private String tjzd;// 条件字段

	private String tjlx;// 条件类型

	private String tjz;// 条件值

	/* 评奖评优 兼得设置 */
	private String[] jxjjd;// 奖学金兼得

	private String[] rychjd;// 荣誉称号兼得

	private String[] fjdlx;// 非兼得类型

	private String[] fjddm;// 非兼得代码

	/* 评奖评优 校外奖学金 */
	private String xwjxjdm;// 校外奖学金代码

	/* 评奖评优 报表统计 */
	private String yhdm;// 银行代码
	
	private String yhlx;// 银行类型

	private String yhmc;// 银行名称
	
	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjlx() {
		return bjlx;
	}

	public void setBjlx(String bjlx) {
		this.bjlx = bjlx;
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

	public String getBzrQx() {
		return bzrQx;
	}

	public void setBzrQx(String bzrQx) {
		this.bzrQx = bzrQx;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getCpxy() {
		return cpxy;
	}

	public void setCpxy(String[] cpxy) {
		this.cpxy = cpxy;
	}

	public String[] getDyf() {
		return dyf;
	}

	public void setDyf(String[] dyf) {
		this.dyf = dyf;
	}

	public String getDyfbl() {
		return dyfbl;
	}

	public void setDyfbl(String dyfbl) {
		this.dyfbl = dyfbl;
	}

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
	}

	public String[] getFjddm() {
		return fjddm;
	}

	public void setFjddm(String[] fjddm) {
		this.fjddm = fjddm;
	}

	public String[] getFjdlx() {
		return fjdlx;
	}

	public void setFjdlx(String[] fjdlx) {
		this.fjdlx = fjdlx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJxjdm() {
		return jxjdm;
	}

	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}

	public String[] getJxjjd() {
		return jxjjd;
	}

	public void setJxjjd(String[] jxjjd) {
		this.jxjjd = jxjjd;
	}

	public String getJxjkg() {
		return jxjkg;
	}

	public void setJxjkg(String jxjkg) {
		this.jxjkg = jxjkg;
	}

	public String getJxjlbdm() {
		return jxjlbdm;
	}

	public void setJxjlbdm(String jxjlbdm) {
		this.jxjlbdm = jxjlbdm;
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

	public String[] getNlf() {
		return nlf;
	}

	public void setNlf(String[] nlf) {
		this.nlf = nlf;
	}

	public String getNlfbl() {
		return nlfbl;
	}

	public void setNlfbl(String nlfbl) {
		this.nlfbl = nlfbl;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getPjxh() {
		return pjxh;
	}

	public void setPjxh(String[] pjxh) {
		this.pjxh = pjxh;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getRychdm() {
		return rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String[] getRychjd() {
		return rychjd;
	}

	public void setRychjd(String[] rychjd) {
		this.rychjd = rychjd;
	}

	public String getTjlx() {
		return tjlx;
	}

	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}

	public String getTjz() {
		return tjz;
	}

	public void setTjz(String tjz) {
		this.tjz = tjz;
	}

	public String getTjzd() {
		return tjzd;
	}

	public void setTjzd(String tjzd) {
		this.tjzd = tjzd;
	}

	public String[] getTyf() {
		return tyf;
	}

	public void setTyf(String[] tyf) {
		this.tyf = tyf;
	}

	public String getTyfbl() {
		return tyfbl;
	}

	public void setTyfbl(String tyfbl) {
		this.tyfbl = tyfbl;
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

	public String[] getWlkbjdm() {
		return wlkbjdm;
	}

	public void setWlkbjdm(String[] wlkbjdm) {
		this.wlkbjdm = wlkbjdm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String[] getXfjd() {
		return xfjd;
	}

	public void setXfjd(String[] xfjd) {
		this.xfjd = xfjd;
	}

	public String[] getXfjdxh() {
		return xfjdxh;
	}

	public void setXfjdxh(String[] xfjdxh) {
		this.xfjdxh = xfjdxh;
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

	public String getXwjxjdm() {
		return xwjxjdm;
	}

	public void setXwjxjdm(String xwjxjdm) {
		this.xwjxjdm = xwjxjdm;
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

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getZhfkg() {
		return zhfkg;
	}

	public void setZhfkg(String zhfkg) {
		this.zhfkg = zhfkg;
	}

	public String[] getZwdm() {
		return zwdm;
	}

	public void setZwdm(String[] zwdm) {
		this.zwdm = zwdm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getZyf() {
		return zyf;
	}

	public void setZyf(String[] zyf) {
		this.zyf = zyf;
	}

	public String getZyfbl() {
		return zyfbl;
	}

	public void setZyfbl(String zyfbl) {
		this.zyfbl = zyfbl;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
