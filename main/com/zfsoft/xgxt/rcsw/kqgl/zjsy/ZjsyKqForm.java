/**
 * @部门:学工产品事业部
 * @日期：2015-6-17 下午02:25:21 
 */
package com.zfsoft.xgxt.rcsw.kqgl.zjsy;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述:浙江商业考勤管理
 * @作者： ChenQ[工号:856]
 * @时间： 2015-6-17 下午02:25:21
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZjsyKqForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String id;
	private String xn;
	private String xq;
	private String yf;
	private String zc;
	private String bjdm;
	private String bjmc;
	private String cqrs;
	private String bjrs;
	private String sjrs;
	private String kkrs;
	private String cql;
	private String kkxq;
	private String bz;
	private String toyf;
	private String tozc;
    private String xqmc;
    private String[] xhArr;
    private String xh;
    private String bjcs;
    private String sjcs;
    private String kkjs;
    private String zydm;
    private String xydm;
    
	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getKkjs() {
		return kkjs;
	}

	public void setKkjs(String kkjs) {
		this.kkjs = kkjs;
	}

	public String getBjcs() {
		return bjcs;
	}

	public void setBjcs(String bjcs) {
		this.bjcs = bjcs;
	}

	public String getSjcs() {
		return sjcs;
	}

	public void setSjcs(String sjcs) {
		this.sjcs = sjcs;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String[] getXhArr() {
		return xhArr;
	}

	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getToyf() {
		return toyf;
	}

	public void setToyf(String toyf) {
		this.toyf = toyf;
	}

	public String getTozc() {
		return tozc;
	}

	public void setTozc(String tozc) {
		this.tozc = tozc;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getCqrs() {
		return cqrs;
	}

	public void setCqrs(String cqrs) {
		this.cqrs = cqrs;
	}

	public String getBjrs() {
		return bjrs;
	}

	public void setBjrs(String bjrs) {
		this.bjrs = bjrs;
	}

	public String getSjrs() {
		return sjrs;
	}

	public void setSjrs(String sjrs) {
		this.sjrs = sjrs;
	}

	public String getKkrs() {
		return kkrs;
	}

	public void setKkrs(String kkrs) {
		this.kkrs = kkrs;
	}

	public String getCql() {
		return cql;
	}

	public void setCql(String cql) {
		this.cql = cql;
	}

	public String getKkxq() {
		return kkxq;
	}

	public void setKkxq(String kkxq) {
		this.kkxq = kkxq;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
