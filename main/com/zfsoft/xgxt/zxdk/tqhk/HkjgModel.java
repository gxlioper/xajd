/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-7 ����03:00:04 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-7 ����03:00:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HkjgModel extends ActionForm {

	private static final long serialVersionUID = 7785185064200690846L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;
	private String xh;
	private String hkzh;
	private String hkje;
	private String hkbj;
	private String hkly;
	private String bz;
	private String sqsj;
	private String hkzt;
	private String hkztmc;
	private String filepath;
	
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the hkztmc
	 */
	public String getHkztmc() {
		return hkztmc;
	}
	/**
	 * @param hkztmcҪ���õ� hkztmc
	 */
	public void setHkztmc(String hkztmc) {
		this.hkztmc = hkztmc;
	}
	/**
	 * @return the hkzt
	 */
	public String getHkzt() {
		return hkzt;
	}
	/**
	 * @param hkztҪ���õ� hkzt
	 */
	public void setHkzt(String hkzt) {
		this.hkzt = hkzt;
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
	 * @return the hkzh
	 */
	public String getHkzh() {
		return hkzh;
	}
	/**
	 * @param hkzhҪ���õ� hkzh
	 */
	public void setHkzh(String hkzh) {
		this.hkzh = hkzh;
	}
	/**
	 * @return the hkje
	 */
	public String getHkje() {
		return hkje;
	}
	/**
	 * @param hkjeҪ���õ� hkje
	 */
	public void setHkje(String hkje) {
		this.hkje = hkje;
	}
	/**
	 * @return the hkbj
	 */
	public String getHkbj() {
		return hkbj;
	}
	/**
	 * @param hkbjҪ���õ� hkbj
	 */
	public void setHkbj(String hkbj) {
		this.hkbj = hkbj;
	}
	/**
	 * @return the hkly
	 */
	public String getHkly() {
		return hkly;
	}
	/**
	 * @param hklyҪ���õ� hkly
	 */
	public void setHkly(String hkly) {
		this.hkly = hkly;
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
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	
	
	
}
