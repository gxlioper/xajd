package com.zfsoft.xgxt.zhdj.dyzlxz;

import org.apache.struts.action.ActionForm;
import xgxt.utils.Pages;

public class DyzlxzForm extends ActionForm {
    private String pk;
    private String filemc;
    private String filelx;
    private String filess;
    private String ss;
    private String lx;
    private String filepath;
    private String cxFilemc;
    private String type;
    private String xzfj;
    private Pages pages = new Pages();

    public String getXzfj() {
        return xzfj;
    }

    public void setXzfj(String xzfj) {
        this.xzfj = xzfj;
    }

    public String getCxFilemc() {
        return cxFilemc;
    }

    public void setCxFilemc(String cxFilemc) {
        this.cxFilemc = cxFilemc;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getFilemc() {
        return filemc;
    }

    public void setFilemc(String filemc) {
        this.filemc = filemc;
    }

    public String getFilelx() {
        return filelx;
    }

    public void setFilelx(String filelx) {
        this.filelx = filelx;
    }

    public String getFiless() {
        return filess;
    }

    public void setFiless(String filess) {
        this.filess = filess;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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
