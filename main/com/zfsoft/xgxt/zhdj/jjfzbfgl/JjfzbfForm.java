package com.zfsoft.xgxt.zhdj.jjfzbfgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class JjfzbfForm extends ActionForm {
    private String bfid;
    private String rdjjfzxh;
    private String bfdxxh;
    private String jlsj;
    private String zhxgsj;
    private String rdjjfzxm; //积极分子姓名
    private String bfdxxm; //帮扶对象姓名
    private String ssqk; //实施情况
    private String bfjhcs;//帮扶计划措施
    private String bfjhmb;//帮扶计划目标
    private String bfjhnr;//帮扶计划内容
    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    private String xh;
    private String xm;
    private String xb;
    private String csrq;
    private String lxdh;
    private String sjhm;
    private String qqhm;
    private String ssld;
    private String ssbh;
    private String fdyxm;
    private String xydm;
    private String xymc;
    private String zydm;
    private String bjdm;
    private String zymc;
    private String bjmc;
    private String[] dels;
    private String[] zysss;
    private String[] hbs;
    private ExportModel exportModel = new ExportModel();

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String[] getHbs() {
        return hbs;
    }

    public void setHbs(String[] hbs) {
        this.hbs = hbs;
    }

    public String[] getZysss() {
        return zysss;
    }

    public void setZysss(String[] zysss) {
        this.zysss = zysss;
    }



    public String[] getDels() {
        return dels;
    }

    public void setDels(String[] dels) {
        this.dels = dels;
    }

    public String getBfjhcs() {
        return bfjhcs;
    }

    public void setBfjhcs(String bfjhcs) {
        this.bfjhcs = bfjhcs;
    }

    public String getBfjhmb() {
        return bfjhmb;
    }

    public void setBfjhmb(String bfjhmb) {
        this.bfjhmb = bfjhmb;
    }

    public String getBfjhnr() {
        return bfjhnr;
    }

    public void setBfjhnr(String bfjhnr) {
        this.bfjhnr = bfjhnr;
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

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getQqhm() {
        return qqhm;
    }

    public void setQqhm(String qqhm) {
        this.qqhm = qqhm;
    }

    public String getSsld() {
        return ssld;
    }

    public void setSsld(String ssld) {
        this.ssld = ssld;
    }

    public String getSsbh() {
        return ssbh;
    }

    public void setSsbh(String ssbh) {
        this.ssbh = ssbh;
    }

    public String getFdyxm() {
        return fdyxm;
    }

    public void setFdyxm(String fdyxm) {
        this.fdyxm = fdyxm;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc;
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

    public String getBfid() {
        return bfid;
    }

    public void setBfid(String bfid) {
        this.bfid = bfid;
    }

    public String getRdjjfzxh() {
        return rdjjfzxh;
    }

    public void setRdjjfzxh(String rdjjfzxh) {
        this.rdjjfzxh = rdjjfzxh;
    }

    public String getBfdxxh() {
        return bfdxxh;
    }

    public void setBfdxxh(String bfdxxh) {
        this.bfdxxh = bfdxxh;
    }

    public String getJlsj() {
        return jlsj;
    }

    public void setJlsj(String jlsj) {
        this.jlsj = jlsj;
    }

    public String getZhxgsj() {
        return zhxgsj;
    }

    public void setZhxgsj(String zhxgsj) {
        this.zhxgsj = zhxgsj;
    }

    public String getRdjjfzxm() {
        return rdjjfzxm;
    }

    public void setRdjjfzxm(String rdjjfzxm) {
        this.rdjjfzxm = rdjjfzxm;
    }

    public String getBfdxxm() {
        return bfdxxm;
    }

    public void setBfdxxm(String bfdxxm) {
        this.bfdxxm = bfdxxm;
    }

    public String getSsqk() {
        return ssqk;
    }

    public void setSsqk(String ssqk) {
        this.ssqk = ssqk;
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
