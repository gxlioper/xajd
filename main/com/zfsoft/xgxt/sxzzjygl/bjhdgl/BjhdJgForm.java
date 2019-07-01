package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class BjhdJgForm extends ActionForm {
    private String hdmc;
    private String hdzt;
    private String hdrq;
    private String jgid;
    private String fzls;
    private String fzlslxdh;
    private String hdfzr;
    private String hdfzrlxdh;
    private String fj;
    private String filepath;
    private String splc;	//审批流程
    private String shzt;	//审核状态
    private String shztmc;
    private String lrr;
    private String lrsj;
    private String hdys;//活动预算
    private String hdysyjmx;//活动预算依据及明细
    private String hdgy;//活动概要
    private String hdssfa ;//活动实施方案
    private String sfjp;//是否精品
    private String lcywid;
    private String sjly;
    private String sfjpmc;
    private String[] jgids;

    //分页
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;
    private ExportModel exportModel = new ExportModel();

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

    public String[] getJgids() {
        return jgids;
    }

    public void setJgids(String[] jgids) {
        this.jgids = jgids;
    }

    public String getSfjpmc() {
        return sfjpmc;
    }

    public void setSfjpmc(String sfjpmc) {
        this.sfjpmc = sfjpmc;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String getLcywid() {
        return lcywid;
    }

    public void setLcywid(String lcywid) {
        this.lcywid = lcywid;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getSfjp() {
        return sfjp;
    }

    public void setSfjp(String sfjp) {
        this.sfjp = sfjp;
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

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getZydm() {
        return zydm;
    }

    public void setZydm(String zydm) {
        this.zydm = zydm;
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
}
