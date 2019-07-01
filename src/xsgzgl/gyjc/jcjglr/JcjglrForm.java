package xsgzgl.gyjc.jcjglr;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JcjglrForm extends ActionForm {
	private String guid;
	private String mxid;
	private String pfid;
	private String xh;
	private String jjlx;
	private String type;
	private String xydm;
	private String rcid;
	private String tjzt;
	private String tjr;
	private String[] cwhs;
	private String[] mxids;
	private String[] mxidflags;
	private String[] pfids;
	private String[] xhs;
	private String[] jjlxs;
	private String[] lddms;
	private String[] qshs;
	private String[] indexs;
	private String[] fids;
	private String lddm;
	private String qsh;
	private String fjid;
	private Pages pages = new Pages();
	private String flag;
	private String js;
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	public String[] getCwhs() {
		return cwhs;
	}
	public void setCwhs(String[] cwhs) {
		this.cwhs = cwhs;
	}
	public String[] getFids() {
		return fids;
	}
	public void setFids(String[] fids) {
		this.fids = fids;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String[] getLddms() {
		return lddms;
	}
	public void setLddms(String[] lddms) {
		this.lddms = lddms;
	}
	public String[] getQshs() {
		return qshs;
	}
	public void setQshs(String[] qshs) {
		this.qshs = qshs;
	}
	
	public String[] getMxidflags() {
		return mxidflags;
	}
	public void setMxidflags(String[] mxidflags) {
		this.mxidflags = mxidflags;
	}
	public String[] getMxids() {
		return mxids;
	}
	public void setMxids(String[] mxids) {
		this.mxids = mxids;
	}
	public String[] getPfids() {
		return pfids;
	}
	public void setPfids(String[] pfids) {
		this.pfids = pfids;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String[] getJjlxs() {
		return jjlxs;
	}
	public void setJjlxs(String[] jjlxs) {
		this.jjlxs = jjlxs;
	}
	public String getFjid() {
		return fjid;
	}
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getRcid() {
		return rcid;
	}
	public void setRcid(String rcid) {
		this.rcid = rcid;
	}
	public String getTjzt() {
		return tjzt;
	}
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getMxid() {
		return mxid;
	}
	public void setMxid(String mxid) {
		this.mxid = mxid;
	}
	public String getPfid() {
		return pfid;
	}
	public void setPfid(String pfid) {
		this.pfid = pfid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJjlx() {
		return jjlx;
	}
	public void setJjlx(String jjlx) {
		this.jjlx = jjlx;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	public String[] getIndexs() {
		return indexs;
	}
	public void setIndexs(String[] indexs) {
		this.indexs = indexs;
	}
}
