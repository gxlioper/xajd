package com.zfsoft.xgxt.sxzzjygl.bjwhhdzq;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class BjwhhdForm extends ActionForm {
    private String hdlx;
    private String jgid;
    private String hdmc;
    private String hdrq;
    private String hdmcbjmc;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    public String getHdmcbjmc() {
        return hdmcbjmc;
    }

    public void setHdmcbjmc(String hdmcbjmc) {
        this.hdmcbjmc = hdmcbjmc;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getHdmc() {
        return hdmc;
    }

    public void setHdmc(String hdmc) {
        this.hdmc = hdmc;
    }

    public String getHdrq() {
        return hdrq;
    }

    public void setHdrq(String hdrq) {
        this.hdrq = hdrq;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHdlx() {
        return hdlx;
    }

    public void setHdlx(String hdlx) {
        this.hdlx = hdlx;
    }
}
