/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-15 ����03:06:07 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.hsdxd;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-15 ����03:06:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HsdxdForm extends ActionForm {
	private String id;
	private String htbh;
	private String xdxn;
	private String xdje;
	private String xdly;
	private String sqsj;
	private String splc;
	private String shzt;
	private String xh;
	private String xn;
	private String fkje;
	private String fksj;
	private String fkpzh;
	private String dkzh;
	private String khh;
	private String fkzt;
	private String xq;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages(); 
	private static final long serialVersionUID = 1L;
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the fkje
	 */
	public String getFkje() {
		return fkje;
	}
	/**
	 * @param fkjeҪ���õ� fkje
	 */
	public void setFkje(String fkje) {
		this.fkje = fkje;
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
	 * @return the fkpzh
	 */
	public String getFkpzh() {
		return fkpzh;
	}
	/**
	 * @param fkpzhҪ���õ� fkpzh
	 */
	public void setFkpzh(String fkpzh) {
		this.fkpzh = fkpzh;
	}
	/**
	 * @return the dkzh
	 */
	public String getDkzh() {
		return dkzh;
	}
	/**
	 * @param dkzhҪ���õ� dkzh
	 */
	public void setDkzh(String dkzh) {
		this.dkzh = dkzh;
	}
	/**
	 * @return the khh
	 */
	public String getKhh() {
		return khh;
	}
	/**
	 * @param khhҪ���õ� khh
	 */
	public void setKhh(String khh) {
		this.khh = khh;
	}
	/**
	 * @return the fkzt
	 */
	public String getFkzt() {
		return fkzt;
	}
	/**
	 * @param fkztҪ���õ� fkzt
	 */
	public void setFkzt(String fkzt) {
		this.fkzt = fkzt;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
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
	 * @return the htbh
	 */
	public String getHtbh() {
		return htbh;
	}
	/**
	 * @param htbhҪ���õ� htbh
	 */
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	/**
	 * @return the xdxn
	 */
	public String getXdxn() {
		return xdxn;
	}
	/**
	 * @param xdxnҪ���õ� xdxn
	 */
	public void setXdxn(String xdxn) {
		this.xdxn = xdxn;
	}
	/**
	 * @return the xdje
	 */
	public String getXdje() {
		return xdje;
	}
	/**
	 * @param xdjeҪ���õ� xdje
	 */
	public void setXdje(String xdje) {
		this.xdje = xdje;
	}
	/**
	 * @return the xdly
	 */
	public String getXdly() {
		return xdly;
	}
	/**
	 * @param xdlyҪ���õ� xdly
	 */
	public void setXdly(String xdly) {
		this.xdly = xdly;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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

}
