/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsgljg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class DsgljgForm extends ActionForm{
	private Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	private String type;
	private String xh;
	private String xn;
	private String xq;
	private String jgid;
	
	private String ssm;
	private String sqsj;
	private String splc;
	private String shzt;
	private String filepath;
	private String xd;
	private String sqid;
	private String sjly;
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：jgid the jgid to set
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the ssm
	 */
	public String getSsm() {
		return ssm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：ssm the ssm to set
	 */
	public void setSsm(String ssm) {
		this.ssm = ssm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the xd
	 */
	public String getXd() {
		return xd;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：xd the xd to set
	 */
	public void setXd(String xd) {
		this.xd = xd;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @return		: the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-18 上午09:41:39 
	 * @param 		：sjly the sjly to set
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	
	
}
