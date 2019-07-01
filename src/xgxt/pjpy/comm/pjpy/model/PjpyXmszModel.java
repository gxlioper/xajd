package xgxt.pjpy.comm.pjpy.model;

import java.util.HashMap;
import java.util.List;

/**
 * Title: 学生工作管理系统 
 * Description: 通用版评评优项目设置model，存放评奖项目的基本设置
 * Module: 通用版评奖评优
 * Copyright:Copyright (c) 2010 
 * Company: zfsoft
 * Author: sjf 
 * Version: 1.0
 * Time: 2011-2-17
 */
public class PjpyXmszModel {
	private String rssx;     //人数上限
	private String kzjb;     //控制级别
	private String zjtg;     //直接通过
	private String lcid;     //流程ID
	private String xmsm;     //项目说明
	private String sqfs;     //申请方式
	private String pjxq;     //评奖学期
	private String xmmc;     //项目名称
	private String sjje;     //涉及金额
	private String xmje;     //项目金额
	private String rssz;     //人数设置
	private String pjxn;     //评奖学年
	private String pjnd;     //评奖年度
	private String sqzq;     //申请周期
	private String qztj;     //前置条件
	private String xmlx;     //项目类型
	private String ywmc;     //英文名称
	private String xmdm;     //项目代码
	private String kzfw;     //控制范围
	private String sfqy;     //是否启用
	private String xmfw;     //项目范围
	private String xmxz;     //项目性质
	private String sjkz;     //时间控制
	private String sfsh;     //是否审核
	
	private String sqsj;	 //申请时间
	
	private List<HashMap<String, String>> shlc;		//审核流程
	
	// ==============2011.9.5 add by luojw==========================
	private String szrs; // 设置人数
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
			sqsj = pjxn + "无无";
		}else if("xq".equalsIgnoreCase(sqzq)){
			sqsj = pjxn + pjxq + "无";
		}else{
			sqsj = "无无" + pjnd;
		}	
		return sqsj;
	}
	
	public String getSqxn(){
		return "nd".equalsIgnoreCase(sqzq) ? "无" : pjxn;
	}
	
	public String getSqxq(){
		return "xq".equalsIgnoreCase(sqzq) ? pjxq : "无";
	}
	
	public String getSqnd(){
		return "nd".equalsIgnoreCase(sqzq) ? pjnd : "无";
	}
	public String getSzrs() {
		return szrs;
	}
	public void setSzrs(String szrs) {
		this.szrs = szrs;
	}
}
