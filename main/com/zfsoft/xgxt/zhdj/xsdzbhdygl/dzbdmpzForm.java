package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class dzbdmpzForm extends ActionForm {
    private String dm;
    private String mc;
    private String xydm;
    private String xymc;

    private String clsj;
    private String hjsj;
    private String[] lxrzghs;
    private String[] jzgmcs;
    private String[] jzgdhs;
    private String[] zwmcs;
    private String[] zwdms;
    private String dzzid;
    private String dzzmc;

    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    private String[] dms;
    
    private String sydm;
    private String symc;

    public String getClsj() {
        return clsj;
    }

    public void setClsj(String clsj) {
        this.clsj = clsj;
    }

    public String getHjsj() {
        return hjsj;
    }

    public void setHjsj(String hjsj) {
        this.hjsj = hjsj;
    }

    public String[] getLxrzghs() {
        return lxrzghs;
    }

    public void setLxrzghs(String[] lxrzghs) {
        this.lxrzghs = lxrzghs;
    }

    public String[] getJzgmcs() {
        return jzgmcs;
    }

    public void setJzgmcs(String[] jzgmcs) {
        this.jzgmcs = jzgmcs;
    }

    public String[] getJzgdhs() {
        return jzgdhs;
    }

    public void setJzgdhs(String[] jzgdhs) {
        this.jzgdhs = jzgdhs;
    }

    public String[] getZwmcs() {
        return zwmcs;
    }

    public void setZwmcs(String[] zwmcs) {
        this.zwmcs = zwmcs;
    }

    public String[] getZwdms() {
        return zwdms;
    }

    public void setZwdms(String[] zwdms) {
        this.zwdms = zwdms;
    }

    public String getDzzid() {
        return dzzid;
    }

    public void setDzzid(String dzzid) {
        this.dzzid = dzzid;
    }

    public String getDzzmc() {
        return dzzmc;
    }

    public void setDzzmc(String dzzmc) {
        this.dzzmc = dzzmc;
    }

    public String[] getDms() {
        return dms;
    }

    public void setDms(String[] dms) {
        this.dms = dms;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
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

	/**
	 * @return the sydm
	 */
	public String getSydm() {
		return sydm;
	}

	/**
	 * @param sydm要设置的 sydm
	 */
	public void setSydm(String sydm) {
		this.sydm = sydm;
	}

	/**
	 * @return the symc
	 */
	public String getSymc() {
		return symc;
	}

	/**
	 * @param symc要设置的 symc
	 */
	public void setSymc(String symc) {
		this.symc = symc;
	}

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }
}
