/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-12 ����09:54:05 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-12 ����09:54:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxKzxxglForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	/** 
	 * @���� pages : ��ҳģ�� 
	 */
	private Pages pages = new Pages();
	
	/** 
	 * @���� searchModel : �߼���ѯģ�� 
	 */
	private SearchModel searchModel = new SearchModel();
	
	/** 
	 * @���� exportModel : ����ģ�� 
	 */
	private ExportModel exportModel = new ExportModel();
	
	
	private String sqid;
	
	private String splc;
	
	private String xh;
	
	private String shzt;
	
	private String sqsj;
	
	private String czr;

	private String exendDateModuleId;
	//��չ����
	private String extendData;
	//��������save or submit
	private String actionType;
	
	private String type;
	
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
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the extendData
	 */
	public String getExtendData() {
		return extendData;
	}

	/**
	 * @param extendDataҪ���õ� extendData
	 */
	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionTypeҪ���õ� actionType
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the exendDateModuleId
	 */
	public String getExendDateModuleId() {
		return exendDateModuleId;
	}

	/**
	 * @param exendDateModuleIdҪ���õ� exendDateModuleId
	 */
	public void setExendDateModuleId(String exendDateModuleId) {
		this.exendDateModuleId = exendDateModuleId;
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
	
   	
	
}
