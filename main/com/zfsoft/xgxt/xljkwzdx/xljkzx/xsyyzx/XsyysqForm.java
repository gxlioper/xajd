/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-25 ����11:22:21 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ-ѧ��ԤԼ��ѯ
 * @�๦������: 
 * @���ߣ�  ��־��[����:1060]
 * @ʱ�䣺 2014-4-25 ����11:22:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsyysqForm extends ActionForm{

	private static final long serialVersionUID = -3752172027912278209L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	/**
	 * ����ID
	 */
	private String sqid;
	/**
	 * ѧ��
	 */
	private String xh;
	/**
	 * ��ѯԤԼʱ��
	 */
	private String yyzxsj;
	/**
	 * ѧ����ϰ�绰
	 */
	private String xslxdh;
	/**
	 * ��������
	 */
	private String wtlx;
	/**
	 * ������������
	 */
	private String[] wtlxarray;
	/**
	 * �����Ҫ����
	 */
	private String yyzxzt;
	/**
	 * ������ע
	 */
	private String yyzxxq;
	/**
	 * ����ʱ��
	 */
	private String cjsj;
	/**
	 * ԤԼ״̬
	 */
	private String yyzt;
	/**
	 * ����״̬
	 */
	private String sjzt;
	/**
	 * ȡ��ԤԼԭ��
	 */
	private String qxyyyy;
	/**
	 * ԤԼʧ��ԭ��
	 */
	private String yysbyy;
	
	/**
	 * ԤԼ��ѯʦ
	 */
	private String zxs;
	
	public String getZxs() {
		return zxs;
	}
	public void setZxs(String zxs) {
		this.zxs = zxs;
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
	 * @return the yyzxsj
	 */
	public String getYyzxsj() {
		return yyzxsj;
	}
	/**
	 * @param yyzxsjҪ���õ� yyzxsj
	 */
	public void setYyzxsj(String yyzxsj) {
		this.yyzxsj = yyzxsj;
	}
	/**
	 * @return the xslxdh
	 */
	public String getXslxdh() {
		return xslxdh;
	}
	/**
	 * @param xslxdhҪ���õ� xslxdh
	 */
	public void setXslxdh(String xslxdh) {
		this.xslxdh = xslxdh;
	}
	/**
	 * @return the wtlx
	 */
	public String getWtlx() {
		return wtlx;
	}
	/**
	 * @param wtlxҪ���õ� wtlx
	 */
	public void setWtlx(String wtlx) {
		this.wtlx = wtlx;
	}
	/**
	 * @return the wtlxarray
	 */
	public String[] getWtlxarray() {
		return wtlxarray;
	}
	/**
	 * @param wtlxarrayҪ���õ� wtlxarray
	 */
	public void setWtlxarray(String[] wtlxarray) {
		this.wtlxarray = wtlxarray;
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
	 * @return the cjsj
	 */
	public String getCjsj() {
		return cjsj;
	}
	/**
	 * @param cjsjҪ���õ� cjsj
	 */
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	/**
	 * @return the yyzt
	 */
	public String getYyzt() {
		return yyzt;
	}
	/**
	 * @param yyztҪ���õ� yyzt
	 */
	public void setYyzt(String yyzt) {
		this.yyzt = yyzt;
	}
	/**
	 * @return the sjzt
	 */
	public String getSjzt() {
		return sjzt;
	}
	/**
	 * @param sjztҪ���õ� sjzt
	 */
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
	}
	/**
	 * @return the qxyyyy
	 */
	public String getQxyyyy() {
		return qxyyyy;
	}
	/**
	 * @param qxyyyyҪ���õ� qxyyyy
	 */
	public void setQxyyyy(String qxyyyy) {
		this.qxyyyy = qxyyyy;
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
	
}
