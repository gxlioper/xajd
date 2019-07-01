package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkcgbb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 安徽农业大学
 * 学生工作情况常规报表form.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:12
 */
public class XsgzqkCgbbForm extends ActionForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    private String id;
    private String xn;
    private String xq;
    private String xqmc;
    private String bszt;
    private String bsnr;
    private String bsr;
    private String bsrmc;
    private String bssj;
    private String bsdw;
    private String bsdwmc;
    private String fjid;

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

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String getBszt() {
        return bszt;
    }

    public void setBszt(String bszt) {
        this.bszt = bszt;
    }

    public String getBsnr() {
        return bsnr;
    }

    public void setBsnr(String bsnr) {
        this.bsnr = bsnr;
    }

    public String getBsr() {
        return bsr;
    }

    public void setBsr(String bsr) {
        this.bsr = bsr;
    }

    public String getBsrmc() {
        return bsrmc;
    }

    public void setBsrmc(String bsrmc) {
        this.bsrmc = bsrmc;
    }

    public String getBssj() {
        return bssj;
    }

    public void setBssj(String bssj) {
        this.bssj = bssj;
    }

    public String getBsdw() {
        return bsdw;
    }

    public void setBsdw(String bsdw) {
        this.bsdw = bsdw;
    }

    public String getBsdwmc() {
        return bsdwmc;
    }

    public void setBsdwmc(String bsdwmc) {
        this.bsdwmc = bsdwmc;
    }

    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid;
    }
}
