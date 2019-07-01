
package xgxt.pjpy.csmz;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院评奖评优ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class PjpyCsmzActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String gnmk;//申请页面功能模块
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String bjmc;//班级名称
	private String zymc;//专业名称
	private String xymc;//学院名称
	private String nj;//年级
	private String xn;//学年
	private String nd;//年度
	private String xh;//学号
	private String xm;//姓名
	private String xq;//学期
	private String xmdm;//
	private String zdm;//组代码
	private String xb;//性别
	private String sjhm;//手机号码
	private String drzw;//担任职务
	private String jxjdm;//奖学金代码
	private String jxjmc;//奖学金名称
	private String jxjlb;//奖学金类别
	private String jlje;//奖励金额
	private String wysp;//外语水平
	private String xxjl;//基本情况简介
	private String jxj1;//奖学金1
	private String shyg1;//三好优干1
	private String ddj1;//德成线1
	private String bxkpjcj1;//必修课班级成绩1
	private String bjcjpx1;//班级成绩排序1
	private String zycjpx1;//专业成绩排序1
	private String tyhgbz1;//体育合格标准1
	private String jxj2;//奖学金2
	private String shyg2;//三好优干2
	private String ddj2;//德成线2
	private String bxkpjcj2;//必修课班级成绩2
	private String bjcjpx2;//班级成绩排序2
	private String zycjpx2;//专业成绩排序2
	private String tyhgbz2;//体育合格标准2
	private String jxj3;//奖学金3
	private String shyg3;//三好优干3
	private String ddj3;//德成线3
	private String bxkpjcj3;//必修课班级成绩3
	private String bjcjpx3;//班级成绩排序3
	private String zycjpx3;//专业成绩排序3
	private String tyhgbz3;//体育合格标准3
	private String jxj4;//奖学金4
	private String shyg4;//三好优干4
	private String ddj4;//德成线4
	private String bxkpjcj4;//必修课班级成绩4
	private String bjcjpx4;//班级成绩排序4
	private String zycjpx4;//专业成绩排序4
	private String tyhgbz4;//体育合格标准4
	private String[] cbv;//批量删除列表
	private String yesNo;//审核
	private String shjg;//审核结果
	private String shyj;//审核意见
	private String userType;//用户类型
	private String isFdy;//是否是辅导员
	private String jxjjb;//奖学金级别
	private String szjdbz;//设置绩点标准
	private String rychdm;//荣誉称号代码
	private String rychmc;//荣誉称号名称
	private String jxdm;//军训代码
	private String jxmc;//军训名称
	private String rychlb;//荣誉称号类别
	private String sztzxfbz;//素质拓展学分标准
	public String getSztzxfbz() {
		return sztzxfbz;
	}

	public void setSztzxfbz(String sztzxfbz) {
		this.sztzxfbz = sztzxfbz;
	}

	public String getRychlb() {
		return rychlb;
	}

	public void setRychlb(String rychlb) {
		this.rychlb = rychlb;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getYesNo() {
		return yesNo;
	}

	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}

	public String[] getCbv() {
		return cbv;
	}

	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getGnmk() {
		return gnmk;
	}

	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}

	public String getBjcjpx1() {
		return bjcjpx1;
	}

	public void setBjcjpx1(String bjcjpx1) {
		this.bjcjpx1 = bjcjpx1;
	}

	public String getBjcjpx2() {
		return bjcjpx2;
	}

	public void setBjcjpx2(String bjcjpx2) {
		this.bjcjpx2 = bjcjpx2;
	}

	public String getBjcjpx3() {
		return bjcjpx3;
	}

	public void setBjcjpx3(String bjcjpx3) {
		this.bjcjpx3 = bjcjpx3;
	}

	public String getBjcjpx4() {
		return bjcjpx4;
	}

	public void setBjcjpx4(String bjcjpx4) {
		this.bjcjpx4 = bjcjpx4;
	}

	public String getBxkpjcj1() {
		return bxkpjcj1;
	}

	public void setBxkpjcj1(String bxkpjcj1) {
		this.bxkpjcj1 = bxkpjcj1;
	}

	public String getBxkpjcj2() {
		return bxkpjcj2;
	}

	public void setBxkpjcj2(String bxkpjcj2) {
		this.bxkpjcj2 = bxkpjcj2;
	}

	public String getBxkpjcj3() {
		return bxkpjcj3;
	}

	public void setBxkpjcj3(String bxkpjcj3) {
		this.bxkpjcj3 = bxkpjcj3;
	}

	public String getBxkpjcj4() {
		return bxkpjcj4;
	}

	public void setBxkpjcj4(String bxkpjcj4) {
		this.bxkpjcj4 = bxkpjcj4;
	}

	public String getDdj1() {
		return ddj1;
	}

	public void setDdj1(String ddj1) {
		this.ddj1 = ddj1;
	}

	public String getDdj2() {
		return ddj2;
	}

	public void setDdj2(String ddj2) {
		this.ddj2 = ddj2;
	}

	public String getDdj3() {
		return ddj3;
	}

	public void setDdj3(String ddj3) {
		this.ddj3 = ddj3;
	}

	public String getDdj4() {
		return ddj4;
	}

	public void setDdj4(String ddj4) {
		this.ddj4 = ddj4;
	}

	public String getDrzw() {
		return drzw;
	}

	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}

	public String getJlje() {
		return jlje;
	}

	public void setJlje(String jlje) {
		this.jlje = jlje;
	}

	public String getJxj1() {
		return jxj1;
	}

	public void setJxj1(String jxj1) {
		this.jxj1 = jxj1;
	}

	public String getJxj2() {
		return jxj2;
	}

	public void setJxj2(String jxj2) {
		this.jxj2 = jxj2;
	}

	public String getJxj3() {
		return jxj3;
	}

	public void setJxj3(String jxj3) {
		this.jxj3 = jxj3;
	}

	public String getJxj4() {
		return jxj4;
	}

	public void setJxj4(String jxj4) {
		this.jxj4 = jxj4;
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

	public String getJxjmc() {
		return jxjmc;
	}

	public void setJxjmc(String jxjmc) {
		this.jxjmc = jxjmc;
	}

	public String getShyg1() {
		return shyg1;
	}

	public void setShyg1(String shyg1) {
		this.shyg1 = shyg1;
	}

	public String getShyg2() {
		return shyg2;
	}

	public void setShyg2(String shyg2) {
		this.shyg2 = shyg2;
	}

	public String getShyg3() {
		return shyg3;
	}

	public void setShyg3(String shyg3) {
		this.shyg3 = shyg3;
	}

	public String getShyg4() {
		return shyg4;
	}

	public void setShyg4(String shyg4) {
		this.shyg4 = shyg4;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getTyhgbz1() {
		return tyhgbz1;
	}

	public void setTyhgbz1(String tyhgbz1) {
		this.tyhgbz1 = tyhgbz1;
	}

	public String getTyhgbz2() {
		return tyhgbz2;
	}

	public void setTyhgbz2(String tyhgbz2) {
		this.tyhgbz2 = tyhgbz2;
	}

	public String getTyhgbz3() {
		return tyhgbz3;
	}

	public void setTyhgbz3(String tyhgbz3) {
		this.tyhgbz3 = tyhgbz3;
	}

	public String getTyhgbz4() {
		return tyhgbz4;
	}

	public void setTyhgbz4(String tyhgbz4) {
		this.tyhgbz4 = tyhgbz4;
	}

	public String getWysp() {
		return wysp;
	}

	public void setWysp(String wysp) {
		this.wysp = wysp;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXxjl() {
		return xxjl;
	}

	public void setXxjl(String xxjl) {
		this.xxjl = xxjl;
	}

	public String getZycjpx1() {
		return zycjpx1;
	}

	public void setZycjpx1(String zycjpx1) {
		this.zycjpx1 = zycjpx1;
	}

	public String getZycjpx2() {
		return zycjpx2;
	}

	public void setZycjpx2(String zycjpx2) {
		this.zycjpx2 = zycjpx2;
	}

	public String getZycjpx3() {
		return zycjpx3;
	}

	public void setZycjpx3(String zycjpx3) {
		this.zycjpx3 = zycjpx3;
	}

	public String getZycjpx4() {
		return zycjpx4;
	}

	public void setZycjpx4(String zycjpx4) {
		this.zycjpx4 = zycjpx4;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getIsFdy() {
		return isFdy;
	}

	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getJxdm() {
		return jxdm;
	}

	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}

	public String getJxjjb() {
		return jxjjb;
	}

	public void setJxjjb(String jxjjb) {
		this.jxjjb = jxjjb;
	}

	public String getJxmc() {
		return jxmc;
	}

	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}

	public String getRychdm() {
		return rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String getRychmc() {
		return rychmc;
	}

	public void setRychmc(String rychmc) {
		this.rychmc = rychmc;
	}

	public String getSzjdbz() {
		return szjdbz;
	}

	public void setSzjdbz(String szjdbz) {
		this.szjdbz = szjdbz;
	}
}
