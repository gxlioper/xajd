package com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class YwxsypxForm extends BaseForm{
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private String type;
    private String jgid;
    private String zgh;
    private String pxmc;
    private String pxsj;
    private String zzbm;
    private String zzbmmc;
    private String xs;
    private String pxnr;
    private String pxxd;
    private String filepath;
    private String sqsj;
    private String sqid;
    private String splc;
    private String shzt;
    private String shid;
    private String shyj;
    private String shjg;
    private String gwid;

    //批量审核用
    private String[] id;
    private String[] gwids;
    private String[] sqrs;
    private String[] splcids;

    public String[] getSplcids() {
        return splcids;
    }

    public void setSplcids(String[] splcids) {
        this.splcids = splcids;
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

    public String[] getSqrs() {
        return sqrs;
    }

    public void setSqrs(String[] sqrs) {
        this.sqrs = sqrs;
    }

    public String getGwid() {
        return gwid;
    }

    public void setGwid(String gwid) {
        this.gwid = gwid;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getShjg() {
        return shjg;
    }

    public void setShjg(String shjg) {
        this.shjg = shjg;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getZzbmmc() {
        return zzbmmc;
    }

    public void setZzbmmc(String zzbmmc) {
        this.zzbmmc = zzbmmc;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
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

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }

    public String getPxmc() {
        return pxmc;
    }

    public void setPxmc(String pxmc) {
        this.pxmc = pxmc;
    }

    public String getPxsj() {
        return pxsj;
    }

    public void setPxsj(String pxsj) {
        this.pxsj = pxsj;
    }

    public String getZzbm() {
        return zzbm;
    }

    public void setZzbm(String zzbm) {
        this.zzbm = zzbm;
    }

    public String getXs() {
        return xs;
    }

    public void setXs(String xs) {
        this.xs = xs;
    }

    public String getPxnr() {
        return pxnr;
    }

    public void setPxnr(String pxnr) {
        this.pxnr = pxnr;
    }

    public String getPxxd() {
        return pxxd;
    }

    public void setPxxd(String pxxd) {
        this.pxxd = pxxd;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }
}
