/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-6 ����03:12:07 
 */  
package com.zfsoft.xgxt.gygl.gypy;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ��Ԣform
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-8-20 ����04:34:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GypyForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String gypyid;//��Ԣ����id
	private String xn;//ѧ��
	private String xqdm;//ѧ��
	private String pylx;//��������
	private String pyly;//����ԭ��
	private String gldm;//��������
	private String lddm;//¥������
	private String ch;//¥��
	private String qsh;//���Һ�
	private String xydm;//ѧԺ����
	private String type;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String otherFilter;
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
	 * @return the pyly
	 */
	public String getPyly() {
		return pyly;
	}
	/**
	 * @param pylyҪ���õ� pyly
	 */
	public void setPyly(String pyly) {
		this.pyly = pyly;
	}
	/**
	 * @return the gldm
	 */
	public String getGldm() {
		return gldm;
	}
	/**
	 * @param gldmҪ���õ� gldm
	 */
	public void setGldm(String gldm) {
		this.gldm = gldm;
	}
	/**
	 * @return the lc
	 */
	public String getCh() {
		return ch;
	}
	/**
	 * @param lcҪ���õ� lc
	 */
	public void setCh(String ch) {
		this.ch = ch;
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
	 * @return the pylx
	 */
	public String getPylx() {
		return pylx;
	}
	/**
	 * @param pylxҪ���õ� pylx
	 */
	public void setPylx(String pylx) {
		this.pylx = pylx;
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
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydmҪ���õ� xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdmҪ���õ� xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * @return the gypyid
	 */
	public String getGypyid() {
		return gypyid;
	}
	/**
	 * @param gypyidҪ���õ� gypyid
	 */
	public void setGypyid(String gypyid) {
		this.gypyid = gypyid;
	}
	public String getOtherFilter() {
		return otherFilter;
	}
	public void setOtherFilter(String otherFilter) {
		this.otherFilter = otherFilter;
	}
}
