/**
 * @����:ѧ����Ʒ��ҵ��
 * @ʱ�䣺 2013-12-3 ����10:56:46 
 */  
package com.zfsoft.xgxt.xsxx.bycl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @ģ������: ѧ����Ϣ
 * @�๦������:��ҵ����
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-12-3 ����10:56:46 
 * @�汾�� V1.0
 */
public class ByclForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	/**
	 * ѧ��
	 */
	private String xh;

	/**
	 * �Ƿ��ѱ�ҵ(0/�գ���1����)
	 */
	private String sfyby;

	/**
	 * ��ҵ����
	 */
	private String byny;
	
	/**
	 * �Ƿ�ȫѡ��all:ȫѡ��
	 */
	private String selected;
	
	/**
	 * ��ѡ��ѧ��
	 */
	private String values;

	private String searchTj;
	private String searchTjz;
	private String mhcx_lx;
	private String searchLx;
	private String input_mhcx;
	private String path;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private FormFile drmb;/*��ҵѧ����Ϣ����ģ��*/
	private String filepath;/*����ģ��·��*/
	
	
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
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}

	/**
	 * @param drmbҪ���õ� drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}

	public String getSearchTj() {
		return searchTj;
	}

	public void setSearchTj(String searchTj) {
		this.searchTj = searchTj;
	}

	public String getSearchTjz() {
		return searchTjz;
	}

	public void setSearchTjz(String searchTjz) {
		this.searchTjz = searchTjz;
	}

	public String getMhcx_lx() {
		return mhcx_lx;
	}

	public void setMhcx_lx(String mhcxLx) {
		mhcx_lx = mhcxLx;
	}

	public String getSearchLx() {
		return searchLx;
	}

	public void setSearchLx(String searchLx) {
		this.searchLx = searchLx;
	}

	public String getInput_mhcx() {
		return input_mhcx;
	}

	public void setInput_mhcx(String inputMhcx) {
		input_mhcx = inputMhcx;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSfyby() {
		return sfyby;
	}

	public void setSfyby(String sfyby) {
		this.sfyby = sfyby;
	}

	public String getByny() {
		return byny;
	}

	public void setByny(String byny) {
		this.byny = byny;
	}
}
