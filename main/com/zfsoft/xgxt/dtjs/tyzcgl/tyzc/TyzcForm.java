
package com.zfsoft.xgxt.dtjs.tyzcgl.tyzc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * ��Աע��
 */
public class TyzcForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	
	private String id; //��ʶ��
	
	private String xh;	//ѧ��
	
	private String xn;	//ѧ��
	
	private String zczt; //ע��״̬
	private String zcztmc; //ע��״̬
	
	private String zcsj;//ע��ʱ��
	
	private String zcr;//ע����
	
	private String pk;//
	private String pks;//
	
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param idҪ���õ� id
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
	 * @param xhҪ���õ� xh
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
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}

	/**
	 * @return the zczt
	 */
	public String getZczt() {
		return zczt;
	}

	/**
	 * @param zcztҪ���õ� zczt
	 */
	public void setZczt(String zczt) {
		this.zczt = zczt;
	}
	
	/**
	 * @return the zcztmc
	 */
	public String getZcztmc() {
		return zcztmc;
	}

	/**
	 * @param zcztmcҪ���õ� zcztmc
	 */
	public void setZcztmc(String zcztmc) {
		this.zcztmc = zcztmc;
	}

	/**
	 * @return the zcsj
	 */
	public String getZcsj() {
		return zcsj;
	}

	/**
	 * @param zcsjҪ���õ� zcsj
	 */
	public void setZcsj(String zcsj) {
		this.zcsj = zcsj;
	}

	/**
	 * @return the zcr
	 */
	public String getZcr() {
		return zcr;
	}

	/**
	 * @param zcrҪ���õ� zcr
	 */
	public void setZcr(String zcr) {
		this.zcr = zcr;
	}

	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}

	/**
	 * @param pkҪ���õ� pk
	 */
	public void setPk(String pk) {
		this.pk = pk;
	}

	/**
	 * @return the pks
	 */
	public String getPks() {
		return pks;
	}

	/**
	 * @param pksҪ���õ� pks
	 */
	public void setPks(String pks) {
		this.pks = pks;
	}

}
