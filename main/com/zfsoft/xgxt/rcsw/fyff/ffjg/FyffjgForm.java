/**
 * @部门:学工产品事业部
 * @日期：2014-4-10 上午10:15:42 
 */
package com.zfsoft.xgxt.rcsw.fyff.ffjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——费用发放——发放结果 管理模块
 * @类功能描述: 发放结果维护
 * @作者： cq [工号:785]
 * @时间： 2014-4-10 上午10:15:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FyffjgForm extends ActionForm {

	private static final long serialVersionUID = -2002064774952893703L;

	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();

	private String guid; // guid
	private String xh; // 学号
	private String ffxmdm; // 发放项目代码
	private String ffsj; // 发放时间
	private String fftjdm; // 发放途径代码
	private String sfje; // 实发金额
	private String ffzh; // 发放账号
	private String bfyfs; // 补发月份数
	private String bfje; // 补发金额
	private String bz; // 备注
	private String fffs; // 发放方式

	private String ffxmdmkz;
	private String fftjdmkz;
	private String ffsjkz;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getFfxmdm() {
		return ffxmdm;
	}

	public void setFfxmdm(String ffxmdm) {
		this.ffxmdm = ffxmdm;
	}

	public String getFfsj() {
		return ffsj;
	}

	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}

	public String getFftjdm() {
		return fftjdm;
	}

	public void setFftjdm(String fftjdm) {
		this.fftjdm = fftjdm;
	}

	public String getSfje() {
		return sfje;
	}

	public void setSfje(String sfje) {
		this.sfje = sfje;
	}

	public String getFfzh() {
		return ffzh;
	}

	public void setFfzh(String ffzh) {
		this.ffzh = ffzh;
	}

	public String getBfyfs() {
		return bfyfs;
	}

	public void setBfyfs(String bfyfs) {
		this.bfyfs = bfyfs;
	}

	public String getBfje() {
		return bfje;
	}

	public void setBfje(String bfje) {
		this.bfje = bfje;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFffs() {
		return fffs;
	}

	public void setFffs(String fffs) {
		this.fffs = fffs;
	}

	public String getFfxmdmkz() {
		if(StringUtils.isNull(ffxmdmkz)){
			setFfxmdmkz(ffxmdm);
		}
		return ffxmdmkz;
	}

	public void setFfxmdmkz(String ffxmdmkz) {
		this.ffxmdmkz = ffxmdmkz;
	}

	public String getFftjdmkz() {
		if(StringUtils.isNull(fftjdmkz)){
			setFftjdmkz(fftjdm);
		}
		return fftjdmkz;
	}

	public void setFftjdmkz(String fftjdmkz) {
		this.fftjdmkz = fftjdmkz;
	}

	public String getFfsjkz() {
		if(StringUtils.isNull(ffsjkz)){
			setFfsjkz(ffsj);
		}
		return ffsjkz;
	}

	public void setFfsjkz(String ffsjkz) {
		this.ffsjkz = ffsjkz;
	}

}
