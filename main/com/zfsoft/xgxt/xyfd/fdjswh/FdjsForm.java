package com.zfsoft.xgxt.xyfd.fdjswh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/6/24.
 */
public class FdjsForm extends ActionForm {

    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;
    private String djh; //登记号
    private String zgh;//职工号
    private String kcmc;//任课名称
    private String xkzy;//学科/专业
    private String lxdh;//联系电话
    private String dzyx;//E-mail
    private String fdkm;//辅导科目
    private String fds;//辅导室
    private String mon;
    private String tues;
    private String wed;
    private String thur;
    private String fri;
    private String sat;
    private String sun;

    public String getDjh() {
        return djh;
    }

    public void setDjh(String djh) {
        this.djh = djh;
    }

    public String getZgh() {
        return zgh;
    }

    public void setZgh(String zgh) {
        this.zgh = zgh;
    }

    public String getKcmc() {
        return kcmc;
    }

    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getXkzy() {
        return xkzy;
    }

    public void setXkzy(String xkzy) {
        this.xkzy = xkzy;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getDzyx() {
        return dzyx;
    }

    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    public String getFdkm() {
        return fdkm;
    }

    public void setFdkm(String fdkm) {
        this.fdkm = fdkm;
    }

    public String getFds() {
        return fds;
    }

    public void setFds(String fds) {
        this.fds = fds;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTues() {
        return tues;
    }

    public void setTues(String tues) {
        this.tues = tues;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThur() {
        return thur;
    }

    public void setThur(String thur) {
        this.thur = thur;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
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
}
