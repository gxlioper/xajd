/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����04:20:13 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (���ֽ���������) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-30 ����04:19:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfjcsqForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String xh;
	private String jcid;            //���id
	private String cfid;            //����ID
	private String sqsj;            //����ʱ��
	private String sqly;            //��������
	private String sqjg;            //������
	private String splcid;            //��������id
	private String jdxx;            //������Ϣ
	
	
	//����ʦ��ѧԺ������ִ�ӡ����Ҫ�õ����ֶ�
	private String xm;				
	private String xb;	
	private String xymc;			//ѧԺ����	
	private String bjmc;			//�༶����
	private String cflbmc;				//�����������
	private String cfsj;			//����ʱ��
	private String cfyymc;				//����ԭ��
	private String jcsj;			//���ֽ��ʱ��
	private String dysj;			//��ӡʱ��
	private String bjyj;//�༶���
	private String qksm;//���˵��
	private String filepath;//���ٽ�����¼��
	private String filepath2;//�ɼ���

	public String getBjyj() {
		return bjyj;
	}

	public void setBjyj(String bjyj) {
		this.bjyj = bjyj;
	}

	public String getQksm() {
		return qksm;
	}

	public void setQksm(String qksm) {
		this.qksm = qksm;
	}

	public String getFilepath2() {
		return filepath2;
	}

	public void setFilepath2(String filepath2) {
		this.filepath2 = filepath2;
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
	
	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getDysj() {
		return dysj;
	}
	public void setDysj(String dysj) {
		this.dysj = dysj;
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
	 * @return the jcid
	 */
	public String getJcid() {
		return jcid;
	}
	/**
	 * @param jcidҪ���õ� jcid
	 */
	public void setJcid(String jcid) {
		this.jcid = jcid;
	}
	/**
	 * @return the cfid
	 */
	public String getCfid() {
		return cfid;
	}
	/**
	 * @param cfidҪ���õ� cfid
	 */
	public void setCfid(String cfid) {
		this.cfid = cfid;
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
	 * @return the sqjg
	 */
	public String getSqjg() {
		return sqjg;
	}
	/**
	 * @param sqjgҪ���õ� sqjg
	 */
	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
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
	public String getJdxx() {
		return jdxx;
	}
	public void setJdxx(String jdxx) {
		this.jdxx = jdxx;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	
	
}
