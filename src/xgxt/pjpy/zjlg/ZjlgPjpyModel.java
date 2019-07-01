package xgxt.pjpy.zjlg;

import xgxt.utils.Pages;

public class ZjlgPjpyModel {

	// 学号
	private String xh;

	// 姓名
	private String xm;

	// 年级
	private String nj;

	// 性别
	private String xb;

	// 学院代码
	private String xydm;

	// 专业代码
	private String zydm;

	// 班级代码
	private String bjdm;

	// 学年
	private String xn;

	// 考勤分
	private String kqf;

	// 测评分
	private String cpf;

	// 年度
	private String nd;

	// 学期
	private String xq;

	// 平时分比例
	private String psfbl;

	// 卫生分比例
	private String wsfbl;

	// 考勤分比例
	private String kqfbl;
	
	// 是否需要比例设置
	private String isBl;

	// 自评分比例
	private String zpfbl;

	// 班级分比例
	private String bjfbl;

	// 自我评议分
	private String zwpyf;

	// 班级评议分
	private String bjpyf;

	// 学院附加分
	private String xyfjf;

	// 学院审核
	private String xysh;
	
	//	奖学金比例
	private String jxjbl;
	
	//奖学金人数
	private String jxjrs;
	
	//参评组代码
	private String cpzdm;
	
	//部门代码
	private String bmdm;
	
	//参评人数
	private String cprs;
	
	//部门类别
	private String bmlb;
	
	//设置比例
	private String szbl;
	
	// 是否走读生
	private String isZds;
	//奖学金代码
	private String jxjdm;

	// 德育比例
	private String dybl;

	// 智育比例
	private String zybl;

	//字段名
	private String zdm;
	
	//条件
	private String tj;

	//条件值
	private String val;
	
	private String csrq;
	//担任任务
	private String drzw;
	//学习经历
	private String xxjl;
	//辅导员意见
	private String fdyyj;
	//学院意见
	private String xyshyj;
	//学校意见
	private String xxshyj;
	//奖罚情况
	private String jfqk;
	//备注
	private String bz;
	//主要事迹
	private String zysj;
	//教育厅已将
	private String jytyj;
	//毕业去向
	private String byqx;
	//担任社会工作情况
	private String kycg;
	//奖学金类别
	private String jxjlb;
	//理事会审批意见
	private String lshshyj ;
	//申请理由
	private String sqly;
	//名额
	private String me;
	//荣誉称号代码
	private String rychdm;	
	//荣誉称号人数
	private String rychrs;
	//银行卡号
	private String yhkh;
	//银行类型 
	private String yhlx;
	
	private String zhszcpcjpm;
	private String dycj;
	private String zycj;
	private String tycj;
	private String sbdj;
	private String djchdjxj;
	private String nccz;
	private String yydjcj; //英语等级成绩
	private String jsjdjcy;//计算机等级成绩
	private String dyxnyxcj;//当前学年英语成绩
	//评奖方式
	private String [] njz;
	private String [] pjfsz;
	private String act;
	
	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getNccz() {
		return nccz;
	}

	public void setNccz(String nccz) {
		this.nccz = nccz;
	}

	public String getSbdj() {
		return sbdj;
	}

	public void setSbdj(String sbdj) {
		this.sbdj = sbdj;
	}

	public String getDjchdjxj() {
		return djchdjxj;
	}

	public void setDjchdjxj(String djchdjxj) {
		this.djchdjxj = djchdjxj;
	}

	Pages pages = new Pages();
	
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getZhszcpcjpm() {
		return zhszcpcjpm;
	}

	public void setZhszcpcjpm(String zhszcpcjpm) {
		this.zhszcpcjpm = zhszcpcjpm;
	}

	public String getDycj() {
		return dycj;
	}

	public void setDycj(String dycj) {
		this.dycj = dycj;
	}

	public String getZycj() {
		return zycj;
	}

	public void setZycj(String zycj) {
		this.zycj = zycj;
	}

	public String getTycj() {
		return tycj;
	}

	public void setTycj(String tycj) {
		this.tycj = tycj;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getRychdm() {
		return rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjfbl() {
		return bjfbl;
	}
	public void setBjfbl(String bjfbl) {
		this.bjfbl = bjfbl;
	}
	public String getBjpyf() {
		return bjpyf;
	}
	public void setBjpyf(String bjpyf) {
		this.bjpyf = bjpyf;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getBmlb() {
		return bmlb;
	}
	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}
	public String getByqx() {
		return byqx;
	}
	public void setByqx(String byqx) {
		this.byqx = byqx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCprs() {
		return cprs;
	}
	public void setCprs(String cprs) {
		this.cprs = cprs;
	}
	public String getCpzdm() {
		return cpzdm;
	}
	public void setCpzdm(String cpzdm) {
		this.cpzdm = cpzdm;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getDrzw() {
		return drzw;
	}
	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}
	public String getDybl() {
		return dybl;
	}
	public void setDybl(String dybl) {
		this.dybl = dybl;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
	public String getIsBl() {
		return isBl;
	}
	public void setIsBl(String isBl) {
		this.isBl = isBl;
	}
	public String getIsZds() {
		return isZds;
	}
	public void setIsZds(String isZds) {
		this.isZds = isZds;
	}
	public String getJfqk() {
		return jfqk;
	}
	public void setJfqk(String jfqk) {
		this.jfqk = jfqk;
	}
	public String getJxjbl() {
		return jxjbl;
	}
	public void setJxjbl(String jxjbl) {
		this.jxjbl = jxjbl;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getJxjlb() {
		return jxjlb;
	}
	public void setJxjlb(String jxjlb) {
		this.jxjlb = jxjlb;
	}
	public String getJxjrs() {
		return jxjrs;
	}
	public void setJxjrs(String jxjrs) {
		this.jxjrs = jxjrs;
	}
	public String getJytyj() {
		return jytyj;
	}
	public void setJytyj(String jytyj) {
		this.jytyj = jytyj;
	}
	public String getKqf() {
		return kqf;
	}
	public void setKqf(String kqf) {
		this.kqf = kqf;
	}
	public String getKqfbl() {
		return kqfbl;
	}
	public void setKqfbl(String kqfbl) {
		this.kqfbl = kqfbl;
	}
	public String getKycg() {
		return kycg;
	}
	public void setKycg(String kycg) {
		this.kycg = kycg;
	}
	public String getLshshyj() {
		return lshshyj;
	}
	public void setLshshyj(String lshshyj) {
		this.lshshyj = lshshyj;
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
	public String getPsfbl() {
		return psfbl;
	}
	public void setPsfbl(String psfbl) {
		this.psfbl = psfbl;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getSzbl() {
		return szbl;
	}
	public void setSzbl(String szbl) {
		this.szbl = szbl;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getWsfbl() {
		return wsfbl;
	}
	public void setWsfbl(String wsfbl) {
		this.wsfbl = wsfbl;
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
	public String getXxjl() {
		return xxjl;
	}
	public void setXxjl(String xxjl) {
		this.xxjl = xxjl;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXyfjf() {
		return xyfjf;
	}
	public void setXyfjf(String xyfjf) {
		this.xyfjf = xyfjf;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	public String getZpfbl() {
		return zpfbl;
	}
	public void setZpfbl(String zpfbl) {
		this.zpfbl = zpfbl;
	}
	public String getZwpyf() {
		return zwpyf;
	}
	public void setZwpyf(String zwpyf) {
		this.zwpyf = zwpyf;
	}
	public String getZybl() {
		return zybl;
	}
	public void setZybl(String zybl) {
		this.zybl = zybl;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZysj() {
		return zysj;
	}
	public void setZysj(String zysj) {
		this.zysj = zysj;
	}

	public String getBjdm() {
		return bjdm;
	}

	public String getRychrs() {
		return rychrs;
	}

	public void setRychrs(String rychrs) {
		this.rychrs = rychrs;
	}

	public String[] getPjfsz() {
		return pjfsz;
	}

	public void setPjfsz(String[] pjfsz) {
		this.pjfsz = pjfsz;
	}

	public String[] getNjz() {
		return njz;
	}

	public void setNjz(String[] njz) {
		this.njz = njz;
	}

	public String getYydjcj() {
		return yydjcj;
	}

	public void setYydjcj(String yydjcj) {
		this.yydjcj = yydjcj;
	}

	public String getJsjdjcy() {
		return jsjdjcy;
	}

	public void setJsjdjcy(String jsjdjcy) {
		this.jsjdjcy = jsjdjcy;
	}

	public String getDyxnyxcj() {
		return dyxnyxcj;
	}

	public void setDyxnyxcj(String dyxnyxcj) {
		this.dyxnyxcj = dyxnyxcj;
	}
	
}
