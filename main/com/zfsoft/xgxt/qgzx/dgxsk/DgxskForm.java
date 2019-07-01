package com.zfsoft.xgxt.qgzx.dgxsk;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-06 09:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DgxskForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String xh;



    private SearchModel searchModel = new SearchModel();
    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
