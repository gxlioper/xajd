/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����04:49:38 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ��������Form 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����04:49:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdsqForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : 5245794201435149247L 
	 */ 
	
	private static final long serialVersionUID = 5245794201435149247L;

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
	
	/**
	 * @���� ��sqid ����id
	 */
	private String sqid;
	
	/**
	 * @���� ��cdid ����id
	 */
	private String cdid;
	
	/**
	 * @���� ��xh ѧ��
	 */
	private String xh;
	
	/**
	 * @���� ��bmlbdm ����������
	 */
	private String bmlbdm;
	
	/**
	 * @���� ��sqsj ����ʱ��
	 */
	private String sqsj;
	
	/**
	 * @���� ��sqsjdkssj ����ʱ��ο�ʼʱ��
	 */
	private String sqsjdkssj;
	
	/**
	 * @���� ��sqsjdjssj ����ʱ��ν���ʱ��
	 */
	private String sqsjdjssj;
	
	/**
	 * @���� ��sqly ��������
	 */
	private String sqly;
	
	/**
	 * @���� ��shzt ���״̬
	 */
	private String shzt;
	
	/**
	 * @���� ��splcid ��������id
	 */
	private String splcid;
	
	/**
	 * @���� ��qxfw Ȩ�޷�Χ
	 */
	private String qxfw;
	private String cyrs;
	private String fzrxm;
	private String fzrlxfs;
	private String type;
	private String cdmc;
	private String xfgfsyxy;
	
	/**
	 * @���� �����췽��
	 */
	public CdsqForm() {
		super();
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	

	/**
	 * @return the cdid
	 */
	public String getCdid() {
		return cdid;
	}

	/**
	 * @param cdidҪ���õ� cdid
	 */
	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the bmlbdm
	 */
	public String getBmlbdm() {
		return bmlbdm;
	}

	/**
	 * @param bmlbdmҪ���õ� bmlbdm
	 */
	public void setBmlbdm(String bmlbdm) {
		this.bmlbdm = bmlbdm;
	}

	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	public String getXfgfsyxy() {
		return xfgfsyxy;
	}

	public void setXfgfsyxy(String xfgfsyxy) {
		this.xfgfsyxy = xfgfsyxy;
	}

	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the sqsjdkssj
	 */
	public String getSqsjdkssj() {
		return sqsjdkssj;
	}

	/**
	 * @param sqsjdkssjҪ���õ� sqsjdkssj
	 */
	public void setSqsjdkssj(String sqsjdkssj) {
		this.sqsjdkssj = sqsjdkssj;
	}

	/**
	 * @return the sqsjdjssj
	 */
	public String getSqsjdjssj() {
		return sqsjdjssj;
	}

	/**
	 * @param sqsjdjssjҪ���õ� sqsjdjssj
	 */
	public void setSqsjdjssj(String sqsjdjssj) {
		this.sqsjdjssj = sqsjdjssj;
	}

	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}

	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
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

	public String getQxfw() {
		return qxfw;
	}

	public void setQxfw(String qxfw) {
		this.qxfw = qxfw;
	}

	/**
	 * @return the cyrs
	 */
	public String getCyrs() {
		return cyrs;
	}

	/**
	 * @param cyrsҪ���õ� cyrs
	 */
	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
	}

	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}

	/**
	 * @param fzrxmҪ���õ� fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}

	/**
	 * @return the fzrlxfs
	 */
	public String getFzrlxfs() {
		return fzrlxfs;
	}

	/**
	 * @param fzrlxfsҪ���õ� fzrlxfs
	 */
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = fzrlxfs;
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
	 * @return the cdmc
	 */
	public String getCdmc() {
		return cdmc;
	}

	/**
	 * @param cdmcҪ���õ� cdmc
	 */
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}
	
	
}
