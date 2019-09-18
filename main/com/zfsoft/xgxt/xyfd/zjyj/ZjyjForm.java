package com.zfsoft.xgxt.xyfd.zjyj;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/9/9.
 */
public class ZjyjForm extends ActionForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    private String zjid;
    private String albh;//案例编号
    private String zjyy;//转介原因
    private String zjr;
    private String xxxx;//详细信息
    private String qrsj;
    private String qrr;
    private String zjsj;

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

    public String getZjid() {
        return zjid;
    }

    public void setZjid(String zjid) {
        this.zjid = zjid;
    }

    public String getAlbh() {
        return albh;
    }

    public void setAlbh(String albh) {
        this.albh = albh;
    }

    public String getZjyy() {
        return zjyy;
    }

    public void setZjyy(String zjyy) {
        this.zjyy = zjyy;
    }

    public String getZjr() {
        return zjr;
    }

    public void setZjr(String zjr) {
        this.zjr = zjr;
    }

    public String getXxxx() {
        return xxxx;
    }

    public void setXxxx(String xxxx) {
        this.xxxx = xxxx;
    }

    public String getQrsj() {
        return qrsj;
    }

    public void setQrsj(String qrsj) {
        this.qrsj = qrsj;
    }

    public String getQrr() {
        return qrr;
    }

    public void setQrr(String qrr) {
        this.qrr = qrr;
    }

    public String getZjsj() {
        return zjsj;
    }

    public void setZjsj(String zjsj) {
        this.zjsj = zjsj;
    }
}
