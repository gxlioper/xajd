package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class BjhdShForm extends ActionForm {
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
    private String splc;	//审批流程
    private String shzt;	//审核状态
    private String shztmc;
    private String sqr;
    private String sqsj;
    private String hdys;//活动预算
    private String hdysyjmx;//活动预算依据及明细
    private String hdgy;//活动概要
    private String hdssfa ;//活动实施方案
    private String bjdm;
    private String bjmc;
    private String shlx;
    private String shid;//审核id
    private String gwid;//岗位id
    private String shsj;	//审核时间
    private String shr;	//审核人
    private String shyj;	//审核意见
    private String thgw;	//岗位退回
    private String shjg;	//审核结果
    private String[] sqids;	//申请id数组，用于批量审核
    private String[] gwids;	//岗位id数组，用于批量审核
    private String[] xhs;

    public String[] getXhs() {
        return xhs;
    }

    public void setXhs(String[] xhs) {
        this.xhs = xhs;
    }

    //分页
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

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

    public String getFzls() {
        return fzls;
    }

    public void setFzls(String fzls) {
        this.fzls = fzls;
    }

    public String getFzlslxdh() {
        return fzlslxdh;
    }

    public void setFzlslxdh(String fzlslxdh) {
        this.fzlslxdh = fzlslxdh;
    }

    public String getHdfzr() {
        return hdfzr;
    }

    public void setHdfzr(String hdfzr) {
        this.hdfzr = hdfzr;
    }

    public String getHdfzrlxdh() {
        return hdfzrlxdh;
    }

    public void setHdfzrlxdh(String hdfzrlxdh) {
        this.hdfzrlxdh = hdfzrlxdh;
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

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getHdys() {
        return hdys;
    }

    public void setHdys(String hdys) {
        this.hdys = hdys;
    }

    public String getHdysyjmx() {
        return hdysyjmx;
    }

    public void setHdysyjmx(String hdysyjmx) {
        this.hdysyjmx = hdysyjmx;
    }

    public String getHdgy() {
        return hdgy;
    }

    public void setHdgy(String hdgy) {
        this.hdgy = hdgy;
    }

    public String getHdssfa() {
        return hdssfa;
    }

    public void setHdssfa(String hdssfa) {
        this.hdssfa = hdssfa;
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

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getShsj() {
        return shsj;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getThgw() {
        return thgw;
    }

    public void setThgw(String thgw) {
        this.thgw = thgw;
    }

    public String getShjg() {
        return shjg;
    }

    public void setShjg(String shjg) {
        this.shjg = shjg;
    }

    public String[] getSqids() {
        return sqids;
    }

    public void setSqids(String[] sqids) {
        this.sqids = sqids;
    }

    public String[] getGwids() {
        return gwids;
    }

    public void setGwids(String[] gwids) {
        this.gwids = gwids;
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
