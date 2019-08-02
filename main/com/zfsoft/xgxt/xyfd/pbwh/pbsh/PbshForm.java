package com.zfsoft.xgxt.xyfd.pbwh.pbsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/7/31.
 */
public class PbshForm extends ActionForm {
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    //自定义导出
    private ExportModel exportModel = new ExportModel();
    private String type;

    private String sqid;
    private String xh;
    private String xsgbrz;
    private String lxdh;
    private String dzyx;
    private String fdkm;//辅导科目
    private String fds;//辅导室
    private String mon;
    private String tues;
    private String wed;
    private String thur;
    private String fri;
    private String sat;
    private String sun;
    private String fjid;
    private String splc;//审批流程
    private String shzt;//审核状态
    private String lrr;
    private String lrsj;

    private String ywid;
    private String shsj;
    private String shr;
    private String shyj;
    private String shlc;
    private String gwid;
    private String shztmc;
    private String shid;
    private String thgw;//岗位退回
    private String shjg;
    private String yxgs;
    private String[] id;
    private String[] gwids;
    private String[] xhs;


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

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }


    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXsgbrz() {
        return xsgbrz;
    }

    public void setXsgbrz(String xsgbrz) {
        this.xsgbrz = xsgbrz;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getDzyx() {
        return dzyx;
    }

    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    public String getFdkm() {
        return fdkm;
    }

    public void setFdkm(String fdkm) {
        this.fdkm = fdkm;
    }

    public String getFds() {
        return fds;
    }

    public void setFds(String fds) {
        this.fds = fds;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTues() {
        return tues;
    }

    public void setTues(String tues) {
        this.tues = tues;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThur() {
        return thur;
    }

    public void setThur(String thur) {
        this.thur = thur;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid;
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

    public String getYwid() {
        return ywid;
    }

    public void setYwid(String ywid) {
        this.ywid = ywid;
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

    public String getShlc() {
        return shlc;
    }

    public void setShlc(String shlc) {
        this.shlc = shlc;
    }

    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getShztmc() {
        return shztmc;
    }

    public void setShztmc(String shztmc) {
        this.shztmc = shztmc;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
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

    public String getYxgs() {
        return yxgs;
    }

    public void setYxgs(String yxgs) {
        this.yxgs = yxgs;
    }

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    public String[] getGwids() {
        return gwids;
    }

    public void setGwids(String[] gwids) {
        this.gwids = gwids;
    }

    public String[] getXhs() {
        return xhs;
    }

    public void setXhs(String[] xhs) {
        this.xhs = xhs;
    }
}
