package xgxt.rcsw.zjjs.rwzb;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(老师)_Form类
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

public class ZjjsRwzbForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	Pages pages = new Pages();

	private String[] checkVal;// 批处理

	private String xh;

	private String xm;

	private String sfjmxf;

	private String bzje;

	private String bz;

	private String nj;

	private String xydm;

	private String zydm;

	private String bjdm;
	
	private String rwlx;//入伍类型

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBzje() {
		return bzje;
	}

	public void setBzje(String bzje) {
		this.bzje = bzje;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getSfjmxf() {
		return sfjmxf;
	}

	public void setSfjmxf(String sfjmxf) {
		this.sfjmxf = sfjmxf;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

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

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
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

	public String getRwlx() {
		return rwlx;
	}

	public void setRwlx(String rwlx) {
		this.rwlx = rwlx;
	}
	
}
