/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午02:35:46 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.sh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-18 下午02:35:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxdkShModel extends ActionForm {
	private String  sqid;
	private String  xh;
	private String  xn;
	private String  xq;
	private String  sqsj;
	private String  shzt;
	private String  splc;
	private String  jttg;
	private String  zxj;
	private String  jxj;
	private String  qgzxsr;
	private String  xnwxjk;
	private String  qtsr;
	private String  zxdksqje;
	private String  zxdksqsj;
	private String  ffje;
	private String  sqje;
	private String  sqly;
	private String  sqr;
	private String  ffsj;
	private String  type;
	private String  pzje;
	private String shid;
    private String shjg;
    private String shyj;
    private String gwid;
    private String thgw;
    private String shlc;
    private String zd1;//审核业务字段名称【批准金额】
    private String zd3;//审核业务字段数据【批准金额】
    //批量审核用
    private String[] id;
    private String[] gwids;
    private String[] xhs;
    private static final long serialVersionUID = 1L;
    private SearchModel searchModel = new SearchModel();
   //导出
   private ExportModel exportModel = new ExportModel();
   private Pages pages = new Pages();
   
   //下载相关
   private FormFile formfile;
   private String filepath;
   
    /**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	
	 /**
	 * @return the pzje
	 */
	public String getPzje() {
		return pzje;
	}
	/**
	 * @param pzje要设置的 pzje
	 */
	public void setPzje(String pzje) {
		this.pzje = pzje;
	}
	 
	 /**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
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
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
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
