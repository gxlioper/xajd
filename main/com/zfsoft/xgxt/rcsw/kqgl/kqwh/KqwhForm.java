/**
 * @部门:学工产品事业部
 * @日期：2016-10-26 下午04:09:03 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务_考勤管理_管理模块
 * @类功能描述: 考情维护form
 * @作者： cq [工号:785]
 * @时间： 2016-10-26 下午04:09:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqwhForm extends ActionForm {

	private static final long serialVersionUID = 2128327451308674726L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String bjmc;
	private String bjrs;
	private String sjrs;
	private String kkrs;
	private String cql;
	private String kkxq;
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
    
    private String id; 		//
    private String xn; 		//学年
    private String xq; 		//学期
    private String yf; 		//月份
    private String zc; 		//周次
    private String bjdm; 	//班级代码
    private String cqrs; 	//出勤人数
    private String bz; 		//备注
    private String shzt; 	//审核状态
    private String splc; 	//审批流程
    private String jlr; 	//记录人
    private String jlsj;	//记录时间
    
    /**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	 * @return the bjrs
	 */
	public String getBjrs() {
		return bjrs;
	}
	/**
	 * @param bjrs要设置的 bjrs
	 */
	public void setBjrs(String bjrs) {
		this.bjrs = bjrs;
	}
	/**
	 * @return the sjrs
	 */
	public String getSjrs() {
		return sjrs;
	}
	/**
	 * @param sjrs要设置的 sjrs
	 */
	public void setSjrs(String sjrs) {
		this.sjrs = sjrs;
	}
	/**
	 * @return the kkrs
	 */
	public String getKkrs() {
		return kkrs;
	}
	/**
	 * @param kkrs要设置的 kkrs
	 */
	public void setKkrs(String kkrs) {
		this.kkrs = kkrs;
	}
	/**
	 * @return the cql
	 */
	public String getCql() {
		return cql;
	}
	/**
	 * @param cql要设置的 cql
	 */
	public void setCql(String cql) {
		this.cql = cql;
	}
	/**
	 * @return the kkxq
	 */
	public String getKkxq() {
		return kkxq;
	}
	/**
	 * @param kkxq要设置的 kkxq
	 */
	public void setKkxq(String kkxq) {
		this.kkxq = kkxq;
	}
	/**
	 * @return the toyf
	 */
	public String getToyf() {
		return toyf;
	}
	/**
	 * @param toyf要设置的 toyf
	 */
	public void setToyf(String toyf) {
		this.toyf = toyf;
	}
	/**
	 * @return the tozc
	 */
	public String getTozc() {
		return tozc;
	}
	/**
	 * @param tozc要设置的 tozc
	 */
	public void setTozc(String tozc) {
		this.tozc = tozc;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArr要设置的 xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the bjcs
	 */
	public String getBjcs() {
		return bjcs;
	}
	/**
	 * @param bjcs要设置的 bjcs
	 */
	public void setBjcs(String bjcs) {
		this.bjcs = bjcs;
	}
	/**
	 * @return the sjcs
	 */
	public String getSjcs() {
		return sjcs;
	}
	/**
	 * @param sjcs要设置的 sjcs
	 */
	public void setSjcs(String sjcs) {
		this.sjcs = sjcs;
	}
	/**
	 * @return the kkjs
	 */
	public String getKkjs() {
		return kkjs;
	}
	/**
	 * @param kkjs要设置的 kkjs
	 */
	public void setKkjs(String kkjs) {
		this.kkjs = kkjs;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}
	/**
	 * @param yf要设置的 yf
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zc要设置的 zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the cqrs
	 */
	public String getCqrs() {
		return cqrs;
	}
	/**
	 * @param cqrs要设置的 cqrs
	 */
	public void setCqrs(String cqrs) {
		this.cqrs = cqrs;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the jlr
	 */
	public String getJlr() {
		return jlr;
	}
	/**
	 * @param jlr要设置的 jlr
	 */
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}
	/**
	 * @param jlsj要设置的 jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}
	
}
