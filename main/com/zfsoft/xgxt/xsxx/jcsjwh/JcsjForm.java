package com.zfsoft.xgxt.xsxx.jcsjwh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������:��������ά�����꼶ѧԺרҵ�༶��
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-12-3 ����10:56:46 
 * @�汾�� V1.0
 */
public class JcsjForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	/**
	 * ѡ��FLG(0��ѧԺ��1��רҵ��2���༶ 3���꼶) 
	 */
	String xzflg;

	/**
	 * �꼶
	 */
	private String nj;

	/**
	 * ѧԺ����
	 */
	private String xydm;

	/**
	 * ѧԺ����Old
	 */
	private String xydmold;
	
	/**
	 * ѧԺ����
	 */
	private String xymc;
	
	/**
	 * רҵ����
	 */
	private String zydm;
	
	/**
	 * רҵ����Old
	 */
	private String zydmold;
	
	/**
	 * ѧԺ����[רҵ]
	 */
	private String xydmzy;
	
	/**
	 * רҵ����
	 */
	private String zymc;
	
	/**
	 * �༶����
	 */
	private String bjdm;

	/**
	 * �༶����Old
	 */
	private String bjdmold;
	
	/**
	 * ѧԺ����[�༶]
	 */
	private String xydmbj;
	/**
	 * רҵ����[�༶]
	 */
	private String zydmbj;
	
	/**
	 * �༶����
	 */
	private String bjmc;

	/**
	 * ѧ����
	 */
	private String xss;
	
	/**
	 * רҵ��
	 */
	private String zys;

	/**
	 * �༶��
	 */
	private String bjs;
	
	/**
	 * �������
	 */
	private String bmlb;
	
	
	/**
	 * �Ƿ�������ݷ�Χ[0:����;1:������]	
	 */
	private String sfkzSjfw;
	
	/**
	 * �Ƿ���У��Χ[0:��Уview_njxyzybj;1:����Уview_njxyzybj_all]	
	 */
	private String sfzx;
	
	/**
	 * �й�ѧԺ����
	 */
	private String tgxydm;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	public String getSfkzSjfw() {
		return sfkzSjfw;
	}

	public void setSfkzSjfw(String sfkzSjfw) {
		this.sfkzSjfw = sfkzSjfw;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getTgxydm() {
		return tgxydm;
	}

	public void setTgxydm(String tgxydm) {
		this.tgxydm = tgxydm;
	}

	public String getBmlb() {
		return bmlb;
	}
	
	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}

	public String getXydmold() {
		return xydmold;
	}

	public void setXydmold(String xydmold) {
		this.xydmold = xydmold;
	}

	public String getZydmold() {
		return zydmold;
	}

	public void setZydmold(String zydmold) {
		this.zydmold = zydmold;
	}

	public String getBjdmold() {
		return bjdmold;
	}

	public void setBjdmold(String bjdmold) {
		this.bjdmold = bjdmold;
	}

	public String getXydmzy() {
		return xydmzy;
	}

	public void setXydmzy(String xydmzy) {
		this.xydmzy = xydmzy;
	}

	public String getXydmbj() {
		return xydmbj;
	}

	public void setXydmbj(String xydmbj) {
		this.xydmbj = xydmbj;
	}

	public String getZydmbj() {
		return zydmbj;
	}

	public void setZydmbj(String zydmbj) {
		this.zydmbj = zydmbj;
	}

	public String getXzflg() {
		return xzflg;
	}

	public void setXzflg(String xzflg) {
		this.xzflg = xzflg;
	}

	public String getZys() {
		return zys;
	}

	public void setZys(String zys) {
		this.zys = zys;
	}

	public String getBjs() {
		return bjs;
	}

	public void setBjs(String bjs) {
		this.bjs = bjs;
	}

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

	public String getXss() {
		return xss;
	}

	public void setXss(String xss) {
		this.xss = xss;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
