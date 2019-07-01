/**
 * @部门:学工产品事业部
 * @日期：2015-1-20 上午11:38:01 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 早晚自习考勤管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-20 上午11:38:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmsbjgForm extends ActionForm{
	
	private String jgid;
	private String xmdm;
	private String xn;
	private String xq;
	private String xmmc;
	private String xmjbdm;
	private String sbbmdm;
	private String sskmdm;//所属科目代码
	private String kcyrs;//可参与人数
	private String xmkssj;
	private String xmsbsj;
	private String jcxf;
	private String sbr;
	private String lxdh;
	private String sfsljx;//是否设立奖项：0|否，1|是
	private String sfrm;//是否热门
	private String xmms;//项目描述
	private String dkfyj;//得/扣分依据
	private String cyyq;//参与要求
	private String czr;//操作人
	private String sjly;//数据来源
	private String splc;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String rskzjb;
	private String type;
	private String[] xhArr;
    private String[] ylzd1Arr;
    private String[] ylzd2Arr;
    private String[] ylzd3Arr;
    private String tjsj;
    private String xfrdjgzt;
    private String rdsqid;
    private String rdsplc;
    private String xfrdsqzt;
    private String csms;
    private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String xmcd;//项目场地
	private String bkgs; //板块归属
	
	
	/**
	 * @return the xmcd
	 */
	public String getXmcd() {
		return xmcd;
	}
	/**
	 * @param xmcd要设置的 xmcd
	 */
	public void setXmcd(String xmcd) {
		this.xmcd = xmcd;
	}
	/**
	 * @return the bkgs
	 */
	public String getBkgs() {
		return bkgs;
	}
	/**
	 * @param bkgs要设置的 bkgs
	 */
	public void setBkgs(String bkgs) {
		this.bkgs = bkgs;
	}
	/**
	 * @return the csms
	 */
	public String getCsms() {
		return csms;
	}
	/**
	 * @param csms要设置的 csms
	 */
	public void setCsms(String csms) {
		this.csms = csms;
	}
	
	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	 * @return the xmjbdm
	 */
	public String getXmjbdm() {
		return xmjbdm;
	}
	/**
	 * @param xmjbdm要设置的 xmjbdm
	 */
	public void setXmjbdm(String xmjbdm) {
		this.xmjbdm = xmjbdm;
	}
	/**
	 * @return the sbbmdm
	 */
	public String getSbbmdm() {
		return sbbmdm;
	}
	/**
	 * @param sbbmdm要设置的 sbbmdm
	 */
	public void setSbbmdm(String sbbmdm) {
		this.sbbmdm = sbbmdm;
	}
	/**
	 * @return the sskmdm
	 */
	public String getSskmdm() {
		return sskmdm;
	}
	/**
	 * @param sskmdm要设置的 sskmdm
	 */
	public void setSskmdm(String sskmdm) {
		this.sskmdm = sskmdm;
	}
	/**
	 * @return the kcyrs
	 */
	public String getKcyrs() {
		return kcyrs;
	}
	/**
	 * @param kcyrs要设置的 kcyrs
	 */
	public void setKcyrs(String kcyrs) {
		this.kcyrs = kcyrs;
	}
	/**
	 * @return the xmkssj
	 */
	public String getXmkssj() {
		return xmkssj;
	}
	/**
	 * @param xmkssj要设置的 xmkssj
	 */
	public void setXmkssj(String xmkssj) {
		this.xmkssj = xmkssj;
	}
	/**
	 * @return the xmsbsj
	 */
	public String getXmsbsj() {
		return xmsbsj;
	}
	/**
	 * @param xmsbsj要设置的 xmsbsj
	 */
	public void setXmsbsj(String xmsbsj) {
		this.xmsbsj = xmsbsj;
	}
	/**
	 * @return the jcxf
	 */
	public String getJcxf() {
		return jcxf;
	}
	/**
	 * @param jcxf要设置的 jcxf
	 */
	public void setJcxf(String jcxf) {
		this.jcxf = jcxf;
	}
	/**
	 * @return the sbr
	 */
	public String getSbr() {
		return sbr;
	}
	/**
	 * @param sbr要设置的 sbr
	 */
	public void setSbr(String sbr) {
		this.sbr = sbr;
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
	 * @return the sfsljx
	 */
	public String getSfsljx() {
		return sfsljx;
	}
	/**
	 * @param sfsljx要设置的 sfsljx
	 */
	public void setSfsljx(String sfsljx) {
		this.sfsljx = sfsljx;
	}
	/**
	 * @return the xmms
	 */
	public String getXmms() {
		return xmms;
	}
	/**
	 * @param xmms要设置的 xmms
	 */
	public void setXmms(String xmms) {
		this.xmms = xmms;
	}
	/**
	 * @return the dkfyj
	 */
	public String getDkfyj() {
		return dkfyj;
	}
	/**
	 * @param dkfyj要设置的 dkfyj
	 */
	public void setDkfyj(String dkfyj) {
		this.dkfyj = dkfyj;
	}
	/**
	 * @return the cyyq
	 */
	public String getCyyq() {
		return cyyq;
	}
	/**
	 * @param cyyq要设置的 cyyq
	 */
	public void setCyyq(String cyyq) {
		this.cyyq = cyyq;
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
	 * @return the sfrm
	 */
	public String getSfrm() {
		return sfrm;
	}
	/**
	 * @param sfrm要设置的 sfrm
	 */
	public void setSfrm(String sfrm) {
		this.sfrm = sfrm;
	}
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArr要设置的 xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	/**
	 * @return the ylzd1Arr
	 */
	public String[] getYlzd1Arr() {
		return ylzd1Arr;
	}
	/**
	 * @param ylzd1Arr要设置的 ylzd1Arr
	 */
	public void setYlzd1Arr(String[] ylzd1Arr) {
		this.ylzd1Arr = ylzd1Arr;
	}
	/**
	 * @return the ylzd2Arr
	 */
	public String[] getYlzd2Arr() {
		return ylzd2Arr;
	}
	/**
	 * @param ylzd2Arr要设置的 ylzd2Arr
	 */
	public void setYlzd2Arr(String[] ylzd2Arr) {
		this.ylzd2Arr = ylzd2Arr;
	}
	/**
	 * @return the ylzd3Arr
	 */
	public String[] getYlzd3Arr() {
		return ylzd3Arr;
	}
	/**
	 * @param ylzd3Arr要设置的 ylzd3Arr
	 */
	public void setYlzd3Arr(String[] ylzd3Arr) {
		this.ylzd3Arr = ylzd3Arr;
	}
	/**
	 * @return the tjsj
	 */
	public String getTjsj() {
		return tjsj;
	}
	/**
	 * @param tjsj要设置的 tjsj
	 */
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	/**
	 * @return the xfrdjgzt
	 */
	public String getXfrdjgzt() {
		return xfrdjgzt;
	}
	/**
	 * @param xfrdjgzt要设置的 xfrdjgzt
	 */
	public void setXfrdjgzt(String xfrdjgzt) {
		this.xfrdjgzt = xfrdjgzt;
	}
	/**
	 * @return the rdsqid
	 */
	public String getRdsqid() {
		return rdsqid;
	}
	/**
	 * @param rdsqid要设置的 rdsqid
	 */
	public void setRdsqid(String rdsqid) {
		this.rdsqid = rdsqid;
	}
	/**
	 * @return the rdsplc
	 */
	public String getRdsplc() {
		return rdsplc;
	}
	/**
	 * @param rdsplc要设置的 rdsplc
	 */
	public void setRdsplc(String rdsplc) {
		this.rdsplc = rdsplc;
	}
	/**
	 * @return the xfrdsqzt
	 */
	public String getXfrdsqzt() {
		return xfrdsqzt;
	}
	/**
	 * @param xfrdsqzt要设置的 xfrdsqzt
	 */
	public void setXfrdsqzt(String xfrdsqzt) {
		this.xfrdsqzt = xfrdsqzt;
	}
	

}
