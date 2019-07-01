package com.zfsoft.xgxt.dycjgl.sjcx;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

public class SjcxForm extends ActionForm {
    private String guid;//
    private String xn;//学年
    private String nj;//年级
    private String xqdm;//学期代码
    private String xmdm;//项目代码
    private String xh;//学号
    private String fs;//分数
    private String zgh;//录入人
    private String lrsj;//录入时间
    private String rszb;//班级人数占比
    private String xq;//学期名称
    private String xydm;//学院代码
    private String xymc;//学院名称
    private String zydm;//专业代码
    private String zymc;//专业名称
    private String bjdm;//班级代码
    private String bjmc;//班级名称
    private String dyzcj;
    private String sfhg;
    private String dyxf;
    private FormFile drmb;//file
    private String filepath;//存放错误文件的路径
    private User user;//user,用于数据范围控制


    //补考情况
    private String bkqk;
    private String bksfhg;
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private FormFile importFile;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getXmdm() {
        return xmdm;
    }

    public void setXmdm(String xmdm) {
        this.xmdm = xmdm;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getFs() {
        return fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }

    public String getRszb() {
        return rszb;
    }

    public void setRszb(String rszb) {
        this.rszb = rszb;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getXymc() {
        return xymc;
    }

    public void setXymc(String xymc) {
        this.xymc = xymc;
    }

    public String getZydm() {
        return zydm;
    }

    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getBjdm() {
        return bjdm;
    }

    public void setBjdm(String bjdm) {
        this.bjdm = bjdm;
    }

    public String getBjmc() {
        return bjmc;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public String getDyzcj() {
        return dyzcj;
    }

    public void setDyzcj(String dyzcj) {
        this.dyzcj = dyzcj;
    }

    public String getSfhg() {
        return sfhg;
    }

    public void setSfhg(String sfhg) {
        this.sfhg = sfhg;
    }

    public String getDyxf() {
        return dyxf;
    }

    public void setDyxf(String dyxf) {
        this.dyxf = dyxf;
    }

    public FormFile getDrmb() {
        return drmb;
    }

    public void setDrmb(FormFile drmb) {
        this.drmb = drmb;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBkqk() {
        return bkqk;
    }

    public void setBkqk(String bkqk) {
        this.bkqk = bkqk;
    }

    public String getBksfhg() {
        return bksfhg;
    }

    public void setBksfhg(String bksfhg) {
        this.bksfhg = bksfhg;
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

    public FormFile getImportFile() {
        return importFile;
    }

    public void setImportFile(FormFile importFile) {
        this.importFile = importFile;
    }
}
