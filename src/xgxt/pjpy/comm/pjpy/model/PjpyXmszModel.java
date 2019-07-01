package xgxt.pjpy.comm.pjpy.model;

import java.util.HashMap;
import java.util.List;

/**
 * Title: ѧ����������ϵͳ 
 * Description: ͨ�ð���������Ŀ����model�����������Ŀ�Ļ�������
 * Module: ͨ�ð���������
 * Copyright:Copyright (c) 2010 
 * Company: zfsoft
 * Author: sjf 
 * Version: 1.0
 * Time: 2011-2-17
 */
public class PjpyXmszModel {
	private String rssx;     //��������
	private String kzjb;     //���Ƽ���
	private String zjtg;     //ֱ��ͨ��
	private String lcid;     //����ID
	private String xmsm;     //��Ŀ˵��
	private String sqfs;     //���뷽ʽ
	private String pjxq;     //����ѧ��
	private String xmmc;     //��Ŀ����
	private String sjje;     //�漰���
	private String xmje;     //��Ŀ���
	private String rssz;     //��������
	private String pjxn;     //����ѧ��
	private String pjnd;     //�������
	private String sqzq;     //��������
	private String qztj;     //ǰ������
	private String xmlx;     //��Ŀ����
	private String ywmc;     //Ӣ������
	private String xmdm;     //��Ŀ����
	private String kzfw;     //���Ʒ�Χ
	private String sfqy;     //�Ƿ�����
	private String xmfw;     //��Ŀ��Χ
	private String xmxz;     //��Ŀ����
	private String sjkz;     //ʱ�����
	private String sfsh;     //�Ƿ����
	
	private String sqsj;	 //����ʱ��
	
	private List<HashMap<String, String>> shlc;		//�������
	
	// ==============2011.9.5 add by luojw==========================
	private String szrs; // ��������
	// ==============end==========================
	
	public String getRssx() {
		return rssx;
	}
	public void setRssx(String rssx) {
		this.rssx = rssx;
	}
	public String getKzjb() {
		return kzjb;
	}
	public void setKzjb(String kzjb) {
		this.kzjb = kzjb;
	}
	public String getZjtg() {
		return zjtg;
	}
	public void setZjtg(String zjtg) {
		this.zjtg = zjtg;
	}
	public String getLcid() {
		return lcid;
	}
	public void setLcid(String lcid) {
		this.lcid = lcid;
	}
	public String getXmsm() {
		return xmsm;
	}
	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
	}
	public String getSqfs() {
		return sqfs;
	}
	public void setSqfs(String sqfs) {
		this.sqfs = sqfs;
	}
	public String getPjxq() {
		return pjxq;
	}
	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getSjje() {
		return sjje;
	}
	public void setSjje(String sjje) {
		this.sjje = sjje;
	}
	public String getXmje() {
		return xmje;
	}
	public void setXmje(String xmje) {
		this.xmje = xmje;
	}
	public String getRssz() {
		return rssz;
	}
	public void setRssz(String rssz) {
		this.rssz = rssz;
	}
	public String getPjxn() {
		return pjxn;
	}
	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}
	public String getPjnd() {
		return pjnd;
	}
	public void setPjnd(String pjnd) {
		this.pjnd = pjnd;
	}
	public String getSqzq() {
		return sqzq;
	}
	public void setSqzq(String sqzq) {
		this.sqzq = sqzq;
	}
	public String getQztj() {
		return qztj;
	}
	public void setQztj(String qztj) {
		this.qztj = qztj;
	}
	public String getXmlx() {
		return xmlx;
	}
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	public String getYwmc() {
		return ywmc;
	}
	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getKzfw() {
		return kzfw;
	}
	public void setKzfw(String kzfw) {
		this.kzfw = kzfw;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String getXmfw() {
		return xmfw;
	}
	public void setXmfw(String xmfw) {
		this.xmfw = xmfw;
	}
	public String getXmxz() {
		return xmxz;
	}
	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}
	public String getSjkz() {
		return sjkz;
	}
	public void setSjkz(String sjkz) {
		this.sjkz = sjkz;
	}
	public String getSfsh() {
		return sfsh;
	}
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	public List<HashMap<String, String>> getShlc() {
		return shlc;
	}
	public void setShlc(List<HashMap<String, String>> shlc) {
		this.shlc = shlc;
	}
	public String getSqsj(){
		String sqsj = null;;
		
		if("xn".equalsIgnoreCase(sqzq)){
			sqsj = pjxn + "����";
		}else if("xq".equalsIgnoreCase(sqzq)){
			sqsj = pjxn + pjxq + "��";
		}else{
			sqsj = "����" + pjnd;
		}	
		return sqsj;
	}
	
	public String getSqxn(){
		return "nd".equalsIgnoreCase(sqzq) ? "��" : pjxn;
	}
	
	public String getSqxq(){
		return "xq".equalsIgnoreCase(sqzq) ? pjxq : "��";
	}
	
	public String getSqnd(){
		return "nd".equalsIgnoreCase(sqzq) ? pjnd : "��";
	}
	public String getSzrs() {
		return szrs;
	}
	public void setSzrs(String szrs) {
		this.szrs = szrs;
	}
}
