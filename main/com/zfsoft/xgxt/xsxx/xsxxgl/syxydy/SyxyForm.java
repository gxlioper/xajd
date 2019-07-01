package com.zfsoft.xgxt.xsxx.xsxxgl.syxydy;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * 书院学院对应关系
 */
public class SyxyForm extends BaseForm {
    private SearchModel searchModel = new SearchModel(); //高级查询
    private Pages pages = new Pages(); // 分页
    private ExportModel exportModel = new ExportModel(); //自定义导出
    private String xydm;
    private String xymc;
    private String sydm;
    private String symc;

    private String type;

    @Override
    public String getXydm() {
        return xydm;
    }

    @Override
    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    @Override
    public String getXymc() {
        return xymc;
    }

    @Override
    public void setXymc(String xymc) {
        this.xymc = xymc;
    }

    public String getSydm() {
        return sydm;
    }

    public void setSydm(String sydm) {
        this.sydm = sydm;
    }

    public String getSymc() {
        return symc;
    }

    public void setSymc(String symc) {
        this.symc = symc;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }
}
