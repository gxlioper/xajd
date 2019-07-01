/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-26 ����04:56:11 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.pfz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ý������_������
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-2-26 ����04:56:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PfzForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String pfzid;	//������ID
	private String pfzmc;	//����������
	private String ssxq;	//����У��
	private String zgh;
	private String xm;
	private String bmdm;
	private String xbdm;
	private String fpzt;	//����״̬
	
	
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
	/**
	 * @return the pfzid
	 */
	public String getPfzid() {
		return pfzid;
	}
	/**
	 * @param pfzidҪ���õ� pfzid
	 */
	public void setPfzid(String pfzid) {
		this.pfzid = pfzid;
	}
	/**
	 * @return the pfzmc
	 */
	public String getPfzmc() {
		return pfzmc;
	}
	/**
	 * @param pfzmcҪ���õ� pfzmc
	 */
	public void setPfzmc(String pfzmc) {
		this.pfzmc = pfzmc;
	}
	/**
	 * @return the ssxq
	 */
	public String getSsxq() {
		return ssxq;
	}
	/**
	 * @param ssxqҪ���õ� ssxq
	 */
	public void setSsxq(String ssxq) {
		this.ssxq = ssxq;
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
	 * @return the xbdm
	 */
	public String getXbdm() {
		return xbdm;
	}
	/**
	 * @param xbdmҪ���õ� xbdm
	 */
	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdmҪ���õ� bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	/**
	 * @return the fpzt
	 */
	public String getFpzt() {
		return fpzt;
	}
	/**
	 * @param fpztҪ���õ� fpzt
	 */
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	

}
