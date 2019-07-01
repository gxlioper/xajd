package xgxt.xsxx.dagl.guizdx;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class XsxxGuizdxDaglForm extends ActionForm {
	
	User user = new User();

	Pages pages = new Pages();
	
	SearchModel searchModel=new SearchModel();
	
	private String xydm;
	
	private String zydm;
	
	private String bjdm;
	
	private String nj;

	private String dm;//代码
	
	private String mc;//名称
	
	private String lx;//类型
	
	private String xh;
	
	private String xm;
	
	private String jlsj;
	
	private String jlr;
	
	private String sfbrtj;
	
	private String datddz;
	
	private String jsr;
	
	private String[]xsdaInfo;//新生档案信息
	
	private String []pkValue;
	
	public String getDatddz() {
		return datddz;
	}

	public void setDatddz(String datddz) {
		this.datddz = datddz;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getJlr() {
		return jlr;
	}

	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	public String getJlsj() {
		return jlsj;
	}

	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}

	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String getSfbrtj() {
		return sfbrtj;
	}

	public void setSfbrtj(String sfbrtj) {
		this.sfbrtj = sfbrtj;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getXsdaInfo() {
		return xsdaInfo;
	}

	public void setXsdaInfo(String[] xsdaInfo) {
		this.xsdaInfo = xsdaInfo;
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

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

}
