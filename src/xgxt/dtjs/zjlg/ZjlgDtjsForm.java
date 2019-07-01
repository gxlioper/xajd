package xgxt.dtjs.zjlg;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class ZjlgDtjsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	private String nj;

	private String nd;

	private String xn;

	private String zydm;

	private String xydm;

	private String bjdm;

	private String zymc;

	private String xymc;

	private String bjmc;

	private String xq;

	private String xh;

	private String zjxh;

	private String xm;

	private String xb;

	private String zjsj;// '转接时间';

	private String ydw;// '有效期';

	private String dfjzyf;// '党费交至月份';

	private String jsxbh;// '介绍信编号';

	private String lxdh;// '联系电话';

	private String zjlx;// '转接类型';

	private String yxq;// '原单位';

	private String zjmm;// '转接政治面貌';

	private String bz;// '备注';

	private String zjdz;// '转接地址';

	private String zbdm; // 支部代码

	private String kssj;// 获得时间

	private String xsccdm;// 学生层次代码

	private String[] checkVal;

	private String[] zbcy; // 支部成员

	private String pxjssj;// 培训开始时间

	private String pxkssj;// 培训结束时间

	private String pxjg;// 培训结果

	private String pxxmdm;// 培训项目代码

	private String thcs;// '谈话次数';

	private String jssj;// '预备结束日期';

	private String lxr2;// 联系人2

	private String cjzzxxqk;// '参加政治学习情况';

	private String sfkyzz;// '是否可以转正';

	private String lxr1;// 联系人1

	private String dfjnqk;// '党费缴纳情况';

	private String kcqk;// '考察情况';

	private String zzsj;// '转正时间';

	private String cjhdqk;// '参加活动情况';

	private String zbshqk;// '支部生活情况';

	private String rdsj;// '入党时间';

	private String ybdykssj;// 预备党员开始时间

	private String ybdyjssj;// 预备党员结束时间

	private String zzxxqk;// '参加政治学习情况';

	private String zzzt;// '在职状态';

	private String zbmc;// '支部名称';

	private String ssxx;// '所属学院';

	private String rdlxr;// '入党联系人';

	private String lxrzb;// '联系人支部';

	private String lxrbj;// '联系人班级';

	private String lxrdh;// '联系人电话';

	private String djsqssj;// '递交入党申请书时间';

	private String sxhbqk;// '思想汇报情况';

	private String sfwj;// '是否违纪';

	private String zzmm;// '政治面貌';

	private String lx;// '类型';

	private String zgh;// '职工号';

	private String fdyxm;// '辅导员姓名';

	// 通用分页
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
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

	public String getZjdz() {
		return zjdz;
	}

	public void setZjdz(String zjdz) {
		this.zjdz = zjdz;
	}

	public String getZjlx() {
		return zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	public String getZjsj() {
		return zjsj;
	}

	public void setZjsj(String zjsj) {
		this.zjsj = zjsj;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
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

	public String getZbdm() {
		return zbdm;
	}

	public void setZbdm(String zbdm) {
		this.zbdm = zbdm;
	}

	public String[] getZbcy() {
		return zbcy;
	}

	public void setZbcy(String[] zbcy) {
		this.zbcy = zbcy;
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

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getXsccdm() {
		return xsccdm;
	}

	public void setXsccdm(String xsccdm) {
		this.xsccdm = xsccdm;
	}

	public String getCjzzxxqk() {
		return cjzzxxqk;
	}

	public void setCjzzxxqk(String cjzzxxqk) {
		this.cjzzxxqk = cjzzxxqk;
	}

	public String getDfjnqk() {
		return dfjnqk;
	}

	public void setDfjnqk(String dfjnqk) {
		this.dfjnqk = dfjnqk;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKcqk() {
		return kcqk;
	}

	public void setKcqk(String kcqk) {
		this.kcqk = kcqk;
	}

	public String getLxr1() {
		return lxr1;
	}

	public void setLxr1(String lxr1) {
		this.lxr1 = lxr1;
	}

	public String getLxr2() {
		return lxr2;
	}

	public void setLxr2(String lxr2) {
		this.lxr2 = lxr2;
	}

	public String getSfkyzz() {
		return sfkyzz;
	}

	public void setSfkyzz(String sfkyzz) {
		this.sfkyzz = sfkyzz;
	}

	public String getThcs() {
		return thcs;
	}

	public void setThcs(String thcs) {
		this.thcs = thcs;
	}

	public String getCjhdqk() {
		return cjhdqk;
	}

	public void setCjhdqk(String cjhdqk) {
		this.cjhdqk = cjhdqk;
	}

	public String getRdsj() {
		return rdsj;
	}

	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
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

	public String getZbshqk() {
		return zbshqk;
	}

	public void setZbshqk(String zbshqk) {
		this.zbshqk = zbshqk;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public String getZzxxqk() {
		return zzxxqk;
	}

	public void setZzxxqk(String zzxxqk) {
		this.zzxxqk = zzxxqk;
	}

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public String getDfjzyf() {
		return dfjzyf;
	}

	public void setDfjzyf(String dfjzyf) {
		this.dfjzyf = dfjzyf;
	}

	public String getJsxbh() {
		return jsxbh;
	}

	public void setJsxbh(String jsxbh) {
		this.jsxbh = jsxbh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getYdw() {
		return ydw;
	}

	public void setYdw(String ydw) {
		this.ydw = ydw;
	}

	public String getYxq() {
		return yxq;
	}

	public void setYxq(String yxq) {
		this.yxq = yxq;
	}

	public String getZjmm() {
		return zjmm;
	}

	public void setZjmm(String zjmm) {
		this.zjmm = zjmm;
	}

	public String getZbmc() {
		return zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}

	public String getSsxx() {
		return ssxx;
	}

	public void setSsxx(String ssxx) {
		this.ssxx = ssxx;
	}

	public String getDjsqssj() {
		return djsqssj;
	}

	public void setDjsqssj(String djsqssj) {
		this.djsqssj = djsqssj;
	}

	public String getLxrbj() {
		return lxrbj;
	}

	public void setLxrbj(String lxrbj) {
		this.lxrbj = lxrbj;
	}

	public String getLxrdh() {
		return lxrdh;
	}

	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}

	public String getLxrzb() {
		return lxrzb;
	}

	public void setLxrzb(String lxrzb) {
		this.lxrzb = lxrzb;
	}

	public String getRdlxr() {
		return rdlxr;
	}

	public void setRdlxr(String rdlxr) {
		this.rdlxr = rdlxr;
	}

	public String getSfwj() {
		return sfwj;
	}

	public void setSfwj(String sfwj) {
		this.sfwj = sfwj;
	}

	public String getSxhbqk() {
		return sxhbqk;
	}

	public void setSxhbqk(String sxhbqk) {
		this.sxhbqk = sxhbqk;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getZjxh() {
		return zjxh;
	}

	public void setZjxh(String zjxh) {
		this.zjxh = zjxh;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getFdyxm() {
		return fdyxm;
	}

	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

}
