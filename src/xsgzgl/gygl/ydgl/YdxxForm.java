/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-9-5 ����10:25:42 
 */  
package xsgzgl.gygl.ydgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �õ����ģ��
 * @���ߣ� caopei[����:1352]
 * @ʱ�䣺 2016-9-5 ����10:25:42 
 */

public class YdxxForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	
	private String lddm;
    private String qsh;
	private String ydyf;
	private String ds;
	private String df;
	private String dfye;
	private String jlr;
	private String ydxxid;
	private String jlsj;
	
	private String type;
	private String ch;
	private String cws;
	private String bz;
	private String path;
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param pathҪ���õ� path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
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
	 * @return the ydyf
	 */
	public String getYdyf() {
		return ydyf;
	}
	/**
	 * @param ydyfҪ���õ� ydyf
	 */
	public void setYdyf(String ydyf) {
		this.ydyf = ydyf;
	}
	/**
	 * @return the ds
	 */
	public String getDs() {
		return ds;
	}
	/**
	 * @param dsҪ���õ� ds
	 */
	public void setDs(String ds) {
		this.ds = ds;
	}
	/**
	 * @return the df
	 */
	public String getDf() {
		return df;
	}
	/**
	 * @param dfҪ���õ� df
	 */
	public void setDf(String df) {
		this.df = df;
	}
	/**
	 * @return the dfye
	 */
	public String getDfye() {
		return dfye;
	}
	/**
	 * @param dfyeҪ���õ� dfye
	 */
	public void setDfye(String dfye) {
		this.dfye = dfye;
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
	 * @return the cws
	 */
	public String getCws() {
		return cws;
	}
	/**
	 * @param cwsҪ���õ� cws
	 */
	public void setCws(String cws) {
		this.cws = cws;
	}
	/**
	 * @return the jlr
	 */
	public String getJlr() {
		return jlr;
	}
	/**
	 * @param jlrҪ���õ� jlr
	 */
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	/**
	 * @return the ydxxid
	 */
	public String getYdxxid() {
		return ydxxid;
	}
	/**
	 * @param ydxxidҪ���õ� ydxxid
	 */
	public void setYdxxid(String ydxxid) {
		this.ydxxid = ydxxid;
	}
	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}
	/**
	 * @param jlsjҪ���õ� jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}
	
	
	
	
	
	
}
