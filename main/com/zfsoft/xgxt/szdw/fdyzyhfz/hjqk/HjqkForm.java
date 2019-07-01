package com.zfsoft.xgxt.szdw.fdyzyhfz.hjqk;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class HjqkForm extends BaseForm{
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private String type;
    private String id       ;
    private String jxmc     ;
    private String hjsj     ;
    private String jldj     ;
    private String jldjmc;
    private String bjdw     ;
    private String filepath ;
    private String pm       ;
    private String pmmc;
    private String bz;
    private String zgh      ;

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

    public String getJxmc() {
        return jxmc;
    }

    public void setJxmc(String jxmc) {
        this.jxmc = jxmc;
    }

    public String getHjsj() {
        return hjsj;
    }

    public void setHjsj(String hjsj) {
        this.hjsj = hjsj;
    }

    public String getJldj() {
        return jldj;
    }

    public void setJldj(String jldj) {
        this.jldj = jldj;
    }

    public String getJldjmc() {
        return jldjmc;
    }

    public void setJldjmc(String jldjmc) {
        this.jldjmc = jldjmc;
    }

    public String getBjdw() {
        return bjdw;
    }

    public void setBjdw(String bjdw) {
        this.bjdw = bjdw;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getPmmc() {
        return pmmc;
    }

    public void setPmmc(String pmmc) {
        this.pmmc = pmmc;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }
}
