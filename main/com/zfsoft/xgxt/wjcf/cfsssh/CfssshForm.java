/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����09:23:08 
 */  
package com.zfsoft.xgxt.wjcf.cfsssh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (�������) 
 * @���ߣ�������[����:913]
 * @ʱ�䣺 2013-10-30 ����09:23:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfssshForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String ssfilepath;
	
	private String shlx;
	private String guid ;//ID
	private String shid ;//ID
	private String ywid ;//ҵ��ID
	private String shr ;//�����
	private String shsj ;//���ʱ��
	private String shzt ;//���״̬
	private String shyj ;//������
	private String gwid ;//��˸�λ
	private String splcid ;//��������id
	private String cfid;//����id
	private String sqly; //��������
	private String sqsj; //����ʱ��
	private String ssjg; //���߽��
	private String sswh; //�����ĺ�
	private String sssj; //����ʱ��
	private String zzssjg; //���߽��
	private String cfggw; //���ֵ�������
	private String fjmc;
	
	private String xh;
	private String thgw;//�˻ظ�λ
	
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	
	
		
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmcҪ���õ� fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the ssfilepath
	 */
	public String getSsfilepath() {
		return ssfilepath;
	}
	/**
	 * @param ssfilepathҪ���õ� ssfilepath
	 */
	public void setSsfilepath(String ssfilepath) {
		this.ssfilepath = ssfilepath;
	}
	/**
	 * @return the zzssjg
	 */
	public String getZzssjg() {
		return zzssjg;
	}
	/**
	 * @param zzssjgҪ���õ� zzssjg
	 */
	public void setZzssjg(String zzssjg) {
		this.zzssjg = zzssjg;
	}
	/**
	 * @return the cfggw
	 */
	public String getCfggw() {
		return cfggw;
	}
	/**
	 * @param cfggwҪ���õ� cfggw
	 */
	public void setCfggw(String cfggw) {
		this.cfggw = cfggw;
	}
	/**
	 * @return the sswh
	 */
	public String getSswh() {
		return sswh;
	}
	/**
	 * @param sswhҪ���õ� sswh
	 */
	public void setSswh(String sswh) {
		this.sswh = sswh;
	}
	/**
	 * @return the sssj
	 */
	public String getSssj() {
		return sssj;
	}
	/**
	 * @param sssjҪ���õ� sssj
	 */
	public void setSssj(String sssj) {
		this.sssj = sssj;
	}
	/**
	 * @return the ssjg
	 */
	public String getSsjg() {
		return ssjg;
	}
	/**
	 * @param ssjgҪ���õ� ssjg
	 */
	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
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
	 * @return the shlx
	 */
	public String getShlx() {
		return shlx;
	}
	/**
	 * @param shlxҪ���õ� shlx
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guidҪ���õ� guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywidҪ���õ� ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shrҪ���õ� shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsjҪ���õ� shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
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
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyjҪ���õ� shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwidҪ���õ� gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
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
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	
	

}
