package com.zfsoft.xgxt.hdgl.jzjh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JzjhForm extends ActionForm {

    private Pages pages = new Pages();
    // 高级查询
    SearchModel searchModel = new SearchModel();
    //自定义导出
    private ExportModel exportModel = new ExportModel();

    private String type;
    private String jzjhid;
    private String jzzt;
    private String jzzjr;
    private String jzndsj;
    private String jznddd;
    private String jzzbdw;
    private String jzzjrjs;
    private String lrr;

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
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

    public String getJzjhid() {
        return jzjhid;
    }

    public void setJzjhid(String jzjhid) {
        this.jzjhid = jzjhid;
    }

    public String getJzzt() {
        return jzzt;
    }

    public void setJzzt(String jzzt) {
        this.jzzt = jzzt;
    }

    public String getJzzjr() {
        return jzzjr;
    }

    public void setJzzjr(String jzzjr) {
        this.jzzjr = jzzjr;
    }

    public String getJzndsj() {
        return jzndsj;
    }

    public void setJzndsj(String jzndsj) {
        this.jzndsj = jzndsj;
    }

    public String getJznddd() {
        return jznddd;
    }

    public void setJznddd(String jznddd) {
        this.jznddd = jznddd;
    }

    public String getJzzbdw() {
        return jzzbdw;
    }

    public void setJzzbdw(String jzzbdw) {
        this.jzzbdw = jzzbdw;
    }

    public String getJzzjrjs() {
        return jzzjrjs;
    }

    public void setJzzjrjs(String jzzjrjs) {
        this.jzzjrjs = jzzjrjs;
    }
}
