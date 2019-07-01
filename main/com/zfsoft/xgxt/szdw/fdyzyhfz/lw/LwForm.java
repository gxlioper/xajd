package com.zfsoft.xgxt.szdw.fdyzyhfz.lw;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class LwForm extends BaseForm{
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private String type;
    private String id   ;
    private String tm   ;
    private String fbsj ;
    private String kwmc ;
    private String kh   ;
    private String qklb ;
    private String qklbmc;
    private String cdjs ;
    private String cdjsmc;
    private String bz   ;
    private String zgh  ;
    private String filepath;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

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

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getFbsj() {
        return fbsj;
    }

    public void setFbsj(String fbsj) {
        this.fbsj = fbsj;
    }

    public String getKwmc() {
        return kwmc;
    }

    public void setKwmc(String kwmc) {
        this.kwmc = kwmc;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public String getQklb() {
        return qklb;
    }

    public void setQklb(String qklb) {
        this.qklb = qklb;
    }

    public String getQklbmc() {
        return qklbmc;
    }

    public void setQklbmc(String qklbmc) {
        this.qklbmc = qklbmc;
    }

    public String getCdjs() {
        return cdjs;
    }

    public void setCdjs(String cdjs) {
        this.cdjs = cdjs;
    }

    public String getCdjsmc() {
        return cdjsmc;
    }

    public void setCdjsmc(String cdjsmc) {
        this.cdjsmc = cdjsmc;
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
