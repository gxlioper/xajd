/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:34:16 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.sq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-18 下午02:34:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkSqModel extends ActionForm {
	 String  sqid;
	 String  xh;
	 String  xn;
	 String  xq;
	 String  sqsj;
	 String  shzt;
	 String  splc;
	 String  jttg;
	 String  zxj;
	 String  jxj;
	 String  qgzxsr;
	 String  xnwxjk;
	 String  qtsr;
	 String  zxdksqje;
	 String  ffje;
	 String  sqje;
	 String  sqly;
	 String  sqr;
	 String  zxdksqsj;
	 String  ffsj;
	 String  type;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	//导出
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 
	//下载相关
	private FormFile formfile;
	private String filepath;
	 
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
	 * @return the jttg
	 */
	public String getJttg() {
		return jttg;
	}
	/**
	 * @param jttg要设置的 jttg
	 */
	public void setJttg(String jttg) {
		this.jttg = jttg;
	}
	/**
	 * @return the zxj
	 */
	public String getZxj() {
		return zxj;
	}
	/**
	 * @param zxj要设置的 zxj
	 */
	public void setZxj(String zxj) {
		this.zxj = zxj;
	}
	/**
	 * @return the jxj
	 */
	public String getJxj() {
		return jxj;
	}
	/**
	 * @param jxj要设置的 jxj
	 */
	public void setJxj(String jxj) {
		this.jxj = jxj;
	}
	/**
	 * @return the qgzxsr
	 */
	public String getQgzxsr() {
		return qgzxsr;
	}
	/**
	 * @param qgzxsr要设置的 qgzxsr
	 */
	public void setQgzxsr(String qgzxsr) {
		this.qgzxsr = qgzxsr;
	}
	/**
	 * @return the xnwxjk
	 */
	public String getXnwxjk() {
		return xnwxjk;
	}
	/**
	 * @param xnwxjk要设置的 xnwxjk
	 */
	public void setXnwxjk(String xnwxjk) {
		this.xnwxjk = xnwxjk;
	}
	/**
	 * @return the qtsr
	 */
	public String getQtsr() {
		return qtsr;
	}
	/**
	 * @param qtsr要设置的 qtsr
	 */
	public void setQtsr(String qtsr) {
		this.qtsr = qtsr;
	}
	/**
	 * @return the zxdksqje
	 */
	public String getZxdksqje() {
		return zxdksqje;
	}
	/**
	 * @param zxdksqje要设置的 zxdksqje
	 */
	public void setZxdksqje(String zxdksqje) {
		this.zxdksqje = zxdksqje;
	}
	/**
	 * @return the ffje
	 */
	public String getFfje() {
		return ffje;
	}
	/**
	 * @param ffje要设置的 ffje
	 */
	public void setFfje(String ffje) {
		this.ffje = ffje;
	}
	/**
	 * @return the sqje
	 */
	public String getSqje() {
		return sqje;
	}
	/**
	 * @param sqje要设置的 sqje
	 */
	public void setSqje(String sqje) {
		this.sqje = sqje;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	/**
	 * @return the zxdksqsj
	 */
	public String getZxdksqsj() {
		return zxdksqsj;
	}
	/**
	 * @param zxdksqsj要设置的 zxdksqsj
	 */
	public void setZxdksqsj(String zxdksqsj) {
		this.zxdksqsj = zxdksqsj;
	}
	/**
	 * @return the ffsj
	 */
	public String getFfsj() {
		return ffsj;
	}
	/**
	 * @param ffsj要设置的 ffsj
	 */
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
