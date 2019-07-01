package com.zfsoft.xgxt.szdw.fdyzyhfz.ktxmyjqk;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class KtxmyjqkForm extends BaseForm{
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private String type;
    private String id    ;
    private String mc    ;
    private String kssj  ;
    private String jssj  ;
    private String xmly  ;
    private String xmjf  ;
    private String sqsj  ;
    private String cdjs  ;
    private String cdjsmc;
    private String sfjx  ;
    private String sfjxmc;
    private String jxsj  ;
    private String jxjg  ;
    private String zgh   ;
    private String xmbh;

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getCdjsmc() {
        return cdjsmc;
    }

    public void setCdjsmc(String cdjsmc) {
        this.cdjsmc = cdjsmc;
    }

    public String getSfjxmc() {
        return sfjxmc;
    }

    public void setSfjxmc(String sfjxmc) {
        this.sfjxmc = sfjxmc;
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

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getXmly() {
        return xmly;
    }

    public void setXmly(String xmly) {
        this.xmly = xmly;
    }

    public String getXmjf() {
        return xmjf;
    }

    public void setXmjf(String xmjf) {
        this.xmjf = xmjf;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getCdjs() {
        return cdjs;
    }

    public void setCdjs(String cdjs) {
        this.cdjs = cdjs;
    }

    public String getSfjx() {
        return sfjx;
    }

    public void setSfjx(String sfjx) {
        this.sfjx = sfjx;
    }

    public String getJxsj() {
        return jxsj;
    }

    public void setJxsj(String jxsj) {
        this.jxsj = jxsj;
    }

    public String getJxjg() {
        return jxjg;
    }

    public void setJxjg(String jxjg) {
        this.jxjg = jxjg;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }
}
