package com.zfsoft.xgxt.szdw.fdyzyhfz.zyhfztj;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/6/10.
 */
public class ZyfzTjModel extends ActionForm {
    private static final long serialVersionUID = 1L;
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();

    private String zgh;
    private String xm;
    private String xn;
    private String xq;
    private String[] bmdm;
    private String type;

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

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
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

    public String[] getBmdm() {
        return bmdm;
    }

    public void setBmdm(String[] bmdm) {
        this.bmdm = bmdm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
