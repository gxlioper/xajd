/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-19 ����09:39:17 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ���ҵ�ʦά��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-19 ����09:39:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QsdswhForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;

	private String lddm;
	
	private String ch;
	
	private String qsh;
	
	private String dsxm;
	
	private String lxdh;
	
	private String dw;
	
	private String bz;
	
	private String zgh;
	
	private String xn;
	
	private String xq;
	
	private String nd;
	
	private String xqfdyxm;
	
	private String xqfdylxdh;
	
	private String rqkssj;
	
	private String rqjssj;
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
	 * @return the dsxm
	 */
	public String getDsxm() {
		return dsxm;
	}

	/**
	 * @param dsxmҪ���õ� dsxm
	 */
	public void setDsxm(String dsxm) {
		this.dsxm = dsxm;
	}

	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}

	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	/**
	 * @return the dw
	 */
	public String getDw() {
		return dw;
	}

	/**
	 * @param dwҪ���õ� dw
	 */
	public void setDw(String dw) {
		this.dw = dw;
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

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getXqfdyxm() {
		return xqfdyxm;
	}

	public void setXqfdyxm(String xqfdyxm) {
		this.xqfdyxm = xqfdyxm;
	}

	public String getXqfdylxdh() {
		return xqfdylxdh;
	}

	public void setXqfdylxdh(String xqfdylxdh) {
		this.xqfdylxdh = xqfdylxdh;
	}

	/**
	 * @return the rqkssj
	 */
	public String getRqkssj() {
		return rqkssj;
	}

	/**
	 * @param rqkssjҪ���õ� rqkssj
	 */
	public void setRqkssj(String rqkssj) {
		this.rqkssj = rqkssj;
	}

	/**
	 * @return the rqjssj
	 */
	public String getRqjssj() {
		return rqjssj;
	}

	/**
	 * @param rqjssjҪ���õ� rqjssj
	 */
	public void setRqjssj(String rqjssj) {
		this.rqjssj = rqjssj;
	}
	
	
}
