/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-25 ����02:24:25 
 */  
package com.zfsoft.xgxt.xszz.sqsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��֮��Ŀ�������
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-25 ����02:24:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszzSqshForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private String sqType; //�������ͣ�wsq ysq δ���� ������
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String shlc ;//�������ID 
	private String guid ;//guid 
	private String xh ;//ѧ�� 
	private String xn ;//ѧ�� 
	private String xq ;//ѧ�� 
	private String xqmc;//ѧ������
	private String xmdm ;//��Ŀ���� 
	private String sqsj ;//����ʱ�� 
	private String sqly ;//�������� 
	private String shzt ;//���״̬ 
	private String ylzd1 ;//ѧ��������
	private String ylzd2 ;//Ԥ���ֶ�2 
	private String ylzd3 ;//Ԥ���ֶ�3 
	private String ylzd4 ;//Ԥ���ֶ�4 
	private String ylzd5 ;//����id
	private String shyj;
	private String xtgwid;
	private String[] id;
	private String[] gwid;
	private String[] xhs;
	private String shxmdm;
	private String xmmc;
	private String je;
	private String tzhxmdm;//��������Ŀ����
	private String jgsqzq;
	
	private String shxmje;  //��ʦ�����Ŀ���
	private String jesfxssq;//��Ŀ����Ƿ�����޸�
	
	private String shid;
	private String shjg;
	private String thgw;
	private String dqxmdm;  //��ǰ��Ŀ���룻
	private String pdxn;	//����ѧ��
	private String pdxq;	//����ѧ��
	private String pdxqmc;	//����ѧ������
	
	private String bjpyjgshzt;
	private String bjpyjgshztmc;
	private String bjpyjgpyhsj;
	private String bjpyjgpyhdd;
	private String bjpyjgpyyj;
	private String bjpyxzcyxms;
	private String bjpyxzdbxms;
		
	private String lbdm;	//������
	private String lbmc;	//�������
	
	/**
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdmҪ���õ� lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
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
	
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
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
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getYlzd1() {
		return ylzd1;
	}
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	public String getYlzd2() {
		return ylzd2;
	}
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	public String getYlzd3() {
		return ylzd3;
	}
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	public String getYlzd4() {
		return ylzd4;
	}
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	public String getYlzd5() {
		return ylzd5;
	}
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
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
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getXtgwid() {
		return xtgwid;
	}
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getGwid() {
		return gwid;
	}
	public void setGwid(String[] gwid) {
		this.gwid = gwid;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String getShxmdm() {
		return shxmdm;
	}
	public void setShxmdm(String shxmdm) {
		this.shxmdm = shxmdm;
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getShid() {
		return shid;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getShjg() {
		return shjg;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getThgw() {
		return thgw;
	}
	public void setShxmje(String shxmje) {
		this.shxmje = shxmje;
	}
	public String getShxmje() {
		return shxmje;
	}
	public void setJesfxssq(String jesfxssq) {
		this.jesfxssq = jesfxssq;
	}
	public String getJesfxssq() {
		return jesfxssq;
	}
	public void setTzhxmdm(String tzhxmdm) {
		this.tzhxmdm = tzhxmdm;
	}
	public String getTzhxmdm() {
		return tzhxmdm;
	}
	/**
	 * @return the sqType
	 */
	public String getSqType() {
		return sqType;
	}
	/**
	 * @param sqTypeҪ���õ� sqType
	 */
	public void setSqType(String sqType) {
		this.sqType = sqType;
	}
	public String getDqxmdm() {
		return dqxmdm;
	}
	public void setDqxmdm(String dqxmdm) {
		this.dqxmdm = dqxmdm;
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
	public String getPdxqmc() {
		return pdxqmc;
	}
	public void setPdxqmc(String pdxqmc) {
		this.pdxqmc = pdxqmc;
	}
	public String getBjpyjgshzt() {
		return bjpyjgshzt;
	}
	public void setBjpyjgshzt(String bjpyjgshzt) {
		this.bjpyjgshzt = bjpyjgshzt;
	}
	public String getBjpyjgshztmc() {
		return bjpyjgshztmc;
	}
	public void setBjpyjgshztmc(String bjpyjgshztmc) {
		this.bjpyjgshztmc = bjpyjgshztmc;
	}
	public String getBjpyjgpyhsj() {
		return bjpyjgpyhsj;
	}
	public void setBjpyjgpyhsj(String bjpyjgpyhsj) {
		this.bjpyjgpyhsj = bjpyjgpyhsj;
	}
	public String getBjpyjgpyhdd() {
		return bjpyjgpyhdd;
	}
	public void setBjpyjgpyhdd(String bjpyjgpyhdd) {
		this.bjpyjgpyhdd = bjpyjgpyhdd;
	}
	public String getBjpyjgpyyj() {
		return bjpyjgpyyj;
	}
	public void setBjpyjgpyyj(String bjpyjgpyyj) {
		this.bjpyjgpyyj = bjpyjgpyyj;
	}
	public String getBjpyxzcyxms() {
		return bjpyxzcyxms;
	}
	public void setBjpyxzcyxms(String bjpyxzcyxms) {
		this.bjpyxzcyxms = bjpyxzcyxms;
	}
	public String getBjpyxzdbxms() {
		return bjpyxzdbxms;
	}
	public void setBjpyxzdbxms(String bjpyxzdbxms) {
		this.bjpyxzdbxms = bjpyxzdbxms;
	}
	/**
	 * @return the jgsqzq
	 */
	public String getJgsqzq() {
		return jgsqzq;
	}
	/**
	 * @param jgsqzqҪ���õ� jgsqzq
	 */
	public void setJgsqzq(String jgsqzq) {
		this.jgsqzq = jgsqzq;
	}
	
}
