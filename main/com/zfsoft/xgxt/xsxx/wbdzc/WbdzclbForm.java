/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����10:58:08 
 */  
package com.zfsoft.xgxt.xsxx.wbdzc;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ--����ע�ᣨ��ʦ��--δ������ע�ᣩ���
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-3-8 ����11:21:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WbdzclbForm extends ActionForm{
	
	private static final long serialVersionUID = -1860077048529228835L;
	// ��ҳ
	Pages pages = new Pages();
	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	FormFile uploadFile;// �ϴ��ļ�
	private String yclx;//�쳣��������
	private String[] primarykey_checkVal;// CheckBox
	private String type;
	private String wbdlbdm ;//δ����ע�����
	private String wbdlbmc ;//δ����ע������
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
	 * @return the uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}
	/**
	 * @param uploadFileҪ���õ� uploadFile
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	/**
	 * @return the yclx
	 */
	public String getYclx() {
		return yclx;
	}
	/**
	 * @param yclxҪ���õ� yclx
	 */
	public void setYclx(String yclx) {
		this.yclx = yclx;
	}
	/**
	 * @return the primarykey_checkVal
	 */
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	/**
	 * @param primarykeyCheckValҪ���õ� primarykey_checkVal
	 */
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
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
	 * @return the wbdlbdm
	 */
	public String getWbdlbdm() {
		return wbdlbdm;
	}
	/**
	 * @param wbdlbdmҪ���õ� wbdlbdm
	 */
	public void setWbdlbdm(String wbdlbdm) {
		this.wbdlbdm = wbdlbdm;
	}
	/**
	 * @return the wbdlbmc
	 */
	public String getWbdlbmc() {
		return wbdlbmc;
	}
	/**
	 * @param wbdlbmcҪ���õ� wbdlbmc
	 */
	public void setWbdlbmc(String wbdlbmc) {
		this.wbdlbmc = wbdlbmc;
	}
	
}
