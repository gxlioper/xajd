/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-20 ����11:22:55 
 */  
package com.zfsoft.xgxt.rcsw.txhd.dmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-20 ����11:22:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TxhdDmwhForm extends ActionForm {
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	
	private String lbdm ;//������
	private String lbmc ;//�������
	private String lbsm ;//���˵��
	
	//��������ά��form�ֶ�
	private String hdggdm ;
	private String hdggmc ;
	public String getType() {
		return type;
	}
	/**
	 * @return the hdggdm
	 */
	public String getHdggdm() {
		return hdggdm;
	}
	/**
	 * @param hdggdmҪ���õ� hdggdm
	 */
	public void setHdggdm(String hdggdm) {
		this.hdggdm = hdggdm;
	}
	/**
	 * @return the hdggmc
	 */
	public String getHdggmc() {
		return hdggmc;
	}
	/**
	 * @param hdggmcҪ���õ� hdggmc
	 */
	public void setHdggmc(String hdggmc) {
		this.hdggmc = hdggmc;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
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
