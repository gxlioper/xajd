/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����11:54:44 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.ylxlxsgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����11:54:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YlxlxsglForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = -3427252545213781832L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String xh;
	
	private String mtkssj;
	
	private String mtjssj;
	
	private String mtdd;
	
	private String mtzgh;
	
	private String ftnr;
	
	private String lxdm;
	
	private String gzdj;
	
	private String mtxjjjsfdcs;

	private String mtzghmc;
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
	 * @return the mtkssj
	 */
	public String getMtkssj() {
		return mtkssj;
	}

	/**
	 * @param mtkssjҪ���õ� mtkssj
	 */
	public void setMtkssj(String mtkssj) {
		this.mtkssj = mtkssj;
	}

	/**
	 * @return the mtjssj
	 */
	public String getMtjssj() {
		return mtjssj;
	}

	/**
	 * @param mtjssjҪ���õ� mtjssj
	 */
	public void setMtjssj(String mtjssj) {
		this.mtjssj = mtjssj;
	}

	/**
	 * @return the mtdd
	 */
	public String getMtdd() {
		return mtdd;
	}

	/**
	 * @param mtddҪ���õ� mtdd
	 */
	public void setMtdd(String mtdd) {
		this.mtdd = mtdd;
	}

	/**
	 * @return the mtzgh
	 */
	public String getMtzgh() {
		return mtzgh;
	}

	/**
	 * @param mtzghҪ���õ� mtzgh
	 */
	public void setMtzgh(String mtzgh) {
		this.mtzgh = mtzgh;
	}

	/**
	 * @return the ftnr
	 */
	public String getFtnr() {
		return ftnr;
	}

	/**
	 * @param ftnrҪ���õ� ftnr
	 */
	public void setFtnr(String ftnr) {
		this.ftnr = ftnr;
	}

	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}

	/**
	 * @param lxdmҪ���õ� lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	/**
	 * @return the gzdj
	 */
	public String getGzdj() {
		return gzdj;
	}

	/**
	 * @param gzdjҪ���õ� gzdj
	 */
	public void setGzdj(String gzdj) {
		this.gzdj = gzdj;
	}

	/**
	 * @return the mtxjjjsfdcs
	 */
	public String getMtxjjjsfdcs() {
		return mtxjjjsfdcs;
	}

	/**
	 * @param mtxjjjsfdcsҪ���õ� mtxjjjsfdcs
	 */
	public void setMtxjjjsfdcs(String mtxjjjsfdcs) {
		this.mtxjjjsfdcs = mtxjjjsfdcs;
	}

	/**
	 * @return the mtzghmc
	 */
	public String getMtzghmc() {
		return mtzghmc;
	}

	/**
	 * @param mtzghmcҪ���õ� mtzghmc
	 */
	public void setMtzghmc(String mtzghmc) {
		this.mtzghmc = mtzghmc;
	}
	
	
}
