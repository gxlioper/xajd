package com.zfsoft.xgxt.xsxx.xsxxgl.kcxxcx;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class KcxxForm extends BaseForm{
    private SearchModel searchModel = new SearchModel(); //�߼���ѯ
    private Pages pages = new Pages(); // ��ҳ
    private ExportModel exportModel = new ExportModel(); //�Զ��嵼��
    private String type;

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
}
