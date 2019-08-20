package com.zfsoft.xgxt.xyfd.wfcyywh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * Created by llf on 2019/8/6.
 */
public class FdyyForm extends ActionForm {
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();
    private String type;

    private String yyid;
    private String yyh; //预约号
    private String xh; //预约人
    private String fdkc; //辅导课程
    private String fdsj; //辅导时间
    private String zt; //预约状态
    private String yytj; //预约途径
    private String yyr; //预约人类型
    private String fqr;//预约发起人
    private String fqsj;//预约发起时间

    private String qxyy; //取消预约原因

    private String yyzt;

    private String fdjl; //辅导记录

    private String pjid; //评价id
    private String pf; //评分
    private String sfjj; //是否解决
    private String xxpj; //详细评价


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYyid() {
        return yyid;
    }

    public void setYyid(String yyid) {
        this.yyid = yyid;
    }

    public String getYyh() {
        return yyh;
    }

    public void setYyh(String yyh) {
        this.yyh = yyh;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getFdkc() {
        return fdkc;
    }

    public void setFdkc(String fdkc) {
        this.fdkc = fdkc;
    }

    public String getFdsj() {
        return fdsj;
    }

    public void setFdsj(String fdsj) {
        this.fdsj = fdsj;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getYytj() {
        return yytj;
    }

    public void setYytj(String yytj) {
        this.yytj = yytj;
    }

    public String getYyr() {
        return yyr;
    }

    public void setYyr(String yyr) {
        this.yyr = yyr;
    }

    public String getFqr() {
        return fqr;
    }

    public void setFqr(String fqr) {
        this.fqr = fqr;
    }

    public String getFqsj() {
        return fqsj;
    }

    public void setFqsj(String fqsj) {
        this.fqsj = fqsj;
    }

    public String getQxyy() {
        return qxyy;
    }

    public void setQxyy(String qxyy) {
        this.qxyy = qxyy;
    }

    public String getYyzt() {
        return yyzt;
    }

    public void setYyzt(String yyzt) {
        this.yyzt = yyzt;
    }

    public String getFdjl() {
        return fdjl;
    }

    public void setFdjl(String fdjl) {
        this.fdjl = fdjl;
    }

    public String getPjid() {
        return pjid;
    }

    public void setPjid(String pjid) {
        this.pjid = pjid;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getSfjj() {
        return sfjj;
    }

    public void setSfjj(String sfjj) {
        this.sfjj = sfjj;
    }

    public String getXxpj() {
        return xxpj;
    }

    public void setXxpj(String xxpj) {
        this.xxpj = xxpj;
    }
}
