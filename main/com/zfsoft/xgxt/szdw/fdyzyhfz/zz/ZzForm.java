package com.zfsoft.xgxt.szdw.fdyzyhfz.zz;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class ZzForm extends BaseForm{
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private String type;
    private String id    ;
    private String mc    ;
    private String sh    ;
    private String cbsj  ;
    private String cbsmc ;
    private String cdjs  ;
    private String cdrwl ;
    private String bz    ;
    private String zgh   ;

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    public String getCbsj() {
        return cbsj;
    }

    public void setCbsj(String cbsj) {
        this.cbsj = cbsj;
    }

    public String getCbsmc() {
        return cbsmc;
    }

    public void setCbsmc(String cbsmc) {
        this.cbsmc = cbsmc;
    }

    public String getCdjs() {
        return cdjs;
    }

    public void setCdjs(String cdjs) {
        this.cdjs = cdjs;
    }

    public String getCdrwl() {
        return cdrwl;
    }

    public void setCdrwl(String cdrwl) {
        this.cdrwl = cdrwl;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }
}
