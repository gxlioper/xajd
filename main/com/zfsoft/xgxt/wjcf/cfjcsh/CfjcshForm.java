/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����06:37:58 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (���ֽ�����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-30 ����06:36:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfjcshForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
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
	private String sqjg; //������
	private String jcwh; //����ĺ�
	private String jcsj; //���ʱ��
	private String xn;
	private String xq;
	private String xh; //����ĺ�
	private String thgw; //���ʱ��
	private String jdxx; //������Ϣ
	private String filepath;//������ַ
	private String filepath2;

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
	 * @return the jcwh
	 */
	public String getJcwh() {
		return jcwh;
	}
	/**
	 * @param jcwhҪ���õ� jcwh
	 */
	public void setJcwh(String jcwh) {
		this.jcwh = jcwh;
	}
	/**
	 * @return the jcsj
	 */
	public String getJcsj() {
		return jcsj;
	}
	/**
	 * @param jcsjҪ���õ� jcsj
	 */
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
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
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
