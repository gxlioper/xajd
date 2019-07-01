package xgxt.rcgl.zjjj.qjgl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class ZjjjQjglForm extends ActionForm{
	Pages pages = new Pages();
	private String sh2 = "未审核"; // 二级辅导员审核
	private String shsj5; // 分管院长审核时间
	private String qjts; // 请假天数
	private String shr2; // 二级辅导员签字
	private String shsj2; // 二级辅导员审核时间
	private String sh5 = "未审核"; // 分管院长审核
	private String shsj6; // 1109室审核时间
	private String xh; // 学号
	private String sh3 = "未审核"; // 党总支书记审核
	private String bz; // 备注
	private String sh1 = "未审核"; // 班主任审核
	private String shr1; // 班主任签字
	private String shr6; // 1109室签字
	private String qjjssj; // 请假结束时间
	private String qjsy; // 请假事由
	private String shsj3; // 党总支书记审核时间
	private String sfzx; // 请假请假是否住校
	private String shr3; // 党总支书记签字
	private String sh4 = "未审核"; // 学生处分管处长审核
	private String shsj4; // 学生处分管处长审核时间
	private String shr5; // 分管院长签字
	private String sh6 = "未审核"; // 1109室审核
	private String qjkssj; // 请假开始时间
	private String shsj1; // 班主任审核时间
	private String shr4; // 学生处分管处长签字
	private String xn;
	private String xq;
	private String xydm;
	private String zydm;
	private String bjdm;
	private String xm;
	private String jzdh;
	private String ldmc;//楼栋名称
	private String lddm;//楼栋代码

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
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

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSh2() {
		return sh2;
	}

	public void setSh2(String sh2) {
		this.sh2 = sh2;
	}

	public String getShsj5() {
		return shsj5;
	}

	public void setShsj5(String shsj5) {
		this.shsj5 = shsj5;
	}

	public String getQjts() {
		return qjts;
	}

	public void setQjts(String qjts) {
		this.qjts = qjts;
	}

	public String getShr2() {
		return shr2;
	}

	public void setShr2(String shr2) {
		this.shr2 = shr2;
	}

	public String getShsj2() {
		return shsj2;
	}

	public void setShsj2(String shsj2) {
		this.shsj2 = shsj2;
	}

	public String getSh5() {
		return sh5;
	}

	public void setSh5(String sh5) {
		this.sh5 = sh5;
	}

	public String getShsj6() {
		return shsj6;
	}

	public void setShsj6(String shsj6) {
		this.shsj6 = shsj6;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSh3() {
		return sh3;
	}

	public void setSh3(String sh3) {
		this.sh3 = sh3;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSh1() {
		return sh1;
	}

	public void setSh1(String sh1) {
		this.sh1 = sh1;
	}

	public String getShr1() {
		return shr1;
	}

	public void setShr1(String shr1) {
		this.shr1 = shr1;
	}

	public String getShr6() {
		return shr6;
	}

	public void setShr6(String shr6) {
		this.shr6 = shr6;
	}

	public String getQjjssj() {
		return qjjssj;
	}

	public void setQjjssj(String qjjssj) {
		this.qjjssj = qjjssj;
	}

	public String getQjsy() {
		return qjsy;
	}

	public void setQjsy(String qjsy) {
		this.qjsy = qjsy;
	}

	public String getShsj3() {
		return shsj3;
	}

	public void setShsj3(String shsj3) {
		this.shsj3 = shsj3;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getShr3() {
		return shr3;
	}

	public void setShr3(String shr3) {
		this.shr3 = shr3;
	}

	public String getSh4() {
		return sh4;
	}

	public void setSh4(String sh4) {
		this.sh4 = sh4;
	}

	public String getShsj4() {
		return shsj4;
	}

	public void setShsj4(String shsj4) {
		this.shsj4 = shsj4;
	}

	public String getShr5() {
		return shr5;
	}

	public void setShr5(String shr5) {
		this.shr5 = shr5;
	}

	public String getSh6() {
		return sh6;
	}

	public void setSh6(String sh6) {
		this.sh6 = sh6;
	}

	public String getQjkssj() {
		return qjkssj;
	}

	public void setQjkssj(String qjkssj) {
		this.qjkssj = qjkssj;
	}

	public String getShsj1() {
		return shsj1;
	}

	public void setShsj1(String shsj1) {
		this.shsj1 = shsj1;
	}

	public String getShr4() {
		return shr4;
	}

	public void setShr4(String shr4) {
		this.shr4 = shr4;
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

	public String getJzdh() {
		return jzdh;
	}

	public void setJzdh(String jzdh) {
		this.jzdh = jzdh;
	}

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	
}
