package xgxt.comm.chart.operation;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;

public class ChartForm extends ActionForm implements Serializable{

	
	SearchModel searchModel = new SearchModel();
	
	private String tjzd;//ͳ���ֶ�
	
	private String tjlb;//ͳ�����(��ͼ����״ͼ) ��pie,category��

	private int width;
	
	private int height;
	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getTjzd() {
		return tjzd;
	}

	public void setTjzd(String tjzd) {
		this.tjzd = tjzd;
	}

	public String getTjlb() {
		return tjlb;
	}

	public void setTjlb(String tjlb) {
		this.tjlb = tjlb;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

}
