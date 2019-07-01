package com.zfsoft.xgxt.comm.zdybd.model;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 自定义表单
 * @类功能描述: 字段定义表
 * @作者： ligl
 * @时间： 2013-11-26 下午03:50:38
 * @版本： V1.0
 * @修改记录:
 */
public class ZddyModel extends ActionForm {

	private static final long serialVersionUID = -2924542695182937526L;
	private String type;
	private String zddyid;// 字段定义id
	private String flszid;// 分类设置id
	private String zd;// 字段
	private String bdmc;// 表单名称
	private String zdsm;// 字段说明
	private String zdlx;// 字段类型 0:仅显示
	private String szlx;// 设置类型
	private String szz;// 设置值 该字段与“字段类型”、“来源类型”配合使用"
	private String yzlx;// 验证类型
	private String zbwz;// 坐标位置
	private String yxwk;// 允许为空
	private String yxxg;// 允许修改
	
	private String dataJson;//
	private String lb;//
	private String gndm;//

	public ZddyModel() {
		super();
	}

	public String getGndm() {
		return gndm;
	}

	public void setGndm(String gndm) {
		this.gndm = gndm;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public String getZddyid() {
		return zddyid;
	}

	public void setZddyid(String zddyid) {
		this.zddyid = zddyid;
	}

	public String getFlszid() {
		return flszid;
	}

	public void setFlszid(String flszid) {
		this.flszid = flszid;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getBdmc() {
		return bdmc;
	}

	public void setBdmc(String bdmc) {
		this.bdmc = bdmc;
	}

	public String getZdsm() {
		return zdsm;
	}

	public void setZdsm(String zdsm) {
		this.zdsm = zdsm;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public String getSzlx() {
		return szlx;
	}

	public void setSzlx(String szlx) {
		this.szlx = szlx;
	}

	public String getSzz() {
		return szz;
	}

	public void setSzz(String szz) {
		this.szz = szz;
	}

	public String getYzlx() {
		return yzlx;
	}

	public void setYzlx(String yzlx) {
		this.yzlx = yzlx;
	}

	public String getZbwz() {
		return zbwz;
	}

	public void setZbwz(String zbwz) {
		this.zbwz = zbwz;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYxwk() {
		return yxwk;
	}

	public void setYxwk(String yxwk) {
		this.yxwk = yxwk;
	}

	public String getYxxg() {
		return yxxg;
	}

	public void setYxxg(String yxxg) {
		this.yxxg = yxxg;
	}

}
