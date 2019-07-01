package com.zfsoft.xgxt.cxcy.sbwh.jzsb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:创新创业-上报维护-讲座上报
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-07 09:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JzsbForm extends ActionForm {

    private static final long serialVersionUID = 1L;
    private String id;
    private String xn;
    private String xq;
    private String jzmc;//讲座名称
    private String jzsj;//讲座时间
    private String jzdd;//讲座地点
    private String zjr;//主讲人
    private String sknr;//授课内容
    private String tjr;//听讲人
    private String tbr;//填报人
    private String tbsj;//填报时间
    private String xydm;
    private String fj;


    private String type;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getJzmc() {
        return jzmc;
    }

    public void setJzmc(String jzmc) {
        this.jzmc = jzmc;
    }

    public String getJzsj() {
        return jzsj;
    }

    public void setJzsj(String jzsj) {
        this.jzsj = jzsj;
    }

    public String getJzdd() {
        return jzdd;
    }

    public void setJzdd(String jzdd) {
        this.jzdd = jzdd;
    }

    public String getZjr() {
        return zjr;
    }

    public void setZjr(String ztr) {
        this.zjr = ztr;
    }

    public String getSknr() {
        return sknr;
    }

    public void setSknr(String sknr) {
        this.sknr = sknr;
    }

    public String getTjr() {
        return tjr;
    }

    public void setTjr(String tjr) {
        this.tjr = tjr;
    }

    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr;
    }

    public String getTbsj() {
        return tbsj;
    }

    public void setTbsj(String tbsj) {
        this.tbsj = tbsj;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

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
