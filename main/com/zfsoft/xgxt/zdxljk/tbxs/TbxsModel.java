/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-11 ����09:11:04 
 */  
package com.zfsoft.xgxt.zdxljk.tbxs;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������: ���������--�ر����ѧ�� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-2-11 ����09:11:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TbxsModel extends ActionForm{

	
	private static final long serialVersionUID = -7826057325474125444L;

	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String xh;
	private String[] thsjArr;
	private String[] gxlxArr;
	private String gzlx;
	private String[] gxsjArr;
	private String[] gzsjArr;
	private String[] thnrArr;
	private String[] cljgArr;
	private String zxzt;
	private String[] ftrArr;
	private String[] qxyyArr;
	private String qxsj;
	private String qxyy;
	private String czsj;
	private String[] canUpdateArr;
	
	
	
	/**
	 * @return the qxsj
	 */
	public String getQxsj() {
		return qxsj;
	}
	/**
	 * @param qxsjҪ���õ� qxsj
	 */
	public void setQxsj(String qxsj) {
		this.qxsj = qxsj;
	}
	/**
	 * @return the qxyy
	 */
	public String getQxyy() {
		return qxyy;
	}
	/**
	 * @param qxyyҪ���õ� qxyy
	 */
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
	/**
	 * @return the zxzt
	 */
	public String getZxzt() {
		return zxzt;
	}
	/**
	 * @param zxztҪ���õ� zxzt
	 */
	public void setZxzt(String zxzt) {
		this.zxzt = zxzt;
	}
	/**
	 * @return the thsjArr
	 */
	public String[] getThsjArr() {
		return thsjArr;
	}
	/**
	 * @param thsjArrҪ���õ� thsjArr
	 */
	public void setThsjArr(String[] thsjArr) {
		this.thsjArr = thsjArr;
	}
	/**
	 * @return the gxlxArr
	 */
	public String[] getGxlxArr() {
		return gxlxArr;
	}
	/**
	 * @param gxlxArrҪ���õ� gxlxArr
	 */
	public void setGxlxArr(String[] gxlxArr) {
		this.gxlxArr = gxlxArr;
	}
	
	public String getGzlx() {
		return gzlx;
	}
	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
	}
	/**
	 * @return the gxsjArr
	 */
	public String[] getGxsjArr() {
		return gxsjArr;
	}
	/**
	 * @param gxsjArrҪ���õ� gxsjArr
	 */
	public void setGxsjArr(String[] gxsjArr) {
		this.gxsjArr = gxsjArr;
	}
	/**
	 * @return the gzsjArr
	 */
	public String[] getGzsjArr() {
		return gzsjArr;
	}
	/**
	 * @param gzsjArrҪ���õ� gzsjArr
	 */
	public void setGzsjArr(String[] gzsjArr) {
		this.gzsjArr = gzsjArr;
	}
	/**
	 * @return the thnrArr
	 */
	public String[] getThnrArr() {
		return thnrArr;
	}
	/**
	 * @param thnrArrҪ���õ� thnrArr
	 */
	public void setThnrArr(String[] thnrArr) {
		this.thnrArr = thnrArr;
	}
	/**
	 * @return the cljgArr
	 */
	public String[] getCljgArr() {
		return cljgArr;
	}
	/**
	 * @param cljgArrҪ���õ� cljgArr
	 */
	public void setCljgArr(String[] cljgArr) {
		this.cljgArr = cljgArr;
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
	public String[] getFtrArr() {
		return ftrArr;
	}
	public void setFtrArr(String[] ftrArr) {
		this.ftrArr = ftrArr;
	}
	public String[] getQxyyArr() {
		return qxyyArr;
	}
	public void setQxyyArr(String[] qxyyArr) {
		this.qxyyArr = qxyyArr;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	public String[] getCanUpdateArr() {
		return canUpdateArr;
	}
	public void setCanUpdateArr(String[] canUpdateArr) {
		this.canUpdateArr = canUpdateArr;
	}
	
	
	
}
