/**
 * @部门:学工产品事业部
 * @日期：2014-6-23 下午03:18:38 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——学团活动
 * @类功能描述: 项目设置Form 
 * @作者： cq [工号:785]
 * @时间： 2014-6-23 下午03:18:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdXmszForm extends ActionForm {

	
	private static final long serialVersionUID = -5407318657974976219L;
	
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	private String xmdm ;	//项目代码
	private String xmmc ;	//项目名称
	private String hdkssj ;	//活动开始时间
	private String hdjssj ;	//活动结束时间
	private String lbdm ;	//类别代码
	private String sqrssx ;	//申请人数上限
	private String shrssx ;	//审核人数上限
	private String shlc ;	//审核流程
	private String rskzjb ;	//人数控制级别
	private String hddd ;	//活动地点
	private String hdsm ;	//活动说明
	private String sqkg ;	//申请开关
	private String sqkssj ;	//申请开始时间
	private String sqjssj ;	//申请结束时间
	private String shkg ;	//审核开关
	private String shkssj ;	//审核开始时间
	private String shjssj ;	//审核结束时间
	private String fbsj ;	//发布时间
	private String xn;		//学年
	private String xq;		//学期
	private String xqmc;
	private String kgbz;	//开关备注
	private String syxf;	//授予学分
	
	private String cbdw;//承办单位
	private String fzrxm;//负责人姓名
	private String lxdh;//联系电话
	private String hdzt;//活动主题
	private String hdmdyy;//活动目的及意义
	private String hdfa;//活动方案
	private String sjly;
	private String hdggdm;//活动规格代码
	
	
	/**
	 * @return the hdggdm
	 */
	public String getHdggdm() {
		return hdggdm;
	}
	/**
	 * @param hdggdm要设置的 hdggdm
	 */
	public void setHdggdm(String hdggdm) {
		this.hdggdm = hdggdm;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
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
	public String getHdkssj() {
		return hdkssj;
	}
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	public String getHdjssj() {
		return hdjssj;
	}
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	public String getLbdm() {
		return lbdm;
	}
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	public String getSqrssx() {
		return sqrssx;
	}
	public void setSqrssx(String sqrssx) {
		this.sqrssx = sqrssx;
	}
	public String getShrssx() {
		return shrssx;
	}
	public void setShrssx(String shrssx) {
		this.shrssx = shrssx;
	}
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String getRskzjb() {
		return rskzjb;
	}
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	public String getHddd() {
		return hddd;
	}
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	public String getHdsm() {
		return hdsm;
	}
	public void setHdsm(String hdsm) {
		this.hdsm = hdsm;
	}
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getShkg() {
		return shkg;
	}
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	public String getShkssj() {
		return shkssj;
	}
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	public String getShjssj() {
		return shjssj;
	}
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
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
	public String getKgbz() {
		return kgbz;
	}
	public void setKgbz(String kgbz) {
		this.kgbz = kgbz;
	}
	public String getSyxf() {
		return syxf;
	}
	public void setSyxf(String syxf) {
		this.syxf = syxf;
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
	 * @return the cbdw
	 */
	public String getCbdw() {
		return cbdw;
	}
	/**
	 * @param cbdw要设置的 cbdw
	 */
	public void setCbdw(String cbdw) {
		this.cbdw = cbdw;
	}
	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}
	/**
	 * @param fzrxm要设置的 fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh要设置的 lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the hdzt
	 */
	public String getHdzt() {
		return hdzt;
	}
	/**
	 * @param hdzt要设置的 hdzt
	 */
	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	/**
	 * @return the hdmdyy
	 */
	public String getHdmdyy() {
		return hdmdyy;
	}
	/**
	 * @param hdmdyy要设置的 hdmdyy
	 */
	public void setHdmdyy(String hdmdyy) {
		this.hdmdyy = hdmdyy;
	}
	/**
	 * @return the hdfa
	 */
	public String getHdfa() {
		return hdfa;
	}
	/**
	 * @param hdfa要设置的 hdfa
	 */
	public void setHdfa(String hdfa) {
		this.hdfa = hdfa;
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
	
	

}
