package xsgzgl.gygl.qsgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class QsglModel {
	// ͨ�� 
	Pages pages;
	// �߼���ѯ
	SearchModel searchModel; 
	
	private String sfbz;     //�շѱ�׼
	private String nj;     //�����꼶
	private String bz;       //��ע
	private String qsdh;     //���ҵ绰
	private String xydm;     //����ѧԺ����
	private String qsxb;     //�����Ա�
	private String cws;      //��λ��
	private String lddm;     //¥������
	private String qsh;      //���Һ�
	private String yqsh;	//ԭ���Һ�
	private String ch;       //���
	private ExportModel exportModel = new ExportModel();
	private String ywkt;  //���޿յ�
	
	public String getSfbz() {
		return sfbz;
	}
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getQsxb() {
		return qsxb;
	}
	public void setQsxb(String qsxb) {
		this.qsxb = qsxb;
	}
	public String getCws() {
		return cws;
	}
	public void setCws(String cws) {
		this.cws = cws;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
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
	 * @return the yqsh
	 */
	public String getYqsh() {
		return yqsh;
	}
	/**
	 * @param yqshҪ���õ� yqsh
	 */
	public void setYqsh(String yqsh) {
		this.yqsh = yqsh;
	}
	/**
	 * @return the ywkt
	 */
	public String getYwkt() {
		return ywkt;
	}
	/**
	 * @param ywktҪ���õ� ywkt
	 */
	public void setYwkt(String ywkt) {
		this.ywkt = ywkt;
	}
	
	
}
