/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����10:03:33 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����10:03:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XxsbjgForm extends ActionForm {
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String sbjgid;
	
	private String sbsqid;
	
	private String xh;
	
	private String sblx;
	
	private String sbsj;
	
	private String sbzbid;
	
	private String ztqk;
	
	private String xlxsxxqk;
	
	private String bz;
	
	private String sjly;
	
	private String xn;
	
	private String xq;

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
	 * @return the sbjgid
	 */
	public String getSbjgid() {
		return sbjgid;
	}

	/**
	 * @param sbjgidҪ���õ� sbjgid
	 */
	public void setSbjgid(String sbjgid) {
		this.sbjgid = sbjgid;
	}

	/**
	 * @return the sbsqid
	 */
	public String getSbsqid() {
		return sbsqid;
	}

	/**
	 * @param sbsqidҪ���õ� sbsqid
	 */
	public void setSbsqid(String sbsqid) {
		this.sbsqid = sbsqid;
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
	 * @return the sblx
	 */
	public String getSblx() {
		return sblx;
	}

	/**
	 * @param sblxҪ���õ� sblx
	 */
	public void setSblx(String sblx) {
		this.sblx = sblx;
	}

	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}

	/**
	 * @param sbsjҪ���õ� sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	/**
	 * @return the sbzbid
	 */
	public String getSbzbid() {
		return sbzbid;
	}

	/**
	 * @param sbzbidҪ���õ� sbzbid
	 */
	public void setSbzbid(String sbzbid) {
		this.sbzbid = sbzbid;
	}

	/**
	 * @return the ztqk
	 */
	public String getZtqk() {
		return ztqk;
	}

	/**
	 * @param ztqkҪ���õ� ztqk
	 */
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}

	/**
	 * @return the xlxsxxqk
	 */
	public String getXlxsxxqk() {
		return xlxsxxqk;
	}

	/**
	 * @param xlxsxxqkҪ���õ� xlxsxxqk
	 */
	public void setXlxsxxqk(String xlxsxxqk) {
		this.xlxsxxqk = xlxsxxqk;
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
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
}
