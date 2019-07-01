package com.zfsoft.xgxt.szdw.fdydk;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class FdydkForm extends ActionForm {

    private String id;
    private String zgh;
    private String kcmc;
    private String kclbdm;
    private String kcsj;
    private String kcdd;
    private String ks;

    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    //自定义导出
    private ExportModel exportModel = new ExportModel();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getKclbdm() {
        return kclbdm;
    }

    public void setKclbdm(String kclbdm) {
        this.kclbdm = kclbdm;
    }

    public String getKcsj() {
        return kcsj;
    }

    public void setKcsj(String kcsj) {
        this.kcsj = kcsj;
    }

    public String getKcdd() {
        return kcdd;
    }

    public void setKcdd(String kcdd) {
        this.kcdd = kcdd;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
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

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }
}
