/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-19 ����04:43:51 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ������ѯ-��ѯʦ�Ű����(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-8-19 ����04:35:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 * @  wanghj [���ţ�1004] 2013-9-4 ����02:35:22
 * 
 */

public class ZxspbForm extends ActionForm {


	private static final long serialVersionUID = 1L;

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	  
	private String id; // �Ű�����
	
	private String pbtype; // �Ű�����
	private String pbdate; // �Ű�����
	private String zgh; //ְ����
	private String sjddm; // ʱ��δ���
	private String createsj; // ��������
	private String createbh; // �����˱��
	private String createmc; // ����������
	private String bz; // ��ע
	private String datazt; // ����״̬0ʧЧ1����
	private String dqny;//��ǰ����
	private String[] zghs;//ְ����
	private String[] sjdm;//ʱ��δ���
	private String[] xqdm;
	private String startDate;
	private String endDate;
	private String sfCopyPb;
	
	public String getSfCopyPb() {
		return sfCopyPb;
	}

	public void setSfCopyPb(String sfCopyPb) {
		this.sfCopyPb = sfCopyPb;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String[] getXqdm() {
		return xqdm;
	}

	public void setXqdm(String[] xqdm) {
		this.xqdm = xqdm;
	}

	public String[] getZghs() {
		return zghs;
	}

	public void setZghs(String[] zghs) {
		this.zghs = zghs;
	}

	public String[] getSjdm() {
		return sjdm;
	}

	public void setSjdm(String[] sjdm) {
		this.sjdm = sjdm;
	}

	/**
	 * @return the dqny
	 */
	public String getDqny() {
		return dqny;
	}

	/**
	 * @param dqnyҪ���õ� dqny
	 */
	public void setDqny(String dqny) {
		this.dqny = dqny;
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
	 * @return the sjddm
	 */
	public String getSjddm() {
		return sjddm;
	}

	/**
	 * @param sjddmҪ���õ� sjddm
	 */
	public void setSjddm(String sjddm) {
		this.sjddm = sjddm;
	}

	/**
	 * @return the pbtype
	 */
	public String getPbtype() {
		return pbtype;
	}

	/**
	 * @param pbtypeҪ���õ� pbtype
	 */
	public void setPbtype(String pbtype) {
		this.pbtype = pbtype;
	}

	/**
	 * @return the pbdate
	 */
	public String getPbdate() {
		return pbdate;
	}

	/**
	 * @param pbdateҪ���õ� pbdate
	 */
	public void setPbdate(String pbdate) {
		this.pbdate = pbdate;
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
	 * @return the createbh
	 */
	public String getCreatebh() {
		return createbh;
	}

	/**
	 * @param createbhҪ���õ� createbh
	 */
	public void setCreatebh(String createbh) {
		this.createbh = createbh;
	}

	/**
	 * @return the createmc
	 */
	public String getCreatemc() {
		return createmc;
	}

	/**
	 * @param createmcҪ���õ� createmc
	 */
	public void setCreatemc(String createmc) {
		this.createmc = createmc;
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

	
	
}
