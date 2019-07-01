package xgxt.pjpy.zjcm;

import xgxt.pjpy.PjpyActionForm;
import xgxt.utils.Pages;

public class PjpyZjcmActionForm extends PjpyActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478850990009121554L;

	private String szlx;

	private String cj;

	private String dybl;

	private String tybl;

	private String zybl;

	private String sjcxbl;

	private String cxbl;

	private String[] zw;

	private String nj;

	private String nd;

	private String xn;

	private String zydm;

	private String xydm;

	private String bjdm;

	private String xq;

	private String xh;

	private String xm;

	private String xb;

	private String lrkssj;

	private String lrjssj;

	private String sbkssj;

	private String sbjssj;

	// 德育分
	private String[] dyf;

	// 智育分
	private String[] zyf;

	// 体育分
	private String[] tyf;

	// 能力分
	private String[] nlf;

	private String dyfbl;

	private String zyfbl;

	private String tyfbl;

	private String nlfbl;

	// 综合分
	private String[] zhf;

	private String[] xhV;

	private String[] xnV;

	private String[] xqV;

	private String pm;// 综合分班级排名;

	private String jxjdm;// 奖学金代码;

	private String xwjxj;// 奖学金代码;
	
	private String jxjlb;// 奖学金类别;

	private String xwlb;// 奖学金类别;
	
	private String sbsj;// 申报时间;

	private String hgjjxjqk;// 获国家奖学金情况;

	private String bjgkms;// 不及格科目数;

	private String yygjqk;// 英语过级情况;

	private String jsjgjqk;// 计算机过级情况;

	private String tqxf;// 拖欠学费;

	private String fdysh;// 辅导员审核;

	private String xysh;// 学院审核;

	private String xxsh;// 学校审核;

	private String fdyshsj;// 辅导员审核时间;

	private String xyshsj;// 学院审核时间;

	private String xxshsj;// 学校审核时间;

	private String fdyyj;// 辅导员审核意见;

	private String xyyj;// 学院审核意见;

	private String xxyj;// 学校审核意见;

	private String zdm;// 字段名

	private String tj;// 条件;

	private String tjzd;// 条件字段;

	private String tjmc;// 条件名称;

	private String tjlx;// 条件类型;

	private String tjz;// 条件值;

	private String bblx;//报表类型
	
	private String[] checkVal;
	
	private String xqdm;
	
	private String rychdm;
	
	private String zysj;
	
	private String xyshyj;
	
	private String xxshyj;
	
	private String bz;
	
	private String zdlcql;
	
	private String sqsj;
	
	private String key;
	
	private String cpfw;
	
	private String fs;
	
	private String jxjbl;

	private String jxjrs;
	private String sh;
	private String yj;
	
	// 通用分页
	Pages pages = new Pages();

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String[] getDyf() {
		return dyf;
	}

	public void setDyf(String[] dyf) {
		this.dyf = dyf;
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

	public String[] getTyf() {
		return tyf;
	}

	public void setTyf(String[] tyf) {
		this.tyf = tyf;
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

	public String[] getZhf() {
		return zhf;
	}

	public void setZhf(String[] zhf) {
		this.zhf = zhf;
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

	public String[] getZw() {
		return zw;
	}

	public void setZw(String[] zw) {
		this.zw = zw;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getCj() {
		return cj;
	}

	public void setCj(String cj) {
		this.cj = cj;
	}

	public String getSzlx() {
		return szlx;
	}

	public void setSzlx(String szlx) {
		this.szlx = szlx;
	}

	public String getDybl() {
		return dybl;
	}

	public void setDybl(String dybl) {
		this.dybl = dybl;
	}

	public String getSjcxbl() {
		return sjcxbl;
	}

	public void setSjcxbl(String sjcxbl) {
		this.sjcxbl = sjcxbl;
	}

	public String getTybl() {
		return tybl;
	}

	public void setTybl(String tybl) {
		this.tybl = tybl;
	}

	public String getZybl() {
		return zybl;
	}

	public void setZybl(String zybl) {
		this.zybl = zybl;
	}

	public String getCxbl() {
		return cxbl;
	}

	public void setCxbl(String cxbl) {
		this.cxbl = cxbl;
	}

	public String[] getXhV() {
		return xhV;
	}

	public void setXhV(String[] xhV) {
		this.xhV = xhV;
	}

	public String[] getXnV() {
		return xnV;
	}

	public void setXnV(String[] xnV) {
		this.xnV = xnV;
	}

	public String[] getXqV() {
		return xqV;
	}

	public void setXqV(String[] xqV) {
		this.xqV = xqV;
	}

	public String getLrjssj() {
		return lrjssj;
	}

	public void setLrjssj(String lrjssj) {
		this.lrjssj = lrjssj;
	}

	public String getLrkssj() {
		return lrkssj;
	}

	public void setLrkssj(String lrkssj) {
		this.lrkssj = lrkssj;
	}

	public String getSbjssj() {
		return sbjssj;
	}

	public void setSbjssj(String sbjssj) {
		this.sbjssj = sbjssj;
	}

	public String getSbkssj() {
		return sbkssj;
	}

	public void setSbkssj(String sbkssj) {
		this.sbkssj = sbkssj;
	}

	public String getDyfbl() {
		return dyfbl;
	}

	public void setDyfbl(String dyfbl) {
		this.dyfbl = dyfbl;
	}

	public String getNlfbl() {
		return nlfbl;
	}

	public void setNlfbl(String nlfbl) {
		this.nlfbl = nlfbl;
	}

	public String getTyfbl() {
		return tyfbl;
	}

	public void setTyfbl(String tyfbl) {
		this.tyfbl = tyfbl;
	}

	public String getZyfbl() {
		return zyfbl;
	}

	public void setZyfbl(String zyfbl) {
		this.zyfbl = zyfbl;
	}

	public String getBjgkms() {
		return bjgkms;
	}

	public void setBjgkms(String bjgkms) {
		this.bjgkms = bjgkms;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}

	public String getFdyshsj() {
		return fdyshsj;
	}

	public void setFdyshsj(String fdyshsj) {
		this.fdyshsj = fdyshsj;
	}

	public String getFdyyj() {
		return fdyyj;
	}

	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}

	public String getHgjjxjqk() {
		return hgjjxjqk;
	}

	public void setHgjjxjqk(String hgjjxjqk) {
		this.hgjjxjqk = hgjjxjqk;
	}

	public String getJsjgjqk() {
		return jsjgjqk;
	}

	public void setJsjgjqk(String jsjgjqk) {
		this.jsjgjqk = jsjgjqk;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getTqxf() {
		return tqxf;
	}

	public void setTqxf(String tqxf) {
		this.tqxf = tqxf;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String getYygjqk() {
		return yygjqk;
	}

	public void setYygjqk(String yygjqk) {
		this.yygjqk = yygjqk;
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

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getTjlx() {
		return tjlx;
	}

	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}

	public String getTjmc() {
		return tjmc;
	}

	public void setTjmc(String tjmc) {
		this.tjmc = tjmc;
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

	public String getXwjxj() {
		return xwjxj;
	}

	public void setXwjxj(String xwjxj) {
		this.xwjxj = xwjxj;
	}

	public String getXwlb() {
		return xwlb;
	}

	public void setXwlb(String xwlb) {
		this.xwlb = xwlb;
	}

	public String getBblx() {
		return bblx;
	}

	public void setBblx(String bblx) {
		this.bblx = bblx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getRychdm() {
		return rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String getXxshyj() {
		return xxshyj;
	}

	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getZdlcql() {
		return zdlcql;
	}

	public void setZdlcql(String zdlcql) {
		this.zdlcql = zdlcql;
	}

	public String getZysj() {
		return zysj;
	}

	public void setZysj(String zysj) {
		this.zysj = zysj;
	}

	public String getCpfw() {
		return cpfw;
	}

	public void setCpfw(String cpfw) {
		this.cpfw = cpfw;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public String getJxjbl() {
		return jxjbl;
	}

	public void setJxjbl(String jxjbl) {
		this.jxjbl = jxjbl;
	}

	public String getJxjrs() {
		return jxjrs;
	}

	public void setJxjrs(String jxjrs) {
		this.jxjrs = jxjrs;
	}

	public String getSh() {
		return sh;
	}

	public void setSh(String sh) {
		this.sh = sh;
	}

	public String getYj() {
		return yj;
	}

	public void setYj(String yj) {
		this.yj = yj;
	}
}
