/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-28 ����02:43:20 
 */  
package com.zfsoft.xgxt.rcsw.rcxwmark;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ�ֱ�ǹ���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-10-28 ����02:43:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwmarkForm extends ActionForm {
	private String id;//ҵ���id������
	private String rcxwjgid; //�ճ���Ϊ���id
	private String jxdm;//�������
	private String pjxn;//����ѧ��
	private String bz;//��ע
	private String czr;//������
	private String czsj;//����ʱ��
	private String type;//��־λ
	//����
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private static final long serialVersionUID = 1L;
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
	 * @return the rcxwjgid
	 */
	public String getRcxwjgid() {
		return rcxwjgid;
	}
	/**
	 * @param rcxwjgidҪ���õ� rcxwjgid
	 */
	public void setRcxwjgid(String rcxwjgid) {
		this.rcxwjgid = rcxwjgid;
	}
	/**
	 * @return the jxdm
	 */
	public String getJxdm() {
		return jxdm;
	}
	/**
	 * @param jxdmҪ���õ� jxdm
	 */
	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}
	/**
	 * @return the pjxn
	 */
	public String getPjxn() {
		return pjxn;
	}
	/**
	 * @param pjxnҪ���õ� pjxn
	 */
	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
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
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czrҪ���õ� czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}
	/**
	 * @param czsjҪ���õ� czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
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
	
	

	
}
