/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-4 ����11:38:01 
 */  
package com.zfsoft.xgxt.xstgl.stgl.stjg;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-4 ����11:38:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class StjgForm extends ActionForm{
	
	private String stid;
	private String id;
	private String xn;
	private String xq;
	private String xqmc;
	private String stxmmc;
	private String stlbdm;
	private String xmlbdm;
	private String gkdw;//�ҿ���λ
	private String kssj;
	private String jssj;
	private String fzrlb;//���������
	private String stfzr;//���Ÿ�����
	private String zdls;//ָ����ʦ
	private String sqsj;
	private String lxdh;
	private String jtr;//������
	private String stsm;//����˵��
	private String sjly;
	private String shzt;
	private String sqkssj;
	private String sqjssj;
	private String sqkg;
	private String splc;
	private String zdlszc;
	private String zdlslxfs;
	private String ssbm;
	private String stclsj;
	private String sthjqk;
	private String cysl;
	private String xhs;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	//����ְҵѧԺ���Ի�
	private String stxj;//�����Ǽ�
	
	//����
	private String fj;
	
	public String getFj() {
		return fj;
	}
	public void setFj(String fj) {
		this.fj = fj;
	}

	public String getStxj() {
		return stxj;
	}

	public void setStxj(String stxj) {
		this.stxj = stxj;
	}

	public String getXhs() {
		return xhs;
	}

	public void setXhs(String xhs) {
		this.xhs = xhs;
	}

	/**
	 * @return the zdlszc
	 */
	public String getZdlszc() {
		return zdlszc;
	}
	/**
	 * @param zdlszcҪ���õ� zdlszc
	 */
	public void setZdlszc(String zdlszc) {
		this.zdlszc = zdlszc;
	}
	/**
	 * @return the zdlslxfs
	 */
	public String getZdlslxfs() {
		return zdlslxfs;
	}
	/**
	 * @param zdlslxfsҪ���õ� zdlslxfs
	 */
	public void setZdlslxfs(String zdlslxfs) {
		this.zdlslxfs = zdlslxfs;
	}
	/**
	 * @return the ssbm
	 */
	public String getSsbm() {
		return ssbm;
	}
	/**
	 * @param ssbmҪ���õ� ssbm
	 */
	public void setSsbm(String ssbm) {
		this.ssbm = ssbm;
	}
	/**
	 * @return the stclsj
	 */
	public String getStclsj() {
		return stclsj;
	}
	/**
	 * @param stclsjҪ���õ� stclsj
	 */
	public void setStclsj(String stclsj) {
		this.stclsj = stclsj;
	}
	/**
	 * @return the sthjqk
	 */
	public String getSthjqk() {
		return sthjqk;
	}
	/**
	 * @param sthjqkҪ���õ� sthjqk
	 */
	public void setSthjqk(String sthjqk) {
		this.sthjqk = sthjqk;
	}
	/**
	 * @return the cysl
	 */
	public String getCysl() {
		return cysl;
	}
	/**
	 * @param cyslҪ���õ� cysl
	 */
	public void setCysl(String cysl) {
		this.cysl = cysl;
	}

	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
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
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getStxmmc() {
		return stxmmc;
	}
	public void setStxmmc(String stxmmc) {
		this.stxmmc = stxmmc;
	}
	public String getStlbdm() {
		return stlbdm;
	}
	public void setStlbdm(String stlbdm) {
		this.stlbdm = stlbdm;
	}
	public String getXmlbdm() {
		return xmlbdm;
	}
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}
	public String getGkdw() {
		return gkdw;
	}
	public void setGkdw(String gkdw) {
		this.gkdw = gkdw;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getFzrlb() {
		return fzrlb;
	}
	public void setFzrlb(String fzrlb) {
		this.fzrlb = fzrlb;
	}
	public String getStfzr() {
		return stfzr;
	}
	public void setStfzr(String stfzr) {
		this.stfzr = stfzr;
	}
	public String getZdls() {
		return zdls;
	}
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getJtr() {
		return jtr;
	}
	public void setJtr(String jtr) {
		this.jtr = jtr;
	}
	public String getStsm() {
		return stsm;
	}
	public void setStsm(String stsm) {
		this.stsm = stsm;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getSqkg() {
		return sqkg;
	}
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	
	
	

}
