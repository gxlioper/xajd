/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class JspjshForm extends ActionForm{
	private Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	private String type;
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
	private String ylzd1;
	private String ylzd2;
	private String ylzd3;
	private String ylzd4;
	private String ylzd5;
	
	
	private String shid;
	private String shlx;
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String thgw;//岗位退回
	private String shjg;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the pjjszgh
	 */
	public String getPjjszgh() {
		return pjjszgh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：pjjszgh the pjjszgh to set
	 */
	public void setPjjszgh(String pjjszgh) {
		this.pjjszgh = pjjszgh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the pjjsxm
	 */
	public String getPjjsxm() {
		return pjjsxm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：pjjsxm the pjjsxm to set
	 */
	public void setPjjsxm(String pjjsxm) {
		this.pjjsxm = pjjsxm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the pjsj
	 */
	public String getPjsj() {
		return pjsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：pjsj the pjsj to set
	 */
	public void setPjsj(String pjsj) {
		this.pjsj = pjsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：pj the pj to set
	 */
	public void setPj(String pj) {
		this.pj = pj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：ylzd1 the ylzd1 to set
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：ylzd2 the ylzd2 to set
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：ylzd3 the ylzd3 to set
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：ylzd4 the ylzd4 to set
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：ylzd5 the ylzd5 to set
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shid the shid to set
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shlx
	 */
	public String getShlx() {
		return shlx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shlx the shlx to set
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：ywid the ywid to set
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shsj the shsj to set
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shr the shr to set
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shyj the shyj to set
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shlc the shlc to set
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：gwid the gwid to set
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shztmc the shztmc to set
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：thgw the thgw to set
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：shjg the shjg to set
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：id the id to set
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：gwids the gwids to set
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：xhs the xhs to set
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @return		: the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-22 上午08:58:06 
	 * @param 		：splcs the splcs to set
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	
}