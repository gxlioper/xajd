package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxwh;


import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.gygl.comm.GyglNewForm;

public class JqfxwhForm extends GyglNewForm {

	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel();
	private String lddm;//¥������
	private String qsh;//���Һ�
	private String cwh;//��λ��
	private String xb;	//�Ա�
	private String xh;
	private String xm;
	private String xydm;
	private String nj;
	private String ch;
	private String tsyy;
	private String bz;	
	private String[] pk_xh;
	private String tssj;
	private String rzsj; //��סʱ��
	private String czr;//������
	private String bzr;
	private ExportModel exportModel = new ExportModel();
	private Pages page=new Pages();
	
	private String ldmc;//¥������	
	private String sfbl;
	private String zymc;
	private String qsxb;
	private String bjmc;
	private String pkValue;
	
	private String fxsj;//��Уʱ��
	private String fxzt;//��У״̬
	private String wfxyy;//δ��Уԭ��
	private String fxztmc;//��У״̬����
	private String id;//��Уѧ��id
	private String fxdm;//��У���
	private String fxmc;//��У����
	private String tbsj;//�ʱ��
	
	/**
	 * @return the tbsj
	 */
	public String getTbsj() {
		return tbsj;
	}


	/**
	 * @param tbsjҪ���õ� tbsj
	 */
	public void setTbsj(String tbsj) {
		this.tbsj = tbsj;
	}
	private String cyyy;//��У����
	private String[]xhs;
	
	private String countNum;
	private String[]fxzts;
	
	
	


	/**
	 * @return the fxzts
	 */
	public String[] getFxzts() {
		return fxzts;
	}


	/**
	 * @param fxztsҪ���õ� fxzts
	 */
	public void setFxzts(String[] fxzts) {
		this.fxzts = fxzts;
	}


	/**
	 * @return the countNum
	 */
	public String getCountNum() {
		return countNum;
	}


	/**
	 * @param countNumҪ���õ� countNum
	 */
	public void setCountNum(String countNum) {
		this.countNum = countNum;
	}


	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}


	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}


	/**
	 * @return the cyyy
	 */
	public String getCyyy() {
		return cyyy;
	}


	/**
	 * @param cyyyҪ���õ� cyyy
	 */
	public void setCyyy(String cyyy) {
		this.cyyy = cyyy;
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
	 * @return the fxdm
	 */
	public String getFxdm() {
		return fxdm;
	}
	
	/**
	 * @param fxdmҪ���õ� fxdm
	 */
	public void setFxdm(String fxdm) {
		this.fxdm = fxdm;
	}
	
	/**
	 * @return the fxztmc
	 */
	public String getFxztmc() {
		return fxztmc;
	}
	/**
	 * @param fxztmcҪ���õ� fxztmc
	 */
	public void setFxztmc(String fxztmc) {
		this.fxztmc = fxztmc;
	}
	/**
	 * @return the fxsj
	 */
	public String getFxsj() {
		return fxsj;
	}
	/**
	 * @param fxsjҪ���õ� fxsj
	 */
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}
	/**
	 * @return the fxzt
	 */
	public String getFxzt() {
		return fxzt;
	}
	/**
	 * @param fxztҪ���õ� fxzt
	 */
	public void setFxzt(String fxzt) {
		this.fxzt = fxzt;
	}
	/**
	 * @return the wfxyy
	 */
	public String getWfxyy() {
		return wfxyy;
	}
	/**
	 * @param wfxyyҪ���õ� wfxyy
	 */
	public void setWfxyy(String wfxyy) {
		this.wfxyy = wfxyy;
	}
	/**
	 * @return the pkValue
	 */
	public String getPkValue() {
		return pkValue;
	}
	/**
	 * @param pkValueҪ���õ� pkValue
	 */
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
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
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmcҪ���õ� ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the sfbl
	 */
	public String getSfbl() {
		return sfbl;
	}
	/**
	 * @param sfblҪ���õ� sfbl
	 */
	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
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
	 * @return the qsxb
	 */
	public String getQsxb() {
		return qsxb;
	}
	/**
	 * @param qsxbҪ���õ� qsxb
	 */
	public void setQsxb(String qsxb) {
		this.qsxb = qsxb;
	}
	private String type;//¥������

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
	 * ѧ��
	 */
	private String xn;
	
	/**
	 * ѧ��
	 */
	private String xq;
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
	
	public Pages getPage() {
		return page;
	}
	public void setPage(Pages page) {
		this.page = page;
	}
	private String rzyy;
	
	private String rzyydm;
	private String rzyymc;
	//������У �Ƿ��ʼ����λ
	private String sfqccwss;
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}	
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}
	public String getTsyy() {
		return tsyy;
	}
	public void setTsyy(String tsyy) {
		this.tsyy = tsyy;
	}
	public String[] getPk_xh() {
		return pk_xh;
	}
	public void setPk_xh(String[] pkXh) {
		pk_xh = pkXh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
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
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getTssj() {
		return tssj;
	}
	public void setTssj(String tssj) {
		this.tssj = tssj;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
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
	public String getRzyy() {
		return rzyy;
	}
	public void setRzyy(String rzyy) {
		this.rzyy = rzyy;
	}
	public String getRzyydm() {
		return rzyydm;
	}
	public void setRzyydm(String rzyydm) {
		this.rzyydm = rzyydm;
	}
	public String getRzyymc() {
		return rzyymc;
	}
	public void setRzyymc(String rzyymc) {
		this.rzyymc = rzyymc;
	}
	/**
	 * @return the sfqccwss
	 */
	public String getSfqccwss() {
		return sfqccwss;
	}
	/**
	 * @param sfqccwssҪ���õ� sfqccwss
	 */
	public void setSfqccwss(String sfqccwss) {
		this.sfqccwss = sfqccwss;
	}
	/**
	 * @return the bzr
	 */
	public String getBzr() {
		return bzr;
	}
	/**
	 * @param bzrҪ���õ� bzr
	 */
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	
	/**
	 * @return the fxmc
	 */
	public String getFxmc() {
		return fxmc;
	}

	/**
	 * @param fxmcҪ���õ� fxmc
	 */
	public void setFxmc(String fxmc) {
		this.fxmc = fxmc;
	}
}
