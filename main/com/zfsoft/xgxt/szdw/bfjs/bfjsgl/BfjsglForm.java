/**
 * @部门:学工产品事业部
 * @日期：2017-4-19 上午11:03:19 
 */  
package com.zfsoft.xgxt.szdw.bfjs.bfjsgl;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 班风建设管理model(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-4-19 上午11:03:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BfjsglForm extends ActionForm{
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -8298860854783929190L;
	private String jcid;
	private String bjdm;
	private String bjmc;
	private String jcrq;
	private String id;
	private String xh;
	private String jclx;
	private String kqlx;
	private String xm;
	private String[] dqs;
	private String[] qjs;
	private String[] qqs;
	private String[] cds;
	private String[] zts;
	private String type;
	private String[] xhs;
	private String doType;
	private String[] dels;
	private List<HashMap<String,String>> zcqqList;
	private List<HashMap<String,String>> zdqqList;
	private List<HashMap<String,String>> skqqList;
	private List<HashMap<String,String>> wzxqqList;
	private List<HashMap<String,String>> zccqList;
	private List<HashMap<String,String>> zdcqList;
	private List<HashMap<String,String>> skcqList;
	private List<HashMap<String,String>> wzxcqList;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the jcid
	 */
	public String getJcid() {
		return jcid;
	}
	/**
	 * @param jcid要设置的 jcid
	 */
	public void setJcid(String jcid) {
		this.jcid = jcid;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	 * @return the jclx
	 */
	public String getJclx() {
		return jclx;
	}
	/**
	 * @param jclx要设置的 jclx
	 */
	public void setJclx(String jclx) {
		this.jclx = jclx;
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
	 * @return the dqs
	 */
	public String[] getDqs() {
		return dqs;
	}
	/**
	 * @param dqs要设置的 dqs
	 */
	public void setDqs(String[] dqs) {
		this.dqs = dqs;
	}
	/**
	 * @return the qjs
	 */
	public String[] getQjs() {
		return qjs;
	}
	/**
	 * @param qjs要设置的 qjs
	 */
	public void setQjs(String[] qjs) {
		this.qjs = qjs;
	}
	/**
	 * @return the qqs
	 */
	public String[] getQqs() {
		return qqs;
	}
	/**
	 * @param qqs要设置的 qqs
	 */
	public void setQqs(String[] qqs) {
		this.qqs = qqs;
	}
	/**
	 * @return the cds
	 */
	public String[] getCds() {
		return cds;
	}
	/**
	 * @param cds要设置的 cds
	 */
	public void setCds(String[] cds) {
		this.cds = cds;
	}
	/**
	 * @return the zts
	 */
	public String[] getZts() {
		return zts;
	}
	/**
	 * @param zts要设置的 zts
	 */
	public void setZts(String[] zts) {
		this.zts = zts;
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
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the zcqqList
	 */
	public List<HashMap<String, String>> getZcqqList() {
		return zcqqList;
	}
	/**
	 * @param zcqqList要设置的 zcqqList
	 */
	public void setZcqqList(List<HashMap<String, String>> zcqqList) {
		this.zcqqList = zcqqList;
	}
	/**
	 * @return the zdqqList
	 */
	public List<HashMap<String, String>> getZdqqList() {
		return zdqqList;
	}
	/**
	 * @param zdqqList要设置的 zdqqList
	 */
	public void setZdqqList(List<HashMap<String, String>> zdqqList) {
		this.zdqqList = zdqqList;
	}
	/**
	 * @return the skqqList
	 */
	public List<HashMap<String, String>> getSkqqList() {
		return skqqList;
	}
	/**
	 * @param skqqList要设置的 skqqList
	 */
	public void setSkqqList(List<HashMap<String, String>> skqqList) {
		this.skqqList = skqqList;
	}
	/**
	 * @return the wzxqqList
	 */
	public List<HashMap<String, String>> getWzxqqList() {
		return wzxqqList;
	}
	/**
	 * @param wzxqqList要设置的 wzxqqList
	 */
	public void setWzxqqList(List<HashMap<String, String>> wzxqqList) {
		this.wzxqqList = wzxqqList;
	}
	/**
	 * @return the zccqList
	 */
	public List<HashMap<String, String>> getZccqList() {
		return zccqList;
	}
	/**
	 * @param zccqList要设置的 zccqList
	 */
	public void setZccqList(List<HashMap<String, String>> zccqList) {
		this.zccqList = zccqList;
	}
	/**
	 * @return the zdcqList
	 */
	public List<HashMap<String, String>> getZdcqList() {
		return zdcqList;
	}
	/**
	 * @param zdcqList要设置的 zdcqList
	 */
	public void setZdcqList(List<HashMap<String, String>> zdcqList) {
		this.zdcqList = zdcqList;
	}
	/**
	 * @return the skcqList
	 */
	public List<HashMap<String, String>> getSkcqList() {
		return skcqList;
	}
	/**
	 * @param skcqList要设置的 skcqList
	 */
	public void setSkcqList(List<HashMap<String, String>> skcqList) {
		this.skcqList = skcqList;
	}
	/**
	 * @return the wzxcqList
	 */
	public List<HashMap<String, String>> getWzxcqList() {
		return wzxcqList;
	}
	/**
	 * @param wzxcqList要设置的 wzxcqList
	 */
	public void setWzxcqList(List<HashMap<String, String>> wzxcqList) {
		this.wzxcqList = wzxcqList;
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
	 * @return the doType
	 */
	public String getDoType() {
		return doType;
	}
	/**
	 * @param doType要设置的 doType
	 */
	public void setDoType(String doType) {
		this.doType = doType;
	}
	/**
	 * @return the dels
	 */
	public String[] getDels() {
		return dels;
	}
	/**
	 * @param dels要设置的 dels
	 */
	public void setDels(String[] dels) {
		this.dels = dels;
	}

	
	
	
}
