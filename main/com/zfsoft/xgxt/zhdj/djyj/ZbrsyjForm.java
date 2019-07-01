package com.zfsoft.xgxt.zhdj.djyj;

import org.apache.struts.action.ActionForm;
import xgxt.utils.Pages;

public class ZbrsyjForm extends ActionForm{
    private String zbrs;
    private String dzbid;
    private String dzbmc;
    private String sjxm;
    private String sjlxdh;
    private String ccrs;
    private String type;
    private Pages pages = new Pages();

    public String getZbrs() {
        return zbrs;
    }

    public void setZbrs(String zbrs) {
        this.zbrs = zbrs;
    }

    public String getDzbid() {
        return dzbid;
    }

    public void setDzbid(String dzbid) {
        this.dzbid = dzbid;
    }

    public String getDzbmc() {
        return dzbmc;
    }

    public void setDzbmc(String dzbmc) {
        this.dzbmc = dzbmc;
    }

    public String getSjxm() {
        return sjxm;
    }

    public void setSjxm(String sjxm) {
        this.sjxm = sjxm;
    }

    public String getSjlxdh() {
        return sjlxdh;
    }

    public void setSjlxdh(String sjlxdh) {
        this.sjlxdh = sjlxdh;
    }

    public String getCcrs() {
        return ccrs;
    }

    public void setCcrs(String ccrs) {
        this.ccrs = ccrs;
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
}
