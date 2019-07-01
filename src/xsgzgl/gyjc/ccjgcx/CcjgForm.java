package xsgzgl.gyjc.ccjgcx;


import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xsgzgl.comm.form.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class CcjgForm extends CommForm {
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 5907266275756350624L;
	private String guid;
	private String xydm;
	private String jjlx;
	private String lddm;
	private String qsh;
	private String tjzt;
	private String tjsj;
	private String tjr;
	private String type;
	private String fjid;
	private String wsjc;
	private String aqjc;
	private String jljc;
	private String jcrq;
	private String pfid;
	private String xymc;
	private String ldmc;
	private String flag;
	private String js;
	private String ch;
	private String xh;
	private String cwh;
	private String qsz;
	private String xm;
	private String[] indexs;
	private String[] fids;
	//下载相关
	private FormFile formfile;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	public String[] getFids() {
		return fids;
	}
	public void setFids(String[] fids) {
		this.fids = fids;
	}
	public String[] getIndexs() {
		return indexs;
	}
	public void setIndexs(String[] indexs) {
		this.indexs = indexs;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
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
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the jjlx
	 */
	public String getJjlx() {
		return jjlx;
	}
	/**
	 * @param jjlx要设置的 jjlx
	 */
	public void setJjlx(String jjlx) {
		this.jjlx = jjlx;
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
	 * @return the tjzt
	 */
	public String getTjzt() {
		return tjzt;
	}
	/**
	 * @param tjzt要设置的 tjzt
	 */
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
	/**
	 * @return the tjsj
	 */
	public String getTjsj() {
		return tjsj;
	}
	/**
	 * @param tjsj要设置的 tjsj
	 */
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	/**
	 * @return the tjr
	 */
	public String getTjr() {
		return tjr;
	}
	/**
	 * @param tjr要设置的 tjr
	 */
	public void setTjr(String tjr) {
		this.tjr = tjr;
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
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @param fjid要设置的 fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
	/**
	 * @return the wsjc
	 */
	public String getWsjc() {
		return wsjc;
	}
	/**
	 * @param wsjc要设置的 wsjc
	 */
	public void setWsjc(String wsjc) {
		this.wsjc = wsjc;
	}
	/**
	 * @return the aqjc
	 */
	public String getAqjc() {
		return aqjc;
	}
	/**
	 * @param aqjc要设置的 aqjc
	 */
	public void setAqjc(String aqjc) {
		this.aqjc = aqjc;
	}
	/**
	 * @return the jljc
	 */
	public String getJljc() {
		return jljc;
	}
	/**
	 * @param jljc要设置的 jljc
	 */
	public void setJljc(String jljc) {
		this.jljc = jljc;
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
	 * @return the pfid
	 */
	public String getPfid() {
		return pfid;
	}
	/**
	 * @param pfid要设置的 pfid
	 */
	public void setPfid(String pfid) {
		this.pfid = pfid;
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
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the ch
	 */
	public String getCh() {
		return ch;
	}
	/**
	 * @param ch要设置的 ch
	 */
	public void setCh(String ch) {
		this.ch = ch;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getQsz() {
		return qsz;
	}
	public void setQsz(String qsz) {
		this.qsz = qsz;
	}
	 
	
}
