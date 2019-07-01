package com.zfsoft.xgxt.zxdk.tyxs.zzsq;

/**
 * @部门:学工产品事业部
 * @日期：2015-4-8 上午11:29:07 
 */  



import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 退役学生资助管理模块
 * @类功能描述: 申请表
 * @作者： 冯兰英[工号:1177]
 * @时间： 2015-4-8 上午11:29:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TyxsZzsq extends SuperAuditModel {
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String  id ; 
	private String  xh  ;
	private String  xn   ;
	private String  lsgx  ;
	private String  fxjdxlcc ;
	private String  rwqrxsj   ;
	private String  rwsj   ;
	private String  tysj  ;
	private String  fxsj   ;
	private String  fxjdnx ;
	private String  sqxfzj  ;
	private String  dyzzxf  ;
	private String  dezzxf ;
	private String dszzxf ;
	private String  dsizzxf ;
	private String  sqsj  ;
	private String  shzt   ;
	private String  splcid ;
	private String sqly;
	private String nd;
	private String je;
	private String dkbj;
	private String yhdm;
	private String dkhth;
	private String dkkssj;
	private String dkjssj;
	private String dklx;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	//下载相关
	private FormFile formfile;
	private String filepath;
	
	//西安科技大个性化
	private String fistyj;
	private String secondyj;
	private String thirdyj;
	private String fourthyj;
	private String xfyjzj;
	private String sfbb;
	/**
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}

	/**
	 * @param nd要设置的 nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}

	/**
	 * @return the je
	 */
	public String getJe() {
		return je;
	}

	/**
	 * @param je要设置的 je
	 */
	public void setJe(String je) {
		this.je = je;
	}

	/**
	 * @return the dkbj
	 */
	public String getDkbj() {
		return dkbj;
	}

	/**
	 * @param dkbj要设置的 dkbj
	 */
	public void setDkbj(String dkbj) {
		this.dkbj = dkbj;
	}

	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}

	/**
	 * @param yhdm要设置的 yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	/**
	 * @return the dkhth
	 */
	public String getDkhth() {
		return dkhth;
	}

	/**
	 * @param dkhth要设置的 dkhth
	 */
	public void setDkhth(String dkhth) {
		this.dkhth = dkhth;
	}

	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}

	/**
	 * @param dkkssj要设置的 dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}

	/**
	 * @return the dkjssj
	 */
	public String getDkjssj() {
		return dkjssj;
	}

	/**
	 * @param dkjssj要设置的 dkjssj
	 */
	public void setDkjssj(String dkjssj) {
		this.dkjssj = dkjssj;
	}

	/**
	 * @return the dklx
	 */
	public String getDklx() {
		return dklx;
	}

	/**
	 * @param dklx要设置的 dklx
	 */
	public void setDklx(String dklx) {
		this.dklx = dklx;
	}
	
	
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the lsgx
	 */
	public String getLsgx() {
		return lsgx;
	}
	/**
	 * @param lsgx要设置的 lsgx
	 */
	public void setLsgx(String lsgx) {
		this.lsgx = lsgx;
	}
	/**
	 * @return the fxjdxlcc
	 */
	public String getFxjdxlcc() {
		return fxjdxlcc;
	}
	/**
	 * @param fxjdxlcc要设置的 fxjdxlcc
	 */
	public void setFxjdxlcc(String fxjdxlcc) {
		this.fxjdxlcc = fxjdxlcc;
	}
	/**
	 * @return the rwqrxsj
	 */
	public String getRwqrxsj() {
		return rwqrxsj;
	}
	/**
	 * @param rwqrxsj要设置的 rwqrxsj
	 */
	public void setRwqrxsj(String rwqrxsj) {
		this.rwqrxsj = rwqrxsj;
	}
	/**
	 * @return the rwsj
	 */
	public String getRwsj() {
		return rwsj;
	}
	/**
	 * @param rwsj要设置的 rwsj
	 */
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}
	/**
	 * @return the tysj
	 */
	public String getTysj() {
		return tysj;
	}
	/**
	 * @param tysj要设置的 tysj
	 */
	public void setTysj(String tysj) {
		this.tysj = tysj;
	}
	/**
	 * @return the fxsj
	 */
	public String getFxsj() {
		return fxsj;
	}
	/**
	 * @param fxsj要设置的 fxsj
	 */
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}
	/**
	 * @return the fxjdnx
	 */
	public String getFxjdnx() {
		return fxjdnx;
	}
	/**
	 * @param fxjdnx要设置的 fxjdnx
	 */
	public void setFxjdnx(String fxjdnx) {
		this.fxjdnx = fxjdnx;
	}
	/**
	 * @return the sqxfzj
	 */
	public String getSqxfzj() {
		return sqxfzj;
	}
	/**
	 * @param sqxfzj要设置的 sqxfzj
	 */
	public void setSqxfzj(String sqxfzj) {
		this.sqxfzj = sqxfzj;
	}
	/**
	 * @return the dyzzxf
	 */
	public String getDyzzxf() {
		return dyzzxf;
	}
	/**
	 * @param dyzzxf要设置的 dyzzxf
	 */
	public void setDyzzxf(String dyzzxf) {
		this.dyzzxf = dyzzxf;
	}
	/**
	 * @return the dezzxf
	 */
	public String getDezzxf() {
		return dezzxf;
	}
	/**
	 * @param dezzxf要设置的 dezzxf
	 */
	public void setDezzxf(String dezzxf) {
		this.dezzxf = dezzxf;
	}
	/**
	 * @return the dszzxf
	 */
	public String getDszzxf() {
		return dszzxf;
	}
	/**
	 * @param dszzxf要设置的 dszzxf
	 */
	public void setDszzxf(String dszzxf) {
		this.dszzxf = dszzxf;
	}
	/**
	 * @return the dsizzxf
	 */
	public String getDsizzxf() {
		return dsizzxf;
	}
	/**
	 * @param dsizzxf要设置的 dsizzxf
	 */
	public void setDsizzxf(String dsizzxf) {
		this.dsizzxf = dsizzxf;
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
	 * @return the sqlcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param sqlcid要设置的 sqlcid
	 */
	public void setSqlcid(String splcid) {
		this.splcid = splcid;
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

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @return		: the fistyj
	 */
	public String getFistyj() {
		return fistyj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @param 		：fistyj the fistyj to set
	 */
	public void setFistyj(String fistyj) {
		this.fistyj = fistyj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @return		: the secondyj
	 */
	public String getSecondyj() {
		return secondyj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @param 		：secondyj the secondyj to set
	 */
	public void setSecondyj(String secondyj) {
		this.secondyj = secondyj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @return		: the thirdyj
	 */
	public String getThirdyj() {
		return thirdyj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @param 		：thirdyj the thirdyj to set
	 */
	public void setThirdyj(String thirdyj) {
		this.thirdyj = thirdyj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @return		: the fourthyj
	 */
	public String getFourthyj() {
		return fourthyj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @param 		：fourthyj the fourthyj to set
	 */
	public void setFourthyj(String fourthyj) {
		this.fourthyj = fourthyj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @return		: the xfyjzj
	 */
	public String getXfyjzj() {
		return xfyjzj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @param 		：xfyjzj the xfyjzj to set
	 */
	public void setXfyjzj(String xfyjzj) {
		this.xfyjzj = xfyjzj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @return		: the sfbb
	 */
	public String getSfbb() {
		return sfbb;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2017-11-13 上午11:03:40 
	 * @param 		：sfbb the sfbb to set
	 */
	public void setSfbb(String sfbb) {
		this.sfbb = sfbb;
	}
	
}
