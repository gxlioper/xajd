/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.pjpy.ghxy.qsryf;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * MyEclipse Struts
 * Creation date: 08-05-2010
 * 
 * XDoclet definition:
 * @struts.form name="ghxyBjryfForm"
 */
public class GhxyQsryfForm extends ActionForm {
	
	private String xmfz[];     //项目分值
	private String pc;     //批次
	private String xn;     //学年
	private String xq;     //学期
	private String plssbh[];    //批量班级代码
	private String xmdm;     //项目代码
	private String checkVal[]; //
	private String xydm;
	private String zydm;
	private String xxsh;
	private String njzrsh;
	private String xxshsj;
	private String njzrshsj;
	private String njzryj;
	private String xxyj;
	private String djdm;
	private String djmc;
	private String jf;
	private String bjbl;
	private String qsh;
	private String lddm;
	private String cs;
	private String dqxn;
	private String dqxq;
	private String []primarykey_cbv;
	private String []xnArr;
	private String []xqArr;
	private String []yhqxfp;
	private String []checks;
	private String nj;
	private String yqqdm;
	
	private String save_xn;
	private String save_bjbl;
	private String save_jf;
	private String save_djmc;
	private String save_djdm;
	private String save_xxsh;
	private String save_njzrsh;
	private String save_xxshsj;
	private String save_njzrshsj;
	private String save_njzryj;
	private String save_xxyj;
	private String save_plbjdm;  //班级代码
	private String save_pc;
	private String save_xmdm;
	private String save_xmfz;
	
	private String queryequals_yqqdm;
	private String queryequals_lddm;
	private String queryequals_qsh;
	private String queryequals_cs;
	private String queryequals_xqqdm;
	private String queryequals_pc;
	private String queryequals_xn;
	private String queryequals_xq;
	private String queryequals_nj;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	
	
	private Pages pages=new Pages();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	

	public String[] getPlssbh() {
		return plssbh;
	}

	public void setPlssbh(String[] plssbh) {
		this.plssbh = plssbh;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String[] getXmfz() {
		return xmfz;
	}

	public void setXmfz(String[] xmfz) {
		this.xmfz = xmfz;
	}

	

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
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

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
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

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
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

	public String getSave_plbjdm() {
		return save_plbjdm;
	}

	public void setSave_plbjdm(String save_plbjdm) {
		this.save_plbjdm = save_plbjdm;
	}

	public String getSave_pc() {
		return save_pc;
	}

	public void setSave_pc(String save_pc) {
		this.save_pc = save_pc;
	}

	

	public String getSave_xmdm() {
		return save_xmdm;
	}

	public void setSave_xmdm(String save_xmdm) {
		this.save_xmdm = save_xmdm;
	}

	public String getSave_xmfz() {
		return save_xmfz;
	}

	public void setSave_xmfz(String save_xmfz) {
		this.save_xmfz = save_xmfz;
	}

	public String getNjzrsh() {
		return njzrsh;
	}

	public void setNjzrsh(String njzrsh) {
		this.njzrsh = njzrsh;
	}

	public String getNjzrshsj() {
		return njzrshsj;
	}

	public void setNjzrshsj(String njzrshsj) {
		this.njzrshsj = njzrshsj;
	}

	public String getNjzryj() {
		return njzryj;
	}

	public void setNjzryj(String njzryj) {
		this.njzryj = njzryj;
	}

	public String getSave_njzrsh() {
		return save_njzrsh;
	}

	public void setSave_njzrsh(String save_njzrsh) {
		this.save_njzrsh = save_njzrsh;
	}

	public String getSave_njzrshsj() {
		return save_njzrshsj;
	}

	public void setSave_njzrshsj(String save_njzrshsj) {
		this.save_njzrshsj = save_njzrshsj;
	}

	public String getSave_njzryj() {
		return save_njzryj;
	}

	public void setSave_njzryj(String save_njzryj) {
		this.save_njzryj = save_njzryj;
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

	public String getSave_xxyj() {
		return save_xxyj;
	}

	public void setSave_xxyj(String save_xxyj) {
		this.save_xxyj = save_xxyj;
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

	public String getBjbl() {
		return bjbl;
	}

	public void setBjbl(String bjbl) {
		this.bjbl = bjbl;
	}

	public String getDjdm() {
		return djdm;
	}

	public void setDjdm(String djdm) {
		this.djdm = djdm;
	}

	public String getDjmc() {
		return djmc;
	}

	public void setDjmc(String djmc) {
		this.djmc = djmc;
	}

	public String getJf() {
		return jf;
	}

	public void setJf(String jf) {
		this.jf = jf;
	}

	public String getSave_bjbl() {
		return save_bjbl;
	}

	public void setSave_bjbl(String save_bjbl) {
		this.save_bjbl = save_bjbl;
	}

	public String getSave_djdm() {
		return save_djdm;
	}

	public void setSave_djdm(String save_djdm) {
		this.save_djdm = save_djdm;
	}

	public String getSave_djmc() {
		return save_djmc;
	}

	public void setSave_djmc(String save_djmc) {
		this.save_djmc = save_djmc;
	}

	public String getSave_jf() {
		return save_jf;
	}

	public void setSave_jf(String save_jf) {
		this.save_jf = save_jf;
	}

	public String getSave_xn() {
		return save_xn;
	}

	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
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

	public String getQueryequals_pc() {
		return queryequals_pc;
	}

	public void setQueryequals_pc(String queryequals_pc) {
		this.queryequals_pc = queryequals_pc;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getQueryequals_xqqdm() {
		return queryequals_xqqdm;
	}

	public void setQueryequals_xqqdm(String queryequals_xqqdm) {
		this.queryequals_xqqdm = queryequals_xqqdm;
	}

	public String getQueryequals_cs() {
		return queryequals_cs;
	}

	public void setQueryequals_cs(String queryequals_cs) {
		this.queryequals_cs = queryequals_cs;
	}

	public String getQueryequals_lddm() {
		return queryequals_lddm;
	}

	public void setQueryequals_lddm(String queryequals_lddm) {
		this.queryequals_lddm = queryequals_lddm;
	}

	public String getQueryequals_qsh() {
		return queryequals_qsh;
	}

	public void setQueryequals_qsh(String queryequals_qsh) {
		this.queryequals_qsh = queryequals_qsh;
	}

	public String getQueryequals_yqqdm() {
		return queryequals_yqqdm;
	}

	public void setQueryequals_yqqdm(String queryequals_yqqdm) {
		this.queryequals_yqqdm = queryequals_yqqdm;
	}

	public String getDqxn() {
		return dqxn;
	}

	public void setDqxn(String dqxn) {
		this.dqxn = dqxn;
	}

	public String getDqxq() {
		return dqxq;
	}

	public void setDqxq(String dqxq) {
		this.dqxq = dqxq;
	}

	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}

	public void setPrimarykey_cbv(String[] primarykey_cbv) {
		this.primarykey_cbv = primarykey_cbv;
	}

	public String[] getXnArr() {
		return xnArr;
	}

	public void setXnArr(String[] xnArr) {
		this.xnArr = xnArr;
	}

	public String[] getXqArr() {
		return xqArr;
	}

	public void setXqArr(String[] xqArr) {
		this.xqArr = xqArr;
	}

	public String[] getChecks() {
		return checks;
	}

	public void setChecks(String[] checks) {
		this.checks = checks;
	}

	public String[] getYhqxfp() {
		return yhqxfp;
	}

	public void setYhqxfp(String[] yhqxfp) {
		this.yhqxfp = yhqxfp;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getYqqdm() {
		return yqqdm;
	}

	public void setYqqdm(String yqqdm) {
		this.yqqdm = yqqdm;
	}


	
}