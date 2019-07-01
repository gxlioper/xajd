package com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypxJg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class PxJgForm extends BaseForm {
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private String type;
    private String jgid;
    private String zgh;
    private String zzbmdm;//组织部门代码
    private String zzbmmc;//组织部门
    private String pxxs;//培训学时
    private String pxsj;//培训时间
    private String pxjj;//培训简介
    private String pxxd;
    private String filepath;
    private String sqsj;
    private String sqid;
    private String sjly;
    private String xmdm;//培训项目代码
    private String xmmc;//项目名称
    private String kqqk;//考勤情况

    public String getPxsj() {
        return pxsj;
    }

    public void setPxsj(String pxsj) {
        this.pxsj = pxsj;
    }

    public String getKqqk() {
        return kqqk;
    }

    public void setKqqk(String kqqk) {
        this.kqqk = kqqk;
    }

    public String getXmdm() {
        return xmdm;
    }
    public void setXmdm(String xmdm) {
        this.xmdm = xmdm;
    }
    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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



    public String getZzbmmc() {
        return zzbmmc;
    }

    public void setZzbmmc(String zzbmmc) {
        this.zzbmmc = zzbmmc;
    }

    public String getZzbmdm() {
        return zzbmdm;
    }

    public void setZzbmdm(String zzbmdm) {
        this.zzbmdm = zzbmdm;
    }

    public String getPxxs() {
        return pxxs;
    }

    public void setPxxs(String pxxs) {
        this.pxxs = pxxs;
    }

    public String getPxjj() {
        return pxjj;
    }

    public void setPxjj(String pxjj) {
        this.pxjj = pxjj;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
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

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }
}
