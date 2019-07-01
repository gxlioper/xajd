/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class JspjsqForm extends ActionForm{

	private Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	private String type;
	private String pjlx;
	private String xh;
	private String xn;
	private String xq;
	
	private String sqid;
	
	private String pjjszgh;
	private String pjjsxm;
	private String pjsj;
	private String pj;
	
	private String splc;
	private String shzt;
	private String ylzd1;//认识途径
	private String ylzd2;
	private String ylzd3;
	private String ylzd4;
	private String ylzd5;
	private String zzshen;
	
	/**
	 * @return the zzshen
	 */
	public String getZzshen() {
		return zzshen;
	}
	/**
	 * @param zzshen要设置的 zzshen
	 */
	public void setZzshen(String zzshen) {
		this.zzshen = zzshen;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the pjjszgh
	 */
	public String getPjjszgh() {
		return pjjszgh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：pjjszgh the pjjszgh to set
	 */
	public void setPjjszgh(String pjjszgh) {
		this.pjjszgh = pjjszgh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the pjsj
	 */
	public String getPjsj() {
		return pjsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：pjsj the pjsj to set
	 */
	public void setPjsj(String pjsj) {
		this.pjsj = pjsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：pj the pj to set
	 */
	public void setPj(String pj) {
		this.pj = pj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：ylzd1 the ylzd1 to set
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：ylzd2 the ylzd2 to set
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：ylzd3 the ylzd3 to set
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：ylzd4 the ylzd4 to set
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @return		: the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午08:54:35 
	 * @param 		：ylzd5 the ylzd5 to set
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午10:28:37 
	 * @return		: the pjlx
	 */
	public String getPjlx() {
		return pjlx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 上午10:28:37 
	 * @param 		：pjlx the pjlx to set
	 */
	public void setPjlx(String pjlx) {
		this.pjlx = pjlx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 下午02:36:24 
	 * @return		: the pjjsxm
	 */
	public String getPjjsxm() {
		return pjjsxm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-19 下午02:36:24 
	 * @param 		：pjjsxm the pjjsxm to set
	 */
	public void setPjjsxm(String pjjsxm) {
		this.pjjsxm = pjjsxm;
	}
	
	
	
	
	
}
