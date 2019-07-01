package xgxt.pjpy.ghxy.ryjf;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * Title: 学生工作管理系统
 * Description:评奖评优荣誉加分Form
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-05
 */
public class PjpyRyjfForm extends ActionForm{

	private static final long serialVersionUID = 5842979679117871663L;
	
	private Pages pages = new Pages();
	private String xh;     //学号
	private String id;     //主键
	private String xn;     //学年
	private String xq;     //学期
	
	//==========================个人获奖======================================
	private String[] grhjf;     //个人获奖分
	private String[] hjsy;     //获奖事由
	private String[] hjsj;     //获奖时间
	private String[] hjmc;     //获奖名称
	private String[] pkValues; // 批量主键
	
	private String xxsh = "未审核";     //学校审核
	private String ryjf = "0";     //荣誉加分
	private String fdysh = "未审核";     //辅导员审核
	private String njbzrsh = "未审核" ;     //年级部主任审核
	private String fdyshsj;     //辅导员审核时间
	private String njbzrshsj;     //年级部主任审核时间
	private String xxshsj;     //学校审核时间
	//===========================个人获奖 end==================================
	
	//===========================通用模块======================================
	private String querylike_xm;			//查询_姓名
	private String queryequals_nj;			//查询_年级
	private String queryequals_xn;         //查询_学年
	private String queryequals_xq;         //查询_学期
	private String querylike_xh;			//查询_学号
	private String queryequals_xydm;		//查询_学院
	private String queryequals_zydm;		//查询_专业
	private String queryequals_bjdm;		//查询_班级
	
	private String save_njbzrsh;     //年级部主任审核
	private String save_xxsh;     //学校审核
	private String save_xh;     //学号
	private String save_fdysh;     //辅导员审核
	private String save_xn;     //学年
	private String save_xq;     //学期
	private String save_njbzrshsj;     //年级部主任审核时间
	private String save_fdyshsj;     //辅导员审核时间
	private String save_xxshsj;     //学校审核时间
	private String save_bzdj;
	private String save_bzf;
	private String save_yd;
	//========================================================================
	
	//===========================班级表彰======================================
	private String xm;     //姓名
	private String yd;     //月度
	private String xb;     //性别
	private String[] bzdj;     //表彰等级
	private String[] bzf;     //表彰
	
	private String queryequals_yd;
	//===========================班级表彰 end==================================
	
	//===========================参数设置=====================================
	private String[] dj;     //表彰等级
	private String[] bl;     //比率
	private String[] jf;     //加分
	//===========================参数设置 end=================================
	
	//===========================荣誉减分====================================
	private String queryequals_xqdm = "";      // 校区代码
	private String queryequals_lddm = "";	  // 楼栋代码
	private String queryequals_cs = "";		  // 楼层
	private String queryequals_qsh = "";       // 寝室号
	
	private String save_bz;
	private String save_sj;
	private String save_jfsy;
	private String save_jf;
	private String[] counts;

	private String[] sj;     //时间
	private String[] bz;     //备注
	private String[] jfsy;     //加分事由
	private String[] xmdm;     //项目代码
	
	//===========================荣誉减分 end ================================
	
	public String getSave_bz() {
		return save_bz;
	}
	public void setSave_bz(String saveBz) {
		save_bz = saveBz;
	}
	public String getSave_sj() {
		return save_sj;
	}
	public void setSave_sj(String saveSj) {
		save_sj = saveSj;
	}
	public String getSave_jfsy() {
		return save_jfsy;
	}
	public void setSave_jfsy(String saveJfsy) {
		save_jfsy = saveJfsy;
	}
	public String getSave_jf() {
		return save_jf;
	}
	public void setSave_jf(String saveJf) {
		save_jf = saveJf;
	}
	public String getQueryequals_xqdm() {
		return queryequals_xqdm;
	}
	public String[] getSj() {
		return sj;
	}
	public void setSj(String[] sj) {
		this.sj = sj;
	}
	public String[] getBz() {
		return bz;
	}
	public void setBz(String[] bz) {
		this.bz = bz;
	}
	public String[] getJfsy() {
		return jfsy;
	}
	public void setJfsy(String[] jfsy) {
		this.jfsy = jfsy;
	}
	public String[] getXmdm() {
		return xmdm;
	}
	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}
	public void setQueryequals_xqdm(String queryequalsXqdm) {
		queryequals_xqdm = queryequalsXqdm;
	}
	public String getQueryequals_lddm() {
		return queryequals_lddm;
	}
	public void setQueryequals_lddm(String queryequalsLddm) {
		queryequals_lddm = queryequalsLddm;
	}
	public String getQueryequals_cs() {
		return queryequals_cs;
	}
	public void setQueryequals_cs(String queryequalsCs) {
		queryequals_cs = queryequalsCs;
	}
	public String getQueryequals_qsh() {
		return queryequals_qsh;
	}
	public void setQueryequals_qsh(String queryequalsQsh) {
		queryequals_qsh = queryequalsQsh;
	}
	public String[] getBl() {
		return bl;
	}
	public void setBl(String[] bl) {
		this.bl = bl;
	}
	public String[] getDj() {
		return dj;
	}
	public void setDj(String[] dj) {
		this.dj = dj;
	}
	public String[] getJf() {
		return jf;
	}
	public void setJf(String[] jf) {
		this.jf = jf;
	}
	public String getQueryequals_yd() {
		return queryequals_yd;
	}
	public void setQueryequals_yd(String queryequals_yd) {
		this.queryequals_yd = queryequals_yd;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
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
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
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
	public String getNjbzrsh() {
		return njbzrsh;
	}
	public void setNjbzrsh(String njbzrsh) {
		this.njbzrsh = njbzrsh;
	}
	public String getNjbzrshsj() {
		return njbzrshsj;
	}
	public void setNjbzrshsj(String njbzrshsj) {
		this.njbzrshsj = njbzrshsj;
	}
	public String getRyjf() {
		return ryjf;
	}
	public void setRyjf(String ryjf) {
		this.ryjf = ryjf;
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
	public String[] getGrhjf() {
		return grhjf;
	}
	public void setGrhjf(String[] grhjf) {
		this.grhjf = grhjf;
	}
	public String[] getHjmc() {
		return hjmc;
	}
	public void setHjmc(String[] hjmc) {
		this.hjmc = hjmc;
	}
	public String[] getHjsj() {
		return hjsj;
	}
	public void setHjsj(String[] hjsj) {
		this.hjsj = hjsj;
	}
	public String[] getHjsy() {
		return hjsy;
	}
	public void setHjsy(String[] hjsy) {
		this.hjsy = hjsy;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
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
	public String getSave_njbzrsh() {
		return save_njbzrsh;
	}
	public void setSave_njbzrsh(String save_njbzrsh) {
		this.save_njbzrsh = save_njbzrsh;
	}
	public String getSave_njbzrshsj() {
		return save_njbzrshsj;
	}
	public void setSave_njbzrshsj(String save_njbzrshsj) {
		this.save_njbzrshsj = save_njbzrshsj;
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
	public String getQueryequals_xq() {
		return queryequals_xq;
	}
	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}
	public String[] getPkValues() {
		return pkValues;
	}
	public void setPkValues(String[] pkValues) {
		this.pkValues = pkValues;
	}
	public String[] getBzdj() {
		return bzdj;
	}
	public void setBzdj(String[] bzdj) {
		this.bzdj = bzdj;
	}
	public String[] getBzf() {
		return bzf;
	}
	public void setBzf(String[] bzf) {
		this.bzf = bzf;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYd() {
		return yd;
	}
	public void setYd(String yd) {
		this.yd = yd;
	}
	public String getSave_bzdj() {
		return save_bzdj;
	}
	public void setSave_bzdj(String save_bzdj) {
		this.save_bzdj = save_bzdj;
	}
	public String getSave_bzf() {
		return save_bzf;
	}
	public void setSave_bzf(String save_bzf) {
		this.save_bzf = save_bzf;
	}
	public String getSave_yd() {
		return save_yd;
	}
	public void setSave_yd(String save_yd) {
		this.save_yd = save_yd;
	}
	public String[] getCounts() {
		return counts;
	}
	public void setCounts(String[] counts) {
		this.counts = counts;
	}

}
