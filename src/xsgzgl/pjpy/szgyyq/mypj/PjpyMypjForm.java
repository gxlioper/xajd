package xsgzgl.pjpy.szgyyq.mypj;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����_Form��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyMypjForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/* ͨ�� */
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();

	List<HashMap<String, String>> xmList;// ��Ŀ�б�

	private String rows;// ����

	private String yhlx;// �û�����

	private String czxm;// ������Ŀ
	
	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
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

	public List<HashMap<String, String>> getXmList() {
		return xmList;
	}

	public void setXmList(List<HashMap<String, String>> xmList) {
		this.xmList = xmList;
	}
}
