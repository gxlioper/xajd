package com.zfsoft.xgxt.zhdj.djyj;

import org.apache.struts.action.ActionForm;
import xgxt.utils.Pages;

public class ZbhjyjForm extends ActionForm {
    private String dzbhjid;
    private String dzbid;
    private String dzbmc;
    private String dzbsj;
    private String sjxm;
    private String sjlxdh;
    private String clsj;
    private String hjsj;
    private String yjsj;
    private String type;
    private Pages pages = new Pages();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDzbhjid() {
        return dzbhjid;
    }

    public void setDzbhjid(String dzbhjid) {
        this.dzbhjid = dzbhjid;
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

    public String getDzbsj() {
        return dzbsj;
    }

    public void setDzbsj(String dzbsj) {
        this.dzbsj = dzbsj;
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

    public String getClsj() {
        return clsj;
    }

    public void setClsj(String clsj) {
        this.clsj = clsj;
    }

    public String getHjsj() {
        return hjsj;
    }

    public void setHjsj(String hjsj) {
        this.hjsj = hjsj;
    }

    public String getYjsj() {
        return yjsj;
    }

    public void setYjsj(String yjsj) {
        this.yjsj = yjsj;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }
}
