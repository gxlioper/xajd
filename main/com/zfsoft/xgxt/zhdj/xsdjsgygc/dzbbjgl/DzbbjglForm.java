package com.zfsoft.xgxt.zhdj.xsdjsgygc.dzbbjgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-13 17:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DzbbjglForm extends ActionForm {

    private String id ;
    private String dzbid ; //党支部id
    private String dzbmc ; //党支部名称
    private String bjdm  ; //班级代码
    private String bjmc ; //班级名称
    private String cjsj ; //创建时间







    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getDzbmc() {
        return dzbmc;
    }

    public void setDzbmc(String dzbmc) {
        this.dzbmc = dzbmc;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
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

    public String getDzbid() {
        return dzbid;
    }

    public void setDzbid(String dzbid) {
        this.dzbid = dzbid;
    }

    public String getBjdm() {
        return bjdm;
    }

    public void setBjdm(String bjdm) {
        this.bjdm = bjdm;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
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

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }
}
