/**
 * @部门:学工产品事业部
 * @日期：2016-11-29 下午04:18:40 
 */  
package com.zfsoft.xgxt.rcsw.gjgl.gjjg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cp[工号:1352]
 * @时间： 2016-11-29 下午04:18:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GjjgForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private ExportModel exportModel = new ExportModel();
	private String path;
	private String gjxxid;
	private String xh;
	private String xm;
	private String qsh;
	private String zbffzr;
	private String sy;
	private String qjkssj;
	private String qjjssj;
	private String qjjc;
	private String sfgq;
	private String bgqsj;
    private String bz;
    private FormFile drmb;//file
    private User user;//user,用于数据范围控制
	 private String filepath;//存放错误文件的路径
	 
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
	 * @return the gjxxid
	 */
	public String getGjxxid() {
		return gjxxid;
	}
	/**
	 * @param gjxxid要设置的 gjxxid
	 */
	public void setGjxxid(String gjxxid) {
		this.gjxxid = gjxxid;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path要设置的 path
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the zbffzr
	 */
	public String getZbffzr() {
		return zbffzr;
	}
	/**
	 * @param zbffzr要设置的 zbffzr
	 */
	public void setZbffzr(String zbffzr) {
		this.zbffzr = zbffzr;
	}
	/**
	 * @return the sy
	 */
	public String getSy() {
		return sy;
	}
	/**
	 * @param sy要设置的 sy
	 */
	public void setSy(String sy) {
		this.sy = sy;
	}
	/**
	 * @return the qjkssj
	 */
	public String getQjkssj() {
		return qjkssj;
	}
	/**
	 * @param qjkssj要设置的 qjkssj
	 */
	public void setQjkssj(String qjkssj) {
		this.qjkssj = qjkssj;
	}
	/**
	 * @return the qjjssj
	 */
	public String getQjjssj() {
		return qjjssj;
	}
	/**
	 * @param qjjssj要设置的 qjjssj
	 */
	public void setQjjssj(String qjjssj) {
		this.qjjssj = qjjssj;
	}
	/**
	 * @return the qjjc
	 */
	public String getQjjc() {
		return qjjc;
	}
	/**
	 * @param qjjc要设置的 qjjc
	 */
	public void setQjjc(String qjjc) {
		this.qjjc = qjjc;
	}
	/**
	 * @return the sfgq
	 */
	public String getSfgq() {
		return sfgq;
	}
	/**
	 * @param sfgq要设置的 sfgq
	 */
	public void setSfgq(String sfgq) {
		this.sfgq = sfgq;
	}
	/**
	 * @return the bgqsj
	 */
	public String getBgqsj() {
		return bgqsj;
	}
	/**
	 * @param bgqsj要设置的 bgqsj
	 */
	public void setBgqsj(String bgqsj) {
		this.bgqsj = bgqsj;
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
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmb要设置的 drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
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
	
	
	
	
	
	
	
	

}
