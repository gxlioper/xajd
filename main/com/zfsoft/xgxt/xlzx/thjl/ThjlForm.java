/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-10 ����11:12:19 
 */  
package com.zfsoft.xgxt.xlzx.thjl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ѧ��ά��ģ��(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-10 ����11:10:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ThjlForm extends ActionForm{
	private static final long serialVersionUID = 1L;


	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	  
	private String id; // ����
	
	private String xh; //ѧ��

	private String zgh; // ��ţ�ְ���ţ�
	
	private String thsj; //̸��ʱ��
	
	private String thnr; // ̸������
	
	private String bz; // ��ע

	private String cjsj; //����ʱ��

	private String  sjzt; //����״̬0ʧЧ1����
	
	private String  fjid; //����id

	private ExportModel exportModel = new ExportModel();

	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}

	/**
	 * @param fjidҪ���õ� fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
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
	 * @return the thsj
	 */
	public String getThsj() {
		return thsj;
	}

	/**
	 * @param thsjҪ���õ� thsj
	 */
	public void setThsj(String thsj) {
		this.thsj = thsj;
	}

	/**
	 * @return the thnr
	 */
	public String getThnr() {
		return thnr;
	}

	/**
	 * @param thnrҪ���õ� thnr
	 */
	public void setThnr(String thnr) {
		this.thnr = thnr;
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
	 * @return the cjsj
	 */
	public String getCjsj() {
		return cjsj;
	}

	/**
	 * @param cjsjҪ���õ� cjsj
	 */
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	/**
	 * @return the sjzt
	 */
	public String getSjzt() {
		return sjzt;
	}

	/**
	 * @param sjztҪ���õ� sjzt
	 */
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
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

}
