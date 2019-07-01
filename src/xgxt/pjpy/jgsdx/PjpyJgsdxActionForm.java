
package xgxt.pjpy.jgsdx;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学评奖评优ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-026</p>
 */
public class PjpyJgsdxActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xn;//学年
	private String nd;//年度
	private String xq;//学期
	private String nj;//年级
	private String jxjdm;//奖学金代码
	private String jlje;//奖励金额
	private String bjdm;//班级代码
	private String zydm;//专业代码
	private String xydm;//学院代码
	private String xm;//姓名
	private String zhszpm;//综合素质排名
	private String xxcjpm;//学习成绩排名
	private String jxjjb;//奖学金级别
	private String bz;//备注
	private String fdysh;//辅导员审核
	private String xysh;//学院审核
	private String xxsh;//学校审核
	private String fdyyj;//辅导员意见
	private String xyyj;//学院意见
	private String xxyj;//学校意见
	private String sfsf;;//是否师范
	private String xh;//学号
	private String pkValue;//主键
	private String[] cbv;//主键列表
	private String shzt;//审核主题
	private String bj;//班级
	private String bzxm;//班长姓名
	private String xsrs;//学生人数
	private String bzr;//班主任
	private String zysj;//先进事迹
	private String shxm;
	private String shyj;//审核意见
	private String xmdm;//荣誉称号代码
	private String rychdm;//荣誉称号代码
	private String rychmc;//荣誉称号名称
	private String yesNo;//审核
	private String zdm;//字段名
	private String zdcz;//字段操作
	private String ysf;//
	private String val;//值
	private String sfwj;//是否违纪

	public String getSfwj() {
		return sfwj;
	}
	public void setSfwj(String sfwj) {
		this.sfwj = sfwj;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getYsf() {
		return ysf;
	}
	public void setYsf(String ysf) {
		this.ysf = ysf;
	}
	public String getZdcz() {
		return zdcz;
	}
	public void setZdcz(String zdcz) {
		this.zdcz = zdcz;
	}
	public String getZdm() {
		return zdm;
	}
	public void setZdm(String zdm) {
		this.zdm = zdm;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShxm() {
		return shxm;
	}
	public void setShxm(String shxm) {
		this.shxm = shxm;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSfsf() {
		return sfsf;
	}
	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getFdysh() {
		return fdysh;
	}
	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
	public String getJlje() {
		return jlje;
	}
	public void setJlje(String jlje) {
		this.jlje = jlje;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getJxjjb() {
		return jxjjb;
	}
	public void setJxjjb(String jxjjb) {
		this.jxjjb = jxjjb;
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
	public String getXxcjpm() {
		return xxcjpm;
	}
	public void setXxcjpm(String xxcjpm) {
		this.xxcjpm = xxcjpm;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxyj() {
		return xxyj;
	}
	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getXyyj() {
		return xyyj;
	}
	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}
	public String getZhszpm() {
		return zhszpm;
	}
	public void setZhszpm(String zhszpm) {
		this.zhszpm = zhszpm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getBzr() {
		return bzr;
	}
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	public String getBzxm() {
		return bzxm;
	}
	public void setBzxm(String bzxm) {
		this.bzxm = bzxm;
	}
	public String getXsrs() {
		return xsrs;
	}
	public void setXsrs(String xsrs) {
		this.xsrs = xsrs;
	}
	public String getZysj() {
		return zysj;
	}
	public void setZysj(String zysj) {
		this.zysj = zysj;
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
}
