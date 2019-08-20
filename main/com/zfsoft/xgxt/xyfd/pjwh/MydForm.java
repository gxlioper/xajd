package com.zfsoft.xgxt.xyfd.pjwh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 满意度评价记录
 * Created by llf on 2019/8/17.
 */
public class MydForm extends ActionForm {
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    private String pjid;
    private String jlbh;
    private String pf;
    private String sfjj;
    private String xxpj;
    private String pjr;
    private String pjsj;
    private String lx;

    private String pjzt;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPjid() {
        return pjid;
    }

    public void setPjid(String pjid) {
        this.pjid = pjid;
    }

    public String getJlbh() {
        return jlbh;
    }

    public void setJlbh(String jlbh) {
        this.jlbh = jlbh;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getSfjj() {
        return sfjj;
    }

    public void setSfjj(String sfjj) {
        this.sfjj = sfjj;
    }

    public String getXxpj() {
        return xxpj;
    }

    public void setXxpj(String xxpj) {
        this.xxpj = xxpj;
    }

    public String getPjr() {
        return pjr;
    }

    public void setPjr(String pjr) {
        this.pjr = pjr;
    }

    public String getPjsj() {
        return pjsj;
    }

    public void setPjsj(String pjsj) {
        this.pjsj = pjsj;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getPjzt() {
        return pjzt;
    }

    public void setPjzt(String pjzt) {
        this.pjzt = pjzt;
    }
}
