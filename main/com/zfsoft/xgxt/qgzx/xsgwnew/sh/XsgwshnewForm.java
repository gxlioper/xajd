/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-6 ����09:22:38 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: ѧ����λ���  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-6 ����09:22:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsgwshnewForm extends ActionForm {
	
	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String sqbh;//������
	
	private String xh;//ѧ��
	
	private String xn;//ѧ��
	
	private String yrdwmc;//���˵�λ����
	
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
	
	private String xm;
	
	private String xb;
	
	private String shlx ;//������� ������ �Ѵ���
	
	private String shyj ;
	
	private String shsj ;
	
	private String gwid ;//������λID
	
	private String sffcap; //�Ƿ���Ӱ���
	
	private String sfzqscy;//�Ƿ���ǿ���Ա�ֶ�
	
	private String shgw;
	private String shid;
	private String thgw;
	private String oldgwdm;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getShlx() {
		return shlx;
	}
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
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
	public String getShgw() {
		return shgw;
	}
	public void setShgw(String shgw) {
		this.shgw = shgw;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getOldgwdm() {
		return oldgwdm;
	}
	public void setOldgwdm(String oldgwdm) {
		this.oldgwdm = oldgwdm;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}	
}
