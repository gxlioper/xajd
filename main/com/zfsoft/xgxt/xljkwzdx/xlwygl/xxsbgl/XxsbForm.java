/**
 * @部门:学工产品事业部
 * @日期：2014-5-26 下午03:17:33 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 信息上报
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-26 下午03:17:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XxsbForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String sbsqid;
	
	private String xh;
	
	private String sblx;
	
	private String sbsj;
	
	private String sbzbid;
	
	private String ztqk;
	
	private String xlxsxxqk;
	
	private String bz;
	
	private String splcid;
	
	private String shzt;
	
	private String xn;
	
	private String xq;

	/**************************/
	private String xtgwid;
	private String shid;
	private String shyj;
	private String thgw;
	private String shjg;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	/**************************/
	
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
	 * @return the sbsqid
	 */
	public String getSbsqid() {
		return sbsqid;
	}

	/**
	 * @param sbsqid要设置的 sbsqid
	 */
	public void setSbsqid(String sbsqid) {
		this.sbsqid = sbsqid;
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
	 * @return the sblx
	 */
	public String getSblx() {
		return sblx;
	}

	/**
	 * @param sblx要设置的 sblx
	 */
	public void setSblx(String sblx) {
		this.sblx = sblx;
	}

	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}

	/**
	 * @param sbsj要设置的 sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	/**
	 * @return the sbzbid
	 */
	public String getSbzbid() {
		return sbzbid;
	}

	/**
	 * @param sbzbid要设置的 sbzbid
	 */
	public void setSbzbid(String sbzbid) {
		this.sbzbid = sbzbid;
	}

	/**
	 * @return the ztqk
	 */
	public String getZtqk() {
		return ztqk;
	}

	/**
	 * @param ztqk要设置的 ztqk
	 */
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}

	/**
	 * @return the xlxsxxqk
	 */
	public String getXlxsxxqk() {
		return xlxsxxqk;
	}

	/**
	 * @param xlxsxxqk要设置的 xlxsxxqk
	 */
	public void setXlxsxxqk(String xlxsxxqk) {
		this.xlxsxxqk = xlxsxxqk;
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the xtgwid
	 */
	public String getXtgwid() {
		return xtgwid;
	}

	/**
	 * @param xtgwid要设置的 xtgwid
	 */
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}

	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}

	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}

	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}

	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}

	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}

	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}

	/**
	 * @param id要设置的 id
	 */
	public void setId(String[] id) {
		this.id = id;
	}

	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}

	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}

	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}

	/**
	 * @param splcs要设置的 splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
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
	
}
