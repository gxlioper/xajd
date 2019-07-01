package xsgzgl.customForm.gnmk;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

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

public class CustomGnmkForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/* ͨ�� */
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();

	private String gnmkdm;// ����ģ�����

	private String nj;// �꼶

	private String xydm;// ѧԺ����

	private String zydm;// רҵ����

	private String bjdm;// �༶����

	private String xh;// ѧ��

	private String xm;// ����

	private String xmb;// ��Ŀ��

	private String pk;// ����

	private String pkValue;// ����ֵ
	
	private String ryfw;// ��Ա��Χ

	private String[] zd;// ѧ��

	private String[] zdz;// ����

	private String xhzd;// ����

	private String[] qtzd = { "xm", "xb", "nj", "xydm", "zydm", "bjdm" };// �����ֶ�

	private String[] qtzdz;// �����ֶ�ֵ

	List<HashMap<String, String>> searchContentList;

	public List<HashMap<String, String>> getSearchContentList() {
		return searchContentList;
	}

	public void setSearchContentList(
			List<HashMap<String, String>> searchContentList) {
		this.searchContentList = searchContentList;
	}

	public String getGnmkdm() {
		return gnmkdm;
	}

	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
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

	public String getRyfw() {
		return ryfw;
	}

	public void setRyfw(String ryfw) {
		this.ryfw = ryfw;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getZd() {
		return zd;
	}

	public void setZd(String[] zd) {
		this.zd = zd;
	}

	public String[] getZdz() {
		return zdz;
	}

	public void setZdz(String[] zdz) {
		this.zdz = zdz;
	}

	public String getXmb() {
		return xmb;
	}

	public void setXmb(String xmb) {
		this.xmb = xmb;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getQtzd() {
		return qtzd;
	}

	public void setQtzd(String[] qtzd) {
		this.qtzd = qtzd;
	}

	public String[] getQtzdz() {
		return qtzdz;
	}

	public void setQtzdz(String[] qtzdz) {
		this.qtzdz = qtzdz;
	}

	public String getXhzd() {
		return xhzd;
	}

	public void setXhzd(String xhzd) {
		this.xhzd = xhzd;
	}

}
