/**
 * @部门:学工产品事业部
 * @日期：2014-7-17 上午10:06:00 
 */ 
package com.zfsoft.xgxt.szdw.thjl;

import org.apache.struts.action.ActionForm;

import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述: 思政队伍谈话记录
 * @作者： cq [工号:785]
 * @时间： 2014-7-17 上午10:06:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwThjlForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();

	private String sqid;
	private String id; // 主键
	
	private String xh; //学号

	private String zgh; // 编号（职工号）
	
	private String thsj; //谈话时间（谈话日期）
	
	private String thnr; // 谈话内容

	private String yyfx;//原因分析

	private String gjcs;//改进措施

	private String qtjy;//其他建议

	private String sdktsj;//深度恳谈时间

	private String sfsdkt;//是否需要深度恳谈

	private FormFile formfile;
	private String filepath;

	private String bz; // 备注

	private String cjsj; //创建时间

	private String  sjzt; //数据状态0失效1正常

	private String  kssj; //开始时间

	private String  jssj; //结束时间

	private String  thsc; //谈话时长

	private String  thlx; //谈话类型代码

	private String  khhwt; //困惑和问题

	private String  mtyd; //面谈要点
	private String  sfzdgz; //是否重点关注
	private String  gzdj; //关注等级
	private String ythrgx;//与谈话人关系
	private String gzqx;//关注期限

	//温州大学个性化
	private String wtms;//问题描述
	private String tgbz1;//提供帮助1
	private String tgbz2;//提供帮助2
	private String tgbz3;//提供帮助3
	private String bzjg;//帮助结果
	private String sqsj;
	private String splc;
	private String shzt;
	private String sjly;
	private String type;

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

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getSdktsj() {
		return sdktsj;
	}

	public void setSdktsj(String sdktsj) {
		this.sdktsj = sdktsj;
	}

	public String getSfsdkt() {
		return sfsdkt;
	}

	public void setSfsdkt(String sfsdkt) {
		this.sfsdkt = sfsdkt;
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

	public String getQtjy() {
		return qtjy;
	}

	public void setQtjy(String qtjy) {
		this.qtjy = qtjy;
	}

	public String getYyfx() {
		return yyfx;
	}

	public void setYyfx(String yyfx) {
		this.yyfx = yyfx;
	}

	public String getGjcs() {
		return gjcs;
	}

	public void setGjcs(String gjcs) {
		this.gjcs = gjcs;
	}

	public String getBzjg() {
		return bzjg;
	}

	public void setBzjg(String bzjg) {
		this.bzjg = bzjg;
	}

	public String getWtms() {
		return wtms;
	}

	public void setWtms(String wtms) {
		this.wtms = wtms;
	}

	public String getTgbz1() {
		return tgbz1;
	}

	public void setTgbz1(String tgbz1) {
		this.tgbz1 = tgbz1;
	}

	public String getTgbz2() {
		return tgbz2;
	}

	public void setTgbz2(String tgbz2) {
		this.tgbz2 = tgbz2;
	}

	public String getTgbz3() {
		return tgbz3;
	}

	public void setTgbz3(String tgbz3) {
		this.tgbz3 = tgbz3;
	}

	/**
	 * @return the gzqx
	 */
	public String getGzqx() {
		return gzqx;
	}

	/**
	 * @param gzqx要设置的 gzqx
	 */
	public void setGzqx(String gzqx) {
		this.gzqx = gzqx;
	}

	private ExportModel exportModel = new ExportModel();
	

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getThsj() {
		return thsj;
	}

	public void setThsj(String thsj) {
		this.thsj = thsj;
	}

	public String getThnr() {
		return thnr;
	}

	public void setThnr(String thnr) {
		this.thnr = thnr;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getSjzt() {
		return sjzt;
	}

	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
	}
	
	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getThsc() {
		return thsc;
	}

	public void setThsc(String thsc) {
		this.thsc = thsc;
	}

	public String getThlx() {
		return thlx;
	}

	public void setThlx(String thlx) {
		this.thlx = thlx;
	}

	public String getKhhwt() {
		return khhwt;
	}

	public void setKhhwt(String khhwt) {
		this.khhwt = khhwt;
	}

	public String getMtyd() {
		return mtyd;
	}

	public void setMtyd(String mtyd) {
		this.mtyd = mtyd;
	}
	
	public String getSfzdgz() {
		return sfzdgz;
	}

	public void setSfzdgz(String sfzdgz) {
		this.sfzdgz = sfzdgz;
	}

	public String getGzdj() {
		return gzdj;
	}

	public void setGzdj(String gzdj) {
		this.gzdj = gzdj;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the ythrgx
	 */
	public String getYthrgx() {
		return ythrgx;
	}

	/**
	 * @param ythrgx要设置的 ythrgx
	 */
	public void setYthrgx(String ythrgx) {
		this.ythrgx = ythrgx;
	}
	
	

}
