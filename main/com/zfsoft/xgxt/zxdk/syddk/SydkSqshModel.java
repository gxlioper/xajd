/**
 * @部门:学工产品事业部
 * @日期：2015-7-1 下午04:09:29 
 */
package com.zfsoft.xgxt.zxdk.syddk;

import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： ChenQ[工号:856]
 * @时间： 2015-7-1 下午04:09:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SydkSqshModel extends SuperAuditModel {

	private static final long serialVersionUID = 1L;

	private String id;
	private String xn;
	private String xh;
	private String yhdm;//银行代码
	private String yhmc;
	private String dkje;// 贷款金额
	private String dkqx;// 贷款期限
	private String zsysf;// 住宿应收费
	private String xfysf;// 学费应收费
	private String sqsj;
	private String sqly;
	private String shzt;
	private String splcid;
	
	private String htbh;
	private String dkkssj;
	private String hzjym;
    
	private String[] xf;
	private String[] zsf;
	private String[] shf;
	private String[] dkxn;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	//下载相关
	private FormFile formfile;
	private String filepath;
    
	//浙大批量审核相关
	private String[] ids;
	private String[] gwids;
	private String[] xhs;
	
	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * @param ids要设置的 ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
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

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String[] getXf() {
		return xf;
	}

	public void setXf(String[] xf) {
		this.xf = xf;
	}

	public String[] getZsf() {
		return zsf;
	}

	public void setZsf(String[] zsf) {
		this.zsf = zsf;
	}

	public String[] getShf() {
		return shf;
	}

	public void setShf(String[] shf) {
		this.shf = shf;
	}

	public String[] getDkxn() {
		return dkxn;
	}

	public void setDkxn(String[] dkxn) {
		this.dkxn = dkxn;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getDkkssj() {
		return dkkssj;
	}

	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}

	public String getHzjym() {
		return hzjym;
	}

	public void setHzjym(String hzjym) {
		this.hzjym = hzjym;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getDkje() {
		return dkje;
	}

	public void setDkje(String dkje) {
		this.dkje = dkje;
	}

	public String getDkqx() {
		return dkqx;
	}

	public void setDkqx(String dkqx) {
		this.dkqx = dkqx;
	}

	public String getZsysf() {
		return zsysf;
	}

	public void setZsysf(String zsysf) {
		this.zsysf = zsysf;
	}

	public String getXfysf() {
		return xfysf;
	}

	public void setXfysf(String xfysf) {
		this.xfysf = xfysf;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getSplcid() {
		return splcid;
	}

	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public FormFile getFormfile() {
		return formfile;
	}

	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
