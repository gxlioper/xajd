/**
 * @部门:学工产品事业部
 * @日期：2018年5月28日 下午3:33:38 
 */  
package xsgzgl.gyjc.jcjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月28日 下午3:33:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsccForm extends ActionForm {
	private String dm;
	private String mc;
	private String type;
	private String lddm;
	private String ldmc;
	private String qsh;
	private String bmmc;
	private String jcsj;
	private String pjdj;
	private String ccid;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String[] ztpjs;
	private String[] grpjs;
	private String[] cwhs;

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}


	public String getCcid() {
		return ccid;
	}

	public void setCcid(String ccid) {
		this.ccid = ccid;
	}


	public String[] getZtpjs() {
		return ztpjs;
	}

	public void setZtpjs(String[] ztpjs) {
		this.ztpjs = ztpjs;
	}

	public String[] getGrpjs() {
		return grpjs;
	}

	public void setGrpjs(String[] grpjs) {
		this.grpjs = grpjs;
	}

	public String[] getCwhs() {
		return cwhs;
	}

	public void setCwhs(String[] cwhs) {
		this.cwhs = cwhs;
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
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
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
	 * @return the jcsj
	 */
	public String getJcsj() {
		return jcsj;
	}
	/**
	 * @param jcsj要设置的 jcsj
	 */
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	/**
	 * @return the pjdj
	 */
	public String getPjdj() {
		return pjdj;
	}
	/**
	 * @param pjdj要设置的 pjdj
	 */
	public void setPjdj(String pjdj) {
		this.pjdj = pjdj;
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

}
