/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:57:54 
 */  
package com.zfsoft.xgxt.xpjpy.cpxz;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013-����С�� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:57:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpxzModel extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String bjdm; //�༶����
	private String pmz;	//������

	//������������Ա���ֶ�
	private String[] zghs;
	private String[] cpzdmArry;
	private String cpzdms;
	private String sffp;
	//������������Ա���ֶ�

	public String[] getZghs() {
		return zghs;
	}

	public void setZghs(String[] zghs) {
		this.zghs = zghs;
	}

	public String[] getCpzdmArry() {
		return cpzdmArry;
	}

	public void setCpzdmArry(String[] cpzdmArry) {
		this.cpzdmArry = cpzdmArry;
	}

	public String getCpzdms() {
		return cpzdms;
	}

	public void setCpzdms(String cpzdms) {
		this.cpzdms = cpzdms;
	}

	public String getSffp() {
		return sffp;
	}

	public void setSffp(String sffp) {
		this.sffp = sffp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public SearchModel getSearchModel() {
		return searchModel;
	}


	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getPmz() {
		return pmz;
	}

	public void setPmz(String pmz) {
		this.pmz = pmz;
	}
	
	
}
