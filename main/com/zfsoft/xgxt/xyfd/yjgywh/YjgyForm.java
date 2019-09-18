package com.zfsoft.xgxt.xyfd.yjgywh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/9/17.
 */
public class YjgyForm extends ActionForm{
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    private String id;
    private String xn;
    private String xq;
    private String xh;
    private String xm;
    private String zjmb;//转介目标
    private String jsh;//转介教师
    private String zcyy;//转介原因
    private String zjr;
    private String zjsj;
    private String yjyy;//预警原因

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

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZjmb() {
        return zjmb;
    }

    public void setZjmb(String zjmb) {
        this.zjmb = zjmb;
    }

    public String getJsh() {
        return jsh;
    }

    public void setJsh(String jsh) {
        this.jsh = jsh;
    }

    public String getZcyy() {
        return zcyy;
    }

    public void setZcyy(String zcyy) {
        this.zcyy = zcyy;
    }

    public String getZjr() {
        return zjr;
    }

    public void setZjr(String zjr) {
        this.zjr = zjr;
    }

    public String getZjsj() {
        return zjsj;
    }

    public void setZjsj(String zjsj) {
        this.zjsj = zjsj;
    }

    public String getYjyy() {
        return yjyy;
    }

    public void setYjyy(String yjyy) {
        this.yjyy = yjyy;
    }
}
