package xgxt.rcsw.kqgl.xskq;

import xgxt.utils.Pages;

public class RcswKqglXskqModel {
	private String xysh;     //学院审核
	private String xxsh;     //学校审核
	private String zydm;     //专业代码
	private String xh;     //学号
	private String fdysh;     //辅导员审核
	private String xn;     //学年
	private String xq;     //学期
	private String bjdm;     //班级代码
	private String qjqssj;     //请假起始时间
	private String qjjssj;    //请假结束时间
	private String qjlx;     //请假理由
	private String sqsj;     //申请时间
	private String xyshyj;     //学院审核意见
	private String xydm;     //学院代码
	private String xxshyj;     //学校审核意见
	private String fdyshyj;     //辅导员审核意见
	private String xyshsj;     //学院审核时间
	private String xxshsj;     //学校审核时间
	private String fdyshsj;     //辅导员审核时间
	private String qjly;		//请假理由
	private String bz;			//备注
	private String nj;      //年级
	private String nd;         //年度
	private String xm;
	private String shzt;
	
	private String save_qjly;		//请假理由
	private String save_xh;     //学号
	private String save_sqsj;     //申请时间
	private String save_xysh;     //学院审核
	private String save_xn;     //学年
	private String save_xyshyj;     //学院审核意见
	private String save_fdyshsj;     //辅导员审核时间
	private String save_qjsj;     //请假时间
	private String save_xxshyj;     //学校审核意见
	private String save_xxsh;     //学校审核
	private String save_xxshsj;     //学校审核时间
	private String save_zydm;     //专业代码
	private String save_fdysh;     //辅导员审核
	private String save_xyshsj;     //学院审核时间
	private String save_xq;     //学期
	private String save_xydm;     //学院代码
	private String save_bjdm;     //班级代码
	private String save_fdyshyj;     //辅导员审核意见
	private String save_qjlx;     //请假理由
	private String save_bz;        //备注
	private String save_nj;      //年级
	private String save_nd;         //年度
	
	
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_xn;
	private String queryequals_xq;
	private String queryequals_bjdm;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_nj;
	private String queryequals_fdysh;
	private String queryequals_xxsh;
	private String queryequals_xysh;
	private String querygreaterethan_qjlx;
	private String querylessthan_qjlx;
	private String queryequals_shzt;
	Pages pages=new Pages();

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

	public String getFdyshsj() {
		return fdyshsj;
	}

	public void setFdyshsj(String fdyshsj) {
		this.fdyshsj = fdyshsj;
	}

	public String getFdyshyj() {
		return fdyshyj;
	}

	public void setFdyshyj(String fdyshyj) {
		this.fdyshyj = fdyshyj;
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

	public String getQjjssj() {
		return qjjssj;
	}

	public void setQjjssj(String qjjssj) {
		this.qjjssj = qjjssj;
	}

	public String getQjlx() {
		return qjlx;
	}

	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}

	public String getQjly() {
		return qjly;
	}

	public void setQjly(String qjly) {
		this.qjly = qjly;
	}

	public String getQjqssj() {
		return qjqssj;
	}

	public void setQjqssj(String qjqssj) {
		this.qjqssj = qjqssj;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_fdysh() {
		return queryequals_fdysh;
	}

	public void setQueryequals_fdysh(String queryequals_fdysh) {
		this.queryequals_fdysh = queryequals_fdysh;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getQueryequals_xq() {
		return queryequals_xq;
	}

	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQuerygreaterethan_qjlx() {
		return querygreaterethan_qjlx;
	}

	public void setQuerygreaterethan_qjlx(String querygreaterethan_qjlx) {
		this.querygreaterethan_qjlx = querygreaterethan_qjlx;
	}

	public String getQuerylessthan_qjlx() {
		return querylessthan_qjlx;
	}

	public void setQuerylessthan_qjlx(String querylessthan_qjlx) {
		this.querylessthan_qjlx = querylessthan_qjlx;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}

	public String getSave_bjdm() {
		return save_bjdm;
	}

	public void setSave_bjdm(String save_bjdm) {
		this.save_bjdm = save_bjdm;
	}

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}

	public String getSave_fdysh() {
		return save_fdysh;
	}

	public void setSave_fdysh(String save_fdysh) {
		this.save_fdysh = save_fdysh;
	}

	public String getSave_fdyshsj() {
		return save_fdyshsj;
	}

	public void setSave_fdyshsj(String save_fdyshsj) {
		this.save_fdyshsj = save_fdyshsj;
	}

	public String getSave_fdyshyj() {
		return save_fdyshyj;
	}

	public void setSave_fdyshyj(String save_fdyshyj) {
		this.save_fdyshyj = save_fdyshyj;
	}

	public String getSave_nd() {
		return save_nd;
	}

	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}

	public String getSave_nj() {
		return save_nj;
	}

	public void setSave_nj(String save_nj) {
		this.save_nj = save_nj;
	}

	public String getSave_qjlx() {
		return save_qjlx;
	}

	public void setSave_qjlx(String save_qjlx) {
		this.save_qjlx = save_qjlx;
	}

	public String getSave_qjly() {
		return save_qjly;
	}

	public void setSave_qjly(String save_qjly) {
		this.save_qjly = save_qjly;
	}

	public String getSave_qjsj() {
		return save_qjsj;
	}

	public void setSave_qjsj(String save_qjsj) {
		this.save_qjsj = save_qjsj;
	}

	public String getSave_sqsj() {
		return save_sqsj;
	}

	public void setSave_sqsj(String save_sqsj) {
		this.save_sqsj = save_sqsj;
	}

	public String getSave_xh() {
		return save_xh;
	}

	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}

	public String getSave_xn() {
		return save_xn;
	}

	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}

	public String getSave_xq() {
		return save_xq;
	}

	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}

	public String getSave_xxsh() {
		return save_xxsh;
	}

	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}

	public String getSave_xxshsj() {
		return save_xxshsj;
	}

	public void setSave_xxshsj(String save_xxshsj) {
		this.save_xxshsj = save_xxshsj;
	}

	public String getSave_xxshyj() {
		return save_xxshyj;
	}

	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}

	public String getSave_xydm() {
		return save_xydm;
	}

	public void setSave_xydm(String save_xydm) {
		this.save_xydm = save_xydm;
	}

	public String getSave_xysh() {
		return save_xysh;
	}

	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}

	public String getSave_xyshsj() {
		return save_xyshsj;
	}

	public void setSave_xyshsj(String save_xyshsj) {
		this.save_xyshsj = save_xyshsj;
	}

	public String getSave_xyshyj() {
		return save_xyshyj;
	}

	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}

	public String getSave_zydm() {
		return save_zydm;
	}

	public void setSave_zydm(String save_zydm) {
		this.save_zydm = save_zydm;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getQueryequals_shzt() {
		return queryequals_shzt;
	}

	public void setQueryequals_shzt(String queryequals_shzt) {
		this.queryequals_shzt = queryequals_shzt;
	}
}
