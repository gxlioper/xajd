/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-20 ����03:15:35 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.kqsj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-20 ����03:15:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqsjForm extends ActionForm{
	//����ģ��
	ExportModel exportModel = new ExportModel();
	//�߼�����
	SearchModel searchModel = new SearchModel();
	// ��ҳ
	Pages pages = new Pages();
	
	private String id;
	private String xh;
	private String xn;
	private String dyxqsjts;    //��һѧ���¼�����
	private String dexqsjts;    //�ڶ�ѧ���¼�����
	private String dyxqbjts;    //��һѧ�ڲ�������
	private String dexqbjts;	//�ڶ�ѧ�ڲ�������		
	private String kkxs;		//����ѧʱ
	private String cdztcs;		//�ٵ����˴���
	private String type;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the dyxqsjts
	 */
	public String getDyxqsjts() {
		return dyxqsjts;
	}
	/**
	 * @param dyxqsjtsҪ���õ� dyxqsjts
	 */
	public void setDyxqsjts(String dyxqsjts) {
		this.dyxqsjts = dyxqsjts;
	}
	/**
	 * @return the dexqsjts
	 */
	public String getDexqsjts() {
		return dexqsjts;
	}
	/**
	 * @param dexqsjtsҪ���õ� dexqsjts
	 */
	public void setDexqsjts(String dexqsjts) {
		this.dexqsjts = dexqsjts;
	}
	/**
	 * @return the dyxqbjts
	 */
	public String getDyxqbjts() {
		return dyxqbjts;
	}
	/**
	 * @param dyxqbjtsҪ���õ� dyxqbjts
	 */
	public void setDyxqbjts(String dyxqbjts) {
		this.dyxqbjts = dyxqbjts;
	}
	/**
	 * @return the dexqbjts
	 */
	public String getDexqbjts() {
		return dexqbjts;
	}
	/**
	 * @param dexqbjtsҪ���õ� dexqbjts
	 */
	public void setDexqbjts(String dexqbjts) {
		this.dexqbjts = dexqbjts;
	}
	/**
	 * @return the kkxs
	 */
	public String getKkxs() {
		return kkxs;
	}
	/**
	 * @param kkxsҪ���õ� kkxs
	 */
	public void setKkxs(String kkxs) {
		this.kkxs = kkxs;
	}
	/**
	 * @return the cdztcs
	 */
	public String getCdztcs() {
		return cdztcs;
	}
	/**
	 * @param cdztcsҪ���õ� cdztcs
	 */
	public void setCdztcs(String cdztcs) {
		this.cdztcs = cdztcs;
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
