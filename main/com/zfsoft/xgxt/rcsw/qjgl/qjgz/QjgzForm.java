/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjgz;

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

public class QjgzForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String qjgzid;
	private String kssj;
	private String jssj;
	private String splcid;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String qjqj;
	private String lcxx;
	//�����ֶ�2016-11-29 �������
	private String qjlxid;
	private String open;//����
	private String ssxydm;//����ѧԺdm����qx��ΪȫУ��
	private String ssxymc;//����ѧԺ����

	public String getSsxymc() {
		return ssxymc;
	}

	public void setSsxymc(String ssxymc) {
		this.ssxymc = ssxymc;
	}

	public String getSsxydm() {
		return ssxydm;
	}

	public void setSsxydm(String ssxydm) {
		this.ssxydm = ssxydm;
	}

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
	 * @param qjlxidҪ���õ� qjlxid
	 */
	public void setQjlxid(String qjlxid) {
		this.qjlxid = qjlxid;
	}

	/**
	 * @return the qjgzid
	 */
	public String getQjgzid() {
		return qjgzid;
	}

	/**
	 * @param qjgzidҪ���õ�
	 *            qjgzid
	 */
	public void setQjgzid(String qjgzid) {
		this.qjgzid = qjgzid;
	}

	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}

	/**
	 * @param kssjҪ���õ�
	 *            kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}

	/**
	 * @param jssjҪ���õ�
	 *            jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcidҪ���õ�
	 *            splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pagesҪ���õ�
	 *            pages
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
	 * @param searchModelҪ���õ�
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param typeҪ���õ�
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the qjqj
	 */
	public String getQjqj() {
		return qjqj;
	}

	/**
	 * @param qjqjҪ���õ� qjqj
	 */
	public void setQjqj(String qjqj) {
		this.qjqj = qjqj;
	}

	/**
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}

	/**
	 * @param lcxxҪ���õ� lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}
}
