package xsgzgl.gygl.qsgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class QsglForm extends ActionForm{
	/* 通用 */
	Pages pages = new Pages();

	//高级查询
	SearchModel searchModel = new SearchModel(); 
	
	private String sfbz;     //收费标准
	private String nj;     //所属年级
	private String bz;     	 //备注
	private String qsdh;     //寝室电话
	private String xydm;     //所属学院代码
	private String qsxb = "男";     //寝室性别
	private String yqsh;	//原寝室号
	private String cws;      //床位数
	private String lddm;     //楼栋代码
	private String qsh;      //寝室号
	private String ch;       //层号
	private String zdsc = "yes";     //自动生成
	private String[] primarykey_checkVal;//主键数组
	private String sfqccwss;	//是否清除床位所属
	private String ywkt;  //有无空调
	
	private FormFile impFilePath;//导入文件
	
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
	public String getSfbz() {
		return sfbz;
	}
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getQsxb() {
		return qsxb;
	}
	public void setQsxb(String qsxb) {
		this.qsxb = qsxb;
	}
	public String getCws() {
		return cws;
	}
	public void setCws(String cws) {
		this.cws = cws;
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
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getZdsc() {
		return zdsc;
	}
	public void setZdsc(String zdsc) {
		this.zdsc = zdsc;
	}
	public FormFile getImpFilePath() {
		return impFilePath;
	}
	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
	}
	public String getSfqccwss() {
		return sfqccwss;
	}
	public void setSfqccwss(String sfqccwss) {
		this.sfqccwss = sfqccwss;
	}
	/**
	 * @return the yqsh
	 */
	public String getYqsh() {
		return yqsh;
	}
	/**
	 * @param yqsh要设置的 yqsh
	 */
	public void setYqsh(String yqsh) {
		this.yqsh = yqsh;
	}
	/**
	 * @return the ywkt
	 */
	public String getYwkt() {
		return ywkt;
	}
	/**
	 * @param ywkt要设置的 ywkt
	 */
	public void setYwkt(String ywkt) {
		this.ywkt = ywkt;
	}
	
	
	
}
