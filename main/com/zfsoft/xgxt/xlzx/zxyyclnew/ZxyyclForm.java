 
package com.zfsoft.xgxt.xlzx.zxyyclnew;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * ��ѯԤԼ���� 
 */

public class ZxyyclForm  extends ActionForm {


	private static final long serialVersionUID = 1L;

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	private String id; //����
	private String yyid; //ԤԼ���
	private String zxlx;//��ѯ����
	private String zxrq;//��ѯʱ��
	private String qssj;//��ѯʱ��
	private String jssj;//��ѯʱ��
	private String zxdz;//��ѯ��ַ
	private String zxtell;//��ѯ�绰
	private String xspj;//ѧ������
	private String xspjsj;//����ʱ��
	private String zxsfk;//��ѯʦ����
	private String zxsfksj;//����ʱ��
	private String zxstatus;//��ѯ״̬-1����ѯ2����ѯ
	private String bz;//��ע
	private String datazt;//����״̬-0ʧЧ1����
	private String xspjzt;//ѧ������״̬-1������2������
	
	private String xh;//ѧ��
	private String zgh;//ְ����
	
	private String yyzxzt;///ԤԼ��ѯ״̬
	private String yyzxxq;//ԤԼ��ѯ����
	private String xstell;//ѧ��
	
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String apzxs;
	private String xlcsjg;
	private String ywyw;
	private String zyzlls;
	private String zxcs;
	private String sfja;
	private String sczxsj;
	private String bczxnr;
	private String bcjjwt;
	private String zxgsfs;
	private String zxfknr;
	private String sjddm;
	private String zxwtlxdm;

	private String yyfs;

	public String getYyfs() {
		return yyfs;
	}

	public void setYyfs(String yyfs) {
		this.yyfs = yyfs;
	}

	public String getZxwtlxdm() {
		return zxwtlxdm;
	}

	public void setZxwtlxdm(String zxwtlxdm) {
		this.zxwtlxdm = zxwtlxdm;
	}

	public String getSjddm() {
		return sjddm;
	}
	public void setSjddm(String sjddm) {
		this.sjddm = sjddm;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param userҪ���õ� user
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * @return the yyid
	 */
	public String getYyid() {
		return yyid;
	}
	/**
	 * @param yyidҪ���õ� yyid
	 */
	public void setYyid(String yyid) {
		this.yyid = yyid;
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	
	/**
	 * @return the zxlx
	 */
	public String getZxlx() {
		return zxlx;
	}
	/**
	 * @param zxlxҪ���õ� zxlx
	 */
	public void setZxlx(String zxlx) {
		this.zxlx = zxlx;
	}
	/**
	 * @return the zxrq
	 */
	public String getZxrq() {
		return zxrq;
	}
	/**
	 * @param zxrqҪ���õ� zxrq
	 */
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}
	
	/**
	 * @return the qssj
	 */
	public String getQssj() {
		return qssj;
	}
	/**
	 * @param qssjҪ���õ� qssj
	 */
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssjҪ���õ� jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	/**
	 * @return the zxdz
	 */
	public String getZxdz() {
		return zxdz;
	}
	/**
	 * @param zxdzҪ���õ� zxdz
	 */
	public void setZxdz(String zxdz) {
		this.zxdz = zxdz;
	}
	
	
	/**
	 * @return the zxtell
	 */
	public String getZxtell() {
		return zxtell;
	}
	/**
	 * @param zxtellҪ���õ� zxtell
	 */
	public void setZxtell(String zxtell) {
		this.zxtell = zxtell;
	}
	/**
	 * @return the xspj
	 */
	public String getXspj() {
		return xspj;
	}
	/**
	 * @param xspjҪ���õ� xspj
	 */
	public void setXspj(String xspj) {
		this.xspj = xspj;
	}
	/**
	 * @return the xspjsj
	 */
	public String getXspjsj() {
		return xspjsj;
	}
	/**
	 * @param xspjsjҪ���õ� xspjsj
	 */
	public void setXspjsj(String xspjsj) {
		this.xspjsj = xspjsj;
	}
	/**
	 * @return the zxsfk
	 */
	public String getZxsfk() {
		return zxsfk;
	}
	/**
	 * @param zxsfkҪ���õ� zxsfk
	 */
	public void setZxsfk(String zxsfk) {
		this.zxsfk = zxsfk;
	}
	/**
	 * @return the zxsfksj
	 */
	public String getZxsfksj() {
		return zxsfksj;
	}
	/**
	 * @param zxsfksjҪ���õ� zxsfksj
	 */
	public void setZxsfksj(String zxsfksj) {
		this.zxsfksj = zxsfksj;
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
	 * @return the datazt
	 */
	public String getDatazt() {
		return datazt;
	}
	/**
	 * @param dataztҪ���õ� datazt
	 */
	public void setDatazt(String datazt) {
		this.datazt = datazt;
	}
	
	/**
	 * @return the zxstatus
	 */
	public String getZxstatus() {
		return zxstatus;
	}
	/**
	 * @param zxstatusҪ���õ� zxstatus
	 */
	public void setZxstatus(String zxstatus) {
		this.zxstatus = zxstatus;
	}
	/**
	 * @return the xspjzt
	 */
	public String getXspjzt() {
		return xspjzt;
	}
	/**
	 * @param xspjztҪ���õ� xspjzt
	 */
	public void setXspjzt(String xspjzt) {
		this.xspjzt = xspjzt;
	}
	/**
	 * @return the yyzxzt
	 */
	public String getYyzxzt() {
		return yyzxzt;
	}
	/**
	 * @param yyzxztҪ���õ� yyzxzt
	 */
	public void setYyzxzt(String yyzxzt) {
		this.yyzxzt = yyzxzt;
	}
	/**
	 * @return the yyzxxq
	 */
	public String getYyzxxq() {
		return yyzxxq;
	}
	/**
	 * @param yyzxxqҪ���õ� yyzxxq
	 */
	public void setYyzxxq(String yyzxxq) {
		this.yyzxxq = yyzxxq;
	}
	/**
	 * @return the xstell
	 */
	public String getXstell() {
		return xstell;
	}
	/**
	 * @param xstellҪ���õ� xstell
	 */
	public void setXstell(String xstell) {
		this.xstell = xstell;
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
	 * @return the apzxs
	 */
	public String getApzxs() {
		return apzxs;
	}
	/**
	 * @param apzxsҪ���õ� apzxs
	 */
	public void setApzxs(String apzxs) {
		this.apzxs = apzxs;
	}
	/**
	 * @return the xlcsjg
	 */
	public String getXlcsjg() {
		return xlcsjg;
	}
	/**
	 * @param xlcsjgҪ���õ� xlcsjg
	 */
	public void setXlcsjg(String xlcsjg) {
		this.xlcsjg = xlcsjg;
	}
	/**
	 * @return the ywyw
	 */
	public String getYwyw() {
		return ywyw;
	}
	/**
	 * @param ywywҪ���õ� ywyw
	 */
	public void setYwyw(String ywyw) {
		this.ywyw = ywyw;
	}
	/**
	 * @return the zyzlls
	 */
	public String getZyzlls() {
		return zyzlls;
	}
	/**
	 * @param zyzllsҪ���õ� zyzlls
	 */
	public void setZyzlls(String zyzlls) {
		this.zyzlls = zyzlls;
	}
	/**
	 * @return the zxcs
	 */
	public String getZxcs() {
		return zxcs;
	}
	/**
	 * @param zxcsҪ���õ� zxcs
	 */
	public void setZxcs(String zxcs) {
		this.zxcs = zxcs;
	}
	/**
	 * @return the sfja
	 */
	public String getSfja() {
		return sfja;
	}
	/**
	 * @param sfjaҪ���õ� sfja
	 */
	public void setSfja(String sfja) {
		this.sfja = sfja;
	}
	/**
	 * @return the sczxsj
	 */
	public String getSczxsj() {
		return sczxsj;
	}
	/**
	 * @param sczxsjҪ���õ� sczxsj
	 */
	public void setSczxsj(String sczxsj) {
		this.sczxsj = sczxsj;
	}
	/**
	 * @return the bczxnr
	 */
	public String getBczxnr() {
		return bczxnr;
	}
	/**
	 * @param bczxnrҪ���õ� bczxnr
	 */
	public void setBczxnr(String bczxnr) {
		this.bczxnr = bczxnr;
	}
	/**
	 * @return the bcjjwt
	 */
	public String getBcjjwt() {
		return bcjjwt;
	}
	/**
	 * @param bcjjwtҪ���õ� bcjjwt
	 */
	public void setBcjjwt(String bcjjwt) {
		this.bcjjwt = bcjjwt;
	}
	/**
	 * @return the zxgsfs
	 */
	public String getZxgsfs() {
		return zxgsfs;
	}
	/**
	 * @param zxgsfsҪ���õ� zxgsfs
	 */
	public void setZxgsfs(String zxgsfs) {
		this.zxgsfs = zxgsfs;
	}
	/**
	 * @return the zxfknr
	 */
	public String getZxfknr() {
		return zxfknr;
	}
	/**
	 * @param zxfknrҪ���õ� zxfknr
	 */
	public void setZxfknr(String zxfknr) {
		this.zxfknr = zxfknr;
	}
	
}
