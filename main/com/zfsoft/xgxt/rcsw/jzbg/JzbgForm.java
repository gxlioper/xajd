/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.jzbg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class JzbgForm extends ActionForm{
	Pages pages = new Pages();	// 分页
	SearchModel searchModel = new SearchModel();	// 高级查询
	private ExportModel exportModel = new ExportModel();//自定义导出
	
	private String type; //类型
	private String jzid ;//主键
	private String mc ; //学号
	private String sj ;
	private String dd ;
	private String zbdw ;
	private String zjr ;
	private String cyrs ;
	private String fbr;
	private String fbsj ;
	private String zt ;
	
	private String pjid ;
	private String bgid;
	private String xh ;
	private String pj;
	private String pjbz ;
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the jzid
	 */
	public String getJzid() {
		return jzid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：jzid the jzid to set
	 */
	public void setJzid(String jzid) {
		this.jzid = jzid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：mc the mc to set
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the sj
	 */
	public String getSj() {
		return sj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：sj the sj to set
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the dd
	 */
	public String getDd() {
		return dd;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：dd the dd to set
	 */
	public void setDd(String dd) {
		this.dd = dd;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the zbdw
	 */
	public String getZbdw() {
		return zbdw;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：zbdw the zbdw to set
	 */
	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the zjr
	 */
	public String getZjr() {
		return zjr;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：zjr the zjr to set
	 */
	public void setZjr(String zjr) {
		this.zjr = zjr;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the cyrs
	 */
	public String getCyrs() {
		return cyrs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：cyrs the cyrs to set
	 */
	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the fbr
	 */
	public String getFbr() {
		return fbr;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：fbr the fbr to set
	 */
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the fbsj
	 */
	public String getFbsj() {
		return fbsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：fbsj the fbsj to set
	 */
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @return		: the zt
	 */
	public String getZt() {
		return zt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-14 下午05:18:21 
	 * @param 		：zt the zt to set
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @return		: the pjid
	 */
	public String getPjid() {
		return pjid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @param 		：pjid the pjid to set
	 */
	public void setPjid(String pjid) {
		this.pjid = pjid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @return		: the bgid
	 */
	public String getBgid() {
		return bgid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @param 		：bgid the bgid to set
	 */
	public void setBgid(String bgid) {
		this.bgid = bgid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @return		: the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @param 		：pj the pj to set
	 */
	public void setPj(String pj) {
		this.pj = pj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @return		: the pjbz
	 */
	public String getPjbz() {
		return pjbz;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-12-15 下午03:12:50 
	 * @param 		：pjbz the pjbz to set
	 */
	public void setPjbz(String pjbz) {
		this.pjbz = pjbz;
	}
	
	
	
}
