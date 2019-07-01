/**
 * @部门:学工产品事业部
 * @日期：2014-6-24 上午09:40:03 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsq.xs;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 团学活动学生申请模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：夏夏[工号:1104]
 * @时间： 2014-6-25 下午13:08:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdXssqForm extends ActionForm{
	private String sqid;
	private String xh;
	private String xn;
	private String xq;
	private String xmdm;
	private String xqmc;
	private String xmmc;
	private String hdlb;//活动类别
	private String hdkssj;//活动开始时间
	private String hdjssj;//活动结束时间
	private String hdsj;//活动时间
	private String hddd;//活动地点
	private String sqly;//申请理由
	private String sqsj;//申请时间
	private String sqr; //申请人
	private String sjly;//数据来源
	private String shzt;//审核状态
	private String splc;//审批流程
	private String xmsm;
	private String hdsm;//活动说明
	private String sqkg;//申请开关
	private String shkg;//审核开关
	private String shkssj;//审核开始时间
	private String shjssj;//审核结束时间
	private String fbsj;//发布时间
	private String sqkssj;
	private String sqjssj;

	private String lbdm;
	private String kgbz;
	private String sqrssx;
	private String shrssx;
	private String rskzjb;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	//下载相关
	private FormFile formfile;
	private String filepath;
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
	 * @return the hdlb
	 */
	public String getHdlb() {
		return hdlb;
	}
	/**
	 * @param hdlb要设置的 hdlb
	 */
	public void setHdlb(String hdlb) {
		this.hdlb = hdlb;
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
	 * @return the xmsm
	 */
	public String getXmsm() {
		return xmsm;
	}
	/**
	 * @param xmsm要设置的 xmsm
	 */
	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
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
	 * @return the hdkssj
	 */
	public String getHdkssj() {
		return hdkssj;
	}
	/**
	 * @param hdkssj要设置的 hdkssj
	 */
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	/**
	 * @return the hdjssj
	 */
	public String getHdjssj() {
		return hdjssj;
	}
	/**
	 * @param hdjssj要设置的 hdjssj
	 */
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	/**
	 * @return the hdsm
	 */
	public String getHdsm() {
		return hdsm;
	}
	/**
	 * @param hdsm要设置的 hdsm
	 */
	public void setHdsm(String hdsm) {
		this.hdsm = hdsm;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the shkg
	 */
	public String getShkg() {
		return shkg;
	}
	/**
	 * @param shkg要设置的 shkg
	 */
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	/**
	 * @return the shkssj
	 */
	public String getShkssj() {
		return shkssj;
	}
	/**
	 * @param shkssj要设置的 shkssj
	 */
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	/**
	 * @return the shjssj
	 */
	public String getShjssj() {
		return shjssj;
	}
	/**
	 * @param shjssj要设置的 shjssj
	 */
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	/**
	 * @return the fbsj
	 */
	public String getFbsj() {
		return fbsj;
	}
	/**
	 * @param fbsj要设置的 fbsj
	 */
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdm要设置的 lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	/**
	 * @return the sqrssx
	 */
	public String getSqrssx() {
		return sqrssx;
	}
	/**
	 * @param sqrssx要设置的 sqrssx
	 */
	public void setSqrssx(String sqrssx) {
		this.sqrssx = sqrssx;
	}
	/**
	 * @return the shrssx
	 */
	public String getShrssx() {
		return shrssx;
	}
	/**
	 * @param shrssx要设置的 shrssx
	 */
	public void setShrssx(String shrssx) {
		this.shrssx = shrssx;
	}
	/**
	 * @return the rskzjb
	 */
	public String getRskzjb() {
		return rskzjb;
	}
	/**
	 * @param rskzjb要设置的 rskzjb
	 */
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	/**
	 * @return the kgbz
	 */
	public String getKgbz() {
		return kgbz;
	}
	/**
	 * @param kgbz要设置的 kgbz
	 */
	public void setKgbz(String kgbz) {
		this.kgbz = kgbz;
	}
	
	
	
	
	
	
	
	
	

}
