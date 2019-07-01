/**
 * @部门:学工产品事业部
 * @日期：2018-5-8 下午02:17:42 
 */  
package xsgzgl.gygl.rcjc.xswsf;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-卫生检查-学生卫生分（苏州卫生职业技术学院）
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-5-8 下午02:17:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XswsfForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	
	
	private String guid;
	private String rcid;//检查日程ID
	private String lddm;//楼栋代码
	private String qsh;//寝室号
	private String xh;
	private String jcrq;//检查日期
	private String fs;//分数
	private String bz;//备注
	private String lrr;//录入人
	private String lrsj;//录入时间
	private String xn;//学年
	private String xq;//学期
	private String rcmc;//检查日程名称
	
	
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
	 * @return the mc
	 */
	public String getRcmc() {
		return rcmc;
	}
	/**
	 * @param mc要设置的 mc
	 */
	public void setRcmc(String rcmc) {
		this.rcmc = rcmc;
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
	 * @return the rcid
	 */
	public String getRcid() {
		return rcid;
	}
	/**
	 * @param rcid要设置的 rcid
	 */
	public void setRcid(String rcid) {
		this.rcid = rcid;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
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
	 * @return the jcrq
	 */
	public String getJcrq() {
		return jcrq;
	}
	/**
	 * @param jcrq要设置的 jcrq
	 */
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fs要设置的 fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
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
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrr要设置的 lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsj要设置的 lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
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

	
	
}
