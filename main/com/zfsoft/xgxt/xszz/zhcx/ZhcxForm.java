package com.zfsoft.xgxt.xszz.zhcx;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 综合查询所需的实体
 * Created by Administrator on 2019/7/3.
 */
public class ZhcxForm extends ActionForm {
    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

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
}
