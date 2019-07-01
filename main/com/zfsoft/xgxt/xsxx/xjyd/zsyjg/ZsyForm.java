package com.zfsoft.xgxt.xsxx.xjyd.zsyjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class ZsyForm extends ActionForm {
    private SearchModel searchModel = new SearchModel(); //高级查询
    private Pages pages = new Pages(); // 分页
    private ExportModel exportModel = new ExportModel(); //自定义导出
    private String type;

    //学号
    private String xh;
    //原班级代码
    private String ybjdm;
    //现班级代码
    private String xbjdm;
    //操作时间
    private String czsj;
    //原书院代码
    private String ysydm;
    //现书院代码
    private String xsydm;
    //是否已调整
    private String cz;

    private String id;
    //ids
    private String ids;

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
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

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getYbjdm() {
        return ybjdm;
    }

    public void setYbjdm(String ybjdm) {
        this.ybjdm = ybjdm;
    }

    public String getXbjdm() {
        return xbjdm;
    }

    public void setXbjdm(String xbjdm) {
        this.xbjdm = xbjdm;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }

    public String getYsydm() {
        return ysydm;
    }

    public void setYsydm(String ysydm) {
        this.ysydm = ysydm;
    }

    public String getXsydm() {
        return xsydm;
    }

    public void setXsydm(String xsydm) {
        this.xsydm = xsydm;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
