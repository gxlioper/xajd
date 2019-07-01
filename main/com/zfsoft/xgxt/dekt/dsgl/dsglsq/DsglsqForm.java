/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsglsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class DsglsqForm extends ActionForm{
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
	
	private String ssm;
	private String sqsj;
	private String splc;
	private String shzt;
	private String filepath;
	private String xd;
	
	private String smid;
	private String ydzt;//阅读状态
	private String nj;
	private String cbs;
	private String author;
	private String lx;
	private String ebook;

	private String textStr;//传入参数，年级

	public String getTextStr() {
		return textStr;
	}

	public void setTextStr(String textStr) {
		this.textStr = textStr;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-17 下午06:23:38 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-17 下午06:23:38 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-17 下午06:23:38 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-17 下午06:23:38 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-5 下午04:34:47 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-17 下午06:26:41 
	 * @return		: the sm
	 */
	public String getSsm() {
		return ssm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-17 下午06:26:41 
	 * @param 		：sm the sm to set
	 */
	public void setSsm(String ssm) {
		this.ssm = ssm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-17 下午06:38:46 
	 * @return		: the xd
	 */
	public String getXd() {
		return xd;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-17 下午06:38:46 
	 * @param 		：xd the xd to set
	 */
	public void setXd(String xd) {
		this.xd = xd;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-15 上午09:13:13 
	 * @return		: the smid
	 */
	public String getSmid() {
		return smid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-15 上午09:13:13 
	 * @param 		：smid the smid to set
	 */
	public void setSmid(String smid) {
		this.smid = smid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-15 上午09:13:13 
	 * @return		: the ydzt
	 */
	public String getYdzt() {
		return ydzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-15 上午09:13:13 
	 * @param 		：ydzt the ydzt to set
	 */
	public void setYdzt(String ydzt) {
		this.ydzt = ydzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @return		: the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @param 		：nj the nj to set
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @return		: the cbs
	 */
	public String getCbs() {
		return cbs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @param 		：cbs the cbs to set
	 */
	public void setCbs(String cbs) {
		this.cbs = cbs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @return		: the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @param 		：author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @return		: the lx
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @param 		：lx the lx to set
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @return		: the ebook
	 */
	public String getEbook() {
		return ebook;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-16 上午09:00:36 
	 * @param 		：ebook the ebook to set
	 */
	public void setEbook(String ebook) {
		this.ebook = ebook;
	}



}
