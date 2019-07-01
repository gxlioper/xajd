/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午03:13:09 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午03:13:09 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazcsqForm extends ActionForm{
	
	private static final long serialVersionUID = -4562302399219564190L;
	private Pages pages = new Pages();//分页
	private String type;//类型
	private SearchModel searchModel = new SearchModel();//高级查询
	private ExportModel exportModel = new ExportModel();//自定义导出
	private String sqid;//申请ID(系统默认)
	private String xh;//学号
	private String zcfs;//转出方式
	private String yjdz;//邮寄地址
	private String yzbm;//邮政编码
	private String sjr;//收件人
	private String sjrdh;//收件人电话
	private String dwmc;//单位名称
	private String dwdz;//单位地址
	private String zddacn;//自带档案承诺
	private String yqtdrq;//预期提档日期(年月日)
	private String sjlrr;//数据录入人
	private String sjlrsj;//数据录入时间
	private String shzt;//审核状态
	private String splcid;//审批流程ID
	private String saveFlag;//保存类型(保存时的参数)
	
	/**
	 * @return the saveFlag
	 */
	public String getSaveFlag() {
		return saveFlag;
	}
	/**
	 * @param saveFlag要设置的 saveFlag
	 */
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
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
}
