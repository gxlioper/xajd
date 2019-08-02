package com.zfsoft.xgxt.zhdj.dzzhd;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-党组织生活管理-党组织活动管理
 * @类功能描述: 党团建设活动实体类
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/19 16:02
 */
public class DzzhdForm extends ActionForm {

    private Pages pages = new Pages();
    private FormFile file;
    private SearchModel searchModel = new SearchModel();
    //主键
    private String id;

    //活动名称
    private String hdmc;

    //开始时间
    private String kssj;

    //结束时间
    private String jssj;

    //活动类型代码(预留)
    private String hdlxdm;

    //活动内容
    private String hdnr;

    //附件
    private String fj;

    //创建人
    private String cjr;

    //创建人姓名
    private String cjrxm;

    //创建时间
    private String cjsj;

    //活动面向对象
    private String mxdx;

    private String joinStatus;//加入状态


    public String getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(String joinStatus) {
        this.joinStatus = joinStatus;
    }

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHdmc() {
        return hdmc;
    }

    public void setHdmc(String hdmc) {
        this.hdmc = hdmc;
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

    public String getHdlxdm() {
        return hdlxdm;
    }

    public void setHdlxdm(String hdlxdm) {
        this.hdlxdm = hdlxdm;
    }

    public String getHdnr() {
        return hdnr;
    }

    public void setHdnr(String hdnr) {
        this.hdnr = hdnr;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getCjrxm() {
        return cjrxm;
    }

    public void setCjrxm(String cjrxm) {
        this.cjrxm = cjrxm;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getMxdx() {
        return mxdx;
    }

    public void setMxdx(String mxdx) {
        this.mxdx = mxdx;
    }
}
