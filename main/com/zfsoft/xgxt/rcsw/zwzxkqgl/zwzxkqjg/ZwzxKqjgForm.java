/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-20 ����11:38:01 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ϰ���ڹ���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-20 ����11:38:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwzxKqjgForm extends ActionForm{
	
	private String jgid;
	private String sqid;
	private String xh;
	private String nj;
	private String xn;
	private String xq;
	private String xqmc;
	private String lrsj;
	private String cclxdm;  //�������
	private String cclxmc;
	private String qqlxdm;
	private String qqlxmc;
	private String xydm;
	private String xymc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private String kkjs;
	private String ylzd1;
	private String ydrs;  //Ӧ������
	private String sdrs;  //ʵ������
	private String qqrs;  //ȱ������
	private String wjrs;  //Υ������
	private String ccrq;  //�������
	private String jlf;  //���ɷ�
	private String bz;
	private String jlr;  //��¼��
	private String jlrxm;  //��¼��
	private String sjly;//������Դ
	private String type;
	private String[] xhArr;
    private String[] qqdmArr;
    private String[] kkjsArr;
    private String[] ylzd1Arr;

    private String dbfdy;	//���ศ��Ա
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
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
	 * @return the cclxdm
	 */
	public String getCclxdm() {
		return cclxdm;
	}
	/**
	 * @param cclxdmҪ���õ� cclxdm
	 */
	public void setCclxdm(String cclxdm) {
		this.cclxdm = cclxdm;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the ydrs
	 */
	public String getYdrs() {
		return ydrs;
	}
	/**
	 * @param ydrsҪ���õ� ydrs
	 */
	public void setYdrs(String ydrs) {
		this.ydrs = ydrs;
	}
	/**
	 * @return the sdrs
	 */
	public String getSdrs() {
		return sdrs;
	}
	/**
	 * @param sdrsҪ���õ� sdrs
	 */
	public void setSdrs(String sdrs) {
		this.sdrs = sdrs;
	}
	/**
	 * @return the qqrs
	 */
	public String getQqrs() {
		return qqrs;
	}
	/**
	 * @param qqrsҪ���õ� qqrs
	 */
	public void setQqrs(String qqrs) {
		this.qqrs = qqrs;
	}
	/**
	 * @return the wjrs
	 */
	public String getWjrs() {
		return wjrs;
	}
	/**
	 * @param wjrsҪ���õ� wjrs
	 */
	public void setWjrs(String wjrs) {
		this.wjrs = wjrs;
	}
	/**
	 * @return the ccrq
	 */
	public String getCcrq() {
		return ccrq;
	}
	/**
	 * @param ccrqҪ���õ� ccrq
	 */
	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	/**
	 * @return the jlf
	 */
	public String getJlf() {
		return jlf;
	}
	/**
	 * @param jlfҪ���õ� jlf
	 */
	public void setJlf(String jlf) {
		this.jlf = jlf;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the jlr
	 */
	public String getJlr() {
		return jlr;
	}
	/**
	 * @param jlrҪ���õ� jlr
	 */
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	/**
	 * @return the jlrxm
	 */
	public String getJlrxm() {
		return jlrxm;
	}
	/**
	 * @param jlrxmҪ���õ� jlrxm
	 */
	public void setJlrxm(String jlrxm) {
		this.jlrxm = jlrxm;
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
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the cclxmc
	 */
	public String getCclxmc() {
		return cclxmc;
	}
	/**
	 * @param cclxmcҪ���õ� cclxmc
	 */
	public void setCclxmc(String cclxmc) {
		this.cclxmc = cclxmc;
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
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymcҪ���õ� xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydmҪ���õ� zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymcҪ���õ� zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	
	
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArrҪ���õ� xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	/**
	 * @return the qqdmArr
	 */
	public String[] getQqdmArr() {
		return qqdmArr;
	}
	/**
	 * @param qqdmArrҪ���õ� qqdmArr
	 */
	public void setQqdmArr(String[] qqdmArr) {
		this.qqdmArr = qqdmArr;
	}
	/**
	 * @return the kkjsArr
	 */
	public String[] getKkjsArr() {
		return kkjsArr;
	}
	/**
	 * @param kkjsArrҪ���õ� kkjsArr
	 */
	public void setKkjsArr(String[] kkjsArr) {
		this.kkjsArr = kkjsArr;
	}
	/**
	 * @return the ylzd1Arr
	 */
	public String[] getYlzd1Arr() {
		return ylzd1Arr;
	}
	/**
	 * @param ylzd1ArrҪ���õ� ylzd1Arr
	 */
	public void setYlzd1Arr(String[] ylzd1Arr) {
		this.ylzd1Arr = ylzd1Arr;
	}
	/**
	 * @return the qqlxdm
	 */
	public String getQqlxdm() {
		return qqlxdm;
	}
	/**
	 * @param qqlxdmҪ���õ� qqlxdm
	 */
	public void setQqlxdm(String qqlxdm) {
		this.qqlxdm = qqlxdm;
	}
	/**
	 * @return the qqlxmc
	 */
	public String getQqlxmc() {
		return qqlxmc;
	}
	/**
	 * @param qqlxmcҪ���õ� qqlxmc
	 */
	public void setQqlxmc(String qqlxmc) {
		this.qqlxmc = qqlxmc;
	}
	/**
	 * @return the kkjs
	 */
	public String getKkjs() {
		return kkjs;
	}
	/**
	 * @param kkjsҪ���õ� kkjs
	 */
	public void setKkjs(String kkjs) {
		this.kkjs = kkjs;
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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

	public String getDbfdy() {
		return dbfdy;
	}

	public void setDbfdy(String dbfdy) {
		this.dbfdy = dbfdy;
	}
}
