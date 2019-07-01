package com.zfsoft.xgxt.szdw.fdyzyhfz.gzf;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class GzfForm extends BaseForm {
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private String type;
    private String  id    ;
    private String  mc    ;
    private String  yjfx  ;
    private String  jb    ;
    private String  jbmc;
    private String  clsj  ;
    private String  jfze  ;
    private String  cdjs  ;
    private String  cdjsmc;
    private String  sfkhtg;
    private String  sfkhtgmc;
    private String  zgh   ;

    public String getJbmc() {
        return jbmc;
    }

    public void setJbmc(String jbmc) {
        this.jbmc = jbmc;
    }

    public String getCdjsmc() {
        return cdjsmc;
    }

    public void setCdjsmc(String cdjsmc) {
        this.cdjsmc = cdjsmc;
    }

    public String getSfkhtgmc() {
        return sfkhtgmc;
    }

    public void setSfkhtgmc(String sfkhtgmc) {
        this.sfkhtgmc = sfkhtgmc;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getYjfx() {
        return yjfx;
    }

    public void setYjfx(String yjfx) {
        this.yjfx = yjfx;
    }

    public String getJb() {
        return jb;
    }

    public void setJb(String jb) {
        this.jb = jb;
    }

    public String getClsj() {
        return clsj;
    }

    public void setClsj(String clsj) {
        this.clsj = clsj;
    }

    public String getJfze() {
        return jfze;
    }

    public void setJfze(String jfze) {
        this.jfze = jfze;
    }

    public String getCdjs() {
        return cdjs;
    }

    public void setCdjs(String cdjs) {
        this.cdjs = cdjs;
    }

    public String getSfkhtg() {
        return sfkhtg;
    }

    public void setSfkhtg(String sfkhtg) {
        this.sfkhtg = sfkhtg;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }
}
