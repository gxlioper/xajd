package xsgzgl.pjpy.general.xmsz.pjtj;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_评奖条件_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XmszPjtjModel {

	private String[] xh;// 学号

	private String xmdm;// 项目代码

	private String xmmc;// 项目名称

	private String xn;// 学年

	private String xq;// 学期

	private String nd;// 年度

	private String tjdm;// 条件代码

	private String[] tjdm_sz;// 设置条件代码

	private String[] tjfw_sz;// 设置条件范围

	private String[] gx_sz;// 设置条件关系

	private String[] tjz_sz;// 设置条件值
	
	List<HashMap<String, String>> pjtjList;//评奖条件列表
	
	List<HashMap<String, String>> bjdlList;//班级大类列表

	public List<HashMap<String, String>> getPjtjList() {
		return pjtjList;
	}

	public void setPjtjList(List<HashMap<String, String>> pjtjList) {
		this.pjtjList = pjtjList;
	}

	public List<HashMap<String, String>> getBjdlList() {
		return bjdlList;
	}

	public void setBjdlList(List<HashMap<String, String>> bjdlList) {
		this.bjdlList = bjdlList;
	}

	public String[] getTjdm_sz() {
		return tjdm_sz;
	}

	public void setTjdm_sz(String[] tjdmSz) {
		tjdm_sz = tjdmSz;
	}

	public String[] getTjfw_sz() {
		return tjfw_sz;
	}

	public void setTjfw_sz(String[] tjfwSz) {
		tjfw_sz = tjfwSz;
	}

	public String[] getGx_sz() {
		return gx_sz;
	}

	public void setGx_sz(String[] gxSz) {
		gx_sz = gxSz;
	}

	public String[] getTjz_sz() {
		return tjz_sz;
	}

	public void setTjz_sz(String[] tjzSz) {
		tjz_sz = tjzSz;
	}

	public String getTjdm() {
		return tjdm;
	}

	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}

	public String[] getXh() {
		return xh;
	}

	public void setXh(String[] xh) {
		this.xh = xh;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
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

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}
}
