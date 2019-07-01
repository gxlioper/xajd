package com.zfsoft.xgxt.dycjgl.jcsz;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DydmwhForm extends ActionForm {
    private String xmdm;//项目代码
    private String xmmc;//项目名称
    private String cjhgfsx;//成绩合格分数线
    private String xmsm;//项目说明
    private String xsxh;//显示序号
    private String cxxmmc;//查询项目名称

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

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

    public String getXmdm() {
        return xmdm;
    }

    public void setXmdm(String xmdm) {
        this.xmdm = xmdm;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getCjhgfsx() {
        return cjhgfsx;
    }

    public void setCjhgfsx(String cjhgfsx) {
        this.cjhgfsx = cjhgfsx;
    }

    public String getXmsm() {
        return xmsm;
    }

    public void setXmsm(String xmsm) {
        this.xmsm = xmsm;
    }

    public String getXsxh() {
        return xsxh;
    }

    public void setXsxh(String xsxh) {
        this.xsxh = xsxh;
    }

    public String getCxxmmc() {
        return cxxmmc;
    }

    public void setCxxmmc(String cxxmmc) {
        this.cxxmmc = cxxmmc;
    }
}
