/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-10 ����02:50:41 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ΥԼ��¼
 * @���ߣ� �Ƴ���
 * @ʱ�䣺 2015-11-26 ����9:41:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WyjlModel extends ActionForm{

	
	private static final long serialVersionUID = 3740163187234420743L;
	private String xh;  	//ѧ��
	private String sjhm; 	//�ֻ�����
	private String qqhm;	//qq����
	private String wxhm;	//΢�ź���
	private String dzyx;	//��������
	private String wyxq;	//ΥԼ����
	private String wyzt;	//ΥԼ״̬
	private String wysj;    //ΥԼʱ��
	private String bz;	//��ע
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
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
	 * @return the sjhm
	 */
	public String getSjhm() {
		return sjhm;
	}
	/**
	 * @param sjhmҪ���õ� sjhm
	 */
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	/**
	 * @return the qqhm
	 */
	public String getQqhm() {
		return qqhm;
	}
	/**
	 * @param qqhmҪ���õ� qqhm
	 */
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	/**
	 * @return the wxhm
	 */
	public String getWxhm() {
		return wxhm;
	}
	/**
	 * @param wxhmҪ���õ� wxhm
	 */
	public void setWxhm(String wxhm) {
		this.wxhm = wxhm;
	}
	/**
	 * @return the dzyx
	 */
	public String getDzyx() {
		return dzyx;
	}
	/**
	 * @param dzyxҪ���õ� dzyx
	 */
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	/**
	 * @return the wyxq
	 */
	public String getWyxq() {
		return wyxq;
	}
	/**
	 * @param wyxqҪ���õ� wyxq
	 */
	public void setWyxq(String wyxq) {
		this.wyxq = wyxq;
	}
	/**
	 * @return the wyzt
	 */
	public String getWyzt() {
		return wyzt;
	}
	/**
	 * @param wyztҪ���õ� wyzt
	 */
	public void setWyzt(String wyzt) {
		this.wyzt = wyzt;
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
	 * @return the wysj
	 */
	public String getWysj() {
		return wysj;
	}
	/**
	 * @param wysjҪ���õ� wysj
	 */
	public void setWysj(String wysj) {
		this.wysj = wysj;
	}
}
