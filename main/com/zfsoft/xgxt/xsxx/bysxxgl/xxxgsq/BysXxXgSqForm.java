/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-8 ����04:31:20 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-8 ����04:31:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysXxXgSqForm extends ActionForm{
	private static final long serialVersionUID = -7538118568402400940L;
	private String sqid;// ����ID
	private String xh;// ѧ��
	private String xgsj;// �޸�ʱ��
	private String shjg;// ��˽��(δ���,�����,ͨ��,�˻�)
	private String xgzd;// �޸��ֶ�
	private String splc;//��������
	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjgҪ���õ� shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the xgzd
	 */
	public String getXgzd() {
		return xgzd;
	}
	/**
	 * @param xgzdҪ���õ� xgzd
	 */
	public void setXgzd(String xgzd) {
		this.xgzd = xgzd;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	
	
	
	

}
