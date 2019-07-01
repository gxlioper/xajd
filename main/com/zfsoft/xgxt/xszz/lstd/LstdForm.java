package com.zfsoft.xgxt.xszz.lstd;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：绿色通道申请form
 * @作者：WANCHEN
 * @日期：2018-09-25
 */
public class LstdForm extends BaseForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    private String sqid;//申请id
    private String xh;//学号
    private String xq;//学期
    private String xn;//学年
    private String hjfs;//缓交方式
    private String dkje;//贷款金额
    private String jtfyje;//交通费用金额
    private String sqhjje;//申请缓交金额
    private String sqsj;//申请时间
    private String sqly;//申请理由
    private String filepath;//附件
    private String shlc;//审批流程
    private String shzt;//审核状态
    private String shyj;//审核意见
    private String type;
    private String zybj;//专业班级
    private String zybjmc;
    private String sydm;//书院
    private String symc;
    private String shztmc;
    private String shid;//审核id
    private String gwid;//岗位id

    /** 批量审核用**/
    private String[] gwids;
    private String[] xhs;
    private String[] shlcs;
    private String[] id;
    /** 批量审核用**/
    private String syshyj;//书院审核意见
    private String xxshyj;//学校审核意见

    //基础设置参数
    private String sqkssj;
    private String sqjssj;
    private String splc;
    //基础设置参数

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }

    public String getSqkssj() {
        return sqkssj;
    }

    public void setSqkssj(String sqkssj) {
        this.sqkssj = sqkssj;
    }

    public String getSqjssj() {
        return sqjssj;
    }

    public void setSqjssj(String sqjssj) {
        this.sqjssj = sqjssj;
    }

    public String getSyshyj() {
        return syshyj;
    }

    public void setSyshyj(String syshyj) {
        this.syshyj = syshyj;
    }

    public String getXxshyj() {
        return xxshyj;
    }

    public void setXxshyj(String xxshyj) {
        this.xxshyj = xxshyj;
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

    public String[] getShlcs() {
        return shlcs;
    }

    public void setShlcs(String[] shlcs) {
        this.shlcs = shlcs;
    }

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    /** 批量审核用**/



    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getShztmc() {
        return shztmc;
    }

    public void setShztmc(String shztmc) {
        this.shztmc = shztmc;
    }

    public String getZybj() {
        return zybj;
    }

    public void setZybj(String zybj) {
        this.zybj = zybj;
    }

    public String getZybjmc() {
        return zybjmc;
    }

    public void setZybjmc(String zybjmc) {
        this.zybjmc = zybjmc;
    }

    public String getSydm() {
        return sydm;
    }

    public void setSydm(String sydm) {
        this.sydm = sydm;
    }

    public String getSymc() {
        return symc;
    }

    public void setSymc(String symc) {
        this.symc = symc;
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

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getHjfs() {
        return hjfs;
    }

    public void setHjfs(String hjfs) {
        this.hjfs = hjfs;
    }

    public String getDkje() {
        return dkje;
    }

    public void setDkje(String dkje) {
        this.dkje = dkje;
    }

    public String getJtfyje() {
        return jtfyje;
    }

    public void setJtfyje(String jtfyje) {
        this.jtfyje = jtfyje;
    }

    public String getSqhjje() {
        return sqhjje;
    }

    public void setSqhjje(String sqhjje) {
        this.sqhjje = sqhjje;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getSqly() {
        return sqly;
    }

    public void setSqly(String sqly) {
        this.sqly = sqly;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getShlc() {
        return shlc;
    }

    public void setShlc(String shlc) {
        this.shlc = shlc;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }
}
