/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjlx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������:form
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:00:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjlxForm extends ActionForm {
	private String qjlxid;
	private String qjlxmc;
	private String open;//����
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	/**
	 * @return the qjlxid
	 */
	public String getQjlxid() {
		return qjlxid;
	}

	/**
	 * @param qjlxidҪ���õ�
	 *            qjlxid
	 */
	public void setQjlxid(String qjlxid) {
		this.qjlxid = qjlxid;
	}

	/**
	 * @return the qjlxmc
	 */
	public String getQjlxmc() {
		return qjlxmc;
	}

	/**
	 * @param qjlxmcҪ���õ�
	 *            qjlxmc
	 */
	public void setQjlxmc(String qjlxmc) {
		this.qjlxmc = qjlxmc;
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
}
