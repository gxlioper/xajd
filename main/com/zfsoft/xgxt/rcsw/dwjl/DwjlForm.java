/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����11:02:08 
 */  
package com.zfsoft.xgxt.rcsw.dwjl;

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

public class DwjlForm extends ActionForm {
	
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
	private String jlxx ;
	private String jlkssj ;
	private String jljssj ;
	private String jlzy ;
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
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @return		: the jlxx
	 */
	public String getJlxx() {
		return jlxx;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @param 		��jlxx the jlxx to set
	 */
	public void setJlxx(String jlxx) {
		this.jlxx = jlxx;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @return		: the jlkssj
	 */
	public String getJlkssj() {
		return jlkssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @param 		��jlkssj the jlkssj to set
	 */
	public void setJlkssj(String jlkssj) {
		this.jlkssj = jlkssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @return		: the jljssj
	 */
	public String getJljssj() {
		return jljssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @param 		��jljssj the jljssj to set
	 */
	public void setJljssj(String jljssj) {
		this.jljssj = jljssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @return		: the jlzy
	 */
	public String getJlzy() {
		return jlzy;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2017-11-20 ����09:15:41 
	 * @param 		��jlzy the jlzy to set
	 */
	public void setJlzy(String jlzy) {
		this.jlzy = jlzy;
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
