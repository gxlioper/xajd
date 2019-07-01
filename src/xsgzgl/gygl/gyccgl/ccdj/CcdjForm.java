package xsgzgl.gygl.gyccgl.ccdj;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class CcdjForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String id;
	private String xn;
	private String xq;
	private String lddm;
	private String qsh;
	private String zje;
	private String je;
	private String shcdmark;
	private String wpdm;
	private String bz;
	private SearchModel searchModel = new SearchModel();
	private Pages Pages = new Pages();
	private String type;
	private String[] wpdms;
	private String[] shcds;
	//导入模板
	private FormFile drmb;
	//损害程度List
	private List<HashMap<String, String>> shcdList;
	//学期List
	private List<HashMap<String, String>> xqList;
	//filepath
	private String filepath;
	private String gyglyQx;
	private String username;
	private String path;
	public String getGyglyQx() {
		return gyglyQx;
	}
	public void setGyglyQx(String gyglyQx) {
		this.gyglyQx = gyglyQx;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public List<HashMap<String, String>> getXqList() {
		return xqList;
	}
	public void setXqList(List<HashMap<String, String>> xqList) {
		this.xqList = xqList;
	}
	public List<HashMap<String, String>> getShcdList() {
		return shcdList;
	}
	public void setShcdList(List<HashMap<String, String>> shcdList) {
		this.shcdList = shcdList;
	}
	public FormFile getDrmb() {
		return drmb;
	}
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getShcdmark() {
		return shcdmark;
	}
	public void setShcdmark(String shcdmark) {
		this.shcdmark = shcdmark;
	}
	
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getWpdm() {
		return wpdm;
	}
	public void setWpdm(String wpdm) {
		this.wpdm = wpdm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getWpdms() {
		return wpdms;
	}
	public void setWpdms(String[] wpdms) {
		this.wpdms = wpdms;
	}
	public String[] getShcds() {
		return shcds;
	}
	public void setShcds(String[] shcds) {
		this.shcds = shcds;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
	public String getZje() {
		return zje;
	}
	public void setZje(String zje) {
		this.zje = zje;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public Pages getPages() {
		return Pages;
	}
	public void setPages(Pages pages) {
		Pages = pages;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
