/**
 * @部门:学工产品事业部
 * @日期：2015-7-7 下午07:29:57 
 */  
package xsgzgl.jxgl.general.jxkqgl.jxkqjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-7 下午07:29:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JxkqjgForm extends ActionForm {
	private String kqid;
	private String xh;
	private String xn;
	private String xq;
	private String xqmc;
	private String jxid;
	private String jxmc;
	private String kqlb;
	private String kqlbmc;
	private String kqlxmc;
	private String kqlx;
	private String kqsj;
	private String sbsj;
	private String kqqk;
	private String sbr;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the kqid
	 */
	public String getKqid() {
		return kqid;
	}
	/**
	 * @param kqid要设置的 kqid
	 */
	public void setKqid(String kqid) {
		this.kqid = kqid;
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
	 * @return the jxid
	 */
	public String getJxid() {
		return jxid;
	}
	/**
	 * @param jxid要设置的 jxid
	 */
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}
	/**
	 * @return the kqlb
	 */
	public String getKqlb() {
		return kqlb;
	}
	/**
	 * @param kqlb要设置的 kqlb
	 */
	public void setKqlb(String kqlb) {
		this.kqlb = kqlb;
	}
	/**
	 * @return the kqlx
	 */
	public String getKqlx() {
		return kqlx;
	}
	/**
	 * @param kqlx要设置的 kqlx
	 */
	public void setKqlx(String kqlx) {
		this.kqlx = kqlx;
	}
	/**
	 * @return the kqsj
	 */
	public String getKqsj() {
		return kqsj;
	}
	/**
	 * @param kqsj要设置的 kqsj
	 */
	public void setKqsj(String kqsj) {
		this.kqsj = kqsj;
	}
	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}
	/**
	 * @param sbsj要设置的 sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
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
	 * @return the kqqk
	 */
	public String getKqqk() {
		return kqqk;
	}
	/**
	 * @param kqqk要设置的 kqqk
	 */
	public void setKqqk(String kqqk) {
		this.kqqk = kqqk;
	}
	/**
	 * @return the jxmc
	 */
	public String getJxmc() {
		return jxmc;
	}
	/**
	 * @param jxmc要设置的 jxmc
	 */
	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}
	/**
	 * @return the kqlbmc
	 */
	public String getKqlbmc() {
		return kqlbmc;
	}
	/**
	 * @param kqlbmc要设置的 kqlbmc
	 */
	public void setKqlbmc(String kqlbmc) {
		this.kqlbmc = kqlbmc;
	}
	/**
	 * @return the kqlxmc
	 */
	public String getKqlxmc() {
		return kqlxmc;
	}
	/**
	 * @param kqlxmc要设置的 kqlxmc
	 */
	public void setKqlxmc(String kqlxmc) {
		this.kqlxmc = kqlxmc;
	}
	

}
