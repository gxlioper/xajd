/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-31 ����04:59:33 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tjsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-31 ����04:59:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TjszModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	private String id;// ID
	private String xmdm;// ��Ŀ����
	private String tjdm;// ��������
	private String yyfw;// Ӧ�÷�Χ
	private String gxdm;// ��ϵ����
	private String tjz;// ����ֵ
	private String ylzq;// ��������

	public TjszModel() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getTjdm() {
		return tjdm;
	}

	public void setTjdm(String tjdm) {
		this.tjdm = tjdm;
	}

	public String getYyfw() {
		return yyfw;
	}

	public void setYyfw(String yyfw) {
		this.yyfw = yyfw;
	}

	public String getGxdm() {
		return gxdm;
	}

	public void setGxdm(String gxdm) {
		this.gxdm = gxdm;
	}

	public String getTjz() {
		return tjz;
	}

	public void setTjz(String tjz) {
		this.tjz = tjz;
	}

	public String getYlzq() {
		return ylzq;
	}

	public void setYlzq(String ylzq) {
		this.ylzq = ylzq;
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

}
