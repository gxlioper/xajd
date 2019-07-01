package com.zfsoft.xgxt.dekt.xfjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DektxfjgForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //�߼���ѯ
	private Pages pages = new Pages(); // ��ҳ
	private ExportModel exportModel = new ExportModel(); //�Զ��嵼��
	
	private String jgid; //���ID
	private String xh; //ѧ��
	private String xmid; //��ĿID
	private String cyfs; //���뷽ʽ gr/tt ����/����
	private String cyfsmc;
	private String hjsj; //��ʱ��
	private String sqsm; //����˵��
	private String filepath; //������ַ
	private String xf; //ѧ��
	private String sjly; //������Դ
	private String sqid; //����ID
	private String lx; //����
	private String rdxm; //�϶���Ŀ
	private String rdnrbz; //�϶����ݱ�׼
	private String dj; //�ȼ�
	private String pjjg;//�������
	private String pjbz;//������ע
	
	
	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * @param lxҪ���õ� lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @return the rdxm
	 */
	public String getRdxm() {
		return rdxm;
	}
	/**
	 * @param rdxmҪ���õ� rdxm
	 */
	public void setRdxm(String rdxm) {
		this.rdxm = rdxm;
	}
	/**
	 * @return the rdnrbz
	 */
	public String getRdnrbz() {
		return rdnrbz;
	}
	/**
	 * @param rdnrbzҪ���õ� rdnrbz
	 */
	public void setRdnrbz(String rdnrbz) {
		this.rdnrbz = rdnrbz;
	}
	/**
	 * @return the dj
	 */
	public String getDj() {
		return dj;
	}
	/**
	 * @param djҪ���õ� dj
	 */
	public void setDj(String dj) {
		this.dj = dj;
	}
	/**
	 * @return the cyfsmc
	 */
	public String getCyfsmc() {
		return cyfsmc;
	}
	/**
	 * @param cyfsmcҪ���õ� cyfsmc
	 */
	public void setCyfsmc(String cyfsmc) {
		this.cyfsmc = cyfsmc;
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
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmidҪ���õ� xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the cyfs
	 */
	public String getCyfs() {
		return cyfs;
	}
	/**
	 * @param cyfsҪ���õ� cyfs
	 */
	public void setCyfs(String cyfs) {
		this.cyfs = cyfs;
	}
	/**
	 * @return the hjsj
	 */
	public String getHjsj() {
		return hjsj;
	}
	/**
	 * @param hjsjҪ���õ� hjsj
	 */
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	/**
	 * @return the sqsm
	 */
	public String getSqsm() {
		return sqsm;
	}
	/**
	 * @param sqsmҪ���õ� sqsm
	 */
	public void setSqsm(String sqsm) {
		this.sqsm = sqsm;
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
	 * @return the xf
	 */
	public String getXf() {
		return xf;
	}
	/**
	 * @param xfҪ���õ� xf
	 */
	public void setXf(String xf) {
		this.xf = xf;
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-18 ����11:25:51 
	 * @return		: the pjjg
	 */
	public String getPjjg() {
		return pjjg;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-18 ����11:25:51 
	 * @param 		��pjjg the pjjg to set
	 */
	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-18 ����11:25:51 
	 * @return		: the pjbz
	 */
	public String getPjbz() {
		return pjbz;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2017-12-18 ����11:25:51 
	 * @param 		��pjbz the pjbz to set
	 */
	public void setPjbz(String pjbz) {
		this.pjbz = pjbz;
	}
	
	
	
}
