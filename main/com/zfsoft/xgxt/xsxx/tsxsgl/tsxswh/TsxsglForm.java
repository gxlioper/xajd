/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-14 ����01:45:59 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.tsxswh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-5-14 ����01:45:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsglForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	  
	private String id; // ����

	private String xh; //ѧ��
	
	private String xslxdm; //����ѧ������
	
	private String gzzt; //��ע״̬
	
	private String qksm; // ���˵��
	
	private String bz; // ��ע

	private String lrsj; //¼��ʱ��
	
	private String  sjzt; //����״̬0ʧЧ1����
	
	private String clcs;//�����ʩ
	
	private String lrr;//¼����
	private String xslxmc;
	
	private String gzztmc;
	
	private String type;
	
	

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
	 * @return the xslxdm
	 */
	public String getXslxdm() {
		return xslxdm;
	}

	/**
	 * @param xslxdmҪ���õ� xslxdm
	 */
	public void setXslxdm(String xslxdm) {
		this.xslxdm = xslxdm;
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
	 * @return the xslxmc
	 */
	public String getXslxmc() {
		return xslxmc;
	}

	/**
	 * @param xslxmcҪ���õ� xslxmc
	 */
	public void setXslxmc(String xslxmc) {
		this.xslxmc = xslxmc;
	}

	/**
	 * @return the gzztmc
	 */
	public String getGzztmc() {
		return gzztmc;
	}

	/**
	 * @param gzztmcҪ���õ� gzztmc
	 */
	public void setGzztmc(String gzztmc) {
		this.gzztmc = gzztmc;
	}
	
	
	
	
	

}
