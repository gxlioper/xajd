/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-2 ����09:39:43 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-���÷���-��������ά��-����;��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-2 ����09:39:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyfftjForm extends ActionForm {

	
	private static final long serialVersionUID = -2599078032310583584L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String fftjdm;	//����;������
	private String fftj;	//����;��
	private String ffzh;	//�����˺�(0:����д; 1:������д)
	
	
	
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
	public String getFftjdm() {
		return fftjdm;
	}
	public void setFftjdm(String fftjdm) {
		this.fftjdm = fftjdm;
	}
	public String getFftj() {
		return fftj;
	}
	public void setFftj(String fftj) {
		this.fftj = fftj;
	}
	public String getFfzh() {
		return ffzh;
	}
	public void setFfzh(String ffzh) {
		this.ffzh = ffzh;
	}
	
	

}
