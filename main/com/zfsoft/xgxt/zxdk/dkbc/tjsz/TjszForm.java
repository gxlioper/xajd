/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-4 ����10:36:05 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.tjsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����
 * @�๦������: �������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-3-4 ����10:36:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjszForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String tjdm;// ��������
	private String xmdm;// ��Ŀ����
	private String tjgx;// ������ϵ
	private String tjz;// ����ֵ
	private String xn;// ����ѧ��
	private String xq;// ����ѧ��
	private String bjdl;// ���Ʒ�Χ(�༶����)
	private String sfqy;// �Ƿ�����(��:1,��:0)
	private String yyfw;//����Ӧ�÷�Χ ���༶����or����Ⱥ�壩
	
	public TjszForm() {
		super();
	}

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

	public String getTjdm() {
		return tjdm;
	}

	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getTjgx() {
		return tjgx;
	}

	public void setTjgx(String tjgx) {
		this.tjgx = tjgx;
	}

	public String getTjz() {
		return tjz;
	}

	public void setTjz(String tjz) {
		this.tjz = tjz;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getBjdl() {
		return bjdl;
	}

	public void setBjdl(String bjdl) {
		this.bjdl = bjdl;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getYyfw() {
		return yyfw;
	}

	public void setYyfw(String yyfw) {
		this.yyfw = yyfw;
	}
	
	
	
}
