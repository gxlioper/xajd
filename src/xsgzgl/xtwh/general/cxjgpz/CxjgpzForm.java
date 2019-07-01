package xsgzgl.xtwh.general.cxjgpz;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_查询结果配置
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
public class CxjgpzForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();	
	private String gnlj;// 功能路径
	private String zd;// 字段
	private String zdmc;// 字段名称
	private String sfjgxs;//是否结果显示
	private String xssx;//显示顺序
	private String xgzdmc;//修改后字段名称
	
	private String cxjg[];
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getGnlj() {
		return gnlj;
	}
	public void setGnlj(String gnlj) {
		this.gnlj = gnlj;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getSfjgxs() {
		return sfjgxs;
	}
	public void setSfjgxs(String sfjgxs) {
		this.sfjgxs = sfjgxs;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getXgzdmc() {
		return xgzdmc;
	}
	public void setXgzdmc(String xgzdmc) {
		this.xgzdmc = xgzdmc;
	}
	public String[] getCxjg() {
		return cxjg;
	}
	public void setCxjg(String[] cxjg) {
		this.cxjg = cxjg;
	}
	
}
