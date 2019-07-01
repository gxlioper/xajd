package com.zfsoft.xgxt.zhdj.djgzjl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-11 17:37
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DjgzjlForm extends ActionForm{
    private String id;
    private String xn; //
    private String xqdm; //  学期代码
    private String xqmc; //  学期名称
    private String xydm; //  学院代码
    private String xymc; //  学院名称
    private String yhjs; //  党支部应换届数
    private String sjhjs; // 党支部实际换届数
    private String jdbksdyfzrs; // 季度本科生党员发展人数
    private String jdyjsdyfzrs; //  季度研究生党员发展人数
    private String sfasjndf; //  党支部是否按时交纳党费，1：是，0：否
    private String sbsj; //   上报时间

    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc;
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

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getYhjs() {
        return yhjs;
    }

    public void setYhjs(String yhjs) {
        this.yhjs = yhjs;
    }

    public String getSjhjs() {
        return sjhjs;
    }

    public void setSjhjs(String sjhjs) {
        this.sjhjs = sjhjs;
    }

    public String getJdbksdyfzrs() {
        return jdbksdyfzrs;
    }

    public void setJdbksdyfzrs(String jdbksdyfzrs) {
        this.jdbksdyfzrs = jdbksdyfzrs;
    }

    public String getJdyjsdyfzrs() {
        return jdyjsdyfzrs;
    }

    public void setJdyjsdyfzrs(String jdyjsdyfzrs) {
        this.jdyjsdyfzrs = jdyjsdyfzrs;
    }

    public String getSfasjndf() {
        return sfasjndf;
    }

    public void setSfasjndf(String sfasjndf) {
        this.sfasjndf = sfasjndf;
    }

    public String getSbsj() {
        return sbsj;
    }

    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
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
