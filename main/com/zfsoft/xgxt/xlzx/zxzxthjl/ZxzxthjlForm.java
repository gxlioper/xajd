/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-1 ����09:57:38 
 */  
package com.zfsoft.xgxt.xlzx.zxzxthjl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-7-1 ����09:57:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxzxthjlForm extends ActionForm{
	private static final long serialVersionUID = 1L;//serialVersionUID���������л�ʱ���ְ汾�ļ����ԣ����ڰ汾����ʱ�����л��Ա��ֶ����Ψһ��
	
	SearchModel searchModel = new SearchModel();
	
	Pages pages = new Pages();
	private String type; //����
	private ExportModel exportModel = new ExportModel();
	private String id; // ����
	private String xh; //ѧ��
	private String xm; //����
	private String ytr; //Լ̸��
	private String thsj; //̸��ʱ��
	private String jbqkms; //�����������
	private String cbpgdm; //������������
	private String ybwtlb; //һ���������
	private String ybwtsfzx; //һ�������Ƿ���ѯ,�Ƿ�ԤԼ��ѯʱ��
	private String zajb; //�����ϰ��ͼ���
	private String cbpgjg; //�����������
	private String zajbsmzx; //�����ϰ��ͼ����Ƿ���ѯ
	private String sfzj; //�Ƿ�ת��
	private String bz; //��ע
	
	
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
	 * @return the ytr
	 */
	public String getYtr() {
		return ytr;
	}
	/**
	 * @param ytrҪ���õ� ytr
	 */
	public void setYtr(String ytr) {
		this.ytr = ytr;
	}
	/**
	 * @return the thsj
	 */
	public String getThsj() {
		return thsj;
	}
	/**
	 * @param thsjҪ���õ� thsj
	 */
	public void setThsj(String thsj) {
		this.thsj = thsj;
	}
	/**
	 * @return the jbqkms
	 */
	public String getJbqkms() {
		return jbqkms;
	}
	/**
	 * @param jbqkmsҪ���õ� jbqkms
	 */
	public void setJbqkms(String jbqkms) {
		this.jbqkms = jbqkms;
	}
	/**
	 * @return the cbpgdm
	 */
	public String getCbpgdm() {
		return cbpgdm;
	}
	/**
	 * @param cbpgdmҪ���õ� cbpgdm
	 */
	public void setCbpgdm(String cbpgdm) {
		this.cbpgdm = cbpgdm;
	}
	/**
	 * @return the ybwtlb
	 */
	public String getYbwtlb() {
		return ybwtlb;
	}
	/**
	 * @param ybwtlbҪ���õ� ybwtlb
	 */
	public void setYbwtlb(String ybwtlb) {
		this.ybwtlb = ybwtlb;
	}
	/**
	 * @return the ybwtsfzx
	 */
	public String getYbwtsfzx() {
		return ybwtsfzx;
	}
	/**
	 * @param ybwtsfzxҪ���õ� ybwtsfzx
	 */
	public void setYbwtsfzx(String ybwtsfzx) {
		this.ybwtsfzx = ybwtsfzx;
	}
	/**
	 * @return the zajb
	 */
	public String getZajb() {
		return zajb;
	}
	/**
	 * @param zajbҪ���õ� zajb
	 */
	public void setZajb(String zajb) {
		this.zajb = zajb;
	}
	/**
	 * @return the cbpgjg
	 */
	public String getCbpgjg() {
		return cbpgjg;
	}
	/**
	 * @param cbpgjgҪ���õ� cbpgjg
	 */
	public void setCbpgjg(String cbpgjg) {
		this.cbpgjg = cbpgjg;
	}
	/**
	 * @return the zajbsmzx
	 */
	public String getZajbsmzx() {
		return zajbsmzx;
	}
	/**
	 * @param zajbsmzxҪ���õ� zajbsmzx
	 */
	public void setZajbsmzx(String zajbsmzx) {
		this.zajbsmzx = zajbsmzx;
	}
	/**
	 * @return the sfzj
	 */
	public String getSfzj() {
		return sfzj;
	}
	/**
	 * @param sfzjҪ���õ� sfzj
	 */
	public void setSfzj(String sfzj) {
		this.sfzj = sfzj;
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
}
