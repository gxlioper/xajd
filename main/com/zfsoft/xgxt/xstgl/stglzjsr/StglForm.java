package com.zfsoft.xgxt.xstgl.stglzjsr;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ��ڹ���ѧ-��������
 * @�๦��������
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2017��4��20��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class StglForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //�߼���ѯ
	private Pages pages = new Pages(); // ��ҳ
	private ExportModel exportModel = new ExportModel(); //�Զ��嵼��
	
	private String id;
	private String bh;//���
	private String stmc;//��������
	private String stlb;//�������
	private String sz;//�糤
	private String cwfzr;//��������
	private String zdls;//ָ����ʦ
	private String yxzt;//��Ч״̬ 1��Ч 0��Ч
	private String filepath;//����·��
	private String bz;//��ע
	private String zd1;//�ֶ�1
	private String zd2;//�ֶ�2
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
	 * @return the bh
	 */
	public String getBh() {
		return bh;
	}
	/**
	 * @param bhҪ���õ� bh
	 */
	public void setBh(String bh) {
		this.bh = bh;
	}
	/**
	 * @return the stmc
	 */
	public String getStmc() {
		return stmc;
	}
	/**
	 * @param stmcҪ���õ� stmc
	 */
	public void setStmc(String stmc) {
		this.stmc = stmc;
	}
	/**
	 * @return the stlb
	 */
	public String getStlb() {
		return stlb;
	}
	/**
	 * @param stlbҪ���õ� stlb
	 */
	public void setStlb(String stlb) {
		this.stlb = stlb;
	}
	/**
	 * @return the sz
	 */
	public String getSz() {
		return sz;
	}
	/**
	 * @param szҪ���õ� sz
	 */
	public void setSz(String sz) {
		this.sz = sz;
	}
	/**
	 * @return the cwfzr
	 */
	public String getCwfzr() {
		return cwfzr;
	}
	/**
	 * @param cwfzrҪ���õ� cwfzr
	 */
	public void setCwfzr(String cwfzr) {
		this.cwfzr = cwfzr;
	}
	/**
	 * @return the zdls
	 */
	public String getZdls() {
		return zdls;
	}
	/**
	 * @param zdlsҪ���õ� zdls
	 */
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	/**
	 * @return the yxzt
	 */
	public String getYxzt() {
		return yxzt;
	}
	/**
	 * @param yxztҪ���õ� yxzt
	 */
	public void setYxzt(String yxzt) {
		this.yxzt = yxzt;
	}
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1Ҫ���õ� zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2Ҫ���õ� zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
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
	
}
