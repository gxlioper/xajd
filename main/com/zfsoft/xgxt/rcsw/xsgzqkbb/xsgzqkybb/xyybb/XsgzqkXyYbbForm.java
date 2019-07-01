package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.xyybb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 安徽农业大学
 * 学生工作情况学院月报表form.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkXyYbbForm extends ActionForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    private String id;
    private String xn;
    private String xq;
    private String yf;
    private String xydm;
    private String xymc;
    private String xsgzfzr;
    private String xsgzfzrmc;
    private String tbr;
    private String tbrmc;
    private String tbrq;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
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

    public String getXsgzfzr() {
        return xsgzfzr;
    }

    public void setXsgzfzr(String xsgzfzr) {
        this.xsgzfzr = xsgzfzr;
    }

    public String getXsgzfzrmc() {
        return xsgzfzrmc;
    }

    public void setXsgzfzrmc(String xsgzfzrmc) {
        this.xsgzfzrmc = xsgzfzrmc;
    }

    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr;
    }

    public String getTbrmc() {
        return tbrmc;
    }

    public void setTbrmc(String tbrmc) {
        this.tbrmc = tbrmc;
    }

    public String getTbrq() {
        return tbrq;
    }

    public void setTbrq(String tbrq) {
        this.tbrq = tbrq;
    }
}
