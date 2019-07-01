/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����11:26:25 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.bcjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����11:26:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BcjgModel extends ActionForm {

	
	private static final long serialVersionUID = 1966791940187986544L;
	
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String bysj;
	private String fwnx;
	private String dwmc;
	private String dwdz;
	private String dwdh;
	private String xfje;
	private String dkbj;
	private String dcje;
	private String dclb;
	private String sqsj;
	private String sjly;
	private String xn;
	private String yjxf;
	private String yjxfmc;
	private String yjnxf;
	private String dclbmc;
	private String filepath;
	private String dwyb;
	private String dkhth;
	private String dkkssj;
	private String dkjssj;
	private String yhdm;
	private String yhmc;
	private String hyxz;//��ҵ����
	private String zwmc;//ְ������
	/*��ʦ����Ի�*/
	private String dklx;

	public String getHyxz() {
		return hyxz;
	}

	public void setHyxz(String hyxz) {
		this.hyxz = hyxz;
	}

	public String getZwmc() {
		return zwmc;
	}

	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}

	/**
	 * @return the dklx
	 */

	public String getDklx() {
		return dklx;
	}
	/**
	 * @param dklxҪ���õ� dklx
	 */
	public void setDklx(String dklx) {
		this.dklx = dklx;
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
	 * @return the bysj
	 */
	public String getBysj() {
		return bysj;
	}
	/**
	 * @param bysjҪ���õ� bysj
	 */
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	/**
	 * @return the fwnx
	 */
	public String getFwnx() {
		return fwnx;
	}
	/**
	 * @param fwnxҪ���õ� fwnx
	 */
	public void setFwnx(String fwnx) {
		this.fwnx = fwnx;
	}
	/**
	 * @return the dwmc
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * @param dwmcҪ���õ� dwmc
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * @return the dwdz
	 */
	public String getDwdz() {
		return dwdz;
	}
	/**
	 * @param dwdzҪ���õ� dwdz
	 */
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	/**
	 * @return the dwdh
	 */
	public String getDwdh() {
		return dwdh;
	}
	/**
	 * @param dwdhҪ���õ� dwdh
	 */
	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}
	/**
	 * @return the xfje
	 */
	public String getXfje() {
		return xfje;
	}
	/**
	 * @param xfjeҪ���õ� xfje
	 */
	public void setXfje(String xfje) {
		this.xfje = xfje;
	}
	/**
	 * @return the dkbj
	 */
	public String getDkbj() {
		return dkbj;
	}
	/**
	 * @param dkbjҪ���õ� dkbj
	 */
	public void setDkbj(String dkbj) {
		this.dkbj = dkbj;
	}
	/**
	 * @return the dcje
	 */
	public String getDcje() {
		return dcje;
	}
	/**
	 * @param dcjeҪ���õ� dcje
	 */
	public void setDcje(String dcje) {
		this.dcje = dcje;
	}
	/**
	 * @return the dclb
	 */
	public String getDclb() {
		return dclb;
	}
	/**
	 * @param dclbҪ���õ� dclb
	 */
	public void setDclb(String dclb) {
		this.dclb = dclb;
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
	 * @return the yjxf
	 */
	public String getYjxf() {
		return yjxf;
	}
	/**
	 * @param yjxfҪ���õ� yjxf
	 */
	public void setYjxf(String yjxf) {
		this.yjxf = yjxf;
	}
	/**
	 * @return the yjxfmc
	 */
	public String getYjxfmc() {
		return yjxfmc;
	}
	/**
	 * @param yjxfmcҪ���õ� yjxfmc
	 */
	public void setYjxfmc(String yjxfmc) {
		this.yjxfmc = yjxfmc;
	}
	/**
	 * @return the yjnxf
	 */
	public String getYjnxf() {
		return yjnxf;
	}
	/**
	 * @param yjnxfҪ���õ� yjnxf
	 */
	public void setYjnxf(String yjnxf) {
		this.yjnxf = yjnxf;
	}
	/**
	 * @return the dclbmc
	 */
	public String getDclbmc() {
		return dclbmc;
	}
	/**
	 * @param dclbmcҪ���õ� dclbmc
	 */
	public void setDclbmc(String dclbmc) {
		this.dclbmc = dclbmc;
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
	 * @return the dwyb
	 */
	public String getDwyb() {
		return dwyb;
	}
	/**
	 * @param dwybҪ���õ� dwyb
	 */
	public void setDwyb(String dwyb) {
		this.dwyb = dwyb;
	}
	/**
	 * @return the dkhth
	 */
	public String getDkhth() {
		return dkhth;
	}
	/**
	 * @param dkhthҪ���õ� dkhth
	 */
	public void setDkhth(String dkhth) {
		this.dkhth = dkhth;
	}
	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}
	/**
	 * @param dkkssjҪ���õ� dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}
	/**
	 * @return the dkjssj
	 */
	public String getDkjssj() {
		return dkjssj;
	}
	/**
	 * @param dkjssjҪ���õ� dkjssj
	 */
	public void setDkjssj(String dkjssj) {
		this.dkjssj = dkjssj;
	}
	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}
	/**
	 * @param yhdmҪ���õ� yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	/**
	 * @return the yhmc
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * @param yhmcҪ���õ� yhmc
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	
	
	
}
