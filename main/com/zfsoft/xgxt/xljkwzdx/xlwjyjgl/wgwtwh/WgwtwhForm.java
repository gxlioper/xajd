/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����11:54:44 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.wgwtwh;

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

public class WgwtwhForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = -3427252545213781832L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String xh;
	
	private String wt1qk;
	
	private String wt2qk;
	
	private String wt3qk;
	
	private String wt4qk;
	
	private String wt5qk;
	
	private String wt1bz;
	
	private String wt2bz;
	
	private String wt3bz;
	
	private String wt4bz;
	
	private String wt5bz;
	
	private String zf;
	
	private String xgsj;

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
	 * @return the wt1qk
	 */
	public String getWt1qk() {
		return wt1qk;
	}

	/**
	 * @param wt1qkҪ���õ� wt1qk
	 */
	public void setWt1qk(String wt1qk) {
		this.wt1qk = wt1qk;
	}

	/**
	 * @return the wt2qk
	 */
	public String getWt2qk() {
		return wt2qk;
	}

	/**
	 * @param wt2qkҪ���õ� wt2qk
	 */
	public void setWt2qk(String wt2qk) {
		this.wt2qk = wt2qk;
	}

	/**
	 * @return the wt3qk
	 */
	public String getWt3qk() {
		return wt3qk;
	}

	/**
	 * @param wt3qkҪ���õ� wt3qk
	 */
	public void setWt3qk(String wt3qk) {
		this.wt3qk = wt3qk;
	}

	/**
	 * @return the wt4qk
	 */
	public String getWt4qk() {
		return wt4qk;
	}

	/**
	 * @param wt4qkҪ���õ� wt4qk
	 */
	public void setWt4qk(String wt4qk) {
		this.wt4qk = wt4qk;
	}

	/**
	 * @return the wt5qk
	 */
	public String getWt5qk() {
		return wt5qk;
	}

	/**
	 * @param wt5qkҪ���õ� wt5qk
	 */
	public void setWt5qk(String wt5qk) {
		this.wt5qk = wt5qk;
	}

	/**
	 * @return the wt1bz
	 */
	public String getWt1bz() {
		return wt1bz;
	}

	/**
	 * @param wt1bzҪ���õ� wt1bz
	 */
	public void setWt1bz(String wt1bz) {
		this.wt1bz = wt1bz;
	}

	/**
	 * @return the wt2bz
	 */
	public String getWt2bz() {
		return wt2bz;
	}

	/**
	 * @param wt2bzҪ���õ� wt2bz
	 */
	public void setWt2bz(String wt2bz) {
		this.wt2bz = wt2bz;
	}

	/**
	 * @return the wt3bz
	 */
	public String getWt3bz() {
		return wt3bz;
	}

	/**
	 * @param wt3bzҪ���õ� wt3bz
	 */
	public void setWt3bz(String wt3bz) {
		this.wt3bz = wt3bz;
	}

	/**
	 * @return the wt4bz
	 */
	public String getWt4bz() {
		return wt4bz;
	}

	/**
	 * @param wt4bzҪ���õ� wt4bz
	 */
	public void setWt4bz(String wt4bz) {
		this.wt4bz = wt4bz;
	}

	/**
	 * @return the wt5bz
	 */
	public String getWt5bz() {
		return wt5bz;
	}

	/**
	 * @param wt5bzҪ���õ� wt5bz
	 */
	public void setWt5bz(String wt5bz) {
		this.wt5bz = wt5bz;
	}

	/**
	 * @return the zf
	 */
	public String getZf() {
		return zf;
	}

	/**
	 * @param zfҪ���õ� zf
	 */
	public void setZf(String zf) {
		this.zf = zf;
	}

	/**
	 * @return the xgsj
	 */
	public String getXgsj() {
		return xgsj;
	}

	/**
	 * @param xgsjҪ���õ� xgsj
	 */
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	
	
}
