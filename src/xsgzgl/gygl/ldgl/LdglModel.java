package xsgzgl.gygl.ldgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class LdglModel {

	
	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	
	private String[] checkVal;

	private String lddm;//¥������
	private String ldmc;//¥������
	private String ldxb;//¥���Ա�
	private String ldcs;//¥������
	private String qsch;//��ʼ���
	private String sfhlc;//�Ƿ�0��
	private String xqdm;//У������
	private String yqdm;//԰������
	private String bz;//��ע

	private String originalLdcs;	//ԭʼ¥������
	private String sfkzjlc;	//�Ƿ������¥�㣬1����

	private ExportModel exportModel = new ExportModel();
	/**
	 * @return pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages Ҫ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel Ҫ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm Ҫ���õ� lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc Ҫ���õ� ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return ldxb
	 */
	public String getLdxb() {
		return ldxb;
	}
	/**
	 * @param ldxb Ҫ���õ� ldxb
	 */
	public void setLdxb(String ldxb) {
		this.ldxb = ldxb;
	}
	/**
	 * @return ldcs
	 */
	public String getLdcs() {
		return ldcs;
	}
	/**
	 * @param ldcs Ҫ���õ� ldcs
	 */
	public void setLdcs(String ldcs) {
		this.ldcs = ldcs;
	}
	/**
	 * @return qsch
	 */
	public String getQsch() {
		return qsch;
	}
	/**
	 * @param qsch Ҫ���õ� qsch
	 */
	public void setQsch(String qsch) {
		this.qsch = qsch;
	}
	/**
	 * @return sfhlc
	 */
	public String getSfhlc() {
		return sfhlc;
	}
	/**
	 * @param sfhlc Ҫ���õ� sfhlc
	 */
	public void setSfhlc(String sfhlc) {
		this.sfhlc = sfhlc;
	}
	/**
	 * @return xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdm Ҫ���õ� xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	/**
	 * @return yqdm
	 */
	public String getYqdm() {
		return yqdm;
	}
	/**
	 * @param yqdm Ҫ���õ� yqdm
	 */
	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}
	/**
	 * @return bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz Ҫ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return checkVal
	 */
	public String[] getCheckVal() {
		return checkVal;
	}
	/**
	 * @param checkVal Ҫ���õ� checkVal
	 */
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
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

	public String getOriginalLdcs() {
		return originalLdcs;
	}

	public void setOriginalLdcs(String originalLdcs) {
		this.originalLdcs = originalLdcs;
	}

	public String getSfkzjlc() {
		return sfkzjlc;
	}

	public void setSfkzjlc(String sfkzjlc) {
		this.sfkzjlc = sfkzjlc;
	}
}
