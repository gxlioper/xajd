/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.rcpysh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class RcpyshForm extends ActionForm{
	/**
	 * @fields ：serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 2817698637934819639L;
	private Pages pages = new Pages();
	SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String xh;
	private String sqid;
	private String sqsj;
	private String xmmc;
	private String pylb;
	private String khzb;
	private String xztj;
	private String rczy;
	private String xjzj;
	private String filepath;
	
	private String shid;
	private String shzt;
	private String splc;
	
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
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：xmmc the xmmc to set
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the pylb
	 */
	public String getPylb() {
		return pylb;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：pylb the pylb to set
	 */
	public void setPylb(String pylb) {
		this.pylb = pylb;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the khzb
	 */
	public String getKhzb() {
		return khzb;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：khzb the khzb to set
	 */
	public void setKhzb(String khzb) {
		this.khzb = khzb;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the xztj
	 */
	public String getXztj() {
		return xztj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：xztj the xztj to set
	 */
	public void setXztj(String xztj) {
		this.xztj = xztj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the rczy
	 */
	public String getRczy() {
		return rczy;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：rczy the rczy to set
	 */
	public void setRczy(String rczy) {
		this.rczy = rczy;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the xjzj
	 */
	public String getXjzj() {
		return xjzj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：xjzj the xjzj to set
	 */
	public void setXjzj(String xjzj) {
		this.xjzj = xjzj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the shlx
	 */
	public String getShlx() {
		return shlx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：shlx the shlx to set
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：ywid the ywid to set
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：shsj the shsj to set
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：shr the shr to set
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：shyj the shyj to set
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：shlc the shlc to set
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：gwid the gwid to set
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：shztmc the shztmc to set
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：thgw the thgw to set
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：shjg the shjg to set
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：id the id to set
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：gwids the gwids to set
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：xhs the xhs to set
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @return		: the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-14 上午10:56:26 
	 * @param 		：splcs the splcs to set
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-15 上午09:21:36 
	 * @return		: the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-15 上午09:21:36 
	 * @param 		：shid the shid to set
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	
	
	
}
