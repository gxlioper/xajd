package com.zfsoft.xgxt.zhdj.djyj;

import org.apache.struts.action.ActionForm;
import xgxt.utils.Pages;

public class ZzyjForm extends ActionForm {
    private String xh;
    private String xm;
    private String zymc;
    private String bjmc;
    private String lxdh;
    private String dzbmc;
    private String zzrq;
    private String ccts;
    private String rdrq;
    private String type;
    private Pages pages = new Pages();

    public String getRdrq() {
        return rdrq;
    }

    public void setRdrq(String rdrq) {
        this.rdrq = rdrq;
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

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getDzbmc() {
        return dzbmc;
    }

    public void setDzbmc(String dzbmc) {
        this.dzbmc = dzbmc;
    }

    public String getZzrq() {
        return zzrq;
    }

    public void setZzrq(String zzrq) {
        this.zzrq = zzrq;
    }

    public String getCcts() {
        return ccts;
    }

    public void setCcts(String ccts) {
        this.ccts = ccts;
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
