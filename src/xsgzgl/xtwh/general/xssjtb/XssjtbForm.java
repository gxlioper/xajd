package xsgzgl.xtwh.general.xssjtb;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_学生数据检测
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class XssjtbForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	
	private String ycb;// 异常表
	private String zj;// 主键
	private String ycyy;// 异常原因
	
	private String jcsj;//检测时间
	private String jcnr;//检测内容
	private String jcb;//检测表
	private String cgs;//成功数
	private String sbs;//失败数
	
	private String jckssj;//检测开始时间
	
	private String jcjssj;//检测结束时间
	
	private String []checkVal;
	
	private String []colList;//输出字段;
	
	private String []topTr;//表头
	
	public String getYcb() {
		return ycb;
	}
	public void setYcb(String ycb) {
		this.ycb = ycb;
	}
	public String getZj() {
		return zj;
	}
	public void setZj(String zj) {
		this.zj = zj;
	}
	public String getYcyy() {
		return ycyy;
	}
	public void setYcyy(String ycyy) {
		this.ycyy = ycyy;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getJcnr() {
		return jcnr;
	}
	public void setJcnr(String jcnr) {
		this.jcnr = jcnr;
	}
	public String getJcb() {
		return jcb;
	}
	public void setJcb(String jcb) {
		this.jcb = jcb;
	}
	public String getCgs() {
		return cgs;
	}
	public void setCgs(String cgs) {
		this.cgs = cgs;
	}
	public String getSbs() {
		return sbs;
	}
	public void setSbs(String sbs) {
		this.sbs = sbs;
	}
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
	public String[] getColList() {
		return colList;
	}
	public void setColList(String[] colList) {
		this.colList = colList;
	}
	public String[] getTopTr() {
		return topTr;
	}
	public void setTopTr(String[] topTr) {
		this.topTr = topTr;
	}
	public String getJckssj() {
		return jckssj;
	}
	public void setJckssj(String jckssj) {
		this.jckssj = jckssj;
	}
	public String getJcjssj() {
		return jcjssj;
	}
	public void setJcjssj(String jcjssj) {
		this.jcjssj = jcjssj;
	}
	
}
