/**
 * @部门:学工产品事业部
 * @日期：2014-6-24 上午09:40:03 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmjg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 团学活动活动结果管理模块
 * @类功能描述: 
 * @作者：夏夏[工号:1104]
 * @时间： 2014-6-24 上午09:40:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdXmjgForm extends ActionForm{
	
	private static final long serialVersionUID = 7187713079641588815L;
	
	private String sqid;
	private String guid;
	private String xh;
	private String xn;
	private String xq;
	private String xqmc;
	private String xmdm;
	private String xmmc;
	private String lbdm;//活动类别
	private String hdsj;//活动时间
	private String hddd;//活动地点
	private String sqly;//申请理由
	private String sqsj;//申请时间
	private String sqr; //申请人
	private String sjly;//数据来源
	private String cbdw;//承办单位
	private String fzrxm;//负责人姓名
	private String lxdh;//联系电话
	private String hdzt;//活动主题
	private String hdmdyy;//活动目的及意义
	private String hdfa;//活动方案
	private String sqwz;//申请物资
	private String wzsysj;//物资使用时间
	private String wzghsj;//物资归还时间
	private String xcfs;//宣传方式
	private String xgdd;//悬挂地点
	private String xckssj;//宣传开始时间
	private String xcjssj;//宣传结束时间
	private String hbsl;//海报数量
	private String xcnr;//宣传内容
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	//下载相关
	private FormFile formfile;
	private String filepath;
	
	private String hdkssj;	//活动开始时间
	private String hdjssj;	//活动结束时间
	private String hdsm;	//活动说明
	private String syxf;	//授予学分
	
	
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the hdsj
	 */
	public String getHdsj() {
		return hdsj;
	}
	/**
	 * @param hdsj要设置的 hdsj
	 */
	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	/**
	 * @return the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @param hddd要设置的 hddd
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
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
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	/**
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}
	/**
	 * @param formfile要设置的 formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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
	public String getHdkssj() {
		return hdkssj;
	}
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	public String getHdjssj() {
		return hdjssj;
	}
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	public String getLbdm() {
		return lbdm;
	}
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	public String getHdsm() {
		return hdsm;
	}
	public void setHdsm(String hdsm) {
		this.hdsm = hdsm;
	}
	public String getSyxf() {
		return syxf;
	}
	public void setSyxf(String syxf) {
		this.syxf = syxf;
	}
	/**
	 * @return the cbdw
	 */
	public String getCbdw() {
		return cbdw;
	}
	/**
	 * @param cbdw要设置的 cbdw
	 */
	public void setCbdw(String cbdw) {
		this.cbdw = cbdw;
	}
	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}
	/**
	 * @param fzrxm要设置的 fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh要设置的 lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the hdzt
	 */
	public String getHdzt() {
		return hdzt;
	}
	/**
	 * @param hdzt要设置的 hdzt
	 */
	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	/**
	 * @return the hdmdyy
	 */
	public String getHdmdyy() {
		return hdmdyy;
	}
	/**
	 * @param hdmdyy要设置的 hdmdyy
	 */
	public void setHdmdyy(String hdmdyy) {
		this.hdmdyy = hdmdyy;
	}
	/**
	 * @return the hdfa
	 */
	public String getHdfa() {
		return hdfa;
	}
	/**
	 * @param hdfa要设置的 hdfa
	 */
	public void setHdfa(String hdfa) {
		this.hdfa = hdfa;
	}
	/**
	 * @return the sqwz
	 */
	public String getSqwz() {
		return sqwz;
	}
	/**
	 * @param sqwz要设置的 sqwz
	 */
	public void setSqwz(String sqwz) {
		this.sqwz = sqwz;
	}
	/**
	 * @return the wzsysj
	 */
	public String getWzsysj() {
		return wzsysj;
	}
	/**
	 * @param wzsysj要设置的 wzsysj
	 */
	public void setWzsysj(String wzsysj) {
		this.wzsysj = wzsysj;
	}
	/**
	 * @return the wzghsj
	 */
	public String getWzghsj() {
		return wzghsj;
	}
	/**
	 * @param wzghsj要设置的 wzghsj
	 */
	public void setWzghsj(String wzghsj) {
		this.wzghsj = wzghsj;
	}
	/**
	 * @return the xcfs
	 */
	public String getXcfs() {
		return xcfs;
	}
	/**
	 * @param xcfs要设置的 xcfs
	 */
	public void setXcfs(String xcfs) {
		this.xcfs = xcfs;
	}
	/**
	 * @return the xgdd
	 */
	public String getXgdd() {
		return xgdd;
	}
	/**
	 * @param xgdd要设置的 xgdd
	 */
	public void setXgdd(String xgdd) {
		this.xgdd = xgdd;
	}
	/**
	 * @return the xckssj
	 */
	public String getXckssj() {
		return xckssj;
	}
	/**
	 * @param xckssj要设置的 xckssj
	 */
	public void setXckssj(String xckssj) {
		this.xckssj = xckssj;
	}
	/**
	 * @return the xcjssj
	 */
	public String getXcjssj() {
		return xcjssj;
	}
	/**
	 * @param xcjssj要设置的 xcjssj
	 */
	public void setXcjssj(String xcjssj) {
		this.xcjssj = xcjssj;
	}
	/**
	 * @return the hbsl
	 */
	public String getHbsl() {
		return hbsl;
	}
	/**
	 * @param hbsl要设置的 hbsl
	 */
	public void setHbsl(String hbsl) {
		this.hbsl = hbsl;
	}
	/**
	 * @return the xcnr
	 */
	public String getXcnr() {
		return xcnr;
	}
	/**
	 * @param xcnr要设置的 xcnr
	 */
	public void setXcnr(String xcnr) {
		this.xcnr = xcnr;
	}
	
	

}
