/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.shlc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ѧ����Ϣ 
 * @�๦������: �޸����
 * @���ߣ� ligl 
 * @ʱ�䣺 2013-12-11 ����01:36:46 
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class ShlcModel extends ActionForm {

	private static final long serialVersionUID = -4765595627423262395L;
	private String id;
	private String xh;
	private String splcid;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSplcid() {
		return splcid;
	}

	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}