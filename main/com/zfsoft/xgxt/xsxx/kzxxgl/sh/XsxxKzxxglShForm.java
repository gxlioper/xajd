/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午09:33:09 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-19 上午09:33:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglShForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 8140417221625843289L;
	
	/** 
	 * @属性 pages : 分页模型 
	 */
	private Pages pages = new Pages();
	
	/** 
	 * @属性 searchModel : 高级查询模型 
	 */
	private SearchModel searchModel = new SearchModel();
	
	/** 
	 * @属性 exportModel : 导出模型 
	 */
	private ExportModel exportModel = new ExportModel();
	
	
	private String sqid;
	
	private String splc;
	
	private String xh;
	
	private String shzt;
	
	private String sqsj;
	
	private String czr;

	private String exendDateModuleId;
	//扩展数据
	private String extendData;
	//操作类型save or submit
	private String actionType;
	
	private String type;

	
	private String xtgwid;
	
	private String shid;
	
	private String shyj;
	
	private String shjg;
	
	private String thgw;
	
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}

	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}

	/**
	 * @param czr要设置的 czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}

	/**
	 * @return the exendDateModuleId
	 */
	public String getExendDateModuleId() {
		return exendDateModuleId;
	}

	/**
	 * @param exendDateModuleId要设置的 exendDateModuleId
	 */
	public void setExendDateModuleId(String exendDateModuleId) {
		this.exendDateModuleId = exendDateModuleId;
	}

	/**
	 * @return the extendData
	 */
	public String getExtendData() {
		return extendData;
	}

	/**
	 * @param extendData要设置的 extendData
	 */
	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType要设置的 actionType
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
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
	 * @return the xtgwid
	 */
	public String getXtgwid() {
		return xtgwid;
	}

	/**
	 * @param xtgwid要设置的 xtgwid
	 */
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
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
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}

	/**
	 * @param splcs要设置的 splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	
	

}
