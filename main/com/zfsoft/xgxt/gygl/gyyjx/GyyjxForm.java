/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-24 ����11:31:10 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-24 ����11:31:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyyjxForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();

	private String type;
	
	private String yjfldm; //����������
	
	private String yjflmc; //�����������
	
	
	private String gyyjid; //���id
	
	private String xh;//ѧ��
	
	private String yjms;//�������
	
	private String yjsj; //���ʱ��
	
	private String fkqk;//�������
	
	private String fknr;//��������
	
	private String fkr;//������
	
	private String fksj;//����ʱ��

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
	 * @return the yjfldm
	 */
	public String getYjfldm() {
		return yjfldm;
	}

	/**
	 * @param yjfldmҪ���õ� yjfldm
	 */
	public void setYjfldm(String yjfldm) {
		this.yjfldm = yjfldm;
	}

	/**
	 * @return the yjflmc
	 */
	public String getYjflmc() {
		return yjflmc;
	}

	/**
	 * @param yjflmcҪ���õ� yjflmc
	 */
	public void setYjflmc(String yjflmc) {
		this.yjflmc = yjflmc;
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
	 * @return the gyyjid
	 */
	public String getGyyjid() {
		return gyyjid;
	}

	/**
	 * @param gyyjidҪ���õ� gyyjid
	 */
	public void setGyyjid(String gyyjid) {
		this.gyyjid = gyyjid;
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
	 * @return the yjms
	 */
	public String getYjms() {
		return yjms;
	}

	/**
	 * @param yjmsҪ���õ� yjms
	 */
	public void setYjms(String yjms) {
		this.yjms = yjms;
	}

	/**
	 * @return the fkqk
	 */
	public String getFkqk() {
		return fkqk;
	}

	/**
	 * @param fkqkҪ���õ� fkqk
	 */
	public void setFkqk(String fkqk) {
		this.fkqk = fkqk;
	}

	/**
	 * @return the fknr
	 */
	public String getFknr() {
		return fknr;
	}

	/**
	 * @param fknrҪ���õ� fknr
	 */
	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

	/**
	 * @return the fkr
	 */
	public String getFkr() {
		return fkr;
	}

	/**
	 * @param fkrҪ���õ� fkr
	 */
	public void setFkr(String fkr) {
		this.fkr = fkr;
	}

	/**
	 * @return the fksj
	 */
	public String getFksj() {
		return fksj;
	}

	/**
	 * @param fksjҪ���õ� fksj
	 */
	public void setFksj(String fksj) {
		this.fksj = fksj;
	}

	/**
	 * @return the yjsj
	 */
	public String getYjsj() {
		return yjsj;
	}

	/**
	 * @param yjsjҪ���õ� yjsj
	 */
	public void setYjsj(String yjsj) {
		this.yjsj = yjsj;
	}
	
	
	
}
