/**
 * @部门:学工产品事业部
 * @日期：2013-10-22 上午11:33:31 
 */  
package com.zfsoft.xgxt.wjcf.cfsb;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪处分管理模块
 * @类功能描述: (违纪处分上报管理) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-22 上午11:33:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfsbglForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String xn;
	private String xq;
	private String xqmc;
	private String xh;
	private String cfyydm;
	private String cflbdm;
	private String cfyymc;
	private String cflbmc;
	private String wjsj;
	private FormFile fj;
	private String wjssjg;
	private String bz;
	private String cfyj; // 处分依据
	private String cfwh;
	private String splcid;            //审批流程id
	private String cfid;            //处分Id
	private String sbb;            //上报人
	private String sbbxm;            //上报人
	private String sbsj;            //上报时间
	private String sbjg;            //上报结果
	private String kzzd1;            //扩展字段1
	private String kzzd2;            //扩展字段2
	private String kzzd3;            //扩展字段3
	private String kzzd4;            //扩展字段4
	private String kzzd5;            //扩展字段5
	private String fjmc;            //附件名称	
	private String filepath;//增加学生检讨书
	private String filepath2;//考场违纪记录单
	private String filepath3;//夹带纸条
	private String filepath4;//申辩会议记录

	public String getFilepath2() {
		return filepath2;
	}

	public void setFilepath2(String filepath2) {
		this.filepath2 = filepath2;
	}

	public String getFilepath3() {
		return filepath3;
	}

	public void setFilepath3(String filepath3) {
		this.filepath3 = filepath3;
	}

	public String getFilepath4() {
		return filepath4;
	}

	public void setFilepath4(String filepath4) {
		this.filepath4 = filepath4;
	}

	/**
	 * @return the cfyymc
	 */
	public String getCfyymc() {
		return cfyymc;
	}
	/**
	 * @param cfyymc要设置的 cfyymc
	 */
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	/**
	 * @return the cflbmc
	 */
	public String getCflbmc() {
		return cflbmc;
	}
	/**
	 * @param cflbmc要设置的 cflbmc
	 */
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the cfid
	 */
	public String getCfid() {
		return cfid;
	}
	/**
	 * @param cfid要设置的 cfid
	 */
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	/**
	 * @return the sbb
	 */
	public String getSbb() {
		return sbb;
	}
	/**
	 * @param sbb要设置的 sbb
	 */
	public void setSbb(String sbb) {
		this.sbb = sbb;
	}
	/**
	 * @return the sbbxm
	 */
	public String getSbbxm() {
		return sbbxm;
	}
	/**
	 * @param sbbxm要设置的 sbbxm
	 */
	public void setSbbxm(String sbbxm) {
		this.sbbxm = sbbxm;
	}
	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}
	/**
	 * @param sbsj要设置的 sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	/**
	 * @return the sbjg
	 */
	public String getSbjg() {
		return sbjg;
	}
	/**
	 * @param sbjg要设置的 sbjg
	 */
	public void setSbjg(String sbjg) {
		this.sbjg = sbjg;
	}
	/**
	 * @return the kzzd1
	 */
	public String getKzzd1() {
		return kzzd1;
	}
	/**
	 * @param kzzd1要设置的 kzzd1
	 */
	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}
	/**
	 * @return the kzzd2
	 */
	public String getKzzd2() {
		return kzzd2;
	}
	/**
	 * @param kzzd2要设置的 kzzd2
	 */
	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}
	/**
	 * @return the kzzd3
	 */
	public String getKzzd3() {
		return kzzd3;
	}
	/**
	 * @param kzzd3要设置的 kzzd3
	 */
	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}
	/**
	 * @return the kzzd4
	 */
	public String getKzzd4() {
		return kzzd4;
	}
	/**
	 * @param kzzd4要设置的 kzzd4
	 */
	public void setKzzd4(String kzzd4) {
		this.kzzd4 = kzzd4;
	}
	/**
	 * @return the kzzd5
	 */
	public String getKzzd5() {
		return kzzd5;
	}
	/**
	 * @param kzzd5要设置的 kzzd5
	 */
	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmc要设置的 fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath要设置的 filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the cfyydm
	 */
	public String getCfyydm() {
		return cfyydm;
	}
	/**
	 * @param cfyydm要设置的 cfyydm
	 */
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	/**
	 * @return the cflbdm
	 */
	public String getCflbdm() {
		return cflbdm;
	}
	/**
	 * @param cflbdm要设置的 cflbdm
	 */
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	/**
	 * @return the wjsj
	 */
	public String getWjsj() {
		return wjsj;
	}
	/**
	 * @param wjsj要设置的 wjsj
	 */
	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}
	/**
	 * @return the fj
	 */
	public FormFile getFj() {
		return fj;
	}
	/**
	 * @param fj要设置的 fj
	 */
	public void setFj(FormFile fj) {
		this.fj = fj;
	}
	/**
	 * @return the wjssjg
	 */
	public String getWjssjg() {
		return wjssjg;
	}
	/**
	 * @param wjssjg要设置的 wjssjg
	 */
	public void setWjssjg(String wjssjg) {
		this.wjssjg = wjssjg;
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
	 * @return the cfyj
	 */
	public String getCfyj() {
		return cfyj;
	}
	/**
	 * @param cfyj要设置的 cfyj
	 */
	public void setCfyj(String cfyj) {
		this.cfyj = cfyj;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
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
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getXqmc() {
		return xqmc;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	
	
	
}
