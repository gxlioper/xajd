/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-4-20 ����09:16:35 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.bbsz;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-��������-��Ŀ����-��������
 * @�๦������: �ǼǱ��ϱ�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-20 ����09:16:35 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbszForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String bbdm;// �������
	private String bbmc;// ��������
	private String bblx;// ��������
	private String xmdm;// ��Ŀ����
	public BbszForm() {
		super();
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
	/**
	 * @return the bbdm
	 */
	public String getBbdm() {
		return bbdm;
	}
	/**
	 * @param bbdmҪ���õ� bbdm
	 */
	public void setBbdm(String bbdm) {
		this.bbdm = bbdm;
	}
	/**
	 * @return the bbmc
	 */
	public String getBbmc() {
		return bbmc;
	}
	/**
	 * @param bbmcҪ���õ� bbmc
	 */
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}
	/**
	 * @return the bblx
	 */
	public String getBblx() {
		return bblx;
	}
	/**
	 * @param bblxҪ���õ� bblx
	 */
	public void setBblx(String bblx) {
		this.bblx = bblx;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
}
