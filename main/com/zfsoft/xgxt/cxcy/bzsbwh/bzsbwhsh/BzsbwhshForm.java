package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:创新创业补助申报审核
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-06 10:37
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BzsbwhshForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String sqid;
    private String xh;
    private String xn;
    private String xq;
    private String xmmc;//项目名称
    private String bzje;//补助金额
    private String sqly;//申请理由
    private String tbr;//填报人
    private String sqsj;//申请时间
    private String fj;
    private String shzt;//审核状态
    private String splc;//审批流程

    private String shid;
    private String thgw;
    private String gwid;
    private String shlc;
    private String shjg;
    private String shyj;
    private String[] ids;
    private String[] gwids;
    private String[] xhs;
    private String[] splcs;


    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    //自定义导出
    private ExportModel exportModel = new ExportModel();

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

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getBzje() {
        return bzje;
    }

    public void setBzje(String bzje) {
        this.bzje = bzje;
    }

    public String getSqly() {
        return sqly;
    }

    public void setSqly(String sqly) {
        this.sqly = sqly;
    }

    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
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

    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getShlc() {
        return shlc;
    }

    public void setShlc(String shlc) {
        this.shlc = shlc;
    }

    public String getShjg() {
        return shjg;
    }

    public void setShjg(String shjg) {
        this.shjg = shjg;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
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

    public String[] getSplcs() {
        return splcs;
    }

    public void setSplcs(String[] splcs) {
        this.splcs = splcs;
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

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }
}
