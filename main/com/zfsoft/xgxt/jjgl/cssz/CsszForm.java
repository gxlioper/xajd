/**
 * @部门:学工产品事业部
 * @日期：2014-8-21 下午04:14:17 
 */  
package com.zfsoft.xgxt.jjgl.cssz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 家教模块-基础设置-参数设置Form
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-21 下午04:14:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszForm extends ActionForm {

	private static final long serialVersionUID = -6886564018188978871L;

	//家教投递
	private String jjtdsqkg;

	private String tdsqkssj;

	private String tdsqjssj;

	private String jjtdsplc;

	//退家教
	private String tjjsqkg;

	private String tjjsqkssj;

	private String tjjsqjssj;

	private String tjjsplc;

	//家教结束
	private String jjjssqkg;

	private String jjjssqkssj;

	private String jjjssqjssj;

	private String jjjssplc;


	//查询类型
	private String qryType;

	private String dm;

	private Pages pages = new Pages();

	public String getTjjsqkg() {
		return tjjsqkg;
	}

	public void setTjjsqkg(String tjjsqkg) {
		this.tjjsqkg = tjjsqkg;
	}

	public String getTjjsqkssj() {
		return tjjsqkssj;
	}

	public void setTjjsqkssj(String tjjsqkssj) {
		this.tjjsqkssj = tjjsqkssj;
	}

	public String getTjjsqjssj() {
		return tjjsqjssj;
	}

	public void setTjjsqjssj(String tjjsqjssj) {
		this.tjjsqjssj = tjjsqjssj;
	}

	public String getTjjsplc() {
		return tjjsplc;
	}

	public void setTjjsplc(String tjjsplc) {
		this.tjjsplc = tjjsplc;
	}

	public String getJjjssqkg() {
		return jjjssqkg;
	}

	public void setJjjssqkg(String jjjssqkg) {
		this.jjjssqkg = jjjssqkg;
	}

	public String getJjjssqkssj() {
		return jjjssqkssj;
	}

	public void setJjjssqkssj(String jjjssqkssj) {
		this.jjjssqkssj = jjjssqkssj;
	}

	public String getJjjssqjssj() {
		return jjjssqjssj;
	}

	public void setJjjssqjssj(String jjjssqjssj) {
		this.jjjssqjssj = jjjssqjssj;
	}

	public String getJjjssplc() {
		return jjjssplc;
	}

	public void setJjjssplc(String jjjssplc) {
		this.jjjssplc = jjjssplc;
	}

	public String getJjtdsqkg() {
		return jjtdsqkg;
	}

	public void setJjtdsqkg(String jjtdsqkg) {
		this.jjtdsqkg = jjtdsqkg;
	}

	public String getTdsqkssj() {
		return tdsqkssj;
	}

	public void setTdsqkssj(String tdsqkssj) {
		this.tdsqkssj = tdsqkssj;
	}

	public String getTdsqjssj() {
		return tdsqjssj;
	}

	public void setTdsqjssj(String tdsqjssj) {
		this.tdsqjssj = tdsqjssj;
	}

	public String getJjtdsplc() {
		return jjtdsplc;
	}

	public void setJjtdsplc(String jjtdsplc) {
		this.jjtdsplc = jjtdsplc;
	}

	public String getQryType() {
		return qryType;
	}

	public void setQryType(String qryType) {
		this.qryType = qryType;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
