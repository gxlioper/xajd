/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-21 ����10:46:53 
 */  
package com.zfsoft.xgxt.rcsw.txhd.hdkhgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-9-21 ����10:46:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class hdkhForm extends ActionForm {
	private String  xh;
	private String  hdxmbh;
	private String  hjqk;
	private String  sfhdxf;
	private String  type;
	private String  xn;
	private String  xq;
	private String  id;
	private String  lbdm;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	/**
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdmҪ���õ� lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
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
	 * @return the hdxmbh
	 */
	public String getHdxmbh() {
		return hdxmbh;
	}
	/**
	 * @param hdxmbhҪ���õ� hdxmbh
	 */
	public void setHdxmbh(String hdxmbh) {
		this.hdxmbh = hdxmbh;
	}
	/**
	 * @return the hjqk
	 */
	public String getHjqk() {
		return hjqk;
	}
	/**
	 * @param hjqkҪ���õ� hjqk
	 */
	public void setHjqk(String hjqk) {
		this.hjqk = hjqk;
	}
	/**
	 * @return the sfhdxf
	 */
	public String getSfhdxf() {
		return sfhdxf;
	}
	/**
	 * @param sfhdxfҪ���õ� sfhdxf
	 */
	public void setSfhdxf(String sfhdxf) {
		this.sfhdxf = sfhdxf;
	}	
}
