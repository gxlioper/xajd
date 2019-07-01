package com.zfsoft.xgxt.xsxx.xsxxgl.xscjcx;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class XscjForm extends BaseForm{
    private SearchModel searchModel = new SearchModel(); //高级查询
    private Pages pages = new Pages(); // 分页
    private ExportModel exportModel = new ExportModel(); //自定义导出
    private String type;

    private String xn;
    private String xq;
    private String xh;
    private String kcsbm;
    private String kcmc;
    private String xf;
    private String cj;
    private String kcxz;
    private String bkcj;
    private String cxcj;
    private String jd;
    private String khfs;
    private String zpcj1;
    private String cxbj;
    private String nd;
    private String kclx;
    private String xkkh;
    private String bz;
    private String zxs;
    private String xdlx;
    private String ksrq;
    private String cxck;
    private String sfjg;
    private String sfyx;

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
    public void setPages(Pages pages) {
        this.pages = pages;
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

    @Override
    public String getXh() {
        return xh;
    }

    @Override
    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getKcsbm() {
        return kcsbm;
    }

    public void setKcsbm(String kcsbm) {
        this.kcsbm = kcsbm;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getXf() {
        return xf;
    }

    public void setXf(String xf) {
        this.xf = xf;
    }

    public String getCj() {
        return cj;
    }

    public void setCj(String cj) {
        this.cj = cj;
    }

    public String getKcxz() {
        return kcxz;
    }

    public void setKcxz(String kcxz) {
        this.kcxz = kcxz;
    }

    public String getBkcj() {
        return bkcj;
    }

    public void setBkcj(String bkcj) {
        this.bkcj = bkcj;
    }

    public String getCxcj() {
        return cxcj;
    }

    public void setCxcj(String cxcj) {
        this.cxcj = cxcj;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getKhfs() {
        return khfs;
    }

    public void setKhfs(String khfs) {
        this.khfs = khfs;
    }

    public String getZpcj1() {
        return zpcj1;
    }

    public void setZpcj1(String zpcj1) {
        this.zpcj1 = zpcj1;
    }

    public String getCxbj() {
        return cxbj;
    }

    public void setCxbj(String cxbj) {
        this.cxbj = cxbj;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getKclx() {
        return kclx;
    }

    public void setKclx(String kclx) {
        this.kclx = kclx;
    }

    public String getXkkh() {
        return xkkh;
    }

    public void setXkkh(String xkkh) {
        this.xkkh = xkkh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZxs() {
        return zxs;
    }

    public void setZxs(String zxs) {
        this.zxs = zxs;
    }

    public String getXdlx() {
        return xdlx;
    }

    public void setXdlx(String xdlx) {
        this.xdlx = xdlx;
    }

    public String getKsrq() {
        return ksrq;
    }

    public void setKsrq(String ksrq) {
        this.ksrq = ksrq;
    }

    public String getCxck() {
        return cxck;
    }

    public void setCxck(String cxck) {
        this.cxck = cxck;
    }

    public String getSfjg() {
        return sfjg;
    }

    public void setSfjg(String sfjg) {
        this.sfjg = sfjg;
    }

    public String getSfyx() {
        return sfyx;
    }

    public void setSfyx(String sfyx) {
        this.sfyx = sfyx;
    }
}
