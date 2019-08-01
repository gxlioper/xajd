package com.zfsoft.xgxt.xszz.xfjm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:
 * @类功能描述:
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/3 10:50
 */
public class XfjmForm extends ActionForm {
    private Pages pages = new Pages();
    private FormFile file;
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    //主键
    private String id;

    //学号
    private String xh;

    //资助学年
    private String xn;

    //申请理由
    private String sqly;

    //申请附件(预留字段)
    private String sqfj;

    //申请时间
    private String sqsj;

    //审核流程id
    private String shlc;

    //学费减免率(预留)
    private String xfjml;

    //学费减免金额
    private String xfjmje;

    //审批流程id
    private String splc;

    private String sqzt;

    private String shzt;

    private String status;

    private String shjg;//审核流状态

    private String thgw;//退回岗位

    private String shyj;//审核意见

    private String xtgwid;//审核岗位id

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String getXtgwid() {
        return xtgwid;
    }

    public void setXtgwid(String xtgwid) {
        this.xtgwid = xtgwid;
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

    public String getThgw() {
        return thgw;
    }

    public void setThgw(String thgw) {
        this.thgw = thgw;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getSqzt() {
        return sqzt;
    }

    public void setSqzt(String sqzt) {
        this.sqzt = sqzt;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSqly() {
        return sqly;
    }

    public void setSqly(String sqly) {
        this.sqly = sqly;
    }

    public String getSqfj() {
        return sqfj;
    }

    public void setSqfj(String sqfj) {
        this.sqfj = sqfj;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getShlc() {
        return shlc;
    }

    public void setShlc(String shlc) {
        this.shlc = shlc;
    }

    public String getXfjml() {
        return xfjml;
    }

    public void setXfjml(String xfjml) {
        this.xfjml = xfjml;
    }

    public String getXfjmje() {
        return xfjmje;
    }

    public void setXfjmje(String xfjmje) {
        this.xfjmje = xfjmje;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }
}
