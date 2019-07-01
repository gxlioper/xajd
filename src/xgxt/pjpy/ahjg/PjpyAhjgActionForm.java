
package xgxt.pjpy.ahjg;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院评奖评优ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class PjpyAhjgActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String nj;//年级
	private String xn;//学年
	private String nd;//年度
	private String xh;//学号
	private String xm;//姓名
	private String xq;//学期
	private String gnmk;//功能模块
	private String bzxm;//班长姓名
	private String bzr;//班主任
	private String xsrs;//学生人数
	private String zysj;//主要事迹
	private String bj;//班级
	private String xmdm;
	private String zdm;
	private String bjbkl;//班级补考率
	private String[] cbv;//批量删除
	private String zccql;//早操出勤率
	private String rychdm;//荣誉称号代码
	private String shxm;//审核项目
	private String sh;//审核
	private String shyj;//审核意见
	private String jg;//结果
	private String pkValue;
	private String rychmc;//荣誉称号名称
	private String yesNo;
	private String xxjsdm;//学生竞赛代码
	private String xxjsmc;//学生竞赛
	private String hjsj;//获奖时间
	private String bz;//备注
	private String cjlx;//成绩类型
	private String djksdm;//等级考试代码
	private String cslx;//参数类型
	private String zdcz;//字段操作
	private String zdbj;//字段比较
	private String zdval;//字段值
	private String jxjdm;//奖学金代码
	private String tjdm;//条件字段名
	private String tjzdlyb;//条件字段来源表
	private String tj;//条件
	private String czmc;
	private String[] kcxz;// 课程性质
	private String djksmc;
	
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getCjlx() {
		return cjlx;
	}
	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}
	public String getDjksdm() {
		return djksdm;
	}
	public void setDjksdm(String djksdm) {
		this.djksdm = djksdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getHjsj() {
		return hjsj;
	}
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getRychmc() {
		return rychmc;
	}
	public void setRychmc(String rychmc) {
		this.rychmc = rychmc;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
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
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getZccql() {
		return zccql;
	}
	public void setZccql(String zccql) {
		this.zccql = zccql;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getBjbkl() {
		return bjbkl;
	}
	public void setBjbkl(String bjbkl) {
		this.bjbkl = bjbkl;
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
	public String getGnmk() {
		return gnmk;
	}
	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getXxjsdm() {
		return xxjsdm;
	}
	public void setXxjsdm(String xxjsdm) {
		this.xxjsdm = xxjsdm;
	}
	public String getXxjsmc() {
		return xxjsmc;
	}
	public void setXxjsmc(String xxjsmc) {
		this.xxjsmc = xxjsmc;
	}
	public String getCslx() {
		return cslx;
	}
	public void setCslx(String cslx) {
		this.cslx = cslx;
	}
	public String getTjdm() {
		return tjdm;
	}
	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}
	public String getZdbj() {
		return zdbj;
	}
	public void setZdbj(String zdbj) {
		this.zdbj = zdbj;
	}
	public String getZdcz() {
		return zdcz;
	}
	public void setZdcz(String zdcz) {
		this.zdcz = zdcz;
	}
	public String getZdval() {
		return zdval;
	}
	public void setZdval(String zdval) {
		this.zdval = zdval;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String getTjzdlyb() {
		return tjzdlyb;
	}
	public void setTjzdlyb(String tjzdlyb) {
		this.tjzdlyb = tjzdlyb;
	}

	public String getCzmc() {
		return czmc;
	}

	public void setCzmc(String czmc) {
		this.czmc = czmc;
	}

	public String[] getKcxz() {
		return kcxz;
	}

	public void setKcxz(String[] kcxz) {
		this.kcxz = kcxz;
	}

	public String getDjksmc() {
		return djksmc;
	}

	public void setDjksmc(String djksmc) {
		this.djksmc = djksmc;
	}
	
}
