/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-9 ����02:25:12 
 */  
package xsgzgl.gygl.sdftj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-9 ����02:25:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class sdfTjForm extends ActionForm {
	 private String id;
	 private String  nd;
	 private String  jd;
	 private String  lddm;
	 private String  qsh;
	 private String  sf;
	 private String  df;
	 private static final long serialVersionUID = 1L;
	 private ExportModel exportModel = new ExportModel();
	 private SearchModel searchModel = new SearchModel();
	 private Pages pages = new Pages();
	 private String type;
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
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * @param ndҪ���õ� nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	/**
	 * @return the jd
	 */
	public String getJd() {
		return jd;
	}
	/**
	 * @param jdҪ���õ� jd
	 */
	public void setJd(String jd) {
		this.jd = jd;
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
	 * @return the sf
	 */
	public String getSf() {
		return sf;
	}
	/**
	 * @param sfҪ���õ� sf
	 */
	public void setSf(String sf) {
		this.sf = sf;
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
	
}
