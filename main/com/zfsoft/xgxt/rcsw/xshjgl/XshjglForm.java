/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-31 ����04:47:08 
 */  
package com.zfsoft.xgxt.rcsw.xshjgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2015-9-14 ����09:34:48 
 * @�汾�� V5.17
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XshjglForm extends ActionForm {
	
	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	
	private String type; //����
	
	private String hjglid; //��������ID
	private String xh; //ѧ��
	private String qyzt; //Ǩ��״̬0:Ǩ��1:Ǩ��
	private String qysj; //Ǩ��ʱ��
	private String bz; //��ע
	private String xm; //����
	private String xb; //�Ա�
	private String nj; //�꼶
	private String xymc; //ѧԺ����
	private String bjmc; //�༶����
	private String zymc; //רҵ����
	private String lxdh; //��ϵ�绰
	private String sfzh; //���֤��
	private String jgmc; //����
	private String qyztmc; //qyztmc
	
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
	 * @return the hjglid
	 */
	public String getHjglid() {
		return hjglid;
	}
	/**
	 * @param hjglidҪ���õ� hjglid
	 */
	public void setHjglid(String hjglid) {
		this.hjglid = hjglid;
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
	 * @return the qyzt
	 */
	public String getQyzt() {
		return qyzt;
	}
	/**
	 * @param qyztҪ���õ� qyzt
	 */
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}
	/**
	 * @return the qysj
	 */
	public String getQysj() {
		return qysj;
	}
	/**
	 * @param qysjҪ���õ� qysj
	 */
	public void setQysj(String qysj) {
		this.qysj = qysj;
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
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymcҪ���õ� xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymcҪ���õ� zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * @param sfzhҪ���õ� sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	
	/**
	 * @return the qyztmc
	 */
	public String getQyztmc() {
		return qyztmc;
	}
	/**
	 * @param qyztmcҪ���õ� qyztmc
	 */
	public void setQyztmc(String qyztmc) {
		this.qyztmc = qyztmc;
	}
	/**
	 * @return the jgmc
	 */
	public String getJgmc() {
		return jgmc;
	}
	/**
	 * @param jgmcҪ���õ� jgmc
	 */
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}
}