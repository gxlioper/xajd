/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-27 ����09:11:04 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷֹ���ģ��
 * @�๦������: ��Ŀά��(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-27 ����09:11:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfXmmkwhForm extends ActionForm{
	
	private static final long serialVersionUID = 1023643161542977601L;
	
	private String xmmkdm;
	private String xmmkmc;
	private String xf;
	private String cxzd;
	private String hgf;
	
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdmҪ���õ� xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
	}
	/**
	 * @return the xmmkmc
	 */
	public String getXmmkmc() {
		return xmmkmc;
	}
	/**
	 * @param xmmkmcҪ���õ� xmmkmc
	 */
	public void setXmmkmc(String xmmkmc) {
		this.xmmkmc = xmmkmc;
	}
	/**
	 * @return the xf
	 */
	public String getXf() {
		return xf;
	}
	/**
	 * @param xfҪ���õ� xf
	 */
	public void setXf(String xf) {
		this.xf = xf;
	}
	/**
	 * @return the jfxmdm
	 */
	
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
	 * @return the cxzd
	 */
	public String getCxzd() {
		return cxzd;
	}
	/**
	 * @param cxzdҪ���õ� cxzd
	 */
	public void setCxzd(String cxzd) {
		this.cxzd = cxzd;
	}
	/**
	 * @return the hgf
	 */
	public String getHgf() {
		return hgf;
	}
	/**
	 * @param hgfҪ���õ� hgf
	 */
	public void setHgf(String hgf) {
		this.hgf = hgf;
	}
	
	
	
	
}
