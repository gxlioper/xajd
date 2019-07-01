/**
 * @部门:学工产品事业部
 * @日期：2015-7-6 上午09:52:35 
 */  
package xsgzgl.jxgl.general.jxqjgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-6 上午09:52:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JxqjjgForm extends ActionForm {
	
	private String qjid;
	private String qjlx;
	private String qjts;
	private String xh;
	private String xn;
	private String xq;
	private String xqmc;
	private String qjsy;
	private String sqsj;
	private String qjkssj;
	private String qjjssj;
	private String jbr;
	private String bz;
	private String sjly;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	//下载相关
	private FormFile formfile;
	private String fjxx;
	/**
	 * @return the qjid
	 */
	public String getQjid() {
		return qjid;
	}
	/**
	 * @param qjid要设置的 qjid
	 */
	public void setQjid(String qjid) {
		this.qjid = qjid;
	}
	/**
	 * @return the qjlx
	 */
	public String getQjlx() {
		return qjlx;
	}
	/**
	 * @param qjlx要设置的 qjlx
	 */
	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}
	/**
	 * @return the qjts
	 */
	public String getQjts() {
		return qjts;
	}
	/**
	 * @param qjts要设置的 qjts
	 */
	public void setQjts(String qjts) {
		this.qjts = qjts;
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the qjsy
	 */
	public String getQjsy() {
		return qjsy;
	}
	/**
	 * @param qjsy要设置的 qjsy
	 */
	public void setQjsy(String qjsy) {
		this.qjsy = qjsy;
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
	 * @return the jbr
	 */
	public String getJbr() {
		return jbr;
	}
	/**
	 * @param jbr要设置的 jbr
	 */
	public void setJbr(String jbr) {
		this.jbr = jbr;
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
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}
	/**
	 * @param formfile要设置的 formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	/**
	 * @return the fjxx
	 */
	public String getFjxx() {
		return fjxx;
	}
	/**
	 * @param fjxx要设置的 fjxx
	 */
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
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
	

}
