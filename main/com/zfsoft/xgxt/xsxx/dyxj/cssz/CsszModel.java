/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-23 ����08:47:13 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.cssz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������:��������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-23 ����08:47:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszModel extends ActionForm {

	private static final long serialVersionUID = -1240429349711890922L;

	private String id;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String splc;
	/**
	 * �����ֶΣ�Ϊ�˿�������
	 */
	private String sqxn;
	private String sqxq;
    private SearchModel searchModel = new SearchModel();//�߼���ѯ����
    private ExportModel exportModel = new ExportModel();//����
    private Pages pages = new Pages();
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
	private String xn;
    private String xq;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 
	/**
	 * @return the sqxn
	 */
	public String getSqxn() {
		return sqxn;
	}
	/**
	 * @param sqxnҪ���õ� sqxn
	 */
	public void setSqxn(String sqxn) {
		this.sqxn = sqxn;
	}
	/**
	 * @return the sqxq
	 */
	public String getSqxq() {
		return sqxq;
	}
	/**
	 * @param sqxqҪ���õ� sqxq
	 */
	public void setSqxq(String sqxq) {
		this.sqxq = sqxq;
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
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssjҪ���õ� sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssjҪ���õ� sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	
	
}
