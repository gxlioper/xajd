/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午02:54:21 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午02:54:21 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazcjgForm extends ActionForm{
	
	private static final long serialVersionUID = -4562302399219564190L;
	private Pages pages = new Pages();//分页
	private String type;//类型
	private SearchModel searchModel = new SearchModel();//高级查询
	private ExportModel exportModel = new ExportModel();//自定义导出
	
	private String guid;//结果ID(系统默认)
	private String xh;//学号
	private String zcfs;//转出方式,1:邮寄、2:自带
	private String yjdz;//邮寄地址
	private String yzbm;//邮政编码
	private String sjr;//收件人
	private String sjrdh;//收件人电话
	private String dwmc;//单位名称
	private String dwdz;//单位地址
	private String kdfs;//快递方式
	private String kddh;//快递单号
	private String yjsj;//邮寄时间(年月日)
	private String yjzt;//邮寄状态,1:已邮寄、2:未邮寄
	private String zddacn;//自带档案承诺
	private String yqtdrq;//预期提档日期(年月日)
	private String sjtdrq;//实际提档日期(年月日)
	private String sjtdr;//实际提档人
	private String dazcxx;//档案转成信息【1:已登记、2:已转出、3:未登记】
	private String sjly;//数据来源【1:申请审核、2:结果增加】
	private String ywid;//业务id
	private String sjlrr;//数据录入人
	private String sjlrsj;//数据录入时间【yyyy-mm-dd hh24:mi:ss】
	
	/**
	 * @return the dwdz
	 */
	public String getDwdz() {
		return dwdz;
	}
	/**
	 * @param dwdz要设置的 dwdz
	 */
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
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
	 * @return the zcfs
	 */
	public String getZcfs() {
		return zcfs;
	}
	/**
	 * @param zcfs要设置的 zcfs
	 */
	public void setZcfs(String zcfs) {
		this.zcfs = zcfs;
	}
	/**
	 * @return the yjdz
	 */
	public String getYjdz() {
		return yjdz;
	}
	/**
	 * @param yjdz要设置的 yjdz
	 */
	public void setYjdz(String yjdz) {
		this.yjdz = yjdz;
	}
	/**
	 * @return the yzbm
	 */
	public String getYzbm() {
		return yzbm;
	}
	/**
	 * @param yzbm要设置的 yzbm
	 */
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	/**
	 * @return the sjr
	 */
	public String getSjr() {
		return sjr;
	}
	/**
	 * @param sjr要设置的 sjr
	 */
	public void setSjr(String sjr) {
		this.sjr = sjr;
	}
	/**
	 * @return the sjrdh
	 */
	public String getSjrdh() {
		return sjrdh;
	}
	/**
	 * @param sjrdh要设置的 sjrdh
	 */
	public void setSjrdh(String sjrdh) {
		this.sjrdh = sjrdh;
	}
	/**
	 * @return the dwmc
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * @param dwmc要设置的 dwmc
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * @return the kdfs
	 */
	public String getKdfs() {
		return kdfs;
	}
	/**
	 * @param kdfs要设置的 kdfs
	 */
	public void setKdfs(String kdfs) {
		this.kdfs = kdfs;
	}
	/**
	 * @return the kddh
	 */
	public String getKddh() {
		return kddh;
	}
	/**
	 * @param kddh要设置的 kddh
	 */
	public void setKddh(String kddh) {
		this.kddh = kddh;
	}
	/**
	 * @return the yjsj
	 */
	public String getYjsj() {
		return yjsj;
	}
	/**
	 * @param yjsj要设置的 yjsj
	 */
	public void setYjsj(String yjsj) {
		this.yjsj = yjsj;
	}
	/**
	 * @return the yjzt
	 */
	public String getYjzt() {
		return yjzt;
	}
	/**
	 * @param yjzt要设置的 yjzt
	 */
	public void setYjzt(String yjzt) {
		this.yjzt = yjzt;
	}
	/**
	 * @return the zddacn
	 */
	public String getZddacn() {
		return zddacn;
	}
	/**
	 * @param zddacn要设置的 zddacn
	 */
	public void setZddacn(String zddacn) {
		this.zddacn = zddacn;
	}
	/**
	 * @return the yqtdrq
	 */
	public String getYqtdrq() {
		return yqtdrq;
	}
	/**
	 * @param yqtdrq要设置的 yqtdrq
	 */
	public void setYqtdrq(String yqtdrq) {
		this.yqtdrq = yqtdrq;
	}
	/**
	 * @return the sjtdrq
	 */
	public String getSjtdrq() {
		return sjtdrq;
	}
	/**
	 * @param sjtdrq要设置的 sjtdrq
	 */
	public void setSjtdrq(String sjtdrq) {
		this.sjtdrq = sjtdrq;
	}
	/**
	 * @return the sjtdr
	 */
	public String getSjtdr() {
		return sjtdr;
	}
	/**
	 * @param sjtdr要设置的 sjtdr
	 */
	public void setSjtdr(String sjtdr) {
		this.sjtdr = sjtdr;
	}
	/**
	 * @return the dazcxx
	 */
	public String getDazcxx() {
		return dazcxx;
	}
	/**
	 * @param dazcxx要设置的 dazcxx
	 */
	public void setDazcxx(String dazcxx) {
		this.dazcxx = dazcxx;
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
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the sjlrr
	 */
	public String getSjlrr() {
		return sjlrr;
	}
	/**
	 * @param sjlrr要设置的 sjlrr
	 */
	public void setSjlrr(String sjlrr) {
		this.sjlrr = sjlrr;
	}
	/**
	 * @return the sjlrsj
	 */
	public String getSjlrsj() {
		return sjlrsj;
	}
	/**
	 * @param sjlrsj要设置的 sjlrsj
	 */
	public void setSjlrsj(String sjlrsj) {
		this.sjlrsj = sjlrsj;
	}
}
