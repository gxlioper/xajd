package com.zfsoft.xgxt.xlzx.yysqnew;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * ������ѯ����ģ��
 */

public class YysqForm  extends ActionForm {


	private static final long serialVersionUID = 1L;

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	private String id; //����
	private String xh;//ѧ��
	private String xstell;//ѧ���绰
	private String zgh;//ְ����
	private String yyzxrq;//ԤԼ��ѯʱ��
	private String yyzxzt;//ԤԼ��ѯ����
	private String yyzxxq;//ԤԼ��ѯ����
	private String status;//ԤԼ״̬-1ԤԼ��2ԤԼ�ɹ�3ԤԼ��(ѧ��ȡ��)4ԤԼ�ɹ�(ѧ��ȡ��)5ԤԼʧ��6�ѹ���
	private String yysbyy;//ԤԼʧ��ԭ��
	private String createsj;//����ʱ��
	private String bz;//��ע
	private String datazt;//����״̬-0ʧЧ1����
	private String qssj;//ԤԼ��ѯ��ʼʱ��
	private String jssj;//ԤԼ��ѯ����ʱ��
	private String xn;//ѧ��
	private String xq;//ѧ��
	
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


	private String qdzt;//ǩ��״̬��yqd ��ǩ��,wqd δǩ��,qj ���,cd �ٵ�
	private String yyfs;//ԤԼ��ʽ:ws ����ԤԼ,dh �绰ԤԼ,xc �ֳ�ԤԼ,jz �ҳ�ԤԼ
	private String qdztbz;//ǩ��״̬��ע����״̬Ϊ��٣��򱣴�������ԭ�򣬵�״̬Ϊ��٣��򱣴���ǳٵ�ʱ��

	public String getQdztbz() {
		return qdztbz;
	}

	public void setQdztbz(String qdztbz) {
		this.qdztbz = qdztbz;
	}

	public String getYyfs() {
		return yyfs;
	}

	public void setYyfs(String yyfs) {
		this.yyfs = yyfs;
	}

	public String getQdzt() {
		return qdzt;
	}

	public void setQdzt(String qdzt) {
		this.qdzt = qdzt;
	}

	/**
	 * ��������
	 */
	private ExportModel exportModel = new ExportModel();
	
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	 * @return the yyzxrq
	 */
	public String getYyzxrq() {
		return yyzxrq;
	}
	/**
	 * @param yyzxrqҪ���õ� yyzxrq
	 */
	public void setYyzxrq(String yyzxrq) {
		this.yyzxrq = yyzxrq;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param statusҪ���õ� status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the yysbyy
	 */
	public String getYysbyy() {
		return yysbyy;
	}
	/**
	 * @param yysbyyҪ���õ� yysbyy
	 */
	public void setYysbyy(String yysbyy) {
		this.yysbyy = yysbyy;
	}
	/**
	 * @return the createsj
	 */
	public String getCreatesj() {
		return createsj;
	}
	/**
	 * @param createsjҪ���õ� createsj
	 */
	public void setCreatesj(String createsj) {
		this.createsj = createsj;
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
