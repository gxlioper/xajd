package com.zfsoft.xgxt.sxzzjygl.bzrztbh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import java.util.List;

public class ZtbhJgForm extends ActionForm {
    private String jgid;
    private String hdmc;
    private String hdzt;
    private String hdrq;
    private String hdfzr;
    private String hdfzrlxdh;
    private String fzls;
    private String fzlslxdh;
    private String bhmd;
    private String bhyc;
    private String lrsj;
    private String lrr;
    private String fj;
    private String lcywid;
    private String sfsfx;
    private String sjly;
    private String sfsfxmc;
    private String[] jgids;
    private String ydrs;
    private String sdrs;
    private String qqrs;
    private String xn;
    private String xq;
    private String bhsj;
    private String dd;
    private String bzrsfcj;

    public String getBhsj() {
        return bhsj;
    }

    public void setBhsj(String bhsj) {
        this.bhsj = bhsj;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getBzrsfcj() {
        return bzrsfcj;
    }

    public void setBzrsfcj(String bzrsfcj) {
        this.bzrsfcj = bzrsfcj;
    }

    private List<CjbJxxForm> bjxxs = new AutoArrayList(CjbJxxForm.class);

    //分页
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private String type;

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

    public List<CjbJxxForm> getBjxxs() {
        return bjxxs;
    }

    public void setBjxxs(List<CjbJxxForm> bjxxs) {
        this.bjxxs = bjxxs;
    }

    public String getYdrs() {
        return ydrs;
    }

    public void setYdrs(String ydrs) {
        this.ydrs = ydrs;
    }

    public String getSdrs() {
        return sdrs;
    }

    public void setSdrs(String sdrs) {
        this.sdrs = sdrs;
    }

    public String getQqrs() {
        return qqrs;
    }

    public void setQqrs(String qqrs) {
        this.qqrs = qqrs;
    }

    public String[] getJgids() {
        return jgids;
    }

    public void setJgids(String[] jgids) {
        this.jgids = jgids;
    }

    public String getSfsfxmc() {
        return sfsfxmc;
    }

    public void setSfsfxmc(String sfsfxmc) {
        this.sfsfxmc = sfsfxmc;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String getJgid() {
        return jgid;
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

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
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

    public String getBjdm() {
        return bjdm;
    }

    public void setBjdm(String bjdm) {
        this.bjdm = bjdm;
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

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getLcywid() {
        return lcywid;
    }

    public void setLcywid(String lcywid) {
        this.lcywid = lcywid;
    }

    public String getSfsfx() {
        return sfsfx;
    }

    public void setSfsfx(String sfsfx) {
        this.sfsfx = sfsfx;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
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
