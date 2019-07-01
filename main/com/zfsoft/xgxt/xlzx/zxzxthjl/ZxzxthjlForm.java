/**
 * @部门:学工产品事业部
 * @日期：2016-7-1 上午09:57:38 
 */  
package com.zfsoft.xgxt.xlzx.zxzxthjl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 孟威[工号:1186]
 * @时间： 2016-7-1 上午09:57:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxthjlForm extends ActionForm{
	private static final long serialVersionUID = 1L;//serialVersionUID作用是序列化时保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
	
	SearchModel searchModel = new SearchModel();
	
	Pages pages = new Pages();
	private String type; //类型
	private ExportModel exportModel = new ExportModel();
	private String id; // 主键
	private String xh; //学号
	private String xm; //姓名
	private String ytr; //约谈人
	private String thsj; //谈话时间
	private String jbqkms; //基本情况描述
	private String cbpgdm; //初步评估代码
	private String ybwtlb; //一般问题类别
	private String ybwtsfzx; //一般问题是否咨询,是否预约咨询时间
	private String zajb; //心理障碍和疾病
	private String cbpgjg; //初步评估结果
	private String zajbsmzx; //心理障碍和疾病是否咨询
	private String sfzj; //是否转介
	private String bz; //备注
	
	
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
	 * @return the ytr
	 */
	public String getYtr() {
		return ytr;
	}
	/**
	 * @param ytr要设置的 ytr
	 */
	public void setYtr(String ytr) {
		this.ytr = ytr;
	}
	/**
	 * @return the thsj
	 */
	public String getThsj() {
		return thsj;
	}
	/**
	 * @param thsj要设置的 thsj
	 */
	public void setThsj(String thsj) {
		this.thsj = thsj;
	}
	/**
	 * @return the jbqkms
	 */
	public String getJbqkms() {
		return jbqkms;
	}
	/**
	 * @param jbqkms要设置的 jbqkms
	 */
	public void setJbqkms(String jbqkms) {
		this.jbqkms = jbqkms;
	}
	/**
	 * @return the cbpgdm
	 */
	public String getCbpgdm() {
		return cbpgdm;
	}
	/**
	 * @param cbpgdm要设置的 cbpgdm
	 */
	public void setCbpgdm(String cbpgdm) {
		this.cbpgdm = cbpgdm;
	}
	/**
	 * @return the ybwtlb
	 */
	public String getYbwtlb() {
		return ybwtlb;
	}
	/**
	 * @param ybwtlb要设置的 ybwtlb
	 */
	public void setYbwtlb(String ybwtlb) {
		this.ybwtlb = ybwtlb;
	}
	/**
	 * @return the ybwtsfzx
	 */
	public String getYbwtsfzx() {
		return ybwtsfzx;
	}
	/**
	 * @param ybwtsfzx要设置的 ybwtsfzx
	 */
	public void setYbwtsfzx(String ybwtsfzx) {
		this.ybwtsfzx = ybwtsfzx;
	}
	/**
	 * @return the zajb
	 */
	public String getZajb() {
		return zajb;
	}
	/**
	 * @param zajb要设置的 zajb
	 */
	public void setZajb(String zajb) {
		this.zajb = zajb;
	}
	/**
	 * @return the cbpgjg
	 */
	public String getCbpgjg() {
		return cbpgjg;
	}
	/**
	 * @param cbpgjg要设置的 cbpgjg
	 */
	public void setCbpgjg(String cbpgjg) {
		this.cbpgjg = cbpgjg;
	}
	/**
	 * @return the zajbsmzx
	 */
	public String getZajbsmzx() {
		return zajbsmzx;
	}
	/**
	 * @param zajbsmzx要设置的 zajbsmzx
	 */
	public void setZajbsmzx(String zajbsmzx) {
		this.zajbsmzx = zajbsmzx;
	}
	/**
	 * @return the sfzj
	 */
	public String getSfzj() {
		return sfzj;
	}
	/**
	 * @param sfzj要设置的 sfzj
	 */
	public void setSfzj(String sfzj) {
		this.sfzj = sfzj;
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
}
