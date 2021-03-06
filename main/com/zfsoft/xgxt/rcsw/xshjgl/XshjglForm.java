/**
 * @部门:学工产品事业部
 * @日期：2015-8-31 下午04:47:08 
 */  
package com.zfsoft.xgxt.rcsw.xshjgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2015-9-14 上午09:34:48 
 * @版本： V5.17
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XshjglForm extends ActionForm {
	
	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	
	private String type; //类型
	
	private String hjglid; //户籍管理ID
	private String xh; //学号
	private String qyzt; //迁移状态0:迁入1:迁出
	private String qysj; //迁移时间
	private String bz; //备注
	private String xm; //姓名
	private String xb; //性别
	private String nj; //年级
	private String xymc; //学院名称
	private String bjmc; //班级名称
	private String zymc; //专业名称
	private String lxdh; //联系电话
	private String sfzh; //身份证号
	private String jgmc; //籍贯
	private String qyztmc; //qyztmc
	
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
	 * @return the hjglid
	 */
	public String getHjglid() {
		return hjglid;
	}
	/**
	 * @param hjglid要设置的 hjglid
	 */
	public void setHjglid(String hjglid) {
		this.hjglid = hjglid;
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
	 * @return the qyzt
	 */
	public String getQyzt() {
		return qyzt;
	}
	/**
	 * @param qyzt要设置的 qyzt
	 */
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}
	/**
	 * @return the qysj
	 */
	public String getQysj() {
		return qysj;
	}
	/**
	 * @param qysj要设置的 qysj
	 */
	public void setQysj(String qysj) {
		this.qysj = qysj;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymc要设置的 zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
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
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * @param sfzh要设置的 sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	
	/**
	 * @return the qyztmc
	 */
	public String getQyztmc() {
		return qyztmc;
	}
	/**
	 * @param qyztmc要设置的 qyztmc
	 */
	public void setQyztmc(String qyztmc) {
		this.qyztmc = qyztmc;
	}
	/**
	 * @return the jgmc
	 */
	public String getJgmc() {
		return jgmc;
	}
	/**
	 * @param jgmc要设置的 jgmc
	 */
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}
}