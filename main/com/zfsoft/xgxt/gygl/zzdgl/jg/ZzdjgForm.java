/**
 * @部门:学工产品事业部
 * @日期：2016-3-1 上午11:46:10 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-1 上午11:46:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdjgForm extends ActionForm{
	private String zzdid;
	private String xh;
	private String xn;
	private String xq;
	private String sqsj;
	private String zdqssj;
	private String zdzzsj;
	private String sdyy;
	private String splcid;
	private String shzt;
	private String filepath;
	private String jgid;
	private String qrzt;
	private String czy;
	private String czsj;
	private String sjly;
	private String qryj;
	private String[] qrids; //用于批量确认
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the zzdid
	 */
	public String getZzdid() {
		return zzdid;
	}
	/**
	 * @param zzdid要设置的 zzdid
	 */
	public void setZzdid(String zzdid) {
		this.zzdid = zzdid;
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
	 * @return the zdqssj
	 */
	public String getZdqssj() {
		return zdqssj;
	}
	/**
	 * @param zdqssj要设置的 zdqssj
	 */
	public void setZdqssj(String zdqssj) {
		this.zdqssj = zdqssj;
	}
	/**
	 * @return the zdzzsj
	 */
	public String getZdzzsj() {
		return zdzzsj;
	}
	/**
	 * @param zdzzsj要设置的 zdzzsj
	 */
	public void setZdzzsj(String zdzzsj) {
		this.zdzzsj = zdzzsj;
	}
	/**
	 * @return the sdyy
	 */
	public String getSdyy() {
		return sdyy;
	}
	/**
	 * @param sdyy要设置的 sdyy
	 */
	public void setSdyy(String sdyy) {
		this.sdyy = sdyy;
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
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @return the qrzt
	 */
	public String getQrzt() {
		return qrzt;
	}
	/**
	 * @param qrzt要设置的 qrzt
	 */
	public void setQrzt(String qrzt) {
		this.qrzt = qrzt;
	}
	/**
	 * @return the czy
	 */
	public String getCzy() {
		return czy;
	}
	/**
	 * @param czy要设置的 czy
	 */
	public void setCzy(String czy) {
		this.czy = czy;
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
	 * @return the qryj
	 */
	public String getQryj() {
		return qryj;
	}
	/**
	 * @param qryj要设置的 qryj
	 */
	public void setQryj(String qryj) {
		this.qryj = qryj;
	}
	/**
	 * @return the qrids
	 */
	public String[] getQrids() {
		return qrids;
	}
	/**
	 * @param qrids要设置的 qrids
	 */
	public void setQrids(String[] qrids) {
		this.qrids = qrids;
	}
	
	
	
	
}
