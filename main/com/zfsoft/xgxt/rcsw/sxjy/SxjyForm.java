/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����11:02:08 
 */  
package com.zfsoft.xgxt.rcsw.sxjy;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @className	�� DwjlForm
 * @description	�� TODO(��������������)
 * @author 		��CP��1352��
 * @date		�� 2017-11-20 ����09:13:12
 * @version 	V1.0
 */

public class SxjyForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();
	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();

	FormFile uploadFile;// �ϴ��ļ�

	private String yclx;//�쳣��������
	
	private String[] primarykey_checkVal;// CheckBox
	
	private String type; //����
	
	private String id ;//����
	private String xh ; //ѧ��
	private String xxkt ;
	private String xxkssj ;
	private String xxjssj ;
	private String pj ;
	private String bz ;
	private String filepath;
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
	 * @return the sqsj
	 */
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @return		: the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @param 		��id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����05:10:56 
	 * @return		: the xxkt
	 */
	public String getXxkt() {
		return xxkt;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����05:10:56 
	 * @param 		��xxkt the xxkt to set
	 */
	public void setXxkt(String xxkt) {
		this.xxkt = xxkt;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����05:10:56 
	 * @return		: the xxkssj
	 */
	public String getXxkssj() {
		return xxkssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����05:10:56 
	 * @param 		��xxkssj the xxkssj to set
	 */
	public void setXxkssj(String xxkssj) {
		this.xxkssj = xxkssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����05:10:56 
	 * @return		: the xxjssj
	 */
	public String getXxjssj() {
		return xxjssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����05:10:56 
	 * @param 		��xxjssj the xxjssj to set
	 */
	public void setXxjssj(String xxjssj) {
		this.xxjssj = xxjssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����05:10:56 
	 * @return		: the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����05:10:56 
	 * @param 		��pj the pj to set
	 */
	public void setPj(String pj) {
		this.pj = pj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @return		: the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @param 		��bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @param 		��filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	
}
