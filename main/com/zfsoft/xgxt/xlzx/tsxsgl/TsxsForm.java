/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-10 ����11:10:19 
 */  
package com.zfsoft.xgxt.xlzx.tsxsgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ѧ��ά��ģ��(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-10 ����11:10:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	  
	private String id; // ����

	private String xh; //ѧ��
	
	private String knlxdm; //��������(�����Ƽ���ѧ��Ԥ���̶�)
	
	private String gzzt; //��ע״̬
	
	private String qksm; // ���˵��
	
	private String bz; // ��ע

	private String xgsj; //�޸�ʱ��
	
	private String sjzt; //����״̬0ʧЧ1����
	
	private String jbqkms;//������������������Ƽ���ѧ��
	
	private String clcs;//�����ʩ�������Ƽ���ѧ��
	
	private String lrsj;//¼��ʱ�䣨�����Ƽ���ѧ��
	
	private String fjid;//�����������Ƽ���ѧ��
	
	private String gzsj;//����ʱ�䣨�����Ƽ���ѧ��
	
	private String gznr;//�������ݣ������Ƽ���ѧ��
	
	private String zc;//�ܴΣ����ϳ���ѧԺ��
	
	private String yyms;//ԭ�����������ϳ���ѧԺ��
	
	
	
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}

	/**
	 * @param zcҪ���õ� zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}

	/**
	 * @return the yyms
	 */
	public String getYyms() {
		return yyms;
	}

	/**
	 * @param yymsҪ���õ� yyms
	 */
	public void setYyms(String yyms) {
		this.yyms = yyms;
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
	 * @return the gzzt
	 */
	public String getGzzt() {
		return gzzt;
	}

	/**
	 * @param gzztҪ���õ� gzzt
	 */
	public void setGzzt(String gzzt) {
		this.gzzt = gzzt;
	}

	/**
	 * @return the qksm
	 */
	public String getQksm() {
		return qksm;
	}

	/**
	 * @param qksmҪ���õ� qksm
	 */
	public void setQksm(String qksm) {
		this.qksm = qksm;
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
	 * @return the xgsj
	 */
	public String getXgsj() {
		return xgsj;
	}

	/**
	 * @param xgsjҪ���õ� xgsj
	 */
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	/**
	 * @return the knlxdm
	 */
	public String getKnlxdm() {
		return knlxdm;
	}
	
	/**
	 * @param knlxdmҪ���õ� knlxdm
	 */
	public void setKnlxdm(String knlxdm) {
		this.knlxdm = knlxdm;
	}

	/**
	 * @return the sjzt
	 */
	public String getSjzt() {
		return sjzt;
	}

	/**
	 * @param sjztҪ���õ� sjzt
	 */
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
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
	 * @return the clcs
	 */
	public String getClcs() {
		return clcs;
	}

	/**
	 * @param clcsҪ���õ� clcs
	 */
	public void setClcs(String clcs) {
		this.clcs = clcs;
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
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}

	/**
	 * @param fjidҪ���õ� fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	/**
	 * @return the gzsj
	 */
	public String getGzsj() {
		return gzsj;
	}

	/**
	 * @param gzsjҪ���õ� gzsj
	 */
	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}

	/**
	 * @return the gznr
	 */
	public String getGznr() {
		return gznr;
	}

	/**
	 * @param gznrҪ���õ� gznr
	 */
	public void setGznr(String gznr) {
		this.gznr = gznr;
	}
	
}
