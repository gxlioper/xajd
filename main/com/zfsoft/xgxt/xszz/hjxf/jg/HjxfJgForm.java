/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����11:50:58 
 */  
package com.zfsoft.xgxt.xszz.hjxf.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-15 ����11:50:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjxfJgForm extends ActionForm {
	 private String jgid;
	 private String hjxfid;
	 private String xh;
	 private String xn;
	 private String xq;
	 private String dkqk;
	 private String wnqfje;
	 private String yjje;
	 private String hjje;
	 private String jqjzsj;
	 private String sqyy;
	 private String splcid;
	 private String shzt;
	 private String filepath;
	 private String sqsj;
	 private String pkdj;
	 private String sjly;
	 private String czy;
	
	private String  type;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	//����
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
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
	
	 /**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgidҪ���õ� jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @return the hjjgid
	 */
	public String getHjxfid() {
		return hjxfid;
	}
	/**
	 * @param hjjgidҪ���õ� hjjgid
	 */
	public void setHjxfid(String hjxfid) {
		this.hjxfid = hjxfid;
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
	 * @return the dkqk
	 */
	public String getDkqk() {
		return dkqk;
	}
	/**
	 * @param dkqkҪ���õ� dkqk
	 */
	public void setDkqk(String dkqk) {
		this.dkqk = dkqk;
	}
	/**
	 * @return the wnqfje
	 */
	public String getWnqfje() {
		return wnqfje;
	}
	/**
	 * @param wnqfjeҪ���õ� wnqfje
	 */
	public void setWnqfje(String wnqfje) {
		this.wnqfje = wnqfje;
	}
	/**
	 * @return the yjje
	 */
	public String getYjje() {
		return yjje;
	}
	/**
	 * @param yjjeҪ���õ� yjje
	 */
	public void setYjje(String yjje) {
		this.yjje = yjje;
	}
	/**
	 * @return the hjje
	 */
	public String getHjje() {
		return hjje;
	}
	/**
	 * @param hjjeҪ���õ� hjje
	 */
	public void setHjje(String hjje) {
		this.hjje = hjje;
	}
	/**
	 * @return the jqjzsj
	 */
	public String getJqjzsj() {
		return jqjzsj;
	}
	/**
	 * @param jqjzsjҪ���õ� jqjzsj
	 */
	public void setJqjzsj(String jqjzsj) {
		this.jqjzsj = jqjzsj;
	}
	/**
	 * @return the sqyy
	 */
	public String getSqyy() {
		return sqyy;
	}
	/**
	 * @param sqyyҪ���õ� sqyy
	 */
	public void setSqyy(String sqyy) {
		this.sqyy = sqyy;
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
	 * @return the pkdj
	 */
	public String getPkdj() {
		return pkdj;
	}
	/**
	 * @param pkdjҪ���õ� pkdj
	 */
	public void setPkdj(String pkdj) {
		this.pkdj = pkdj;
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
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 /**
	 * @return the czy
	 */
	public String getCzy() {
		return czy;
	}
	/**
	 * @param czyҪ���õ� czy
	 */
	public void setCzy(String czy) {
		this.czy = czy;
	}
}
