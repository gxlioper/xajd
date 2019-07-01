package xsgzgl.dtjs.dtxxgl;

import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;

public class DtxxglForm  extends CommForm {
	// 分页
	Pages pages = new Pages();

	// 高级查询
	SearchModel searchModel = new SearchModel();
	
	private User user;
	private String xh;						//学号
	private String[] jddm;					//阶段代码
	private String[] kssj;					//开始时间
	private String[] jssj;					//结束时间
	private String[] primarykey_checkVal;	//CheckBox
	private String tjlx;
	private String jd;
	private String[] jdList;
	private String[] jdArray;
	private String exportdate;
	private String curr_jddm;               //选择的当前阶段代码
	
	private String zd1;
	private String zd2;
	private String zd3;
	private String zd4;
	private String zd5;
	private String zd6;
	private String zd7;
	
	private FormFile impFilePath;			//导入文件
	private ExportModel exportModel = new ExportModel();
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String[] getJddm() {
		return jddm;
	}
	public void setJddm(String[] jddm) {
		this.jddm = jddm;
	}
	public String[] getKssj() {
		return kssj;
	}
	public void setKssj(String[] kssj) {
		this.kssj = kssj;
	}
	public String[] getJssj() {
		return jssj;
	}
	public void setJssj(String[] jssj) {
		this.jssj = jssj;
	}
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
	}
	public String getCurr_jddm() {
		return curr_jddm;
	}
	public void setCurr_jddm(String currJddm) {
		curr_jddm = currJddm;
	}
	public FormFile getImpFilePath() {
		return impFilePath;
	}
	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}
	public String getTjlx() {
		return tjlx;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
	public String getJd() {
		return jd;
	}
	public void setJdList(String[] jdList) {
		this.jdList = jdList;
	}
	public String[] getJdList() {
		return jdList;
	}
	public void setJdArray(String[] jdArray) {
		this.jdArray = jdArray;
	}
	public String[] getJdArray() {
		return jdArray;
	}
	public void setExportdate(String exportdate) {
		this.exportdate = exportdate;
	}
	public String getExportdate() {
		return exportdate;
	}
	public String getZd1() {
		return zd1;
	}
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	public String getZd2() {
		return zd2;
	}
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	public String getZd3() {
		return zd3;
	}
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	public String getZd4() {
		return zd4;
	}
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	public String getZd5() {
		return zd5;
	}
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	public void setZd6(String zd6) {
		this.zd6 = zd6;
	}
	public String getZd6() {
		return zd6;
	}
	public void setZd7(String zd7) {
		this.zd7 = zd7;
	}
	public String getZd7() {
		return zd7;
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
