/**
 * @部门:学工产品事业部
 * @日期：2015-4-2 上午10:30:25 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.bxbxsh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-4-2 上午10:30:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BxbxshForm extends ActionForm{
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	private String sqid;
	private String xh;
	private String xm;
	private String xb;
	private String nj;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String zlbh;
	private String clzb;
	private String yfjsd;
	private String bxxz;
	private String bxje;
	private String lx;
	private String jbnr;
	private String csfysj;
	private String bxshr;
	private String shr;
	private String sqsj;
	
	private String jzyy;
	private String jzsj;
	private String bzqk;
	private String ylzd1;
	private String ylzd2;
	private String ylzd3;
	private String ylzd4;
	private String ylzd5;
	private String shzt;
	private String splc;
	
	private String ywid;
	private String shsj;

	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String shid;
	private String thgw;//岗位退回
	private String shjg;
	
	private String[] id;
	private String[] gwids;
	private String[] splcs;
	private String[] xhs;
	//下载相关
	private FormFile formfile;
	private String filepath;
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
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getZlbh() {
		return zlbh;
	}
	public void setZlbh(String zlbh) {
		this.zlbh = zlbh;
	}
	public String getClzb() {
		return clzb;
	}
	public void setClzb(String clzb) {
		this.clzb = clzb;
	}
	public String getYfjsd() {
		return yfjsd;
	}
	public void setYfjsd(String yfjsd) {
		this.yfjsd = yfjsd;
	}
	public String getBxxz() {
		return bxxz;
	}
	public void setBxxz(String bxxz) {
		this.bxxz = bxxz;
	}
	public String getBxje() {
		return bxje;
	}
	public void setBxje(String bxje) {
		this.bxje = bxje;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getJbnr() {
		return jbnr;
	}
	public void setJbnr(String jbnr) {
		this.jbnr = jbnr;
	}
	public String getCsfysj() {
		return csfysj;
	}
	public void setCsfysj(String csfysj) {
		this.csfysj = csfysj;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getJzyy() {
		return jzyy;
	}
	public void setJzyy(String jzyy) {
		this.jzyy = jzyy;
	}
	public String getJzsj() {
		return jzsj;
	}
	public void setJzsj(String jzsj) {
		this.jzsj = jzsj;
	}
	public String getBzqk() {
		return bzqk;
	}
	public void setBzqk(String bzqk) {
		this.bzqk = bzqk;
	}
	public String getYlzd1() {
		return ylzd1;
	}
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	public String getYlzd2() {
		return ylzd2;
	}
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	public String getYlzd3() {
		return ylzd3;
	}
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	public String getYlzd4() {
		return ylzd4;
	}
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	public String getYlzd5() {
		return ylzd5;
	}
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getShztmc() {
		return shztmc;
	}
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
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
	public String getBxshr() {
		return bxshr;
	}
	public void setBxshr(String bxshr) {
		this.bxshr = bxshr;
	}
	public String[] getSplcs() {
		return splcs;
	}
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	
	
	

}
