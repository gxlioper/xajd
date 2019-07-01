package com.zfsoft.xgxt.szdw.jfxxwh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class JfxxForm extends BaseForm {
    private String jgid;
    private String xh ;
    private String jfsj ;
    private String dd ;
    private String jfxz ;
    private String rs ;
    private String yy ;
    private String nr ;
    private String csjy ;
    private String filepath ;
    private String fj;
    private String lrsj ;
    private String lrr;
    private String type;
    private String sfzdgz;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    //自定义导出
    private ExportModel exportModel = new ExportModel();
    private List<JfcyForm> jfcyxx = new AutoArrayList(JfcyForm.class);

    public String getSfzdgz() {
        return sfzdgz;
    }

    public void setSfzdgz(String sfzdgz) {
        this.sfzdgz = sfzdgz;
    }

    public List<JfcyForm> getJfcyxx() {
        return jfcyxx;
    }

    public void setJfcyxx(List<JfcyForm> jfcyxx) {
        this.jfcyxx = jfcyxx;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
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

    @Override
    public String getXh() {
        return xh;
    }

    @Override
    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getJfsj() {
        return jfsj;
    }

    public void setJfsj(String jfsj) {
        this.jfsj = jfsj;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getJfxz() {
        return jfxz;
    }

    public void setJfxz(String jfxz) {
        this.jfxz = jfxz;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getCsjy() {
        return csjy;
    }

    public void setCsjy(String csjy) {
        this.csjy = csjy;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }
}
