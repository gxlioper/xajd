package xsgzgl.gyjc.ccjgcx;


import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xsgzgl.comm.form.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class CcjgForm extends CommForm {
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
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
	//�������
	private FormFile formfile;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
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
	 * @param guidҪ���õ� guid
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
	 * @param xydmҪ���õ� xydm
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
	 * @param jjlxҪ���õ� jjlx
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
	 * @param lddmҪ���õ� lddm
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
	 * @param qshҪ���õ� qsh
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
	 * @param tjztҪ���õ� tjzt
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
	 * @param tjsjҪ���õ� tjsj
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
	 * @param tjrҪ���õ� tjr
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
	 * @param pagesҪ���õ� pages
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
	 * @param searchModelҪ���õ� searchModel
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
	 * @param exportModelҪ���õ� exportModel
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
	 * @param typeҪ���õ� type
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
	 * @param fjidҪ���õ� fjid
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
	 * @param wsjcҪ���õ� wsjc
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
	 * @param aqjcҪ���õ� aqjc
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
	 * @param jljcҪ���õ� jljc
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
	 * @param jcrqҪ���õ� jcrq
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
	 * @param pfidҪ���õ� pfid
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
	 * @param xymcҪ���õ� xymc
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
	 * @param ldmcҪ���õ� ldmc
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
	 * @param chҪ���õ� ch
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
	 * @param formfileҪ���õ� formfile
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
