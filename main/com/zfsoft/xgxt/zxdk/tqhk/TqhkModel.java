/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-5 ����09:29:08 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ǰ���� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-5 ����09:29:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TqhkModel extends SuperAuditModel {

	private static final long serialVersionUID = 5193255054692579117L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String id;
	private String xh;
	private String hkzh;
	private String hkje;
	private String hkbj;
	private String hkly;
	private String bz;
	private String splcid;
	private String shzt;
	private String sqsj;
	private String filepath;
	
	
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
	 * @return the hkbj
	 */
	public String getHkbj() {
		return hkbj;
	}
	/**
	 * @param hkbjҪ���õ� hkbj
	 */
	public void setHkbj(String hkbj) {
		this.hkbj = hkbj;
	}
	/**
	 * @return the hkzh
	 */
	public String getHkzh() {
		return hkzh;
	}
	/**
	 * @param hkzhҪ���õ� hkzh
	 */
	public void setHkzh(String hkzh) {
		this.hkzh = hkzh;
	}
	/**
	 * @return the hkje
	 */
	public String getHkje() {
		return hkje;
	}
	/**
	 * @param hkjeҪ���õ� hkje
	 */
	public void setHkje(String hkje) {
		this.hkje = hkje;
	}
	/**
	 * @return the hkly
	 */
	public String getHkly() {
		return hkly;
	}
	/**
	 * @param hklyҪ���õ� hkly
	 */
	public void setHkly(String hkly) {
		this.hkly = hkly;
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}
