/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-23 ����02:52:38 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-23 ����02:52:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnxjJgForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;
	
	private String xh;
	
	private String xn;
	
	private String xjnr;
	
	private String txsj ;
	
	private String sjly ;
	
	private String sqid ;

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
	 * @return the xjnr
	 */
	public String getXjnr() {
		return xjnr;
	}

	/**
	 * @param xjnrҪ���õ� xjnr
	 */
	public void setXjnr(String xjnr) {
		this.xjnr = xjnr;
	}

	/**
	 * @return the txsj
	 */
	public String getTxsj() {
		return txsj;
	}

	/**
	 * @param txsjҪ���õ� txsj
	 */
	public void setTxsj(String txsj) {
		this.txsj = txsj;
	}

	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	
	
	
	
}
