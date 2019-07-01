package com.zfsoft.xgxt.xyfd.fdswh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/6/24.
 */
public class FdsForm extends ActionForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;
    private String id;
    private String fdsmc;
    private String fdsdd;
    private String syksrq;
    private String syjsrq;
    private String sykssj;
    private String syjssj;
    private String yxzt;
    private String qkms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFdsmc() {
        return fdsmc;
    }

    public void setFdsmc(String fdsmc) {
        this.fdsmc = fdsmc;
    }

    public String getFdsdd() {
        return fdsdd;
    }

    public void setFdsdd(String fdsdd) {
        this.fdsdd = fdsdd;
    }

    public String getSyksrq() {
        return syksrq;
    }

    public void setSyksrq(String syksrq) {
        this.syksrq = syksrq;
    }

    public String getSyjsrq() {
        return syjsrq;
    }

    public void setSyjsrq(String syjsrq) {
        this.syjsrq = syjsrq;
    }

    public String getSykssj() {
        return sykssj;
    }

    public void setSykssj(String sykssj) {
        this.sykssj = sykssj;
    }

    public String getSyjssj() {
        return syjssj;
    }

    public void setSyjssj(String syjssj) {
        this.syjssj = syjssj;
    }

    public String getYxzt() {
        return yxzt;
    }

    public void setYxzt(String yxzt) {
        this.yxzt = yxzt;
    }

    public String getQkms() {
        return qkms;
    }

    public void setQkms(String qkms) {
        this.qkms = qkms;
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
}
