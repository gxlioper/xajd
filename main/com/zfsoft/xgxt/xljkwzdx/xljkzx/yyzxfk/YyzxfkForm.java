/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:33:55 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -ԤԼ��ѯ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����03:33:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YyzxfkForm extends ActionForm{


	private static final long serialVersionUID = 6049055260101630099L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	private String sqid;
	
	private String zxid;
	
	private String xh;
	
	private String yyzt;
	
	private String zzaprq;
	
	private String zxsdkssj;
	
	private String zxsdjssj;
	
	private String zxs;
	
	private String zxslxdh;
	
	private String zxdz;
	
	private String bz;
	
	private String yysbyy;
	
	private String yyzxfs;

	public String getYyzxfs() {
		return yyzxfs;
	}

	public void setYyzxfs(String yyzxfs) {
		this.yyzxfs = yyzxfs;
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
	 * @return ����ID
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param ����ID
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	/**
	 * @return ��ѯID
	 */
	public String getZxid() {
		return zxid;
	}

	/**
	 * @param ��ѯID
	 */
	public void setZxid(String zxid) {
		this.zxid = zxid;
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
	 * @return ԤԼ״̬-1ԤԼ��2ԤԼ�ɹ�3ԤԼ��(ѧ��ȡ��)4ԤԼ�ɹ�(ѧ��ȡ��)5ԤԼʧ��
	 */
	public String getYyzt() {
		return yyzt;
	}

	/**
	 * @param ԤԼ״̬-1ԤԼ��2ԤԼ�ɹ�3ԤԼ��(ѧ��ȡ��)4ԤԼ�ɹ�(ѧ��ȡ��)5ԤԼʧ��
	 */
	public void setYyzt(String yyzt) {
		this.yyzt = yyzt;
	}

	/**
	 * @return ��ѯ��������
	 */
	public String getZzaprq() {
		return zzaprq;
	}

	/**
	 * @param ��ѯ��������
	 */
	public void setZzaprq(String zzaprq) {
		this.zzaprq = zzaprq;
	}

	/**
	 * @return ��ѯʱ�ο�ʼʱ��
	 */
	public String getZxsdkssj() {
		return zxsdkssj;
	}

	/**
	 * @param ��ѯʱ�ο�ʼʱ��
	 */
	public void setZxsdkssj(String zxsdkssj) {
		this.zxsdkssj = zxsdkssj;
	}

	/**
	 * @return ��ѯʱ�ν���ʱ��
	 */
	public String getZxsdjssj() {
		return zxsdjssj;
	}

	/**
	 * @param ��ѯʱ�ν���ʱ��
	 */
	public void setZxsdjssj(String zxsdjssj) {
		this.zxsdjssj = zxsdjssj;
	}

	/**
	 * @return ������ѯʦ
	 */
	public String getZxs() {
		return zxs;
	}

	/**
	 * @param ������ѯʦ
	 */
	public void setZxs(String zxs) {
		this.zxs = zxs;
	}

	/**
	 * @return ��ѯʦ��ϵ�绰
	 */
	public String getZxslxdh() {
		return zxslxdh;
	}

	/**
	 * @param ��ѯʦ��ϵ�绰
	 */
	public void setZxslxdh(String zxslxdh) {
		this.zxslxdh = zxslxdh;
	}

	/**
	 * @return ��ѯ��ַ
	 */
	public String getZxdz() {
		return zxdz;
	}

	/**
	 * @param ��ѯ��ַ
	 */
	public void setZxdz(String zxdz) {
		this.zxdz = zxdz;
	}

	/**
	 * @return ��ע
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param ��ע
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return ԤԼʧ��ԭ��
	 */
	public String getYysbyy() {
		return yysbyy;
	}

	/**
	 * @param ԤԼʧ��ԭ��
	 */
	public void setYysbyy(String yysbyy) {
		this.yysbyy = yysbyy;
	}
	
}
