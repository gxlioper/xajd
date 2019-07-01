/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.strtsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class StrtsqForm extends ActionForm{
	private Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	private String type;
	private String jgid;
	private String guid;
	private String xh;
	private String sqly;
	private String tc;
	private String sqsj;
	private String shzt;
	private String shsj;
	private String tnzt;
	private String stqc;
	private String tnzw;
	private String sjly;
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：jgid the jgid to set
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：sqly the sqly to set
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the tc
	 */
	public String getTc() {
		return tc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：tc the tc to set
	 */
	public void setTc(String tc) {
		this.tc = tc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：shsj the shsj to set
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the tnzt
	 */
	public String getTnzt() {
		return tnzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：tnzt the tnzt to set
	 */
	public void setTnzt(String tnzt) {
		this.tnzt = tnzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the tnzw
	 */
	public String getTnzw() {
		return tnzw;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：tnzw the tnzw to set
	 */
	public void setTnzw(String tnzw) {
		this.tnzw = tnzw;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @return		: the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-6 下午04:04:09 
	 * @param 		：sjly the sjly to set
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-7 下午02:01:35 
	 * @return		: the stqc
	 */
	public String getStqc() {
		return stqc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-7 下午02:01:35 
	 * @param 		：stqc the stqc to set
	 */
	public void setStqc(String stqc) {
		this.stqc = stqc;
	}
	
	
}
