/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmlbwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ŀ������bean
 * @�๦������:
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����06:33:00
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmlbwhForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String lbdm;// ������
	private String lbmc;// �������
	private String lbsm;// ���˵��

	public XmlbwhForm() {
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

	public String getLbdm() {
		return lbdm;
	}

	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	public String getLbsm() {
		return lbsm;
	}

	public void setLbsm(String lbsm) {
		this.lbsm = lbsm;
	}

}
