/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-29 ����11:34:21 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zdzc;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2015-5-29 ����11:34:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzcForm extends ActionForm {
	
	
	private static final long serialVersionUID = -4392027219234431796L;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String doType;
	private String editType;		//�ж��۲���Ŀ�Ǳ༭ģʽ���ǲ鿴ģʽ
	
	private String id;
	private String xh;
	private String xm;
	private String bjdm;
	private String fs;
	private String lrr;
	private String lrsj;
	private String xn;
	private String xq;
	private String xmdm;
	private FormFile importFile;
	private String pmfs;	//������ʽ
	private String qxyy;	//ȡ��ԭ��
	private String tjr;	//ԭ�ύ��
	private String tjsj;	//ԭ�ύʱ��
	private String zczq;	//�۲�����
	
	
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
	 * @return the doType
	 */
	public String getDoType() {
		return doType;
	}
	/**
	 * @param doTypeҪ���õ� doType
	 */
	public void setDoType(String doType) {
		this.doType = doType;
	}
	/**
	 * @return the editType
	 */
	public String getEditType() {
		return editType;
	}
	/**
	 * @param editTypeҪ���õ� editType
	 */
	public void setEditType(String editType) {
		this.editType = editType;
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
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fsҪ���õ� fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrrҪ���õ� lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsjҪ���õ� lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the importFile
	 */
	public FormFile getImportFile() {
		return importFile;
	}
	/**
	 * @param importFileҪ���õ� importFile
	 */
	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}
	/**
	 * @return the pmfs
	 */
	public String getPmfs() {
		return pmfs;
	}
	/**
	 * @param pmfsҪ���õ� pmfs
	 */
	public void setPmfs(String pmfs) {
		this.pmfs = pmfs;
	}
	/**
	 * @return the qxyy
	 */
	public String getQxyy() {
		return qxyy;
	}
	/**
	 * @param qxyyҪ���õ� qxyy
	 */
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
	/**
	 * @return the tjr
	 */
	public String getTjr() {
		return tjr;
	}
	/**
	 * @param tjrҪ���õ� tjr
	 */
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	/**
	 * @return the tjsj
	 */
	public String getTjsj() {
		return tjsj;
	}
	/**
	 * @param tjsjҪ���õ� tjsj
	 */
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	/**
	 * @return the zczq
	 */
	public String getZczq() {
		return zczq;
	}
	/**
	 * @param zczqҪ���õ� zczq
	 */
	public void setZczq(String zczq) {
		this.zczq = zczq;
	}
	

}
