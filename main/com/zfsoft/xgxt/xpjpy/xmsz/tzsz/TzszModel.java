/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tzsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������:��Ŀά��-��˵�����������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class TzszModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String xmdm;// ��Ŀ����
	private String tzjxdm;// �����������
	private String xmmc;// 

	public TzszModel() {
		super();
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

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getTzjxdm() {
		return tzjxdm;
	}

	public void setTzjxdm(String tzjxdm) {
		this.tzjxdm = tzjxdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

}
