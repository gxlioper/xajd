package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class ZtbhSqForm extends ActionForm {
    private String hdmc;
    private String hdzt;
    private String hdrq;
    private String sqid;
    private String fzls;
    private String fzlslxdh;
    private String hdfzr;
    private String hdfzrlxdh;
    private String fj;
    private String filepath;
    private String bhmd;
    private String bhyc;
    private String splc;	//审批流程
    private String shzt;	//审核状态
    private String shztmc;
    private String sqr;
    private String sqsj;

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    //分页
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    //学生信息
    private String xh;
    private String xm;
    private String lxdh;
    private String xymc;
    private String zymc;

    //班级信息
    private String xydm;
    private String zydm;
    private String bjdm;
    private String bjmc;

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getBhmd() {
        return bhmd;
    }

    public void setBhmd(String bhmd) {
        this.bhmd = bhmd;
    }

    public String getBhyc() {
        return bhyc;
    }

    public void setBhyc(String bhyc) {
        this.bhyc = bhyc;
    }

    public String getFzlslxdh() {
        return fzlslxdh;
    }

    public void setFzlslxdh(String fzlslxdh) {
        this.fzlslxdh = fzlslxdh;
    }

    public String getHdfzrlxdh() {
        return hdfzrlxdh;
    }

    public void setHdfzrlxdh(String hdfzrlxdh) {
        this.hdfzrlxdh = hdfzrlxdh;
    }

    public String getFzls() {
        return fzls;
    }

    public void setFzls(String fzls) {
        this.fzls = fzls;
    }

    public String getHdfzr() {
        return hdfzr;
    }

    public void setHdfzr(String hdfzr) {
        this.hdfzr = hdfzr;
    }
    public String getZydm() {
        return zydm;
    }

    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
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

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getHdmc() {
        return hdmc;
    }

    public void setHdmc(String hdmc) {
        this.hdmc = hdmc;
    }

    public String getHdzt() {
        return hdzt;
    }

    public void setHdzt(String hdzt) {
        this.hdzt = hdzt;
    }

    public String getHdrq() {
        return hdrq;
    }

    public void setHdrq(String hdrq) {
        this.hdrq = hdrq;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }

    public String getBjdm() {
        return bjdm;
    }

    public void setBjdm(String bjdm) {
        this.bjdm = bjdm;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getShztmc() {
        return shztmc;
    }

    public void setShztmc(String shztmc) {
        this.shztmc = shztmc;
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
}
