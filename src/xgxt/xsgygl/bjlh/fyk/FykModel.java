package xgxt.xsgygl.bjlh.fyk;

import xgxt.utils.Pages;

public class FykModel {
	
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

	private String lddm;// 楼栋代码	

	private String xqdm;// '校区代码';

	private String cs;// '层数';

	private String qsh;// '寝室号';

	private String lx;// '类型';
	
	private String fbbj;// '发布标记';

	private String bblx;// 报表类型

	private String byny;// 毕业年月

	private String conditionSqlText;

	private String conditionSqlValue;

	private String oldCondiSqlValue;

	private String oracleItem;

	private String sql;
	
	private String fpbj;
	
	private String bysj;
	
	private String tjlx;
	
	private String zt;

	private String sclx;

	/* 数据维护 */

	private String[] sftws;// 是否团委生

	private String[] sftys;// 是否体优生

	private String sfbj;// 是否标记

	private String[] twsxh;// 团委生

	private String[] tysxh;// 体优生

	private String mz;// '民族';

	private String zzmm;// '政治面貌';

	private String csrq;// '出生日期';

	private String sg;// '身高';

	private String tz;// '体重';

	private String sfzh;// '身份证号';

	private String lxdh;// '联系电话';

	private String jg;// '籍贯';

	private String xz;// '学制';

	private String lydq;// '来源地区';

	private String rxrq;// '入学日期';

	private String cws;// '床位数';

	private String qsdh;// '寝室电话';

	private String sfbz;// '收费标准';

	private String cwh;// '床位号';

	private String rzrq;// '入住日期';

	private String tfrq;// '退房日期';

	private String zzbj;// '住宿标记';

	private String fplx;// '分配类型';
	
	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getCwh() {
		return cwh;
	}

	public void setCwh(String cwh) {
		this.cwh = cwh;
	}

	public String getCws() {
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getFplx() {
		return fplx;
	}

	public void setFplx(String fplx) {
		this.fplx = fplx;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getLydq() {
		return lydq;
	}

	public void setLydq(String lydq) {
		this.lydq = lydq;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getQsdh() {
		return qsdh;
	}

	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}

	public String getRxrq() {
		return rxrq;
	}

	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}

	public String getRzrq() {
		return rzrq;
	}

	public void setRzrq(String rzrq) {
		this.rzrq = rzrq;
	}

	public String getSfbj() {
		return sfbj;
	}

	public void setSfbj(String sfbj) {
		this.sfbj = sfbj;
	}

	public String getSfbz() {
		return sfbz;
	}

	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}

	public String[] getSftws() {
		return sftws;
	}

	public void setSftws(String[] sftws) {
		this.sftws = sftws;
	}

	public String[] getSftys() {
		return sftys;
	}

	public void setSftys(String[] sftys) {
		this.sftys = sftys;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getTfrq() {
		return tfrq;
	}

	public void setTfrq(String tfrq) {
		this.tfrq = tfrq;
	}

	public String[] getTwsxh() {
		return twsxh;
	}

	public void setTwsxh(String[] twsxh) {
		this.twsxh = twsxh;
	}

	public String[] getTysxh() {
		return tysxh;
	}

	public void setTysxh(String[] tysxh) {
		this.tysxh = tysxh;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getZzbj() {
		return zzbj;
	}

	public void setZzbj(String zzbj) {
		this.zzbj = zzbj;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
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

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getFbbj() {
		return fbbj;
	}

	public void setFbbj(String fbbj) {
		this.fbbj = fbbj;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
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

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
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

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
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

	public String getBblx() {
		return bblx;
	}

	public void setBblx(String bblx) {
		this.bblx = bblx;
	}

	public String getByny() {
		return byny;
	}

	public void setByny(String byny) {
		this.byny = byny;
	}

	public String getBysj() {
		return bysj;
	}

	public void setBysj(String bysj) {
		this.bysj = bysj;
	}

	public String getConditionSqlText() {
		return conditionSqlText;
	}

	public void setConditionSqlText(String conditionSqlText) {
		this.conditionSqlText = conditionSqlText;
	}

	public String getConditionSqlValue() {
		return conditionSqlValue;
	}

	public void setConditionSqlValue(String conditionSqlValue) {
		this.conditionSqlValue = conditionSqlValue;
	}

	public String getFpbj() {
		return fpbj;
	}

	public void setFpbj(String fpbj) {
		this.fpbj = fpbj;
	}

	public String getOldCondiSqlValue() {
		return oldCondiSqlValue;
	}

	public void setOldCondiSqlValue(String oldCondiSqlValue) {
		this.oldCondiSqlValue = oldCondiSqlValue;
	}

	public String getOracleItem() {
		return oracleItem;
	}

	public void setOracleItem(String oracleItem) {
		this.oracleItem = oracleItem;
	}

	public String getSclx() {
		return sclx;
	}

	public void setSclx(String sclx) {
		this.sclx = sclx;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getTjlx() {
		return tjlx;
	}

	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
	
	
}
