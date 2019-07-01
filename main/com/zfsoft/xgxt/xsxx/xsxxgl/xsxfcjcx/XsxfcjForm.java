package com.zfsoft.xgxt.xsxx.xsxxgl.xsxfcjcx;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：学分成绩
 * @作者：tanhao
 * @日期：
 */
public class XsxfcjForm extends BaseForm{
    private SearchModel searchModel = new SearchModel(); //高级查询
    private Pages pages = new Pages(); // 分页
    private ExportModel exportModel = new ExportModel(); //自定义导出
    private String type;

    private String xn;
    private String xh;
    private String xf;
    private String xfcj;
    private String gk;
    private String sfgk;
    private String xq;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    @Override
    public String getXh() {
        return xh;
    }

    @Override
    public void setXh(String xh) {
        this.xh = xh;
    }


    public String getXf() {
        return xf;
    }

    public void setXf(String xf) {
        this.xf = xf;
    }

    public String getXfcj() {
        return xfcj;
    }

    public void setXfcj(String xfcj) {
        this.xfcj = xfcj;
    }

    public String getGk() {
        return gk;
    }

    public void setGk(String gk) {
        this.gk = gk;
    }

    public String getSfgk() {
        return sfgk;
    }

    public void setSfgk(String sfgk) {
        this.sfgk = sfgk;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }
}
