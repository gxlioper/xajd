package xsgzgl.xsxx.bzrpy.bzrpygl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XsxxBzrpyForm extends ActionForm {
	
	Pages pages=new Pages();
	
	SearchModel searchModel=new SearchModel();
	
	private String nj; // �꼶

	private String zymc; // רҵ

	private String zydm; // רҵ����

	private String xm; // ����

	private String xh; // ѧ��

	private String bjdm; // �༶����

	private String xymc; // ѧԺ

	private String xn; // ѧ��

	private String pyr; // ������

	private String pyyj; // ����

	private String bjmc; // �༶

	private String xydm; // ѧԺ����

	private String xb; // �Ա�

	private String pysj; // ����ʱ��

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getPyr() {
		return pyr;
	}

	public void setPyr(String pyr) {
		this.pyr = pyr;
	}

	public String getPysj() {
		return pysj;
	}

	public void setPysj(String pysj) {
		this.pysj = pysj;
	}

	public String getPyyj() {
		return pyyj;
	}

	public void setPyyj(String pyyj) {
		this.pyyj = pyyj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
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

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
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
