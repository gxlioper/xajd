package com.zfsoft.xgxt.zhdj.xszbhd.zbhdfb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述: 西北工业：智慧党校-学生支部活动管理
 * @作者： lgx [工号:1553]
 * @时间： 2018-5-30 上午09:19:08
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdfbForm extends ActionForm{
    private static final long serialVersionUID = 1L;
    private  String hdid;
    private  String xn;
    private  String xqdm;
    private  String hdzt;//主题
    private  String dzbid;//党支部id
    private  String kssj;//开始时间
    private  String jzsj;//截止时间
    private  String hdnr;//内容
    private  String fjid;
    private  String jcdwdm;//基层党委代码


    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();


    public String getJcdwdm() {
        return jcdwdm;
    }

    public void setJcdwdm(String jcdwdm) {
        this.jcdwdm = jcdwdm;
    }

    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getHdzt() {
        return hdzt;
    }

    public void setHdzt(String hdzt) {
        this.hdzt = hdzt;
    }

    public String getDzbid() {
        return dzbid;
    }

    public void setDzbid(String dzbid) {
        this.dzbid = dzbid;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getJzsj() {
        return jzsj;
    }

    public void setJzsj(String jzsj) {
        this.jzsj = jzsj;
    }

    public String getHdnr() {
        return hdnr;
    }

    public void setHdnr(String hdnr) {
        this.hdnr = hdnr;
    }

    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid;
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

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }
}
