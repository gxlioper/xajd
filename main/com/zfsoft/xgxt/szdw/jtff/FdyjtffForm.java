/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:09:05 
 */  
package com.zfsoft.xgxt.szdw.jtff;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(辅导员津贴发放--山东潍坊) 
 * @作者： cmj [工号：913]
 * @时间： 2013-8-5 上午11:09:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyjtffForm extends ActionForm {
	
private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;            //
	private String zgh;            //职工号
	private String xm;            //姓名
	private String xb;            //性别
	private String xbmc;            //性别
	private String lxdh;            //联系电话
	private String bmmc;            //部门名称
	
	private String bzlx;            //补助类型
	private String bzlxmc;            //补助类型
	private String kpdj;            //考评等级
	private String kpdjmc;            //考评等级
	private String xn;            //学年
	private String xq;            //学期
	private String xqmc;            //学期
	private String bzje;            //补助金额
	
	private String fdyjtfflx;            //业务模块根据需要添加个性化代码
	
	
	
	/**
	 * @return the bzlxmc
	 */
	public String getBzlxmc() {
		return bzlxmc;
	}
	/**
	 * @param bzlxmc要设置的 bzlxmc
	 */
	public void setBzlxmc(String bzlxmc) {
		this.bzlxmc = bzlxmc;
	}
	/**
	 * @return the kpdjmc
	 */
	public String getKpdjmc() {
		return kpdjmc;
	}
	/**
	 * @param kpdjmc要设置的 kpdjmc
	 */
	public void setKpdjmc(String kpdjmc) {
		this.kpdjmc = kpdjmc;
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the bzlx
	 */
	public String getBzlx() {
		return bzlx;
	}
	/**
	 * @param bzlx要设置的 bzlx
	 */
	public void setBzlx(String bzlx) {
		this.bzlx = bzlx;
	}
	/**
	 * @return the kpdj
	 */
	public String getKpdj() {
		return kpdj;
	}
	/**
	 * @param kpdj要设置的 kpdj
	 */
	public void setKpdj(String kpdj) {
		this.kpdj = kpdj;
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
	 * @return the bzje
	 */
	public String getBzje() {
		return bzje;
	}
	/**
	 * @param bzje要设置的 bzje
	 */
	public void setBzje(String bzje) {
		this.bzje = bzje;
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
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * @param bmmc要设置的 bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
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
	 * @return the xbmc
	 */
	public String getXbmc() {
		return xbmc;
	}
	/**
	 * @param xbmc要设置的 xbmc
	 */
	public void setXbmc(String xbmc) {
		this.xbmc = xbmc;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public String getFdyjtfflx() {
		return fdyjtfflx;
	}
	public void setFdyjtfflx(String fdyjtfflx) {
		this.fdyjtfflx = fdyjtfflx;
	}
	
}
