/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-12 ����08:59:34 
 */  
package com.zfsoft.xgxt.xsxx.jcsjwh.ddwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ-���ά��
 * @�๦������: �㽭����ѧԺ���Ի����ά������ 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-6-12 ����08:59:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DdwhForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String dddm;// ��Ӵ���

	private String ddmc;// �������

	private String qds;// ������(�༶)
	
	private String qddm;

	private Pages pages = new Pages();

	private SearchModel searchModel = new SearchModel();
    
	private String type;
	
	private String flag;
	
	private String[] qdAll;
	
	public String[] getQdAll() {
		return qdAll;
	}

	public String getQddm() {
		return qddm;
	}


	public void setQddm(String qddm) {
		this.qddm = qddm;
	}


	public void setQdAll(String[] qdAll) {
		this.qdAll = qdAll;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDddm() {
		return dddm;
	}

	public void setDddm(String dddm) {
		this.dddm = dddm;
	}

	public String getDdmc() {
		return ddmc;
	}

	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}

	public String getQds() {
		return qds;
	}

	public void setQds(String qds) {
		this.qds = qds;
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
   
   
}
