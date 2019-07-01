package com.zfsoft.xgxt.xlzx.xlwjga.zdgzxs;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-04-10 14:52
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZdgzxsForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String type;
    // 分页
    Pages pages = new Pages();
    // 高级查询
    private SearchModel searchModel = new SearchModel();
    //自定义导出
    private ExportModel exportModel = new ExportModel();
    private String id;
    private String xh;
    private String zxs;//咨询师
    private String zxcs;//目前咨询次数
    private String sfxsty;//是否征得学生同意 1 是 0否
    private String wtlb;//问题类别：A:A 类、B:B 类、C:C 类、D:D 类
    private String cljy;//处理建议
    private String wtms;//问题描述
    private String lrr;//录入人
    private String lrsj;//录入时间

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

    public String getZxs() {
        return zxs;
    }

    public void setZxs(String zxs) {
        this.zxs = zxs;
    }

    public String getZxcs() {
        return zxcs;
    }

    public void setZxcs(String zxcs) {
        this.zxcs = zxcs;
    }

    public String getSfxsty() {
        return sfxsty;
    }

    public void setSfxsty(String sfxsty) {
        this.sfxsty = sfxsty;
    }

    public String getWtlb() {
        return wtlb;
    }

    public void setWtlb(String wtlb) {
        this.wtlb = wtlb;
    }

    public String getCljy() {
        return cljy;
    }

    public void setCljy(String cljy) {
        this.cljy = cljy;
    }

    public String getWtms() {
        return wtms;
    }

    public void setWtms(String wtms) {
        this.wtms = wtms;
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
}
