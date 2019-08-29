package com.zfsoft.xgxt.xyfd.xyzyzxwh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/8/14.
 */
public class XyzyzxForm extends ActionForm {
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    private String zxid;
    private String xh;
    private String fdjs;
    private String zxyy;
    private String gks;
    private String jtyy;
    private String fdrq;
    private String fdsj;
    private String fddd;
    private String jtjc;
    private String zxzt;
    private String jtwt;//未解决，具体问题

    private String lrr;
    private String lrsj;

    private String xn;
    private String xq;



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

    public String getZxid() {
        return zxid;
    }

    public void setZxid(String zxid) {
        this.zxid = zxid;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getFdjs() {
        return fdjs;
    }

    public void setFdjs(String fdjs) {
        this.fdjs = fdjs;
    }

    public String getZxyy() {
        return zxyy;
    }

    public void setZxyy(String zxyy) {
        this.zxyy = zxyy;
    }

    public String getGks() {
        return gks;
    }

    public void setGks(String gks) {
        this.gks = gks;
    }

    public String getJtyy() {
        return jtyy;
    }

    public void setJtyy(String jtyy) {
        this.jtyy = jtyy;
    }

    public String getFdrq() {
        return fdrq;
    }

    public void setFdrq(String fdrq) {
        this.fdrq = fdrq;
    }

    public String getFdsj() {
        return fdsj;
    }

    public void setFdsj(String fdsj) {
        this.fdsj = fdsj;
    }

    public String getFddd() {
        return fddd;
    }

    public void setFddd(String fddd) {
        this.fddd = fddd;
    }

    public String getJtjc() {
        return jtjc;
    }

    public void setJtjc(String jtjc) {
        this.jtjc = jtjc;
    }

    public String getZxzt() {
        return zxzt;
    }

    public void setZxzt(String zxzt) {
        this.zxzt = zxzt;
    }

    public String getJtwt() {
        return jtwt;
    }

    public void setJtwt(String jtwt) {
        this.jtwt = jtwt;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
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
}
