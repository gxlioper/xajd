/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 上午11:49:23 
 */  
package com.zfsoft.xgxt.xszz.hjxf.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-15 上午11:49:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjxfShForm extends ActionForm {
	 private String hjxfid;
	 private String xh;
	 private String xn;
	 private String xq;
	 private String dkqk;
	 private String wnqfje;
	 private String yjje;
	 private String hjje;
	 private String jqjzsj;
	 private String sqyy;
	 private String splcid;
	 private String shzt;
	 private String filepath;
	 private String sqsj;
	 private String pkdj;
	 private String  type;
	 private String shid;
    private String shjg;
    private String shyj;
    private String gwid;
    private String thgw;
    private String shlc;
    //批量审核用
    private String[] id;
    private String[] gwids;
    private String[] xhs;
    private static final long serialVersionUID = 1L;
    private SearchModel searchModel = new SearchModel();
   //导出
   private ExportModel exportModel = new ExportModel();
   private Pages pages = new Pages();
	/**
	 * @return the hjxfid
	 */
	public String getHjxfid() {
		return hjxfid;
	}
	/**
	 * @param hjxfid要设置的 hjxfid
	 */
	public void setHjxfid(String hjxfid) {
		this.hjxfid = hjxfid;
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
	 * @return the dkqk
	 */
	public String getDkqk() {
		return dkqk;
	}
	/**
	 * @param dkqk要设置的 dkqk
	 */
	public void setDkqk(String dkqk) {
		this.dkqk = dkqk;
	}
	/**
	 * @return the wnqfje
	 */
	public String getWnqfje() {
		return wnqfje;
	}
	/**
	 * @param wnqfje要设置的 wnqfje
	 */
	public void setWnqfje(String wnqfje) {
		this.wnqfje = wnqfje;
	}
	/**
	 * @return the yjje
	 */
	public String getYjje() {
		return yjje;
	}
	/**
	 * @param yjje要设置的 yjje
	 */
	public void setYjje(String yjje) {
		this.yjje = yjje;
	}
	/**
	 * @return the hjje
	 */
	public String getHjje() {
		return hjje;
	}
	/**
	 * @param hjje要设置的 hjje
	 */
	public void setHjje(String hjje) {
		this.hjje = hjje;
	}
	/**
	 * @return the jqjzsj
	 */
	public String getJqjzsj() {
		return jqjzsj;
	}
	/**
	 * @param jqjzsj要设置的 jqjzsj
	 */
	public void setJqjzsj(String jqjzsj) {
		this.jqjzsj = jqjzsj;
	}
	/**
	 * @return the sqyy
	 */
	public String getSqyy() {
		return sqyy;
	}
	/**
	 * @param sqyy要设置的 sqyy
	 */
	public void setSqyy(String sqyy) {
		this.sqyy = sqyy;
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
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath要设置的 filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	 * @return the pkdj
	 */
	public String getPkdj() {
		return pkdj;
	}
	/**
	 * @param pkdj要设置的 pkdj
	 */
	public void setPkdj(String pkdj) {
		this.pkdj = pkdj;
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
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
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
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
