/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����11:26:25 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.thjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ճ���Ϊ--̸����¼
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-12-1 ����01:35:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ThjlModel extends ActionForm {

	
	private static final long serialVersionUID = 1966791940187986544L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String thid;
	private String xh;
	private String xn;
	private String xq;
	private String thsj;
	private String thnr;
	private String fjmc;
	private String thjs;
	private String xqmc;
	private String jsxm;
	
	
	
	/**
	 * @return the jsxm
	 */
	public String getJsxm() {
		return jsxm;
	}
	/**
	 * @param jsxmҪ���õ� jsxm
	 */
	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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
	 * @return the thid
	 */
	public String getThid() {
		return thid;
	}
	/**
	 * @param thidҪ���õ� thid
	 */
	public void setThid(String thid) {
		this.thid = thid;
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
	 * @return the thsj
	 */
	public String getThsj() {
		return thsj;
	}
	/**
	 * @param thsjҪ���õ� thsj
	 */
	public void setThsj(String thsj) {
		this.thsj = thsj;
	}
	/**
	 * @return the thnr
	 */
	public String getThnr() {
		return thnr;
	}
	/**
	 * @param thnrҪ���õ� thnr
	 */
	public void setThnr(String thnr) {
		this.thnr = thnr;
	}
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmcҪ���õ� fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the thjs
	 */
	public String getThjs() {
		return thjs;
	}
	/**
	 * @param thjsҪ���õ� thjs
	 */
	public void setThjs(String thjs) {
		this.thjs = thjs;
	}
	
	
}
