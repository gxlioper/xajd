/**
 * @����: ѧ����Ʒ1��
 * @���ڣ� 2017-3-13 ����02:00:23 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����������_�ҵ�����_�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-13 ����02:00:23 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjjgForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	private String id;        //GUID
	private String xh;        //ѧ��
	private String xn;        //ѧ��
	private String xmdm;      //��Ŀ����
	private String xmmc;      //��Ŀ����
	private String xmje;      //��Ŀ���
	private String sqsj;      //����ʱ��
	private String sqly;      //��������
	private String sjly;      //������Դ 0:���롢1:�������� 2:��̨����
	private String lrr;       //¼����
	private String xzdm;      //���ʴ���
	private String lxdm;      //���ʹ���
	private String cpnj;      //�����꼶
	private String cpxymc;    //����ѧԺ����
	private String cpzymc;    //����רҵ����
	private String cpbjmc;    //�����༶����
	private String cpxydm;    //����ѧԺ����
	private String cpzydm;    //����רҵ����
	private String cpbjdm;    //�����༶����
	private String ylzd1;     //Ԥ���ֶ�һ
	private String ylzd2;     //Ԥ���ֶζ�
	private String ylzd3;     //Ԥ���ֶ���
	private String ylzd4;     //Ԥ���ֶ���
	private String ylzd5;     //����ID
	private String lrsj;      //¼��ʱ��
	private String lylcywid;  // ��Դҵ��id
	private String bjdw;      // �佱��λ
	
	private String wysp;/*����ˮƽ*/
	private String ssdh;/*����绰*/
	private String gzzw;/*������Ṥ��ְ��*/
	private String cjkyqk;/*�μӿ������*/
	private String dwrs;/*���轱��λ����ʶ*/
	private String grxxjl;	//����ѧϰ����
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
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the xmje
	 */
	public String getXmje() {
		return xmje;
	}
	/**
	 * @param xmjeҪ���õ� xmje
	 */
	public void setXmje(String xmje) {
		this.xmje = xmje;
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
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
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrrҪ���õ� lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the xzdm
	 */
	public String getXzdm() {
		return xzdm;
	}
	/**
	 * @param xzdmҪ���õ� xzdm
	 */
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdmҪ���õ� lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the cpnj
	 */
	public String getCpnj() {
		return cpnj;
	}
	/**
	 * @param cpnjҪ���õ� cpnj
	 */
	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}
	/**
	 * @return the cpxymc
	 */
	public String getCpxymc() {
		return cpxymc;
	}
	/**
	 * @param cpxymcҪ���õ� cpxymc
	 */
	public void setCpxymc(String cpxymc) {
		this.cpxymc = cpxymc;
	}
	/**
	 * @return the cpzymc
	 */
	public String getCpzymc() {
		return cpzymc;
	}
	/**
	 * @param cpzymcҪ���õ� cpzymc
	 */
	public void setCpzymc(String cpzymc) {
		this.cpzymc = cpzymc;
	}
	/**
	 * @return the cpbjmc
	 */
	public String getCpbjmc() {
		return cpbjmc;
	}
	/**
	 * @param cpbjmcҪ���õ� cpbjmc
	 */
	public void setCpbjmc(String cpbjmc) {
		this.cpbjmc = cpbjmc;
	}
	/**
	 * @return the cpxydm
	 */
	public String getCpxydm() {
		return cpxydm;
	}
	/**
	 * @param cpxydmҪ���õ� cpxydm
	 */
	public void setCpxydm(String cpxydm) {
		this.cpxydm = cpxydm;
	}
	/**
	 * @return the cpzydm
	 */
	public String getCpzydm() {
		return cpzydm;
	}
	/**
	 * @param cpzydmҪ���õ� cpzydm
	 */
	public void setCpzydm(String cpzydm) {
		this.cpzydm = cpzydm;
	}
	/**
	 * @return the cpbjdm
	 */
	public String getCpbjdm() {
		return cpbjdm;
	}
	/**
	 * @param cpbjdmҪ���õ� cpbjdm
	 */
	public void setCpbjdm(String cpbjdm) {
		this.cpbjdm = cpbjdm;
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
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsjҪ���õ� lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	/**
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywidҪ���õ� lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
	}
	/**
	 * @return the bjdw
	 */
	public String getBjdw() {
		return bjdw;
	}
	/**
	 * @param bjdwҪ���õ� bjdw
	 */
	public void setBjdw(String bjdw) {
		this.bjdw = bjdw;
	}
	/**
	 * @return the wysp
	 */
	public String getWysp() {
		return wysp;
	}
	/**
	 * @param wyspҪ���õ� wysp
	 */
	public void setWysp(String wysp) {
		this.wysp = wysp;
	}
	/**
	 * @return the ssdh
	 */
	public String getSsdh() {
		return ssdh;
	}
	/**
	 * @param ssdhҪ���õ� ssdh
	 */
	public void setSsdh(String ssdh) {
		this.ssdh = ssdh;
	}
	/**
	 * @return the gzzw
	 */
	public String getGzzw() {
		return gzzw;
	}
	/**
	 * @param gzzwҪ���õ� gzzw
	 */
	public void setGzzw(String gzzw) {
		this.gzzw = gzzw;
	}
	/**
	 * @return the cjkyqk
	 */
	public String getCjkyqk() {
		return cjkyqk;
	}
	/**
	 * @param cjkyqkҪ���õ� cjkyqk
	 */
	public void setCjkyqk(String cjkyqk) {
		this.cjkyqk = cjkyqk;
	}
	/**
	 * @return the dwrs
	 */
	public String getDwrs() {
		return dwrs;
	}
	/**
	 * @param dwrsҪ���õ� dwrs
	 */
	public void setDwrs(String dwrs) {
		this.dwrs = dwrs;
	}
	/**
	 * @return the grxxjl
	 */
	public String getGrxxjl() {
		return grxxjl;
	}
	/**
	 * @param grxxjlҪ���õ� grxxjl
	 */
	public void setGrxxjl(String grxxjl) {
		this.grxxjl = grxxjl;
	}
}
