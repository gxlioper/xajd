/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-20 ����09:34:39 
 */  
package com.zfsoft.xgxt.xpjpy.tsxsdm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-20 ����09:34:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsDmForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String lxdm;
	private String lxmc;
	private String lxsm;
	private String lxsx;
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
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getLxsm() {
		return lxsm;
	}
	public void setLxsm(String lxsm) {
		this.lxsm = lxsm;
	}
	public String getLxsx() {
		return lxsx;
	}
	public void setLxsx(String lxsx) {
		this.lxsx = lxsx;
	}
	
}
