/**
 * @部门:学工产品事业部
 * @日期：2016-4-20 下午06:20:44 
 */  
package com.zfsoft.xgxt.xszz.qxknszg;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-4-21 上午08:35:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QxknszgForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 	
	private static final long serialVersionUID = 1L;	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String xh;// 学号
	private String xm;// 姓名
	private String xn;// 学年
	private String xq;// 学期
	private String xqmc;// 学期名称
	private String sqsj;// 申请时间
	private String rddc;// 认定档次
	private String sqly;// 申请理由
	private String sjly;// 数据来源
	private String ylzd1;// 预留字段1
	private String ylzd2;// 预留字段2
	private String ylzd3;// 预留字段3
	private String ylzd4;// 预留字段4
	private String ylzd5;// 预留字段5
	private String guid;// ID
	private String dcmc;// 认定档次
	private String lylcywid;// 数据原来业务id	
	private String czr;// 操作人
	private String czsj;// 操作时间
	private String qxyy;// 取消原因	
	private String countNum;	
	private String qxtype;
	private String[]guids;//取消困难生结果ID			
	
	/**
	 * @return the guids
	 */
	public String[] getGuids() {
		return guids;
	}
	/**
	 * @param guids要设置的 guids
	 */
	public void setGuids(String[] guids) {
		this.guids = guids;
	}
	/**
	 * @return the qxtype
	 */
	public String getQxtype() {
		return qxtype;
	}
	/**
	 * @param qxtype要设置的 qxtype
	 */
	public void setQxtype(String qxtype) {
		this.qxtype = qxtype;
	}
	/**
	 * @return the countNum
	 */
	public String getCountNum() {
		return countNum;
	}
	/**
	 * @param countNum要设置的 countNum
	 */
	public void setCountNum(String countNum) {
		this.countNum = countNum;
	}
	
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czr要设置的 czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}
	/**
	 * @param czsj要设置的 czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	/**
	 * @return the qxyy
	 */
	public String getQxyy() {
		return qxyy;
	}
	/**
	 * @param qxyy要设置的 qxyy
	 */
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the rddc
	 */
	public String getRddc() {
		return rddc;
	}
	/**
	 * @param rddc要设置的 rddc
	 */
	public void setRddc(String rddc) {
		this.rddc = rddc;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1要设置的 ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2要设置的 ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3要设置的 ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4要设置的 ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5要设置的 ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the dcmc
	 */
	public String getDcmc() {
		return dcmc;
	}
	/**
	 * @param dcmc要设置的 dcmc
	 */
	public void setDcmc(String dcmc) {
		this.dcmc = dcmc;
	}
	/**
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywid要设置的 lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
	}

}
