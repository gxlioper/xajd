/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-1 ����11:06:37 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: ѧ����λ���� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-1 ����11:06:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsgwsqForm extends ActionForm {
	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String sqbh;//������
	
	private String xh;//ѧ��
	
	private String xm;
	
	private String xn;//ѧ��
	
	private String yrdwmc;//���˵�λ����
	private String yrdwdm;
	
	private String gwdm;//��λ����
	
	private String gwmc;//��λ����
	
	private String gwxzmc;//��λ��������
	
	private String xqrs;//��������
	
	private String zgrs;//�ڸ�����
	
	private String knss;//��������
	
	private String sqsj;//����ʱ��
	
	private String shzt;//���״̬
	
	private String gwms;//��λ����
	
	private String splc;//��������
	
	private String sqly;//��������
	
	private String bz;//��ע
	
	private String gwryyq;//��λ��ԱҪ��
	
	private String gwyqryxg;//��λ��ԱҪ��
	
	private String shgw;

	private String gwcjsx;//��λ�������
	
	private String mxrq; //��ϸ����
	
	private String sffcap; //�Ƿ���Ӱ���
	
	private String sfzqscy; // �Ƿ���ǿ���Ա
	private String sfsgwsqsxzmc; // �Ƿ��ܸ�λ����������
	
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getSqbh() {
		return sqbh;
	}
	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getYrdwmc() {
		return yrdwmc;
	}
	public void setYrdwmc(String yrdwmc) {
		this.yrdwmc = yrdwmc;
	}
	public String getYrdwdm() {
		return yrdwdm;
	}
	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}
	public String getGwdm() {
		return gwdm;
	}
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getGwxzmc() {
		return gwxzmc;
	}
	public void setGwxzmc(String gwxzmc) {
		this.gwxzmc = gwxzmc;
	}
	public String getXqrs() {
		return xqrs;
	}
	public void setXqrs(String xqrs) {
		this.xqrs = xqrs;
	}
	public String getZgrs() {
		return zgrs;
	}
	public void setZgrs(String zgrs) {
		this.zgrs = zgrs;
	}
	public String getKnss() {
		return knss;
	}
	public void setKnss(String knss) {
		this.knss = knss;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getGwms() {
		return gwms;
	}
	public void setGwms(String gwms) {
		this.gwms = gwms;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getGwryyq() {
		return gwryyq;
	}
	public void setGwryyq(String gwryyq) {
		this.gwryyq = gwryyq;
	}
	public String getGwyqryxg() {
		return gwyqryxg;
	}
	public void setGwyqryxg(String gwyqryxg) {
		this.gwyqryxg = gwyqryxg;
	}
	public String getShgw() {
		return shgw;
	}
	public void setShgw(String shgw) {
		this.shgw = shgw;
	}
	public String getGwcjsx() {
		return gwcjsx;
	}
	public void setGwcjsx(String gwcjsx) {
		this.gwcjsx = gwcjsx;
	}
	public String getMxrq() {
		return mxrq;
	}
	public void setMxrq(String mxrq) {
		this.mxrq = mxrq;
	}
	public String getSffcap() {
		return sffcap;
	}
	public void setSffcap(String sffcap) {
		this.sffcap = sffcap;
	}
	public String getSfzqscy() {
		return sfzqscy;
	}
	public void setSfzqscy(String sfzqscy) {
		this.sfzqscy = sfzqscy;
	}
	public String getSfsgwsqsxzmc() {
		return sfsgwsqsxzmc;
	}
	public void setSfsgwsqsxzmc(String sfsgwsqsxzmc) {
		this.sfsgwsqsxzmc = sfsgwsqsxzmc;
	}
	
}
