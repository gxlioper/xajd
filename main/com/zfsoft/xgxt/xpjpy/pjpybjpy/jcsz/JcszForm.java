package com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JcszForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String bjpyisopen ;//��ǰʱ���Ƿ���
	private String bjpykg; // �����༶���鿪��
	private String bjpykssj; // �����༶���鿪ʼʱ��
	private String bjpyjssj; // �����༶�������ʱ��
	private String xzrsxx; // С����������
	private String pyyxbl; // ������Ч����
	private String xzrssfbxwjs; // С�������Ƿ����Ϊ����
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
	public String getBjpyisopen() {
		return bjpyisopen;
	}
	public void setBjpyisopen(String bjpyisopen) {
		this.bjpyisopen = bjpyisopen;
	}
	public String getBjpykg() {
		return bjpykg;
	}
	public void setBjpykg(String bjpykg) {
		this.bjpykg = bjpykg;
	}
	public String getBjpykssj() {
		return bjpykssj;
	}
	public void setBjpykssj(String bjpykssj) {
		this.bjpykssj = bjpykssj;
	}
	public String getBjpyjssj() {
		return bjpyjssj;
	}
	public void setBjpyjssj(String bjpyjssj) {
		this.bjpyjssj = bjpyjssj;
	}
	public String getXzrsxx() {
		return xzrsxx;
	}
	public void setXzrsxx(String xzrsxx) {
		this.xzrsxx = xzrsxx;
	}
	public String getPyyxbl() {
		return pyyxbl;
	}
	public void setPyyxbl(String pyyxbl) {
		this.pyyxbl = pyyxbl;
	}
	public String getXzrssfbxwjs() {
		return xzrssfbxwjs;
	}
	public void setXzrssfbxwjs(String xzrssfbxwjs) {
		this.xzrssfbxwjs = xzrssfbxwjs;
	}
	
}
