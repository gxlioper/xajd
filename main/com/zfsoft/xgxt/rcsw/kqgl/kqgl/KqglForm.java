/**
 * @部门:学工产品事业部
 * @日期：2015-8-31 下午02:29:31 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
public class KqglForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String id; //ID
	private String kqrq; //考勤日期
	private String xh;
	private String xm;
	private String bjdm;
	private String kqlbdm; //考勤类别代码
	private String kqlbmc; //考勤类别名称
	private String qqts;  //缺勤天数
	private String kkjs;  //旷课节数
	private String qkjblbdm; //缺课疾病类别代码
	private String ybqkjbdm; //因病缺课疾病代码
	private String dqztdm;  //当前状态代码
	private String qkjblbmc;
	private String ybqkjbmc;
	private String dqztmc;
	private String bz;
	private String bjmc;
	private String zydm;
	private String zymc;
	private String xydm;
	private String xymc;
	private String nj;
	private String xb;
	private String type;
	private String zjsj;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}

	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getKqrq() {
		return kqrq;
	}

	public void setKqrq(String kqrq) {
		this.kqrq = kqrq;
	}

	public String getKqlbdm() {
		return kqlbdm;
	}
	
	public void setKqlbdm(String kqlbdm) {
		this.kqlbdm = kqlbdm;
	}
	
	public String getKqlbmc() {
		return kqlbmc;
	}
	
	public void setKqlbmc(String kqlbmc) {
		this.kqlbmc = kqlbmc;
	}
	
	public String getQqts() {
		return qqts;
	}
	
	public void setQqts(String qqts) {
		this.qqts = qqts;
	}
	
	public String getKkjs() {
		return kkjs;
	}
	
	public void setKkjs(String kkjs) {
		this.kkjs = kkjs;
	}
	
	public String getQkjblbdm() {
		return qkjblbdm;
	}
	
	public void setQkjblbdm(String qkjblbdm) {
		this.qkjblbdm = qkjblbdm;
	}
	
	public String getYbqkjbdm() {
		return ybqkjbdm;
	}
	
	public void setYbqkjbdm(String ybqkjbdm) {
		this.ybqkjbdm = ybqkjbdm;
	}
	
	public String getDqztdm() {
		return dqztdm;
	}
	
	public void setDqztdm(String dqztdm) {
		this.dqztdm = dqztdm;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Pages getPages() {
		return pages;
	}
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	public SearchModel getSearchModel() {
		return searchModel;
	}
	
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getQkjblbmc() {
		return qkjblbmc;
	}


	public void setQkjblbmc(String qkjblbmc) {
		this.qkjblbmc = qkjblbmc;
	}


	public String getYbqkjbmc() {
		return ybqkjbmc;
	}


	public void setYbqkjbmc(String ybqkjbmc) {
		this.ybqkjbmc = ybqkjbmc;
	}

	public String getDqztmc() {
		return dqztmc;
	}

	public void setDqztmc(String dqztmc) {
		this.dqztmc = dqztmc;
	}

	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}

	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}

	/**
	 * @param zydm要设置的 zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}

	/**
	 * @param zymc要设置的 zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}

	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}

	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}

	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}

	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}

	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}

	/**
	 * @return the zjsj
	 */
	public String getZjsj() {
		return zjsj;
	}

	/**
	 * @param zjsj要设置的 zjsj
	 */
	public void setZjsj(String zjsj) {
		this.zjsj = zjsj;
	}
   
}
