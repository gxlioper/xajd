package com.zfsoft.xgxt.zhdj.dzzhd;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-党组织生活管理-党组织活动管理
 * @类功能描述: 党组织活动人员实体类
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/19 16:02
 */
public class DzzhdryForm extends ActionForm {

    private Pages pages = new Pages();
    private FormFile file;
    private SearchModel searchModel = new SearchModel();
    //主键
    private String id;

    //活动id
    private String hdid;

    //参与人学号
    private String xh;

    //活动心得
    private String hdxd;

    //附件信息
    private String fj;

    //提交时间
    private String tjsj;

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

    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getHdxd() {
        return hdxd;
    }

    public void setHdxd(String hdxd) {
        this.hdxd = hdxd;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }
}
