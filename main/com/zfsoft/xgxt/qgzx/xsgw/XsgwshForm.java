/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-17 ����09:15:55 
 */  
package com.zfsoft.xgxt.qgzx.xsgw;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ѧ����λ-��λ���
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-6-17 ����09:09:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: 
 */

public class XsgwshForm  extends ActionForm{
	
	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String sqbh;//������

	private String jgData;

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

	private String shjg;//��˽��
	
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

	private String bmlb;//�������
	
	/**�㽭��ҽҩ���Ի��ֶ�
	 */
	private String yhtc;
	private String jtqk;
	private String qgzxrs;
	
	/**
	 * ����ѧԺ���Ի��ж�
	 */
	private String xxcj;
	private String stzk;

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getJgData() {
		return jgData;
	}

	public void setJgData(String jgData) {
		this.jgData = jgData;
	}

	public String getXxcj() {
		return xxcj;
	}
	public void setXxcj(String xxcj) {
		this.xxcj = xxcj;
	}
	public String getStzk() {
		return stzk;
	}
	public void setStzk(String stzk) {
		this.stzk = stzk;
	}

	public String getBmlb() {
		return bmlb;
	}

	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}

	/**
	 * @return the yhtc
	 */
	public String getYhtc() {
		return yhtc;
	}
	/**
	 * @param yhtcҪ���õ� yhtc
	 */
	public void setYhtc(String yhtc) {
		this.yhtc = yhtc;
	}
	/**
	 * @return the jtqk
	 */
	public String getJtqk() {
		return jtqk;
	}
	/**
	 * @param jtqkҪ���õ� jtqk
	 */
	public void setJtqk(String jtqk) {
		this.jtqk = jtqk;
	}
	/**
	 * @return the qgzxrs
	 */
	public String getQgzxrs() {
		return qgzxrs;
	}
	/**
	 * @param qgzxrsҪ���õ� qgzxrs
	 */
	public void setQgzxrs(String qgzxrs) {
		this.qgzxrs = qgzxrs;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getKnss() {
		return knss;
	}
	public void setKnss(String knss) {
		this.knss = knss;
	}
	public String getGwdm() {
		return gwdm;
	}
	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}
	public String getGwms() {
		return gwms;
	}
	public void setGwms(String gwms) {
		this.gwms = gwms;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqbh() {
		return sqbh;
	}
	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
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
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shgw
	 */
	public String getShgw() {
		return shgw;
	}
	/**
	 * @param shgwҪ���õ� shgw
	 */
	public void setShgw(String shgw) {
		this.shgw = shgw;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the oldgwdm
	 */
	public String getOldgwdm() {
		return oldgwdm;
	}
	/**
	 * @param oldgwdmҪ���õ� oldgwdm
	 */
	public void setOldgwdm(String oldgwdm) {
		this.oldgwdm = oldgwdm;
	}
	/**
	 * @return the sffcap
	 */
	public String getSffcap() {
		return sffcap;
	}
	/**
	 * @param sffcapҪ���õ� sffcap
	 */
	public void setSffcap(String sffcap) {
		this.sffcap = sffcap;
	}
	/**
	 * @return the sfzqscy
	 */
	public String getSfzqscy() {
		return sfzqscy;
	}
	/**
	 * @param sfzqscyҪ���õ� sfzqscy
	 */
	public void setSfzqscy(String sfzqscy) {
		this.sfzqscy = sfzqscy;
	}
	
}
