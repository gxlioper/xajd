/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-24 ����01:42:50 
 */
package com.zfsoft.xgxt.xszz.zzxmjg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����������ģ��
 * @�๦������: form
 * @���ߣ� maxh
 * @ʱ�䣺 2013-4-24 ����01:42:50
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZzxmjgForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String guid;// guid
	private String xh;// ѧ��
	private String xn;// ѧ��
	private String xq;// ѧ��
	private String xqmc;
	private String nj;//�꼶
	private String xydm;//ѧԺ����
	private String lbdm;// ������
	private String lbmc;
	private String xmmc;// ��Ŀ����
	private String xmdm;// ��Ŀ����
	private String sjly;// ������Դ
	private String je;// ���
	private String sqsj;// ����ʱ��
	private String sqly;// ��������
	private String lylcywid;// ����ԭ��ҵ��id
	private String pdxn; //����ѧ��
	private String pdxq; //����ѧ��
	private String pdxqmc;
	private String zsbm;//֤�����
	private String ylzd1 ;//Ԥ���ֶ�1 
	private String ylzd2 ;//Ԥ���ֶ�2 
	private String ylzd3 ;//Ԥ���ֶ�3 
	private String ylzd4 ;//Ԥ���ֶ�4 
	private String ylzd5 ;//����id
	
	private String bjpyjgshzt;
	private String bjpyjgshztmc;
	private String bjpyjgpyhsj;
	private String bjpyjgpyhdd;
	private String bjpyjgpyyj;
	private String bjpyxzcyxms;
	private String bjpyxzdbxms;
	private FormFile drmb;//����ģ���ļ�
	private String fjmc;//��������
	private String filepath;//��Ŵ����ļ���·��
	private String sfzh;

	//�ӱ�����ʦ��ѧԺ���Ի�
	private String sxxm;//ѡ�����Ŀ����

	public String getSxxm() {
		return sxxm;
	}

	public void setSxxm(String sxxm) {
		this.sxxm = sxxm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getLbdm() {
		return lbdm;
	}

	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
	}

	public String getLylcywid() {
		return lylcywid;
	}

	public String getPdxn() {
		return pdxn;
	}

	public void setPdxn(String pdxn) {
		this.pdxn = pdxn;
	}

	public String getPdxq() {
		return pdxq;
	}

	public void setPdxq(String pdxq) {
		this.pdxq = pdxq;
	}

	/**
	 * @return the zsbm
	 */
	public String getZsbm() {
		return zsbm;
	}

	/**
	 * @param zsbmҪ���õ� zsbm
	 */
	public void setZsbm(String zsbm) {
		this.zsbm = zsbm;
	}

	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}

	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}

	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}

	/**
	 * @param xydmҪ���õ� xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	/**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}

	/**
	 * @param ylzd1Ҫ���õ� ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}

	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}

	/**
	 * @param ylzd2Ҫ���õ� ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}

	/**
	 * @param ylzd3Ҫ���õ� ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}

	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}

	/**
	 * @param ylzd4Ҫ���õ� ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}

	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}

	/**
	 * @param ylzd5Ҫ���õ� ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	/**
	 * @return the lbmc
	 */
	public String getLbmc() {
		return lbmc;
	}

	/**
	 * @param lbmcҪ���õ� lbmc
	 */
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	/**
	 * @return the bjpyjgshzt
	 */
	public String getBjpyjgshzt() {
		return bjpyjgshzt;
	}

	/**
	 * @param bjpyjgshztҪ���õ� bjpyjgshzt
	 */
	public void setBjpyjgshzt(String bjpyjgshzt) {
		this.bjpyjgshzt = bjpyjgshzt;
	}

	/**
	 * @return the bjpyjgshztmc
	 */
	public String getBjpyjgshztmc() {
		return bjpyjgshztmc;
	}

	/**
	 * @param bjpyjgshztmcҪ���õ� bjpyjgshztmc
	 */
	public void setBjpyjgshztmc(String bjpyjgshztmc) {
		this.bjpyjgshztmc = bjpyjgshztmc;
	}

	/**
	 * @return the bjpyjgpyhsj
	 */
	public String getBjpyjgpyhsj() {
		return bjpyjgpyhsj;
	}

	/**
	 * @param bjpyjgpyhsjҪ���õ� bjpyjgpyhsj
	 */
	public void setBjpyjgpyhsj(String bjpyjgpyhsj) {
		this.bjpyjgpyhsj = bjpyjgpyhsj;
	}

	/**
	 * @return the bjpyjgpyhdd
	 */
	public String getBjpyjgpyhdd() {
		return bjpyjgpyhdd;
	}

	/**
	 * @param bjpyjgpyhddҪ���õ� bjpyjgpyhdd
	 */
	public void setBjpyjgpyhdd(String bjpyjgpyhdd) {
		this.bjpyjgpyhdd = bjpyjgpyhdd;
	}

	/**
	 * @return the bjpyjgpyyj
	 */
	public String getBjpyjgpyyj() {
		return bjpyjgpyyj;
	}

	/**
	 * @param bjpyjgpyyjҪ���õ� bjpyjgpyyj
	 */
	public void setBjpyjgpyyj(String bjpyjgpyyj) {
		this.bjpyjgpyyj = bjpyjgpyyj;
	}

	/**
	 * @return the bjpyxzcyxms
	 */
	public String getBjpyxzcyxms() {
		return bjpyxzcyxms;
	}

	/**
	 * @param bjpyxzcyxmsҪ���õ� bjpyxzcyxms
	 */
	public void setBjpyxzcyxms(String bjpyxzcyxms) {
		this.bjpyxzcyxms = bjpyxzcyxms;
	}

	/**
	 * @return the bjpyxzdbxms
	 */
	public String getBjpyxzdbxms() {
		return bjpyxzdbxms;
	}

	/**
	 * @param bjpyxzdbxmsҪ���õ� bjpyxzdbxms
	 */
	public void setBjpyxzdbxms(String bjpyxzdbxms) {
		this.bjpyxzdbxms = bjpyxzdbxms;
	}

	/**
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}

	/**
	 * @param drmbҪ���õ� drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}

	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}

	/**
	 * @param fjmcҪ���õ� fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
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
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}

	/**
	 * @param sfzhҪ���õ� sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	/**
	 * @return the pdxqmc
	 */
	public String getPdxqmc() {
		return pdxqmc;
	}

	/**
	 * @param pdxqmcҪ���õ� pdxqmc
	 */
	public void setPdxqmc(String pdxqmc) {
		this.pdxqmc = pdxqmc;
	}
	
	
	
	
}
