/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @�๦������:form
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:00:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ShlcpzForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	
	private String jddm;//�׶δ���
	private String jdmc;//�׶�����
	private String sfszjssj;//�Ƿ��Զ�ת����һ�׶�
	private String sfyrshl;//�Ƿ����������
	private String sfsztj;//�Ƿ���������
	private String dyzd;//��Ӧ�ֶ�
	private String dyz;//��Ӧֵ
	private String bz;//��ע
	private String jdsx;//�׶�˳��
	private String splc;//��������id
	private String sfkpzshl;//0���������������1���������������     (ѧУ����������)
	private String ksqkg;//��������(Ĭ��0)��1��������
	private String ksqkssj;//�����뿪ʼʱ��
	private String ksqjssj;//���������ʱ��

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	/**
	 * @return the jddm
	 */
	public String getJddm() {
		return jddm;
	}
	/**
	 * @param jddmҪ���õ� jddm
	 */
	public void setJddm(String jddm) {
		this.jddm = jddm;
	}
	/**
	 * @return the jdmc
	 */
	public String getJdmc() {
		return jdmc;
	}
	/**
	 * @param jdmcҪ���õ� jdmc
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}
	/**
	 * @return the sfszjssj
	 */
	public String getSfszjssj() {
		return sfszjssj;
	}
	/**
	 * @param sfszjssjҪ���õ� sfszjssj
	 */
	public void setSfszjssj(String sfszjssj) {
		this.sfszjssj = sfszjssj;
	}
	/**
	 * @return the sfyrshl
	 */
	public String getSfyrshl() {
		return sfyrshl;
	}
	/**
	 * @param sfyrshlҪ���õ� sfyrshl
	 */
	public void setSfyrshl(String sfyrshl) {
		this.sfyrshl = sfyrshl;
	}
	/**
	 * @return the sfsztj
	 */
	public String getSfsztj() {
		return sfsztj;
	}
	/**
	 * @param sfsztjҪ���õ� sfsztj
	 */
	public void setSfsztj(String sfsztj) {
		this.sfsztj = sfsztj;
	}
	/**
	 * @return the dyzd
	 */
	public String getDyzd() {
		return dyzd;
	}
	/**
	 * @param dyzdҪ���õ� dyzd
	 */
	public void setDyzd(String dyzd) {
		this.dyzd = dyzd;
	}
	/**
	 * @return the dyz
	 */
	public String getDyz() {
		return dyz;
	}
	/**
	 * @param dyzҪ���õ� dyz
	 */
	public void setDyz(String dyz) {
		this.dyz = dyz;
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
	 * @return the jdsx
	 */
	public String getJdsx() {
		return jdsx;
	}
	/**
	 * @param jdsxҪ���õ� jdsx
	 */
	public void setJdsx(String jdsx) {
		this.jdsx = jdsx;
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
	 * @return the sfkpzshl
	 */
	public String getSfkpzshl() {
		return sfkpzshl;
	}
	/**
	 * @param sfkpzshlҪ���õ� sfkpzshl
	 */
	public void setSfkpzshl(String sfkpzshl) {
		this.sfkpzshl = sfkpzshl;
	}
	/**
	 * @return the ksqkg
	 */
	public String getKsqkg() {
		return ksqkg;
	}
	/**
	 * @param ksqkgҪ���õ� ksqkg
	 */
	public void setKsqkg(String ksqkg) {
		this.ksqkg = ksqkg;
	}
	/**
	 * @return the ksqkssj
	 */
	public String getKsqkssj() {
		return ksqkssj;
	}
	/**
	 * @param ksqkssjҪ���õ� ksqkssj
	 */
	public void setKsqkssj(String ksqkssj) {
		this.ksqkssj = ksqkssj;
	}
	/**
	 * @return the ksqjssj
	 */
	public String getKsqjssj() {
		return ksqjssj;
	}
	/**
	 * @param ksqjssjҪ���õ� ksqjssj
	 */
	public void setKsqjssj(String ksqjssj) {
		this.ksqjssj = ksqjssj;
	}
}
