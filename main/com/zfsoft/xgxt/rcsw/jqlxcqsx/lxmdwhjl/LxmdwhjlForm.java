/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��3��27�� ����1:51:04 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ĩ������У����ģ��
 * @�๦������: ��У����ά����¼Form
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��3��27�� ����1:51:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxmdwhjlForm extends ActionForm{
	
	private static final long serialVersionUID = 4504656165427919558L;
	
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	
	private String jlid;		//��У����ά����¼id
	private String czr;			//������
	private String czsj;		//����ʱ��
	private String czlx;		//��������
	private String xh;			//ѧ��
	private String xm;			//����
	private String xmid;		//��У��Ŀid
	private String xgqlxqksm;	//�޸�ǰ��У���˵��
	private String xghlxqksm;	//�޸ĺ���У���˵��
	private String czxq;		//��������
	
	
	/**
	 * @return the jlid
	 */
	public String getJlid() {
		return jlid;
	}
	/**
	 * @param jlidҪ���õ� jlid
	 */
	public void setJlid(String jlid) {
		this.jlid = jlid;
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
	 * @return the czlx
	 */
	public String getCzlx() {
		return czlx;
	}
	/**
	 * @param czlxҪ���õ� czlx
	 */
	public void setCzlx(String czlx) {
		this.czlx = czlx;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmidҪ���õ� xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the xgqlxqksm
	 */
	public String getXgqlxqksm() {
		return xgqlxqksm;
	}
	/**
	 * @param xgqlxqksmҪ���õ� xgqlxqksm
	 */
	public void setXgqlxqksm(String xgqlxqksm) {
		this.xgqlxqksm = xgqlxqksm;
	}
	/**
	 * @return the xghlxqksm
	 */
	public String getXghlxqksm() {
		return xghlxqksm;
	}
	/**
	 * @param xghlxqksmҪ���õ� xghlxqksm
	 */
	public void setXghlxqksm(String xghlxqksm) {
		this.xghlxqksm = xghlxqksm;
	}
	/**
	 * @return the czxq
	 */
	public String getCzxq() {
		return czxq;
	}
	/**
	 * @param czxqҪ���õ� czxq
	 */
	public void setCzxq(String czxq) {
		this.czxq = czxq;
	}
	
	
	
	
}
