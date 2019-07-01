/**
 * @部门:学工产品事业部
 * @日期：2014-10-10 下午02:50:41 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 违约记录
 * @作者： 黄辰霁
 * @时间： 2015-11-26 上午9:41:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WyjlModel extends ActionForm{

	
	private static final long serialVersionUID = 3740163187234420743L;
	private String xh;  	//学号
	private String sjhm; 	//手机号码
	private String qqhm;	//qq号码
	private String wxhm;	//微信号码
	private String dzyx;	//电子邮箱
	private String wyxq;	//违约详情
	private String wyzt;	//违约状态
	private String wysj;    //违约时间
	private String bz;	//备注
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
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
	 * @return the sjhm
	 */
	public String getSjhm() {
		return sjhm;
	}
	/**
	 * @param sjhm要设置的 sjhm
	 */
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	/**
	 * @return the qqhm
	 */
	public String getQqhm() {
		return qqhm;
	}
	/**
	 * @param qqhm要设置的 qqhm
	 */
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	/**
	 * @return the wxhm
	 */
	public String getWxhm() {
		return wxhm;
	}
	/**
	 * @param wxhm要设置的 wxhm
	 */
	public void setWxhm(String wxhm) {
		this.wxhm = wxhm;
	}
	/**
	 * @return the dzyx
	 */
	public String getDzyx() {
		return dzyx;
	}
	/**
	 * @param dzyx要设置的 dzyx
	 */
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	/**
	 * @return the wyxq
	 */
	public String getWyxq() {
		return wyxq;
	}
	/**
	 * @param wyxq要设置的 wyxq
	 */
	public void setWyxq(String wyxq) {
		this.wyxq = wyxq;
	}
	/**
	 * @return the wyzt
	 */
	public String getWyzt() {
		return wyzt;
	}
	/**
	 * @param wyzt要设置的 wyzt
	 */
	public void setWyzt(String wyzt) {
		this.wyzt = wyzt;
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
	 * @return the wysj
	 */
	public String getWysj() {
		return wysj;
	}
	/**
	 * @param wysj要设置的 wysj
	 */
	public void setWysj(String wysj) {
		this.wysj = wysj;
	}
}
